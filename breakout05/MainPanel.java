import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

/*
 * Created on 2007/05/05
 * 
 * �u���b�N�����̃Q�[����ʗp�p�l��
 */

public class MainPanel extends JPanel implements Runnable, MouseMotionListener {
    public static final int WIDTH = 360;
    public static final int HEIGHT = 480;

    // �u���b�N�̍s��
    private static final int NUM_BLOCK_ROW = 10;
    // �u���b�N�̗�
    private static final int NUM_BLOCK_COL = 7;
    // �u���b�N��
    private static final int NUM_BLOCK = NUM_BLOCK_ROW * NUM_BLOCK_COL;

    private Racket racket; // ���P�b�g
    private Ball ball; // �{�[��
    private Block[] block; // �u���b�N

    private Thread gameLoop; // �Q�[�����[�v

    public MainPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addMouseMotionListener(this);

        racket = new Racket();
        ball = new Ball();
        block = new Block[NUM_BLOCK];

        // �u���b�N����ׂ�
        for (int i = 0; i < NUM_BLOCK_ROW; i++) {
            for (int j = 0; j < NUM_BLOCK_COL; j++) {
                int x = j * Block.WIDTH + Block.WIDTH;
                int y = i * Block.HEIGHT + Block.HEIGHT;
                block[i * NUM_BLOCK_COL + j] = new Block(x, y);
            }
        }

        gameLoop = new Thread(this);
        gameLoop.start();
    }

    /**
     * �Q�[�����[�v
     * 
     */
    public void run() {
        while (true) {
            // �{�[���̈ړ�
            ball.move();

            // ���P�b�g�ƃ{�[���̏Փˏ���
            int collidePos = racket.collideWith(ball);
            if (collidePos != Racket.NO_COLLISION) {  // ���P�b�g�ɓ������Ă�����
                // �{�[���̓��������ʒu�ɉ����ă{�[���̑��x��ς���
                switch (collidePos) {
                    case Racket.LEFT:
                        // ���P�b�g�̍����ɓ��������Ƃ��͍��ɔ��˂���悤�ɂ�����
                        // �����{�[�����E�ɐi��ł����甽�]���č���
                        // ���ɐi��ł����炻�̂܂�
                        if (ball.getVX() > 0) ball.boundX();
                        ball.boundY();
                        break;
                    case Racket.RIGHT:
                        // ���P�b�g�̉E���ɓ��������Ƃ��͉E�ɔ��˂���悤�ɂ�����
                        // �����{�[�������ɐi��ł����甽�]���ĉE��
                        // �E�ɐi��ł����炻�̂܂�
                        if (ball.getVX() < 0) ball.boundX();
                        ball.boundY();
                        break;
                }
            }

            // �u���b�N�ƃ{�[���̏Փˏ���
            for (int i = 0; i < NUM_BLOCK; i++) {
                // ���łɏ����Ă���u���b�N�͖���
                if (block[i].isDeleted())
                    continue;
                // �{�[�������������ʒu���v�Z
                collidePos = block[i].collideWith(ball);
                if (collidePos != Block.NO_COLLISION) { // �u���b�N�ɓ������Ă�����
                    block[i].delete();
                    // �{�[���̓��������ʒu����{�[���̔��˕������v�Z
                    switch (collidePos) {
                        case Block.DOWN :
                        case Block.UP :
                            ball.boundY();
                            break;
                        case Block.LEFT :
                        case Block.RIGHT :
                            ball.boundX();
                            break;
                        case Block.UP_LEFT :
                        case Block.UP_RIGHT :
                        case Block.DOWN_LEFT :
                        case Block.DOWN_RIGHT :
                            ball.boundXY();
                            break;
                    }
                    break; // 1��ɉ󂹂�u���b�N��1��
                }
            }

            repaint();

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // �w�i
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        racket.draw(g); // ���P�b�g
        ball.draw(g); // �{�[��

        // �u���b�N
        for (int i = 0; i < NUM_BLOCK; i++) {
            if (!block[i].isDeleted()) {
                block[i].draw(g);
            }
        }
    }

    public void mouseMoved(MouseEvent e) {
        int x = e.getX(); // �}�E�X��X���W
        racket.move(x); // ���P�b�g���ړ�
        repaint();
    }

    public void mouseDragged(MouseEvent e) {
    }
}
