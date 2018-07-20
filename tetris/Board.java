import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

/*
 * Created on 2005/08/17
 *
 */

/**
 * @author mori
 *
 */
public class Board {
    // �t�B�[���h�̃T�C�Y
    public static final int MAX_X = 12;
    public static final int MAX_Y = 26;

    // �}�X�̃T�C�Y
    public static final int TILE_SIZE = 16;
    
    // �{�[�h
    private int[][] board;
    // �{�[�h�̃C���[�W
    private int[][] boardImage;

    public Board() {
        board = new int[MAX_Y][MAX_X];
        boardImage = new int[MAX_Y][MAX_X];
        init();
    }

    /**
     * �t�B�[���h������������
     */
    public void init() {
        for (int y=0; y<MAX_Y; y++) {
            for (int x=0; x<MAX_X; x++) {
                // �ǂ�����
                if (x == 0 || x == MAX_X - 1) {
                    board[y][x] = 1;
                    boardImage[y][x] = Block.WALL;
                } else if (y == MAX_Y - 1) {
                    board[y][x] = 1;
                    boardImage[y][x] = Block.WALL;
                } else {
                    board[y][x] = 0;
                }
            }
        }
    }
    
    /**
     * �{�[�h�i�Œ�u���b�N���܂ށj�̕`��
     * @param g �`��I�u�W�F�N�g
     */
    public void draw(Graphics g, Image blockImage) {
        for (int y=0; y<MAX_Y; y++) {
            for (int x=0; x<MAX_X; x++) {
                if (board[y][x] == 1) {
                    g.drawImage(blockImage, x * TILE_SIZE, y * TILE_SIZE, x*TILE_SIZE+TILE_SIZE, y*TILE_SIZE+TILE_SIZE,
                            boardImage[y][x] * TILE_SIZE, 0, boardImage[y][x] * TILE_SIZE + TILE_SIZE, TILE_SIZE, null);
                }
            }
        }
    }

    /**
     * �u���b�N���ړ��ł��邩���ׂ�
     * @param newPos �u���b�N�̈ړ�����W
     * @param block �u���b�N
     * @return �ړ��ł�����true
     */
    public boolean isMovable(Point newPos, int[][] block) {
        for (int y=0; y<Block.MAX_Y; y++) {
            for (int x=0; x<Block.MAX_X; x++) {
                if (block[y][x] == 1) {  // 4�~4���Ńu���b�N�̂���}�X�̂ݒ��ׂ�
                    if (newPos.y + y < 0) {  // ���̃}�X����ʂ̏�[�O�̂Ƃ�
                        // �u���b�N�̂���}�X���ǂ̂���0��ڈȉ��܂���
                        // MAX_X-1��ڈȏ�Ɉړ����悤�Ƃ��Ă�ꍇ�͈ړ��ł��Ȃ�
                        if (newPos.x + x <= 0 || newPos.x + x >= MAX_X - 1) {
                            return false;
                        }
                    } else if (board[newPos.y+y][newPos.x+x] == 1) {  // ���̃}�X����ʓ��̂Ƃ�
                        // �ړ���ɂ��łɃu���b�N������ꍇ�͈ړ��ł��Ȃ�
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * �����������u���b�N���{�[�h�ɌŒ肷��
     * @param pos �u���b�N�̈ʒu
     * @param block �u���b�N
     * @param color �u���b�N�̐F
     */
    public void fixBlock(Point pos, int[][] block, int imageNo) {
        for (int y=0; y<Block.MAX_Y; y++) {
            for (int x=0; x<Block.MAX_X; x++) {
                if (block[y][x] == 1) {
                    if (pos.y + y < 0) continue;
                    board[pos.y+y][pos.x+x] = 1;
                    boardImage[pos.y+y][pos.x+x] = imageNo;
                }
            }
        }
    }
    
    /**
     * ��������s���폜
     */
    public void deleteLine() {
        for (int y=0; y<MAX_Y-1; y++) {
            int count = 0;
            for (int x=1; x<MAX_X-1; x++) {
                // �u���b�N�������𐔂���
                if (board[y][x] == 1) count++;
            }
            // ��������s����������
            if (count == Board.MAX_X - 2) {
                // ���̍s������
                for (int x=1; x<MAX_X-1; x++) {
                    board[y][x] = 0;
                }
                // �������̍s�𗎂Ƃ�
                for (int ty=y; ty>0; ty--) {
                    for (int tx=1; tx<MAX_X-1; tx++) {
                        board[ty][tx] = board[ty-1][tx];
                        boardImage[ty][tx] = boardImage[ty-1][tx];
                    }
                }
            }
        }
    }
    
    /**
     * �u���b�N���ςݏオ���Ă邩
     * @return �ŏ�s�܂Őςݏオ���Ă���true
     */
    public boolean isStacked() {
        for (int x=1; x<MAX_X-1; x++) {
            if (board[0][x] == 1) {
                return true;
            }
        }
        
        return false;
    }
}
