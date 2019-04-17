import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class differ extends JFrame {
	 
   
	BufferedImage img=null;
	BufferedImage img2=null;
	 
    public differ()  {
    	
          setTitle("틀린그림찾기");
          setLayout(new GridLayout(0,2,5,5));
          setSize(900,900);
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
          try {
              img = ImageIO.read(new File("B동 원본.jpg"));
              img2 = ImageIO.read(new File("틀린그림찾기(B동)(2).jpg"));
       } catch (IOException e) {
              System.out.println(e.getMessage());
              System.exit(0);
        }
          

          add(new MyPanel1());
          add(new MyPanel2());
          pack();

          setVisible(true);
    }

    class MyPanel1 extends JPanel {

          public void paint(Graphics g) {
                 g.drawImage(img, 0, 0, null);
          }
          public Dimension getPreferredSize() {
        	    if (img == null) {
        	           return new Dimension(500, 500);
        	    } else {
        	           return new Dimension(img.getWidth(null), img.getHeight(null));
        	    }
        	}

    }
    class MyPanel2 extends JPanel {

        public void paint(Graphics g) {
               g.drawImage(img2, 0, 0, null);
        }
        public Dimension getPreferredSize() {
      	    if (img2 == null) {
      	           return new Dimension(500, 500);
      	    } else {
      	           return new Dimension(img2.getWidth(null), img2.getHeight(null));
      	    }
      	}

  }

    public static void main(String[] args) {
          new differ();
    }
}