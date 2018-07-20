import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JPanel;

/*
 * Created on 2005/06/06
 *
 */

/**
 * @author mori
 *
 */
public class MainPanel extends JPanel implements Runnable, KeyListener {
    // �p�l���T�C�Y
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;

    // �}�b�v
    private Map map;

    // �v���C���[
    private Player player;

    // �A�N�V�����L�[
    private ActionKey goLeftKey;
    private ActionKey goRightKey;
    private ActionKey jumpKey;

    // �Q�[�����[�v�p�X���b�h
    private Thread gameLoop;

    public MainPanel() {
        // �p�l���̐����T�C�Y��ݒ�Apack()����Ƃ��ɕK�v
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // �p�l�����L�[���͂��󂯕t����悤�ɂ���
        setFocusable(true);

        // �A�N�V�����L�[���쐬
        goLeftKey = new ActionKey();
        goRightKey = new ActionKey();
        // �W�����v�����̓L�[�����������Ă�1�񂾂������W�����v���Ȃ��悤�ɂ���
        jumpKey = new ActionKey(ActionKey.DETECT_INITIAL_PRESS_ONLY);

        // �}�b�v���쐬
        map = new Map("map01.dat");

        // �v���C���[���쐬
        player = new Player(192, 32, "player.gif", map);

        // �L�[�C�x���g���X�i�[��o�^
        addKeyListener(this);

        // �Q�[�����[�v�J�n
        gameLoop = new Thread(this);
        gameLoop.start();
    }

    /**
     * �Q�[���I�[�o�[
     */
    public void gameOver() {
        // �}�b�v���쐬
        map = new Map("map01.dat");

        // �v���C���[���쐬
        player = new Player(192, 32, "player.gif", map);
    }

    /**
     * �Q�[�����[�v
     */
    public void run() {
        while (true) {
            if (goLeftKey.isPressed()) {
                // ���L�[��������Ă���΍������ɉ���
                player.accelerateLeft();
            } else if (goRightKey.isPressed()) {
                // �E�L�[��������Ă���ΉE�����ɉ���
                player.accelerateRight();
            } else {
                // ����������ĂȂ��Ƃ��͒�~
                player.stop();
            }

            if (jumpKey.isPressed()) {
                // �W�����v����
                player.jump();
            }

            // �v���C���[�̏�Ԃ��X�V
            player.update();

            // �}�b�v�ɂ���X�v���C�g���擾
            LinkedList<?> sprites = map.getSprites();
            Iterator<?> iterator = sprites.iterator();
            while (iterator.hasNext()) {
                Sprite sprite = (Sprite)iterator.next();

                // �X�v���C�g�̏�Ԃ��X�V����
                sprite.update();

                // �v���C���[�ƐڐG���Ă���
                if (player.isCollision(sprite)) {
                    if (sprite instanceof Kuribo) {  // �I�{�[
                        Kuribo kuribo = (Kuribo)sprite;
                        // �ォ�瓥�܂�Ă���
                        if ((int)player.getY() < (int)kuribo.getY()) {
                            // �I�{�[�͏�����
                            sprites.remove(kuribo);
                            // �T�E���h
                            kuribo.play();
                            // ���ނƃv���C���[�͍ăW�����v
                            player.setForceJump(true);
                            player.jump();
                            break;
                        } else {
                            // �Q�[���I�[�o�[
                            gameOver();
                        }
                    } else if (sprite instanceof Coin) {  // �R�C��
                            Coin coin = (Coin)sprite;
                            // �R�C���͏�����
                            sprites.remove(coin);
                            // �����`��
                            coin.play();
                            // sprites����폜�����̂�
                            // break���Ȃ���iterator�����������Ȃ�
                            break;
                    } else if (sprite instanceof Accelerator) {  // �����A�C�e��
                        // �A�C�e���͏�����
                        sprites.remove(sprite);
                        Accelerator accelerator = (Accelerator)sprite;
                        // �T�E���h
                        accelerator.play();
                        // �A�C�e�������̏�Ŏg��
                        accelerator.use(player);
                        break;
                    } else if (sprite instanceof JumperTwo) {  // ��i�W�����v�A�C�e��
                        // �A�C�e���͏�����
                        sprites.remove(sprite);
                        JumperTwo jumperTwo = (JumperTwo)sprite;
                        // �T�E���h
                        jumperTwo.play();
                        // �A�C�e�������̏�Ŏg��
                        jumperTwo.use(player);
                        break;
                    }
                }
            }

            // �ĕ`��
            repaint();

            // �x�~
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * �`�揈��
     *
     * @param �`��I�u�W�F�N�g
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // �w�i�����œh��Ԃ�
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        // X�����̃I�t�Z�b�g���v�Z
        int offsetX = MainPanel.WIDTH / 2 - (int)player.getX();
        // �}�b�v�̒[�ł̓X�N���[�����Ȃ��悤�ɂ���
        offsetX = Math.min(offsetX, 0);
        offsetX = Math.max(offsetX, MainPanel.WIDTH - map.getWidth());

        // Y�����̃I�t�Z�b�g���v�Z
        int offsetY = MainPanel.HEIGHT / 2 - (int)player.getY();
        // �}�b�v�̒[�ł̓X�N���[�����Ȃ��悤�ɂ���
        offsetY = Math.min(offsetY, 0);
        offsetY = Math.max(offsetY, MainPanel.HEIGHT - map.getHeight());

        // �}�b�v��`��
        map.draw(g, offsetX, offsetY);

        // �v���C���[��`��
        player.draw(g, offsetX, offsetY);

        // �X�v���C�g��`��
        // �}�b�v�ɂ���X�v���C�g���擾
        LinkedList<?> sprites = map.getSprites();
        Iterator<?> iterator = sprites.iterator();
        while (iterator.hasNext()) {
            Sprite sprite = (Sprite)iterator.next();
            sprite.draw(g, offsetX, offsetY);
        }
    }

    /**
     * �L�[�������ꂽ��L�[�̏�Ԃ��u�����ꂽ�v�ɕς���
     *
     * @param e �L�[�C�x���g
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            goLeftKey.press();
        }
        if (key == KeyEvent.VK_RIGHT) {
            goRightKey.press();
        }
        if (key == KeyEvent.VK_UP) {
            jumpKey.press();
        }
    }

    /**
     * �L�[�������ꂽ��L�[�̏�Ԃ��u�����ꂽ�v�ɕς���
     *
     * @param e �L�[�C�x���g
     */
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            goLeftKey.release();
        }
        if (key == KeyEvent.VK_RIGHT) {
            goRightKey.release();
        }
        if (key == KeyEvent.VK_UP) {
            jumpKey.release();
        }
    }

    public void keyTyped(KeyEvent e) {
    }
}
