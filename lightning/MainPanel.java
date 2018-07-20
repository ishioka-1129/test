import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

/*
 * Created on 2006/08/17
 */

public class MainPanel extends JPanel implements Runnable, MouseListener {
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;

	// ���
	private Lightning lightning;

	// �J�E���^
	private int cnt = 0;

	// �Q�[�����[�v
	private Thread gameLoop;

	// �_�u���o�b�t�@�����O�idb�j�p
	private Graphics dbg;
	private Image dbImage = null;

	// �T�E���h
    private AudioClip thunderSound;

	public MainPanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		addMouseListener(this);

	    // �T�E���h�����[�h
	    thunderSound = Applet.newAudioClip(getClass().getResource("don09_a.wav"));

		// �Q�[�����[�v�J�n
		gameLoop = new Thread(this);
		gameLoop.start();
	}

	public void run() {
		while (true) {
			gameRender(); // �o�b�t�@�Ƀ����_�����O�i�_�u���o�b�t�@�����O�j
			paintScreen(); // �o�b�t�@����ʂɕ`��irepaint()�������ł���I�j

			cnt++;

			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * �����_�����O
	 */
	private void gameRender() {
		// ����̌Ăяo�����Ƀ_�u���o�b�t�@�����O�p�I�u�W�F�N�g���쐬
		if (dbImage == null) {
			// �o�b�t�@�C���[�W
			dbImage = createImage(WIDTH, HEIGHT);
			if (dbImage == null) {
				System.out.println("dbImage is null");
				return;
			} else {
				// �o�b�t�@�C���[�W�̕`��I�u�W�F�N�g
				dbg = dbImage.getGraphics();
			}
			return;
		}

		// �o�b�t�@���N���A����
		dbg.setColor(Color.BLACK);
		dbg.fillRect(0, 0, WIDTH, HEIGHT);

		// cnt��18���z�������Ȃ�`�悵�Ȃ�
		if (cnt > 18)
			return;

		// cnt�������̂Ƃ��݈̂�Ȃ�`��
		// cnt����̂Ƃ��͕`�悳��Ȃ��̂œ_�ł��Č�����
		if (lightning != null) {
			if (cnt % 2 == 0) {
				lightning.drawTo(dbg);
			}
		}
	}

	/**
	 * �o�b�t�@����ʂɕ`��
	 */
	private void paintScreen() {
		try {
			Graphics g = getGraphics(); // �O���t�B�b�N�I�u�W�F�N�g���擾
			if ((g != null) && (dbImage != null)) {
				g.drawImage(dbImage, 0, 0, null); // �o�b�t�@�C���[�W����ʂɕ`��
			}
			Toolkit.getDefaultToolkit().sync();
			if (g != null) {
				g.dispose(); // �O���t�B�b�N�I�u�W�F�N�g��j��
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		// �J�E���^���N���A
		cnt = 0;
		// �N���b�N�����ʒu����Ȃ̎n�_
		lightning = new Lightning(new Point(x, y), Color.BLUE);
		// �҂�����[��
		thunderSound.play();
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}
}
