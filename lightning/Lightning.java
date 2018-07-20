import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Enumeration;
import java.util.Random;
import java.util.Vector;

/*
 * Created on 2006/08/17
 */

public class Lightning {
	private int x, y;  // ��Ȃ̎n�_
	private Color color;  // ��Ȃ̐F
	private Vector<Point> points = new Vector<Point>();  // ��Ȃ̎���̐܂�Ȃ���_
	private Vector<Lightning> lights = new Vector<Lightning>();  // �܂�Ȃ���_�ŕ��򂵂����
	private Random rand = new Random(System.currentTimeMillis());

	private int move; // �傫���Ƃ����������₷��
	private int gap;  // �������قǐ܂�Ȃ��肪�����Ȃ�
	private int div;  // �������قǐ܂�Ȃ���_�ŕ��򂵂₷���Ȃ�

	public Lightning(Point begin, Color color) {
		this.x = begin.x;
		this.y = begin.y;
		this.color = color;

		// ���̒l���Ƃ����������ꂢ�Ȉ�ȂɂȂ�
		move = 30;
		gap = 20;
		div = 20;

		initPoints();
	}

	/**
	 * ��Ȃ�`��
	 * @param g �`��I�u�W�F�N�g
	 */
	public void drawTo(Graphics g) {
		drawPoints(g);  // ��Ȃ̎����`��
		// ���򂵂���Ȃ�`��
		Enumeration<Lightning> e = lights.elements();
		while (e.hasMoreElements()) {
			Lightning lightning = (Lightning)e.nextElement();
			lightning.drawTo(g);
		}
	}

	/**
	 * ��Ȃ̎����`��
	 * @param g �`��I�u�W�F�N�g
	 */
	public void drawPoints(Graphics g) {
		Enumeration<Point> e = points.elements();
		Point before = new Point(x, y);  // ���̈�Ȃ̎n�_
		g.setColor(color);
		while (e.hasMoreElements()) {
			Point p = (Point)e.nextElement();  // ���̓_
			drawLightLine(g, before.x, before.y, p.x, p.y);
			before = p;
		}
	}

	/**
	 * ��Ȃ̐܂�Ȃ���_��ݒ�
	 */
	private void initPoints() {
		Point before = new Point(x, y);
		while (before.y < MainPanel.HEIGHT) {  // ���ɏ�����܂�
			before = new Point(before.x + rand.nextInt() % move,
					before.y + gap);
			points.addElement(before);  // �܂�Ȃ���_��ǉ�
			if ((int)(rand.nextDouble() * div) == 0) {  // �ċA�I�ɐV������Ȃ�ڑ�����
				// ���݂�before�̈ʒu����V������Ȃ��J�n
				lights.addElement(new Lightning(before, color));
			}
		}
	}

	/**
	 * ��Ȃ̐܂�Ȃ���_���Ȃ�����`��
	 * @param g �`��I�u�W�F�N�g
	 * @param x1 �n�_X
	 * @param y1 �n�_Y
	 * @param x2 �I�_X
	 * @param y2 �I�_Y
	 */
	private void drawLightLine(Graphics g, int x1, int y1, int x2, int y2) {
		Color c = g.getColor();
		// �����ɂ��炵�Ȃ������4�{�`��
		g.drawLine(x1-1, y1, x2-1, y2);
		g.drawLine(x1, y1+1, x2, y2+1);

		// �������ŃA�N�Z���g������
		g.setColor(Color.WHITE);
		g.drawLine(x1, y1, x2, y2);

		g.setColor(c);  // ���̐F�ɖ߂��Ă���
	}
}
