package 라운드2;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class 판정2라운드 extends JPanel {
 
 private JLabel jlb1,jlb2;
 private ImageIcon img,img2;
 
 //틀린그림
 static boolean f1 = false;
 static boolean f2 = false;
 static boolean f3 = false;


 public 판정2라운드(String imageFile) {
 
  img = new ImageIcon(imageFile);

  jlb1 = new JLabel(img);
  add(jlb1);
 }
 
 @Override
 public void paint(Graphics g) {
  super.paint(g);

  g.setColor(Color.RED);

  if (f1 == true) {
   g.drawOval(60, 240, 50, 50); //등
  } 
  if (f2 == true) {
   g.drawOval(350, 240, 50, 50); //기둥
  }
  if (f3 == true) {
   g.drawOval(111, 404, 50, 50); //잔디-기둥
  }

 }
}

