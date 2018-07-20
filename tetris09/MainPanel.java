import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/*
 * Created on 2006/07/08
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

    // �u���b�N�̃C���[�W
    private Image blockImage;

    // �Q�[�����[�v�p�X���b�h
    private Thread gameLoop;

    private Random rand;

    public MainPanel() {
        // �p�l���̐����T�C�Y��ݒ�Apack()����Ƃ��ɕK�v
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // �p�l�����L�[���͂��󂯕t����悤�ɂ���
        setFocusable(true);

        // �u���b�N�̃C���[�W�����[�h
        loadImage("image/block.gif");

        rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        field = new Field();
        block = createBlock(field);

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
        field.draw(g, blockImage);
        // �u���b�N��`��
        block.draw(g, blockImage);
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
     * @param field
     *            �t�B�[���h�ւ̎Q��
     * @return �����_���ɐ������ꂽ�u���b�N
     */
    private Block createBlock(Field field) {
        int blockNo = rand.nextInt(7); // �u���b�N��0-6��7���
        switch (blockNo) {
            case Block.BAR :
                return new BarBlock(field);
            case Block.Z_SHAPE :
                return new ZShapeBlock(field);
            case Block.SQUARE :
                return new SquareBlock(field);
            case Block.L_SHAPE :
                return new LShapeBlock(field);
            case Block.REVERSE_Z_SHAPE :
                return new ReverseZShapeBlock(field);
            case Block.T_SHAPE :
                return new TShapeBlock(field);
            case Block.REVERSE_L_SHAPE :
                return new ReverseLShapeBlock(field);
        }

        return null;
    }

    /**
     * �u���b�N�̃C���[�W�����[�h
     * 
     * @param filename
     */
    private void loadImage(String filename) {
        // �u���b�N�̃C���[�W��ǂݍ���
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        blockImage = icon.getImage();
    }
}
