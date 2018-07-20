import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DecimalFormat;

import javax.swing.JPanel;

/*
 * Created on 2006/07/16
 */

public class ScorePanel extends JPanel {
    // �p�l���T�C�Y
    public static final int WIDTH = 96;
    public static final int HEIGHT = 16;

    // �X�R�A
    private int score;

    public ScorePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // �X�R�A��`��
        g.setColor(Color.YELLOW);
        DecimalFormat formatter = new DecimalFormat("000000");
        // �t�H���g���쐬
        Font font = new Font("Ariel", Font.BOLD, 16);
        g.setFont(font);

        g.drawString(formatter.format(score), 16, 12);
    }

    /**
     * �X�R�A���Z�b�g����
     * 
     * @param score �X�R�A
     */
    public void setScore(int score) {
        this.score = score;

        repaint();
    }

    /**
     * �X�R�A���v���X����
     * 
     * @param d �v���X��
     */
    public void upScore(int d) {
        score += d;

        repaint();
    }
}
