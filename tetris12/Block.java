import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

/*
 * Created on 2006/07/08
 */

public class Block {
    // �u���b�N�̃T�C�Y
    public static final int ROW = 4;
    public static final int COL = 4;

    // 1�}�X�̃T�C�Y
    private static final int TILE_SIZE = Field.TILE_SIZE;

    // �ړ�����
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;

    // �u���b�N�̖��O
    public static final int BAR = 0;
    public static final int Z_SHAPE = 1;
    public static final int SQUARE = 2;
    public static final int L_SHAPE = 3;
    public static final int REVERSE_Z_SHAPE = 4;
    public static final int T_SHAPE = 5;
    public static final int REVERSE_L_SHAPE = 6;

    public static final int WALL = 7;

    // �u���b�N�̌`���i�[
    protected int[][] block = new int[ROW][COL];

    // �C���[�W�ԍ�
    protected int imageNo;

    // �ʒu�i�P�ʁF�}�X�j
    protected Point pos;

    // �t�B�[���h�ւ̎Q��
    protected Field field;

    public Block(Field field) {
        this.field = field;

        init();

        imageNo = 6;
        pos = new Point(4, -4);
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
    public void draw(Graphics g, Image blockImage) {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (block[i][j] == 1) {
                    // pos�̈ʒu����Ƃ���_�ɒ��ӁI
                	g.drawImage(blockImage, (pos.x+j) * TILE_SIZE, (pos.y+i) * TILE_SIZE,
                			(pos.x+j) * TILE_SIZE + TILE_SIZE, (pos.y+i) * TILE_SIZE + TILE_SIZE,
                			imageNo * TILE_SIZE, 0, imageNo * TILE_SIZE + TILE_SIZE, TILE_SIZE, null);
                }
            }
        }
    }

    /**
     * ���̃u���b�N�p�l�����Ƀu���b�N��`��
     * @param g �`��I�u�W�F�N�g
     * @param blockImage �u���b�N�C���[�W
     */
    public void drawInPanel(Graphics g, Image blockImage) {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (block[i][j] == 1) {
                    g.drawImage(blockImage, (j+1) * TILE_SIZE, (i+1) * TILE_SIZE,
                            (j+1)*TILE_SIZE+TILE_SIZE, (i+1)*TILE_SIZE+TILE_SIZE,
                            imageNo * TILE_SIZE, 0, imageNo * TILE_SIZE + TILE_SIZE, TILE_SIZE, null);
                }
            }
        }
    }
    
    /**
     * dir�̕����Ƀu���b�N���ړ�
     * 
     * @param dir ����
     * @return �t�B�[���h�ɌŒ肳�ꂽ��true��Ԃ�
     */
    public boolean move(int dir) {
        switch (dir) {
            case LEFT :
                Point newPos = new Point(pos.x - 1, pos.y);
                if (field.isMovable(newPos, block)) { // �Փ˂��Ȃ���Έʒu���X�V
                    pos = newPos;
                }
                break;
            case RIGHT :
                newPos = new Point(pos.x + 1, pos.y);
                if (field.isMovable(newPos, block)) {
                    pos = newPos;
                }
                break;
            case DOWN :
                newPos = new Point(pos.x, pos.y + 1);
                if (field.isMovable(newPos, block)) {
                    pos = newPos;
                } else { // �ړ��ł��Ȃ������̃u���b�N�ƂԂ��遁�Œ肷��
                    // �u���b�N���t�B�[���h�ɌŒ肷��
                    field.fixBlock(pos, block, imageNo);
                    // �Œ肳�ꂽ��true��Ԃ�
                    return true;
                }
                break;
        }

        return false;
    }

    /**
     * �u���b�N����]�����
     */
    public void turn() {
        int[][] turnedBlock = new int[ROW][COL];

        // ��]�����u���b�N
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                turnedBlock[j][ROW - 1 - i] = block[i][j];
            }
        }

        // ��]�\�����ׂ�
        if (field.isMovable(pos, turnedBlock)) {
            block = turnedBlock;
        }
    }
}
