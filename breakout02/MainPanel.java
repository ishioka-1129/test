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

    private Racket racket; // ���P�b�g
    private Ball ball; // �{�[��

    private Thread gameLoop; // �Q�[�����[�v

    public MainPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addMouseMotionListener(this);

        racket = new Racket();
        ball = new Ball();

        gameLoop = new Thread(this);
        gameLoop.start();
    }

    /**
     * �Q�[�����[�v
     * 
     */
    public void run() {
        while (true) {
            ball.move(); // �{�[���̈ړ�
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
    }

    public void mouseMoved(MouseEvent e) {
        int x = e.getX(); // �}�E�X��X���W
        racket.move(x); // ���P�b�g���ړ�
        repaint();
    }

    public void mouseDragged(MouseEvent e) {
    }
}
