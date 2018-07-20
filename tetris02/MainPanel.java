import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/*
 * Created on 2006/03/19
 */

public class MainPanel extends JPanel {
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
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // �t�B�[���h��`��
        field.draw(g);
        // �u���b�N��`��
        block.draw(g);
    }
}
