import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

/*
 * Created on 2006/04/23
 */

public class MainPanel extends JPanel implements KeyListener, Runnable {
    // �p�l���T�C�Y
    public static final int WIDTH = 192;
    public static final int HEIGHT = 416;

    // �t�B�[���h
    private Field field;
    // �u���b�N
    private Block block;
    // ���̃u���b�N
    private Block nextBlock;

    // �Q�[�����[�v�p�X���b�h
    private Thread gameLoop;

    public MainPanel() {
        // �p�l���̐����T�C�Y��ݒ�Apack()����Ƃ��ɕK�v
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // �p�l�����L�[���͂��󂯕t����悤�ɂ���
        setFocusable(true);

        field = new Field();
        block = new Block(field);

        addKeyListener(this);

        // �Q�[�����[�v�J�n
        gameLoop = new Thread(this);
        gameLoop.start();
    }

    /**
     * �Q�[�����[�v
     */
    public void run() {
        while (true) {
            // �u���b�N���������ֈړ�����
            boolean isFixed = block.move(Block.DOWN);
            if (isFixed) { // �u���b�N���Œ肳�ꂽ��
                // ���̃u���b�N�������_���ɍ쐬
                nextBlock = createBlock(field);
                block = nextBlock;
            }

            // �u���b�N����������s������
            field.deleteLine();

            repaint();

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // �t�B�[���h��`��
        field.draw(g);
        // �u���b�N��`��
        block.draw(g);
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) { // �u���b�N�����ֈړ�
            block.move(Block.LEFT);
        } else if (key == KeyEvent.VK_RIGHT) { // �u���b�N���E�ֈړ�
            block.move(Block.RIGHT);
        } else if (key == KeyEvent.VK_DOWN) { // �u���b�N�����ֈړ�
            block.move(Block.DOWN);
        } else if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_UP) { // �u���b�N����]
            block.turn();
        }

        repaint();
    }

    public void keyReleased(KeyEvent e) {
    }

    /**
     * �����_���Ɏ��̃u���b�N���쐬
     * 
     * @param field �t�B�[���h�ւ̎Q��
     * @return �����_���ɐ������ꂽ�u���b�N
     */
    private Block createBlock(Field field) {
        // ������p���ă����_���Ƀu���b�N�����
        // ���͎l�p���u���b�N�������Ȃ�
        return new Block(field);
    }
}
