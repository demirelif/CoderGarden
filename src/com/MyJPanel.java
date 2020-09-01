package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/**
 * @author omerozbekler
 * @version 10.04.2018
 * MyJPanel - This program
 */
public class MyJPanel extends JPanel{


   protected JButton home;
   private   ButtonListener buttonListener;

   protected static ImageObserver imageObserver;


   public MyJPanel () {

      buttonListener  = new ButtonListener() {
         @Override
         public void actionPerformed (ActionEvent e) {
            if ( e.getActionCommand() == "Main Menu" )
               MyJPanel.changePanel(MyJPanel.this, new MenuPanel());
         }
      };

      home = new MyJButton("Main Menu", MyJFrame.homeIcon, (MyJFrame.screen_Width / 7), 50);
      home.setSize(100,100);
      home.setFont(new Font("Serif", Font.BOLD, 12));
      home.setVerticalTextPosition(SwingConstants.BOTTOM);
      home.setHorizontalTextPosition(SwingConstants.CENTER);
      home.addActionListener(buttonListener);
      add(home);

      imageObserver = new ImageObserver() {
         @Override
         public boolean imageUpdate (Image img, int infoflags, int x, int y, int width, int height) {
            return false;
         }
      };
   }

   @Override
   protected void paintComponent (Graphics g) {
      super.paintComponent(g);
      g.drawImage(MyJFrame.backgroundImage, 0,0, null);
   }

   protected static void changePanel (JPanel currentPanel, JPanel newPanel) {
      JFrame tmp;
      tmp = (JFrame) SwingUtilities.getWindowAncestor( currentPanel );
      tmp.add( newPanel );
      tmp.remove( currentPanel );
      tmp.setVisible(true);
   }

   protected class ButtonListener implements ActionListener{
      public JPanel panel = MyJPanel.this;;

      @Override
      public void actionPerformed (ActionEvent e) {
      }
   }

   protected static BufferedImage resize ( BufferedImage img, int height, int width ) {
      Image tmp = img.getScaledInstance( width, height, Image.SCALE_SMOOTH);
      BufferedImage resized = new BufferedImage( width, height, BufferedImage.TYPE_INT_ARGB);
      Graphics2D g2d = resized.createGraphics();
      g2d.drawImage(tmp, 0, 0, null);
      g2d.dispose();
      return resized;
   }
}
