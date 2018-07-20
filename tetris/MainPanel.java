import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Random;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/*
 * Created on 2005/08/17
 *
 */

/**
 * @author mori
 *  
 */
public class MainPanel extends JPanel implements Runnable, KeyListener {
    // �p�l���T�C�Y
    public static final int WIDTH = 192;
    public static final int HEIGHT = 416;

    // �{�[�h
    private Board board;

    // ���݂̃u���b�N
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

        board = new Board();
        block = createBlock(board);

        addKeyListener(this);

        try {
            // �T�E���h�����[�h
            WaveEngine.load("se/kachi42.wav");
        } catch (UnsupportedAudioFileException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (LineUnavailableException e1) {
            e1.printStackTrace();
        }
        
        try {
            // BGM�����[�h
            MidiEngine.load("bgm/tetrisb.mid");
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // BGM�X�^�[�g�I
        MidiEngine.play(0);

        // �Q�[�����[�v�J�n
        gameLoop = new Thread(this);
        gameLoop.start();
    }

    public void run() {
        long lastTime = 0;
        
        while (true) {
            // �u���b�N���ړ�����
            // �u���b�N���Œ肳�ꂽ��true���Ԃ����
            boolean isFixed = block.move(Block.DOWN);
            if (isFixed) { // �u���b�N���Œ肳�ꂽ��
                // ��������Ė炷
                WaveEngine.play(0);
                // ���̃u���b�N���쐬�i�����_���Ɂj
                nextBlock = createBlock(board);
                block = nextBlock;
            }

            // �u���b�N����������s������
            board.deleteLine();

            // �Q�[���I�[�o�[��
            if (board.isStacked()) {
                board = new Board();
                block = createBlock(board);
            }

            // WAVE�t�@�C���̃����_�����O
            WaveEngine.render();

            // �ĕ`��
            repaint();

            // �x�~
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // �{�[�h�i�Œ�u���b�N���܂ށj��`��
        board.draw(g, blockImage);

        // �����Ă���u���b�N��`��
        block.draw(g, blockImage);
    }

    /**
     * �����_���Ɏ��̃u���b�N���쐬
     * 
     * @param board �{�[�h�ւ̎Q��
     * @return �u���b�N
     */
    public Block createBlock(Board board) {
        int blockNo = rand.nextInt(7);
        switch (blockNo) {
            case Block.BAR :
                return new BarBlock(board);
            case Block.Z_SHAPE :
                return new ZShapeBlock(board);
            case Block.SQUARE :
                return new SquareBlock(board);
            case Block.L_SHAPE :
                return new LShapeBlock(board);
            case Block.REVERSE_Z_SHAPE :
                return new ReverseZShapeBlock(board);
            case Block.T_SHAPE :
                return new TShapeBlock(board);
            case Block.REVERSE_L_SHAPE :
                return new ReverseLShapeBlock(board);
        }

        return null;
    }

    /**
     * �u���b�N�̃C���[�W�����[�h
     * 
     * @param string
     */
    private void loadImage(String filename) {
        // �u���b�N�̃C���[�W��ǂݍ���
        // ImageIcon���g����MediaTracker���g��Ȃ��Ă���
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        blockImage = icon.getImage();
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
}