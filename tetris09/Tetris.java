import java.awt.Container;

import javax.swing.JFrame;

/*
 * Created on 2006/07/08
 */

public class Tetris extends JFrame {
    public Tetris() {
        // �^�C�g����ݒ�
        setTitle("�J���t���u���b�N");
        // �T�C�Y�ύX�s��
        setResizable(false);

        // ���C���p�l�����쐬���ăt���[���ɒǉ�
        MainPanel panel = new MainPanel();
        Container contentPane = getContentPane();
        contentPane.add(panel);

        // �p�l���T�C�Y�ɍ��킹�ăt���[���T�C�Y�������ݒ�
        pack();
    }

    public static void main(String[] args) {
        Tetris frame = new Tetris();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
