import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ������ extends JFrame implements Runnable {

	BufferedImage img = null; // ���� ����
	BufferedImage img2 = null; // �ٲ� ����

	int life = 3; // ����(�⺻�� = 3) *�Ŀ� ���ҵǵ��� �ϸ� ���� ����.
	int time = 90; // �ð� ����(�⺻�� = 120)
	long time3; // ī��Ʈ �Ǵ� �ð�
	int answer = 0; // ���� ī��Ʈ
	int round = 1;

	Thread th; // ������

	// 1��,2��,3��
	static int[] imageX = { 70, 353, 120,570,853,620 };
	static int[] imageY = { 240, 240, 400,240, 240, 400 };
	static final int range = 25; // �����⺻ ������

	static boolean f1 = false;
	static boolean f2 = false;
	static boolean f3 = false;

	// ------------------------------------------------------------------

	public ������() {
		th = new Thread(this); // ������ ����
		th.start(); // ������ ����

		setTitle("Ʋ���׸�ã��");
		setSize(1000, 650);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		CustomMouseAdapter cma = new CustomMouseAdapter(); // ���콺 ������ ����
		this.addMouseListener(cma); // JFrame�� ��ӹ��� ������() ������ ������ ���콺 ������ �߰�

		try {
			img = ImageIO.read(new File("������ ����.jpg"));
			img2 = ImageIO.read(new File("Ʋ���׸�ã��(����Ȧ) (2).jpg"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

		setVisible(true);

		count_time(); // �ð��� ���� ���
	}
	
	// ------------------------------------------------------------------
	
	public void round2(Graphics g) {

		try {
			img = ImageIO.read(new File("�б���ü ����.jpg"));
			img2 = ImageIO.read(new File("Ʋ���׸�ã��(�б���ü)(2).jpg"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

	}

	public void draw_text(Graphics g) {
		g.setFont(new Font("Defualt", Font.BOLD, 20));
		// ��Ʈ ���� = �⺻��Ʈ, ����, ������ 20
		g.setColor(Color.BLACK);
		// ��Ʈ ���� = ����

		g.drawString("Life : " + life, 140, img.getHeight(null) + 75);
		// ��� ǥ��
		g.drawString("Time : " + (time - time3), 460, img.getHeight(null) + 75);
		// �ð� ǥ��
		g.drawString("Round : " + round, 790, img.getHeight(null) + 75);
	}

	public void draw_rect(Graphics g) { // ���� *�Ŀ� fillRect���� �̹����� �ٲٸ� ����
										// ����.
		g.setColor(Color.RED); // ���
		g.fillRect(0, img.getHeight(null), 340, 150);

		g.setColor(Color.GREEN); // �ð�
		g.fillRect(340, img.getHeight(null), 330, 150);

		g.setColor(Color.BLUE); // ���� �ΰ�
		g.fillRect(670, img.getHeight(null), 330, 150);

	}

	public void draw_image(Graphics g) {

		g.drawImage(img, 0, 0, null);
		g.drawImage(img2, img.getWidth(null), 0, null);

		g.setColor(Color.RED); // ���׶�� �κ��� ���� �� ����
		if (f1 == true) {
			g.drawOval(60, 240, 50, 50);
			g.drawOval(560, 240, 50, 50);
			answer++; // ���� �� ���� ���� �μ� ����
		}
		if (f2 == true) {
			g.drawOval(350, 240, 50, 50);
			g.drawOval(850, 240, 50, 50);
			answer++;
		}
		if (f3 == true) {
			g.drawOval(111, 404, 50, 50);
			g.drawOval(611, 404, 50, 50);
			answer++;
		}

	}

	public void count_time() { // Ÿ�̸� ���

		long time = System.currentTimeMillis();

		while (true) { // �ݺ��� ���� �ð� - Ŭ���� ���۽� ����� �ð� => �ð��� ����
			long time2 = System.currentTimeMillis();
			time3 = (time2 - time) / 1000; // 1�ʿ� 1�� �ö�

			if (time3 == this.time) { // 15�ʿ� ��
				break;
			}
			;
		}
	}

	public void update(Graphics g) { // thread�� repaint�� ȣ�� ����.
		draw_image(g); // Ʋ�� �׸� �κ�
		draw_rect(g); // Ÿ�̸� ��� ǥ�� -> ���� �̹����� ����
		draw_text(g); // ���, �ð� ǥ��
	}

	public void paint(Graphics g) { // update()�κ� �׸���
		update(g);
	}

	@Override
	public void run() {
		try {// ���� �ɼ� �������� ���� ����
			while (true) { // while������ ���� ����
				repaint(); // paint() -> update()�� ȣ��
				th.sleep(60); // 60milli sec �� ������ ������
				if (round == 2) {
					round2(null);
					f1 = false;
					f2 = false;
					f3 = false;
				}
			}
		} catch (Exception e) {
		}
	}

	public boolean isRangeof(int index, Point p) { // ���� ��ŭ�� ������

		int pointX = p.x; // x ��ǥ
		int pointY = p.y; // y ��ǥ

		return (pointX >= imageX[index] - range) && (pointX <= imageX[index] + range)
				&& (pointY >= imageY[index] - range) && (pointY <= imageY[index] + range);
	}

	class CustomMouseAdapter extends MouseAdapter {
		public void mouseReleased(MouseEvent e) {

			Point p = e.getPoint();

			if (isRangeof(0, p)) { // imageX[],imageY[] 0��° ����
				f1 = true;

			} else if (isRangeof(1, p)) { // imageX[],imageY[] 1�� ����
				f2 = true;

			} else if (isRangeof(2, p)) { // imageX2[],imageY[] �� ����
				f3 = true;

			} else { // ���� ����Ʈ (+ ������) ���� ����
				life--;
				if (life == 0) {
					JOptionPane.showMessageDialog(null, "��ȸ�� �� ���������~ �Ф�");
					th.stop();
				}
			}
			if (f1 == true && f2 == true && f3 == true) {
				JOptionPane.showMessageDialog(null, "�� �¾Ҿ�� ~ ����");
				System.out.println("0");
				round++;
				// th.stop();
			}
		}
	}

	public static void main(String[] args) {
		new ������();
	}

}