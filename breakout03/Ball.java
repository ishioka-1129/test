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
    public static final int SIZE = 8;

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
            boundX();
        }

        // ��̕ǂɂԂ������ꍇ�Ƀo�E���h
        if (y < 0) {
            boundY();
        }
    }
    
    /**
     * X�����̃o�E���h
     *
     */
    public void boundX() {
        vx = -vx;
    }
    
    /**
     * Y�����̃o�E���h
     *
     */
    public void boundY() {
        vy = -vy;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getSize() {
        return SIZE;
    }
}
