package com;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author omerozbekler
 * @version 12.04.2018
 * MenuPanel - This program
 */
public class MenuPanel extends MyJPanel{
   JButton games;
   JButton textbook;
   JButton resources;
   JButton about;
   JButton exit;

   private ButtonListener buttonListener;

   protected static int welcomeHeight;
   protected static int welcomeWidth;
   protected static int tableHeight;
   protected static int tableWidth;

   protected static int welcomeX;
   protected static int welcomeY;
   protected static int tableX;
   protected static int tableY;

   public MenuPanel () {

      welcomeWidth      = MyJFrame.welcomeImage.getWidth(MyJPanel.imageObserver);
      welcomeHeight     = MyJFrame.welcomeImage.getHeight(MyJPanel.imageObserver);
      tableWidth        = MyJFrame.tableImage.getWidth(MyJPanel.imageObserver);
      tableHeight       = MyJFrame.tableImage.getHeight(MyJPanel.imageObserver);

      welcomeX          = MyJFrame.screen_Width / 4;
      welcomeY          = 25;
      tableX            = MyJFrame.screen_Width  / 20;
      tableY            = MyJFrame.screen_Height / 4;

      setLayout( null );

      buttonListener  = new ButtonListener() {
         @Override
         public void actionPerformed (ActionEvent e) {

            if ( e.getActionCommand() == "Games" )
               MyJPanel.changePanel(MenuPanel.this, new GameMenuPanel());
            else if ( e.getActionCommand() == "Textbook" )
               MyJPanel.changePanel(MenuPanel.this, new TextbookPanel());
            else if ( e.getActionCommand() == "Resources" )
               MyJPanel.changePanel(MenuPanel.this, new ResourcesPanel());
            else if ( e.getActionCommand() == "About" )
               MyJPanel.changePanel(MenuPanel.this, new AboutPanel());
            else if ( e.getActionCommand() == "Exit" )
               System.exit(0);
         }
      };

      games        = new MyJButton("Games",    MyJFrame.screen_Width * 2 / 3,200);
      textbook     = new MyJButton("Textbook", MyJFrame.screen_Width * 2 / 3,300);
      resources    = new MyJButton("Resources",MyJFrame.screen_Width * 2 / 3,400);
      about        = new MyJButton("About",    MyJFrame.screen_Width * 2 / 3,500);
      exit         = new MyJButton("Exit",     MyJFrame.screen_Width * 2 / 3,600, true);

      games.addActionListener(buttonListener);
      textbook.addActionListener(buttonListener);
      resources.addActionListener(buttonListener);
      about.addActionListener(buttonListener);
      exit.addActionListener(buttonListener);

      home.setEnabled(false);
      home.setVisible(false);

      add(games);
      add(textbook);
      add(resources);
      add(about);
      add(exit);
   }

   @Override
   protected void paintComponent (Graphics g) {
      super.paintComponent(g);
      g.drawImage(MyJFrame.tableImage, tableX, tableY, MyJPanel.imageObserver);
      g.drawImage(MyJFrame.welcomeImage, welcomeX, welcomeY, MyJPanel.imageObserver);
   }
}
