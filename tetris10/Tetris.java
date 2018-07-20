import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

/*
 * Created on 2006/07/16
 */

public class Tetris extends JFrame {
	public Tetris() {
		// �^�C�g����ݒ�
		setTitle("");
		// �T�C�Y�ύX�s��
		setResizable(false);

		Container contentPane = getContentPane();

		// �X�R�A�\���p�l��
		ScorePanel scorePanel = new ScorePanel();
		contentPane.add(scorePanel, BorderLayout.NORTH);

		// ���C���p�l�����쐬���ăt���[���ɒǉ�
		// ���C���p�l������X�R�A�\���p�l���𑀍삷�邽��scorePanel��n���K�v����I
		MainPanel mainPanel = new MainPanel(scorePanel);
		contentPane.add(mainPanel, BorderLayout.CENTER);

		// �p�l���T�C�Y�ɍ��킹�ăt���[���T�C�Y�������ݒ�
		pack();
	}

	public static void main(String[] args) {
		Tetris frame = new Tetris();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
