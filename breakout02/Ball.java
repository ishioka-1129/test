import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/*
 * Created on 2007/05/05
 * 
 * �{�[���N���X
 */

public class Ball {
    // �T�C�Y
    private static final int SIZE = 8;

    // �ʒu�i�{�[�����͂ދ�`�̍�����j
    private int x, y;
    // ���x
    private int vx, vy;

    // ����������
    private Random rand;

    public Ball() {
        rand = new Random(System.currentTimeMillis());

        // �ʒu��������
        x = rand.nextInt(MainPanel.WIDTH - SIZE);
        y = 0;

        // ���x���������i�Ƃ肠�����Œ�j
        vx = 5;
        vy = 5;
    }

    /**
     * �{�[����`��
     * 
     * @param g
     */
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, SIZE, SIZE);
    }

    /**
     * �{�[���̈ړ�
     * 
     */
    public void move() {
        x += vx;
        y += vy;

        // ���E�̕ǂɂԂ������ꍇ�Ƀo�E���h
        if (x < 0 || x > MainPanel.WIDTH - SIZE) {
            vx = -vx;
        }

        // �㉺�̕ǂɂԂ������ꍇ�Ƀo�E���h
        if (y < 0 || y > MainPanel.HEIGHT - SIZE) {
            vy = -vy;
        }
    }
}
