package com;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;

/**
 * @author omerozbekler
 * @version 13.04.2018
 * MyJButton - This program
 */
public class MyJButton extends JButton {

   final static int BUTTONWIDTH     = MyJFrame.screen_Width / 5;
   final static int BUTTONHEIGHT    = MyJFrame.screen_Height / 10;

   public MyJButton (String str, Icon icon, int xLocation, int yLocation, boolean isExit) {
      super(str, icon);

      if ( icon == null )
         setOpaque(true);
      else
         setOpaque(false);

      setBorderPainted(false);
      setForeground(new Color(0,0,0,255));
      setFont( new Font(Font.DIALOG, Font.BOLD, 25));

      if ( !isExit ) {
         setBounds(xLocation - (BUTTONWIDTH / 2), yLocation - (BUTTONHEIGHT / 2), BUTTONWIDTH, BUTTONHEIGHT);
         setBackground(new Color(160, 200, 255, 255));
      }else {
         setBounds(xLocation - (BUTTONWIDTH / 4), yLocation - (BUTTONHEIGHT / 2), BUTTONWIDTH / 2, BUTTONHEIGHT);
         setBackground(new Color(240, 20, 20, 255));
      }

   }

   public MyJButton (String str, int xLocation, int yLocation) {
      this(str, null, xLocation, yLocation, false);
   }

   public MyJButton (String str, Icon icon, int xLocation, int yLocation) {
      this(str, icon, xLocation, yLocation, false);
   }

   public MyJButton (String str, int xLocation, int yLocation, boolean isExit) {
      this(str, null, xLocation, yLocation, isExit);
   }
}
