import java.awt.Color;
import java.awt.Graphics;

/*
 * Created on 2006/03/19
 */

public class Field {
    // �t�B�[���h�̃T�C�Y�i�P�ʁF�}�X�j
    public static final int COL = 12;
    public static final int ROW = 26;

    // �}�X�̃T�C�Y
    public static final int TILE_SIZE = 16;

    // �t�B�[���h
    private int[][] field;

    public Field() {
        field = new int[ROW][COL];

        // �t�B�[���h��������
        init();
    }

    /**
     * �t�B�[���h������������
     */
    public void init() {
        for (int y = 0; y < ROW; y++) {
            for (int x = 0; x < COL; x++) {
                // �ǂ�����
                if (x == 0 || x == COL - 1) {
                    field[y][x] = 1;
                } else if (y == ROW - 1) {
                    field[y][x] = 1;
                } else {
                    field[y][x] = 0;
                }
            }
        }
    }

    /**
     * �t�B�[���h��`��
     */
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, MainPanel.WIDTH, MainPanel.HEIGHT);

        g.setColor(Color.LIGHT_GRAY);
        for (int y = 0; y < ROW; y++) {
            for (int x = 0; x < COL; x++) {
                if (field[y][x] == 1) {
                    g.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE,
                            TILE_SIZE);
                }
            }
        }
    }

}
