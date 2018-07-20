import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/*
 * Created on 2006/04/23
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

    /**
     * �u���b�N���ړ��ł��邩���ׂ�
     * 
     * @param newPos �u���b�N�̈ړ�����W
     * @param block �u���b�N
     * @return �ړ��ł�����true
     */
    public boolean isMovable(Point newPos, int[][] block) {
        // block=1�̃}�X���ׂĂɂ��ďՓ˂��Ă��邩���ׂ�
        // �ǂꂩ1�}�X�ł��Փ˂��Ă���ړ��ł��Ȃ�
        for (int i = 0; i < Block.ROW; i++) {
            for (int j = 0; j < Block.COL; j++) {
                if (block[i][j] == 1) { // 4x4���Ńu���b�N�̂���}�X�̂ݒ��ׂ�
                    if (newPos.y + i < 0) { // ���̃}�X����ʂ̏�[�O�̂Ƃ�
                        // �u���b�N�̂���}�X���ǂ̂���0��ڈȉ��܂���
                        // COL-1��ڈȏ�Ɉړ����悤�Ƃ��Ă���ꍇ�͈ړ��ł��Ȃ�
                        if (newPos.x + j <= 0 || newPos.x + j >= COL - 1) {
                            return false;
                        }
                    } else if (field[newPos.y + i][newPos.x + j] == 1) { // �t�B�[���h����
                        // �ړ���ɂ��łɃu���b�N�i�Ǌ܂ށj������ꍇ�͈ړ��ł��Ȃ�
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * �����������u���b�N���{�[�h�ɌŒ肷��
     * 
     * @param pos �u���b�N�̈ʒu
     * @param block �u���b�N
     */
    public void fixBlock(Point pos, int[][] block) {
        for (int i = 0; i < Block.ROW; i++) {
            for (int j = 0; j < Block.COL; j++) {
                if (block[i][j] == 1) {
                    if (pos.y + i < 0) continue;
                    field[pos.y + i][pos.x + j] = 1;  // �t�B�[���h���u���b�N�Ŗ��߂�
                }
            }
        }
    }
}
