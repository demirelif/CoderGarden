package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

/**
 * @author omerozbekler
 * @version 13.05.2018
 * ResourcesPanel - This program
 */
public class ResourcesPanel extends MyJPanel {

   JButton turnBack;

   JButton V1;
   JButton V2;
   JButton V3;
   JButton V4;
   JButton V5;

   JButton W1;
   JButton W2;
   JButton W3;
   JButton W4;
   JButton W5;

   JButton E2;
   JButton E3;
   JButton E4;
   JButton E5;

   private ButtonListener buttonListener;

   public ResourcesPanel () {
      setLayout(null);

      home.setEnabled(false);
      home.setVisible(false);

      buttonListener = new ButtonListener() {
         @Override
         public void actionPerformed (ActionEvent e) {


            if ( e.getSource() == turnBack )
               MyJPanel.changePanel(ResourcesPanel.this, new MenuPanel());
            else {
               JButton clicked = (JButton) e.getSource();
               String url = getResourceUrl(clicked.getText());
               try {
                  openWebpage(new URI(url));
               } catch (URISyntaxException err) {
                  err.printStackTrace();
               }
            }
         }
      };


      initButtons();

   }

   public static boolean openWebpage(URI uri) {
      Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
      if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
         try {
            desktop.browse(uri);
            return true;
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      return false;
   }

   @Override
   protected void paintComponent (Graphics g) {
      super.paintComponent(g);
      g.drawImage(MyJFrame.resourcesImage, 0, 0, MyJPanel.imageObserver);
   }


   public String getResourceUrl (String str)  // fdt, dec, loop, method, array
   {
      if ( str == "V1" )
         return "https://www.youtube.com/watch?v=bqPIWlnjWbA";
      if ( str == "V2" )
         return "https://www.khanacademy.org/computing/computer-programming/programming/logic-if-statements/p/if-statements";
      if ( str == "V3" )
         return "https://www.khanacademy.org/computing/computer-programming/programming/looping/p/intro-to-while-loops";
      if ( str == "V4" )
         return "https://www.youtube.com/watch?v=-IJ5izjbWIA";
      if ( str == "V5" )
         return "https://www.khanacademy.org/computing/computer-programming/programming/arrays/p/intro-to-arrays";


      if ( str == "W1" )
         return "https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html";
      if ( str == "W2" )
         return "https://docs.oracle.com/javase/tutorial/java/nutsandbolts/if.html";
      if ( str == "W3" )
         return "https://docs.oracle.com/javase/tutorial/java/nutsandbolts/for.html";
      if ( str == "W4" )
         return "https://docs.oracle.com/javase/tutorial/java/javaOO/methods.html";
      if ( str == "W5" )
         return "https://docs.oracle.com/javase/tutorial/java/nutsandbolts/arrays.html";


      if ( str == "E2" )
         return "https://www.hackerrank.com/challenges/java-if-else/problem";
      if ( str == "E3" )
         return "https://www.hackerrank.com/challenges/java-loops-i/problem";
      if ( str == "E4" )
         return "https://www.hackerrank.com/challenges/java-method-overriding/problem";
      if ( str == "E5" )
         return "https://www.hackerrank.com/challenges/java-1d-array-introduction/problem";

      else
         return "ERROR!!";
   }


   public void initButtons () {

      turnBack = new MyJButton("ASFAS", (int) (MyJFrame.screen_Width * 0.11), (int) (MyJFrame.screen_Height * 0.24));
      turnBack.setSize((int) (MyJFrame.screen_Width * 0.07), MyJFrame.screen_Height * 1 / 9);
      turnBack.addActionListener(buttonListener);
      turnBack.setOpaque(false);
      turnBack.setContentAreaFilled(false);
      turnBack.setBorderPainted(false);
      turnBack.setForeground(new Color(0, 0, 0, 0));
      add(turnBack);

      V1 = new MyJButton("V1", (int) (MyJFrame.screen_Width * 0.53), (int) (MyJFrame.screen_Height * 0.22));
      V2 = new MyJButton("V2", (int) (MyJFrame.screen_Width * 0.53), (int) (MyJFrame.screen_Height * 0.35));
      V3 = new MyJButton("V3", (int) (MyJFrame.screen_Width * 0.53), (int) (MyJFrame.screen_Height * 0.48));
      V4 = new MyJButton("V4", (int) (MyJFrame.screen_Width * 0.53), (int) (MyJFrame.screen_Height * 0.62));
      V5 = new MyJButton("V5", (int) (MyJFrame.screen_Width * 0.53), (int) (MyJFrame.screen_Height * 0.76));


      V1.setSize((int) (MyJFrame.screen_Height * 0.11), (int) (MyJFrame.screen_Height * 0.11));
      V2.setSize((int) (MyJFrame.screen_Height * 0.11), (int) (MyJFrame.screen_Height * 0.11));
      V3.setSize((int) (MyJFrame.screen_Height * 0.11), (int) (MyJFrame.screen_Height * 0.11));
      V4.setSize((int) (MyJFrame.screen_Height * 0.11), (int) (MyJFrame.screen_Height * 0.11));
      V5.setSize((int) (MyJFrame.screen_Height * 0.11), (int) (MyJFrame.screen_Height * 0.11));

      V1.addActionListener(buttonListener);
      V2.addActionListener(buttonListener);
      V3.addActionListener(buttonListener);
      V4.addActionListener(buttonListener);
      V5.addActionListener(buttonListener);

      V1.setContentAreaFilled(false);
      V2.setContentAreaFilled(false);
      V3.setContentAreaFilled(false);
      V4.setContentAreaFilled(false);
      V5.setContentAreaFilled(false);





      add(V1);
      add(V2);
      add(V3);
      add(V4);
      add(V5);

      W1 = new MyJButton("W1", (int) (MyJFrame.screen_Width * 0.63), (int) (MyJFrame.screen_Height * 0.22));
      W2 = new MyJButton("W2", (int) (MyJFrame.screen_Width * 0.63), (int) (MyJFrame.screen_Height * 0.35));
      W3 = new MyJButton("W3", (int) (MyJFrame.screen_Width * 0.63), (int) (MyJFrame.screen_Height * 0.48));
      W4 = new MyJButton("W4", (int) (MyJFrame.screen_Width * 0.63), (int) (MyJFrame.screen_Height * 0.62));
      W5 = new MyJButton("W5", (int) (MyJFrame.screen_Width * 0.63), (int) (MyJFrame.screen_Height * 0.76));

      W1.setSize((int) (MyJFrame.screen_Height * 0.11), (int) (MyJFrame.screen_Height * 0.11));
      W2.setSize((int) (MyJFrame.screen_Height * 0.11), (int) (MyJFrame.screen_Height * 0.11));
      W3.setSize((int) (MyJFrame.screen_Height * 0.11), (int) (MyJFrame.screen_Height * 0.11));
      W4.setSize((int) (MyJFrame.screen_Height * 0.11), (int) (MyJFrame.screen_Height * 0.11));
      W5.setSize((int) (MyJFrame.screen_Height * 0.11), (int) (MyJFrame.screen_Height * 0.11));

      W1.addActionListener(buttonListener);
      W2.addActionListener(buttonListener);
      W3.addActionListener(buttonListener);
      W4.addActionListener(buttonListener);
      W5.addActionListener(buttonListener);

      W1.setContentAreaFilled(false);
      W2.setContentAreaFilled(false);
      W3.setContentAreaFilled(false);
      W4.setContentAreaFilled(false);
      W5.setContentAreaFilled(false);

      add(W1);
      add(W2);
      add(W3);
      add(W4);
      add(W5);

      E2 = new MyJButton("E2", (int) (MyJFrame.screen_Width * 0.72), (int) (MyJFrame.screen_Height * 0.35));
      E3 = new MyJButton("E3", (int) (MyJFrame.screen_Width * 0.72), (int) (MyJFrame.screen_Height * 0.48));
      E4 = new MyJButton("E4", (int) (MyJFrame.screen_Width * 0.72), (int) (MyJFrame.screen_Height * 0.62));
      E5 = new MyJButton("E5", (int) (MyJFrame.screen_Width * 0.72), (int) (MyJFrame.screen_Height * 0.76));

      E2.setSize((int) (MyJFrame.screen_Height * 0.11), (int) (MyJFrame.screen_Height * 0.11));
      E3.setSize((int) (MyJFrame.screen_Height * 0.11), (int) (MyJFrame.screen_Height * 0.11));
      E4.setSize((int) (MyJFrame.screen_Height * 0.11), (int) (MyJFrame.screen_Height * 0.11));
      E5.setSize((int) (MyJFrame.screen_Height * 0.11), (int) (MyJFrame.screen_Height * 0.11));

      E2.addActionListener(buttonListener);
      E3.addActionListener(buttonListener);
      E4.addActionListener(buttonListener);
      E5.addActionListener(buttonListener);

      E2.setContentAreaFilled(false);
      E3.setContentAreaFilled(false);
      E4.setContentAreaFilled(false);
      E5.setContentAreaFilled(false);

      add(E2);
      add(E3);
      add(E4);
      add(E5);


      V1.setForeground(new Color(0, 0, 0, 0));
      V2.setForeground(new Color(0, 0, 0, 0));
      V3.setForeground(new Color(0, 0, 0, 0));
      V4.setForeground(new Color(0, 0, 0, 0));
      V5.setForeground(new Color(0, 0, 0, 0));
      W1.setForeground(new Color(0, 0, 0, 0));
      W2.setForeground(new Color(0, 0, 0, 0));
      W3.setForeground(new Color(0, 0, 0, 0));
      W4.setForeground(new Color(0, 0, 0, 0));
      W5.setForeground(new Color(0, 0, 0, 0));
      E2.setForeground(new Color(0, 0, 0, 0));
      E3.setForeground(new Color(0, 0, 0, 0));
      E4.setForeground(new Color(0, 0, 0, 0));
      E5.setForeground(new Color(0, 0, 0, 0));
   }

}
