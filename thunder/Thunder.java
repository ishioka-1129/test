import java.awt.Color;
import java.awt.Graphics;

/*
 * Created on 2006/02/04
 */

/**
 * @author mori
 */
public class Thunder {
    private static final double SPEED = 10;
    private static final int HEIGHT = 50;

    private double x;
    private double y;
    private double width;
    private boolean used;

    public Thunder() {
        this.x = y = -10;
        this.width = 0;
    }

    /**
     * �T���_�[�����
     * 
     * @param x �n�_��X���W
     * @param y �n�_��Y���W
     * @param width �T���_�[�̕�
     */
    public void lightning(int x, int y, double width) {
        this.x = x;
        this.y = y;
        this.width = width;
        used = true;
    }

    /**
     * �ړ�
     */
    public void move() {
        y += SPEED;

        // ��ʊO�ɏo����
        if (y > MainPanel.HEIGHT) {
            used = false;
        }
    }

    /**
     * �`��
     * 
     * @param g
     */
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect((int) x, (int) y, (int) width, HEIGHT);
    }

    /**
     * �T���_�[���g�p����
     * 
     * @return �\�����Ȃ�true��Ԃ�
     */
    public boolean isUsed() {
        return used;
    }
}