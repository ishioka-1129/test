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

public class MainPanel extends JPanel implements MouseMotionListener {
    public static final int WIDTH = 360;
    public static final int HEIGHT = 480;

    private Racket racket; // ���P�b�g

    public MainPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addMouseMotionListener(this);

        // ���P�b�g���쐬
        racket = new Racket();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // �w�i
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // ���P�b�g
        racket.draw(g);
    }

    public void mouseMoved(MouseEvent e) {
        int x = e.getX(); // �}�E�X��X���W
        racket.move(x); // ���P�b�g���ړ�
        repaint();
    }

    public void mouseDragged(MouseEvent e) {
    }
}
