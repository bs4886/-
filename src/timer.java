import java.awt.*;
import javax.swing.*;

class gameframe extends JPanel implements Runnable {

	//JPanel P1 = new JPanel();
	
	int life = 3; // ����(�⺻�� = 3) *�Ŀ� ���ҵǵ��� �ϸ� ���� ����.
	int time = 120; // �ð� ����(�⺻�� = 120)
	long time3; // ī��Ʈ �Ǵ� �ð�

	Thread th; // ������

	// ------------------------------------------------------------------------------------------------------------

	public void count_time() { // Ÿ�̸� ���

		long time = System.currentTimeMillis();

		while (true) { // �ݺ��� ���� �ð� - Ŭ���� ���۽� ����� �ð� => �ð��� ����
			long time2 = System.currentTimeMillis();
			time3 = (time2 - time) / 1000; // 1�ʿ� 1�� �ö�

			if (time3 == this.time) { // 15�ʿ� ��
				break;
			};
		}
	}

	gameframe() {
		th = new Thread(this); // ������ ����
		th.start(); // ������ ����		
		
		/*
		setTitle("testing");
		setSize(900, 150);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		 */
		
		count_time(); // �ð��� ���� ���
	}

	public void draw_text(Graphics g) {
		g.setFont(new Font("Defualt", Font.BOLD, 20));
		// ��Ʈ ���� = �⺻��Ʈ, ����, ������ 20
		g.setColor(Color.BLACK);
		// ��Ʈ ���� = ����

		g.drawString("Life : " + life, 120, 90);
		// ��� ǥ��
		g.drawString("Time : " + (time - time3), 410, 90);
		// �ð� ǥ��
	}

	public void draw_rect(Graphics g) { // ����  *�Ŀ� fillRect���� �̹����� �ٲٸ� ���� ����.
		g.setColor(Color.RED); // ���
		g.fillRect(0, 0, 300, 150);

		g.setColor(Color.GREEN); // �ð�
		g.fillRect(300, 0, 300, 150);

		g.setColor(Color.BLUE); // ���� �ΰ�
		g.fillRect(600, 0, 300, 150);

	}

	public void update(Graphics g) { // thread�� repaint�� ȣ�� ����.
		draw_rect(g);
		draw_text(g); // ���, �ð� ǥ��
	}

	public void paint(Graphics g) { // update()�κ� �׸���
		update(g);
	}

	@Override
	public void run() {
		try {// ���� �ɼ� ����
			while (true) { // ���� ����
				repaint(); // update()�� ȣ��
				th.sleep(60); // 60milli sec �� ������ ������
			}
		} catch (Exception e) {
		}

	}

}

public class timer {

	public static void main(String[] args) {

		gameframe f = new gameframe();

	}

}
