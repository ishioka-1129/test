import java.awt.Color;
import java.awt.Graphics;

/*
 * Created on 2007/05/05
 * 
 * �{�[����ł��P�b�g�N���X
 */

public class Racket {
    // ���P�b�g�̃T�C�Y
    private static final int WIDTH = 80;
    private static final int HEIGHT = 5;

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
        g.fillRect(centerPos - WIDTH / 2, MainPanel.HEIGHT - HEIGHT, WIDTH, HEIGHT);
    }

    /**
     * ���P�b�g�̈ړ�
     * 
     * @param x �ړ�����W
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
}
