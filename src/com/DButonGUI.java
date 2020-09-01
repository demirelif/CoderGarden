package com;

import javax.swing.*;
import java.awt.*;

/**
 * @author omerozbekler
 * @version 13.05.2018
 * DButonGUI - This program
 */
public class DButonGUI extends JButton{

   //   int MAINBUTTONWIDTH     = 300;
//   int MAINBUTTONHEIGHT    = 75;
//   int SUBBUTTONWIDTH      = 300;
//   int SUBBUTTONHEIGHT     = 75;
   int LEVELBUTTONWIDTH    = 150;
   int LEVELBUTTONHEIGHT   = 75;



   public DButonGUI (String str, Icon icon, int type, int y) {
      super(str, icon);
      setFont( new Font("Arial", Font.TYPE1_FONT, 18));
      //setOpaque(true);
      //setBorderPainted(true);
      setForeground(new Color(0,0,0,255));

      if ( type == 1 ) {     // begin button
         setBackground(Color.YELLOW);
         setBounds(500, y, LEVELBUTTONWIDTH, LEVELBUTTONHEIGHT);
         setPreferredSize(new Dimension(230, 55));
      } else if ( type == 2 ) {     // see real code BUTTON
         setBackground(new Color(10, 180, 10, 255));
         setBounds(800, y, LEVELBUTTONWIDTH, LEVELBUTTONHEIGHT);
         setPreferredSize(new Dimension(230, 55));
      } else if ( type == 3 ) {     // Home button
         //setBackground(new Color(160, 200, 255, 10));
         setOpaque(false);
         setFont( new Font(Font.DIALOG, Font.BOLD, 10));
         setVerticalTextPosition(SwingConstants.BOTTOM);
         setHorizontalTextPosition(SwingConstants.CENTER);
         setBounds(10, y, 100, 100);
         //setBorderPainted(false);
      } else if ( type == 4 ) {     // hint icon
         setBackground(new Color(160, 200, 255, 10));
         setOpaque(false);
         setFont( new Font(Font.DIALOG, Font.BOLD, 10));
         setVerticalTextPosition(SwingConstants.BOTTOM);
         //setHorizontalTextPosition(SwingConstants.CENTER);
         setBounds(0, 25, 20, 20);
         setBorderPainted(false);
      } else if ( type == 5 ) {     // trash
         //setBackground(new Color(160, 200, 255, 10));
         setFont( new Font(Font.DIALOG, Font.BOLD, 10));
         setVerticalTextPosition(SwingConstants.BOTTOM);

         setHorizontalTextPosition(SwingConstants.CENTER);
         setBounds(0, 0, 320, 240);
         setBorderPainted(false);
      }


   }

   public DButonGUI (String str, int type, int y) {
      this(str, null, type, y);
   }

//   @Override
//   protected void paintComponent(Graphics g) {
//      final Graphics2D g2 = (Graphics2D) g.create();
//      g2.setPaint(new GradientPaint(
//                                    new Point(0, 0),
//                                    new Color(10, 180, 10, 255),
//                                    new Point(0, getHeight()),
//                                    new Color(10, 180, 10, 255)));
//      g2.fillRect(0, 0, getWidth(), getHeight());
//      g2.dispose();
//
//      super.paintComponent(g);
//   }
}
