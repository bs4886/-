package ����2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ������2���� extends JFrame  {
 
  ����2���� a = new ����2����("������ ����.jpg");
 ����2���� b = new ����2����("Ʋ���׸�ã��(����Ȧ) (2).jpg");

//                       1��,2��,3��
 static int[] imageX = {70,353,120};
 static int[] imageY = {240,240,400};
 static final int range = 25; //�����⺻ ������ 
 
 
 JPanel p1 =new JPanel();
 
 public ������2����(String string) {
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
  //����Ʈ X��ǥ�� �Է¹޾� pointX�� ����
  int pointX = p.x;

  int pointY = p.y;
  
  return (pointX >= imageX[index]-range) && (pointX <= imageX[index]+range) 
    && (pointY >= imageY[index]-range) && (pointY <= imageY[index]+range);
 }
 
 
 class CustomMouseAdapter extends MouseAdapter{
  public void mouseReleased(MouseEvent e) {

   Point p = e.getPoint();
   if (isRangeof(0,p)) {
    
    ����2����.f1 = true; 
    a.repaint();
    b.repaint(); 

   } else if (isRangeof(1,p)) {
 
    ����2����.f2 = true;
    a.repaint();
    b.repaint();

   } else if (isRangeof(2,p)) {
 
    ����2����.f3 = true;
    a.repaint();
    b.repaint();

   }  else if (p.x >= 0 && p.x <= 50 && p.y >= 0 && p.y <= 50) {
    //�޼��� ���!!
    JOptionPane.showMessageDialog(null, "�� �������");
    a.repaint();
    b.repaint();
    
   } 
 if (����2����.f1 == true && ����2����.f2 == true && ����2����.f3 == true) {
    JOptionPane.showMessageDialog(null, "�� �¾Ҿ�� ~ ����");
    //�� �Ϸ��ϸ� �ٽ� ������ ���� ���׶�� ����, ���� ���� �Ѿ����� ����Ҽ�������? �̰� ���� �𸣰ڈ�����
    ����2����.f1 = false;
    ����2����.f2 = false;
    ����2����.f3 = false;

    a.repaint();
    b.repaint();
   }
  }
 }

 public static void main(String[] args) {
  new ������2����("Ʋ���׸�ã�� -COSMOS");
 }
}
