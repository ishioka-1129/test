import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/*
 * Created on 2006/03/25
 */

public class Block {
    // �u���b�N�̃T�C�Y
    public static final int ROW = 4;
    public static final int COL = 4;

    // 1�}�X�̃T�C�Y
    private static final int TILE_SIZE = Field.TILE_SIZE;

    // �u���b�N�̌`���i�[
    private int[][] block = new int[ROW][COL];

    // �ʒu�i�P�ʁF�}�X�j
    private Point pos;

    public Block() {
        init();

        // �l�p���u���b�N���쐬
        // ��������
        // ��������
        // ��������
        // ��������
        block[1][1] = 1;
        block[1][2] = 1;
        block[2][1] = 1;
        block[2][2] = 1;

        pos = new Point(4, 4);
    }

    /**
     * �u���b�N�̏�����
     */
    public void init() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                block[i][j] = 0;
            }
        }
    }

    /**
     * �u���b�N�̕`��
     * 
     * @param g �`��I�u�W�F�N�g
     */
    public void draw(Graphics g) {
        g.setColor(Color.MAGENTA);
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (block[i][j] == 1) {
                    // pos�̈ʒu����Ƃ���_�ɒ��ӁI
                    g.fillRect((pos.x + j) * TILE_SIZE,
                            (pos.y + i) * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
            }
        }
    }
}
