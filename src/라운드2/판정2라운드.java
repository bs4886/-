package ����2;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ����2���� extends JPanel {
 
 private JLabel jlb1,jlb2;
 private ImageIcon img,img2;
 
 //Ʋ���׸�
 static boolean f1 = false;
 static boolean f2 = false;
 static boolean f3 = false;


 public ����2����(String imageFile) {
 
  img = new ImageIcon(imageFile);

  jlb1 = new JLabel(img);
  add(jlb1);
 }
 
 @Override
 public void paint(Graphics g) {
  super.paint(g);

  g.setColor(Color.RED);

  if (f1 == true) {
   g.drawOval(60, 240, 50, 50); //��
  } 
  if (f2 == true) {
   g.drawOval(350, 240, 50, 50); //���
  }
  if (f3 == true) {
   g.drawOval(111, 404, 50, 50); //�ܵ�-���
  }

 }
}

