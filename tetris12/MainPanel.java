import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/*
 * Created on 2006/07/16
 */

public class MainPanel extends JPanel implements KeyListener, Runnable {
    // �p�l���T�C�Y
    public static final int WIDTH = 192;
    public static final int HEIGHT = 416;

    // �X�R�A
    private static final int BLOCK_DOWN = 1; // ���L�[���������Ƃ�+1
    private static final int ONE_LINE = 100; // 1�s�������Ƃ�
    private static final int TWO_LINE = 400; // 2�s�������Ƃ�
    private static final int THREE_LINE = 1000; // 3�s�������Ƃ�
    private static final int TETRIS = 2000; // 4�s�������i�e�g���X�j�Ƃ�

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

    // �X�R�A�p�l���ւ̎Q��
    private ScorePanel scorePanel;
    // ���̃u���b�N�p�l���ւ̎Q��
    private NextBlockPanel nextBlockPanel;

    public MainPanel(ScorePanel scorePanel, NextBlockPanel nextBlockPanel) {
        // �p�l���̐����T�C�Y��ݒ�Apack()����Ƃ��ɕK�v
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // �p�l�����L�[���͂��󂯕t����悤�ɂ���
        setFocusable(true);

        this.scorePanel = scorePanel;
        this.nextBlockPanel = nextBlockPanel;

        // �u���b�N�̃C���[�W�����[�h
        loadImage("image/block.gif");

        rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        field = new Field();
        block = createBlock(field);
        nextBlock = createBlock(field);
        nextBlockPanel.set(nextBlock, blockImage);

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
                // ���̃u���b�N���Z�b�g
                block = nextBlock;
                // ����Ɏ��̃u���b�N���쐬���ăp�l���ɕ\��
                nextBlock = createBlock(field);
                nextBlockPanel.set(nextBlock, blockImage);
            }

            // �u���b�N����������s������
            // deleteLine�͏������s��
            int deleteLine = field.deleteLine();

            // �������s���ɉ����ăX�R�A���v���X����
            if (deleteLine == 1) {
                scorePanel.upScore(ONE_LINE);
            } else if (deleteLine == 2) {
                scorePanel.upScore(TWO_LINE);
            } else if (deleteLine == 3) {
                scorePanel.upScore(THREE_LINE);
            } else if (deleteLine == 4) {
                scorePanel.upScore(TETRIS);
            }

            // �Q�[���I�[�o�[��
            if (field.isStacked()) {
                System.out.println("Game Over");
                // �X�R�A�����Z�b�g
                scorePanel.setScore(0);
                // �t�B�[���h�����Z�b�g
                field = new Field();
                block = createBlock(field);
                nextBlock = createBlock(field);
                nextBlockPanel.set(nextBlock, blockImage);
            }

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
            scorePanel.upScore(BLOCK_DOWN);
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
