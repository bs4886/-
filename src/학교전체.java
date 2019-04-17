import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class 학교전체 extends JFrame implements Runnable {

	BufferedImage img = null; // 원본 사진
	BufferedImage img2 = null; // 바뀐 사진

	int life = 3; // 생명(기본값 = 3) *후에 감소되도록 하면 문제 없음.
	int time = 90; // 시간 세팅(기본값 = 120)
	long time3; // 카운트 되는 시간
	int answer = 0; // 정답 카운트

	Thread th; // 스레드

	// 1번,2번,3번
	static int[] imageX = {80,295,346,580,795,846};
	static int[] imageY = { 70,260,140,70,260,140};
	static final int range = 25; // 범위기본 설정값

	static boolean f1 = false;
	static boolean f2 = false;
	static boolean f3 = false;

	// ------------------------------------------------------------------

	public 학교전체() {

		System.out.println("학교 전체 진입");
		
		try {
			img = ImageIO.read(new File("학교전체 원본.jpg"));
			img2 = ImageIO.read(new File("틀린그림찾기(학교전체)(2).jpg"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
		th = new Thread(this); // 스레드 생성
		th.start(); // 스레드 시작
		
		System.out.println("쓰레드 밑 부분");

		setTitle("틀린그림찾기2");
		setSize(1000, 650);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		CustomMouseAdapter cma = new CustomMouseAdapter(); // 마우스 리스너 생성
		this.addMouseListener(cma); // JFrame을 상속받은 만오관() 프레임 자제에 마우스 리스너 추가

		setVisible(true);

		count_time(); // 시간초 세는 기능
	}

	public void draw_text(Graphics g) {
		g.setFont(new Font("Defualt", Font.BOLD, 20));
		// 폰트 설정 = 기본폰트, 굵게, 사이즈 20
		g.setColor(Color.BLACK);
		// 폰트 색상 = 검정

		g.drawString("Life : " + life, 140, img.getHeight(null) + 75);
		// 목숨 표시
		g.drawString("Time : " + (time - time3), 460, img.getHeight(null) + 75);
		// 시간 표시
	}

	public void draw_rect(Graphics g) { // 배경용 *후에 fillRect들을 이미지로 바꾸면 문제
										// 없음.
		g.setColor(Color.RED); // 목숨
		g.fillRect(0, img.getHeight(null), 340, 150);

		g.setColor(Color.GREEN); // 시간
		g.fillRect(340, img.getHeight(null), 330, 150);

		g.setColor(Color.BLUE); // 추후 로고
		g.fillRect(670, img.getHeight(null), 330, 150);

	}

	public void count_time() { // 타이머 기능

		long time = System.currentTimeMillis();

		while (true) { // 반복문 시작 시각 - 클래스 시작시 저장된 시간 => 시간초 세기
			long time2 = System.currentTimeMillis();
			time3 = (time2 - time) / 1000; // 1초에 1씩 올라감

			if (time3 == this.time) { // 15초에 끝
				break;
			}
			;
		}
	}

	public void draw_image(Graphics g) {
		g.drawImage(img, 0, 0, null);
		g.drawImage(img2, img.getWidth(null), 0, null);

		g.setColor(Color.RED); // 동그라미 부분을 위해 색 변경
		if (f1 == true) {
	         g.drawOval(80, 70, 50, 50);
	         g.drawOval(580, 70, 50, 50);
	         answer++; // 맞을 때 마다 정답 인수 증가
	      }
	      if (f2 == true) {
	         g.drawOval(295, 260, 50, 50);
	         g.drawOval(795, 260, 50, 50);
	         answer++;
	      }
	      if (f3 == true) {
	         g.drawOval(346, 140, 50, 50);
	         g.drawOval(846, 140, 50, 50);
	         answer++;
	      }

	}

	public void update(Graphics g) { // thread의 repaint에 호출 당함.
		
		System.out.println("update");
		
		draw_image(g); // 틀린 그림 부분
		draw_rect(g); // 타이머 배경 표시 -> 추후 이미지로 변경
		draw_text(g); // 목숨, 시간 표시

	}

	public void paint(Graphics g) { // update()부분 그리기

		System.out.println("paint");
		
		update(g);

	}

	@Override
	public void run() {

		System.out.println("run");
		
		
		try {// 예외 옵션 설정으로 오류 방지
			while (true) { // while문으로 무한 루프
				repaint(); // paint() -> update()를 호출
				th.sleep(60); // 60milli sec 로 스레드 돌리기
			}
		} catch (Exception e) {
		}
	}

	public boolean isRangeof(int index, Point p) { // 범위 만큼의 여유분

		int pointX = p.x; // x 좌표
		int pointY = p.y; // y 좌표

		return (pointX >= imageX[index] - range) && (pointX <= imageX[index] + range)
				&& (pointY >= imageY[index] - range) && (pointY <= imageY[index] + range);
	}

	class CustomMouseAdapter extends MouseAdapter {
		public void mouseReleased(MouseEvent e) {
			
			Point p = e.getPoint();
			
			if (isRangeof(0, p)) {        // imageX[],imageY[] 0번째 판정
				f1 = true;

			} else if (isRangeof(1, p)) { // imageX[],imageY[] 1번 판정
				f2 = true;

			} else if (isRangeof(2, p)) { // imageX2[],imageY[] 번 판정
				f3 = true;

			} else { // 지정 포인트 (+ 허용범위) 외의 판정
				life--;
				if (life == 0) {
					JOptionPane.showMessageDialog(null, "기회가 다 떨어졌어요~ ㅠㅠ");
					th.stop();
				}
			}
			if (f1 == true && f2 == true && f3 == true) {
				JOptionPane.showMessageDialog(null, "다 맞았어요 ~ ㅊㅊ");
				th.stop();
			}
		}
	}
	/*public static void main(String[] args) {
		new 학교전체();
	}*/
}