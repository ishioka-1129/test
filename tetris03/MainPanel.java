import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

/*
 * Created on 2006/04/22
 */

public class MainPanel extends JPanel implements KeyListener {
    // �p�l���T�C�Y
    public static final int WIDTH = 192;
    public static final int HEIGHT = 416;

    // �t�B�[���h
    private Field field;
    // �u���b�N
    private Block block;

    public MainPanel() {
        // �p�l���̐����T�C�Y��ݒ�Apack()����Ƃ��ɕK�v
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // �p�l�����L�[���͂��󂯕t����悤�ɂ���
        setFocusable(true);

        field = new Field();
        block = new Block();

        addKeyListener(this);
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
        } else if (key == KeyEvent.VK_N) {  // �o�[�u���b�N��\��
            block.createBarBlock();
        }

        repaint();
    }

    public void keyReleased(KeyEvent e) {
    }
}
