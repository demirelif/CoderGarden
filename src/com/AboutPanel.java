package com;

import javax.swing.*;
import java.awt.*;

/**
 * @author omerozbekler
 * @version 10.05.2018
 * AboutPanel - This program
 */
public class AboutPanel extends MyJPanel {

   public AboutPanel () {
      setLayout( null );

      home.setEnabled(true);
      home.setVisible(true);
   }

   @Override
   protected void paintComponent (Graphics g) {
      super.paintComponent(g);
      g.drawImage(MyJFrame.aboutImage, 0, 0, MyJPanel.imageObserver);
   }
}
