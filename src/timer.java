import java.awt.*;
import javax.swing.*;

class gameframe extends JPanel implements Runnable {

	//JPanel P1 = new JPanel();
	
	int life = 3; // 생명(기본값 = 3) *후에 감소되도록 하면 문제 없음.
	int time = 120; // 시간 세팅(기본값 = 120)
	long time3; // 카운트 되는 시간

	Thread th; // 스레드

	// ------------------------------------------------------------------------------------------------------------

	public void count_time() { // 타이머 기능

		long time = System.currentTimeMillis();

		while (true) { // 반복문 시작 시각 - 클래스 시작시 저장된 시간 => 시간초 세기
			long time2 = System.currentTimeMillis();
			time3 = (time2 - time) / 1000; // 1초에 1씩 올라감

			if (time3 == this.time) { // 15초에 끝
				break;
			};
		}
	}

	gameframe() {
		th = new Thread(this); // 스레드 생성
		th.start(); // 스레드 시작		
		
		/*
		setTitle("testing");
		setSize(900, 150);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		 */
		
		count_time(); // 시간초 세는 기능
	}

	public void draw_text(Graphics g) {
		g.setFont(new Font("Defualt", Font.BOLD, 20));
		// 폰트 설정 = 기본폰트, 굵게, 사이즈 20
		g.setColor(Color.BLACK);
		// 폰트 색상 = 검정

		g.drawString("Life : " + life, 120, 90);
		// 목숨 표시
		g.drawString("Time : " + (time - time3), 410, 90);
		// 시간 표시
	}

	public void draw_rect(Graphics g) { // 배경용  *후에 fillRect들을 이미지로 바꾸면 문제 없음.
		g.setColor(Color.RED); // 목숨
		g.fillRect(0, 0, 300, 150);

		g.setColor(Color.GREEN); // 시간
		g.fillRect(300, 0, 300, 150);

		g.setColor(Color.BLUE); // 추후 로고
		g.fillRect(600, 0, 300, 150);

	}

	public void update(Graphics g) { // thread의 repaint에 호출 당함.
		draw_rect(g);
		draw_text(g); // 목숨, 시간 표시
	}

	public void paint(Graphics g) { // update()부분 그리기
		update(g);
	}

	@Override
	public void run() {
		try {// 예외 옵션 설정
			while (true) { // 무한 루프
				repaint(); // update()를 호출
				th.sleep(60); // 60milli sec 로 스레드 돌리기
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
