package 라운드2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class 프레임2라운드 extends JFrame  {
 
  판정2라운드 a = new 판정2라운드("만오관 원본.jpg");
 판정2라운드 b = new 판정2라운드("틀린그림찾기(만오홀) (2).jpg");

//                       1번,2번,3번
 static int[] imageX = {70,353,120};
 static int[] imageY = {240,240,400};
 static final int range = 25; //범위기본 설정값 
 
 
 JPanel p1 =new JPanel();
 
 public 프레임2라운드(String string) {
  super(string);
  setSize(1040,700);  
  CustomMouseAdapter cma = new CustomMouseAdapter (); 
  
  a.addMouseListener(cma);
  
  b.addMouseListener(cma);
 
  add(a, BorderLayout.WEST);
  add(b, BorderLayout.EAST);
  

  setDefaultCloseOperation(EXIT_ON_CLOSE);

  

  
  
  
  setVisible(true);
 }
 
 
 public boolean isRangeof(int index, Point p){
  //포인트 X좌표를 입력받아 pointX로 저장
  int pointX = p.x;

  int pointY = p.y;
  
  return (pointX >= imageX[index]-range) && (pointX <= imageX[index]+range) 
    && (pointY >= imageY[index]-range) && (pointY <= imageY[index]+range);
 }
 
 
 class CustomMouseAdapter extends MouseAdapter{
  public void mouseReleased(MouseEvent e) {

   Point p = e.getPoint();
   if (isRangeof(0,p)) {
    
    판정2라운드.f1 = true; 
    a.repaint();
    b.repaint(); 

   } else if (isRangeof(1,p)) {
 
    판정2라운드.f2 = true;
    a.repaint();
    b.repaint();

   } else if (isRangeof(2,p)) {
 
    판정2라운드.f3 = true;
    a.repaint();
    b.repaint();

   }  else if (p.x >= 0 && p.x <= 50 && p.y >= 0 && p.y <= 50) {
    //메세지 출력!!
    JOptionPane.showMessageDialog(null, "오 비슷했음");
    a.repaint();
    b.repaint();
    
   } 
 if (판정2라운드.f1 == true && 판정2라운드.f2 == true && 판정2라운드.f3 == true) {
    JOptionPane.showMessageDialog(null, "다 맞았어요 ~ ㅊㅊ");
    //다 완료하면 다시 시작을 위해 동그라미 갱신, 다음 라운드 넘어갔을경우 대비할수있을듯? 이건 나도 모르겠닼ㅋㅋ
    판정2라운드.f1 = false;
    판정2라운드.f2 = false;
    판정2라운드.f3 = false;

    a.repaint();
    b.repaint();
   }
  }
 }

 public static void main(String[] args) {
  new 프레임2라운드("틀린그림찾기 -COSMOS");
 }
}
