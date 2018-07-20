import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/*
 * Created on 2007/05/05
 * 
 * �{�[����ł��P�b�g�N���X
 */

public class Racket {
    // ���P�b�g�̃T�C�Y
    public static final int WIDTH = 80;
    public static final int HEIGHT = 5;

    // �{�[���̓�����ʒu
    public static final int NO_COLLISION = 0;  // ���Փ�
    public static final int LEFT = 1;
    public static final int RIGHT = 2;

    // ���P�b�g�̒��S�ʒu
    private int centerPos;

    public Racket() {
        // ���P�b�g�̈ʒu����ʂ̐^�񒆂ŏ�����
        centerPos = MainPanel.WIDTH / 2;
    }

    /**
     * ���P�b�g�̕`��
     * 
     * @param g
     */
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(centerPos - WIDTH / 2, MainPanel.HEIGHT - HEIGHT, WIDTH,
                HEIGHT);
    }

    /**
     * ���P�b�g�̈ړ�
     * 
     * @param pos �ړ�����W
     */
    public void move(int pos) {
        centerPos = pos;

        // ���P�b�g����ʂ̒[�����яo�Ȃ��悤�ɂ���
        if (centerPos < WIDTH / 2) { // ���[
            centerPos = WIDTH / 2;
        } else if (centerPos > MainPanel.WIDTH - WIDTH / 2) { // �E�[
            centerPos = MainPanel.WIDTH - WIDTH / 2;
        }
    }

    /**
     * �{�[�������������ʒu��Ԃ�
     * 
     * @param ball �{�[��
     * @return �{�[���ɓ���������true
     */
    public int collideWith(Ball ball) {
        // ���P�b�g�̋�`
        Rectangle racketRectLeft = new Rectangle(
                centerPos - WIDTH / 2, MainPanel.HEIGHT - HEIGHT,
                WIDTH / 2, HEIGHT);
        Rectangle racketRectRight = new Rectangle(
                centerPos, MainPanel.HEIGHT - HEIGHT,
                WIDTH / 2, HEIGHT);
        // �{�[���̋�`
        Rectangle ballRect = new Rectangle(
                ball.getX(), ball.getY(),
                ball.getSize(), ball.getSize());

        // ���P�b�g�ƃ{�[���̋�`�̈悪�d�Ȃ����瓖�����Ă���
        if (racketRectLeft.intersects(ballRect)) {
            return LEFT;
        } else if (racketRectRight.intersects(ballRect)) {
            return RIGHT;
        }

        return NO_COLLISION;
    }
}
