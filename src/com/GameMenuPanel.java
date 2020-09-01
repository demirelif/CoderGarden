package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author omerozbekler
 * @version 14.04.2018
 * GameMenu - This program
 */
public class GameMenuPanel extends MyJPanel{

   JButton mainMenu;

   JButton reachTheHomeLevel1;
   JButton reachTheHomeLevel2;
   JButton reachTheHomeLevel3;

   JButton drawingLevel1;
   JButton drawingLevel2;

   JLabel games;
   JLabel reachTheHome;
   JLabel drawing;

   private ButtonListener buttonListener;

   protected static int welcomeHeight;
   protected static int welcomeWidth;
   protected static int welcomeX;
   protected static int welcomeY;

   public GameMenuPanel () {
      setLayout(null);

      welcomeWidth      = MyJFrame.welcomeImage.getWidth(MyJPanel.imageObserver);
      welcomeHeight     = MyJFrame.welcomeImage.getHeight(MyJPanel.imageObserver);

      welcomeX          = MyJFrame.screen_Width / 4;
      welcomeY          = 25;

      buttonListener  = new ButtonListener() {
         @Override
         public void actionPerformed (ActionEvent e) {

            if ( e.getSource() == mainMenu )
               MyJPanel.changePanel(GameMenuPanel.this, new MenuPanel());
            else if ( e.getSource() == reachTheHomeLevel1 )
               MyJPanel.changePanel(GameMenuPanel.this, new ReachTheHomePanel(1));
            else if ( e.getSource() == reachTheHomeLevel2 )
               MyJPanel.changePanel(GameMenuPanel.this, new ReachTheHomePanel(2));
            else if ( e.getSource() == reachTheHomeLevel3 )
               MyJPanel.changePanel(GameMenuPanel.this, new ReachTheHomePanel(3));
            else if ( e.getSource() == drawingLevel1 )
               MyJPanel.changePanel(GameMenuPanel.this, new D_GamePane(1));
            else if ( e.getSource() == drawingLevel2 )
               MyJPanel.changePanel(GameMenuPanel.this, new D_GamePane(2));
         }
      };

      mainMenu             = new MyJButton("Main Menu",     MyJFrame.screen_Width - 200,   MyJFrame.screen_Height - 100);

      reachTheHomeLevel1   = new MyJButton("Level 1",        MyJFrame.screen_Width / 3,     MyJFrame.screen_Height / 4 + 200);
      reachTheHomeLevel2   = new MyJButton("Level 2",        MyJFrame.screen_Width / 3,     MyJFrame.screen_Height / 4 + 300);
      reachTheHomeLevel3   = new MyJButton("Level 3",        MyJFrame.screen_Width / 3,     MyJFrame.screen_Height / 4 + 400);

      drawingLevel1        = new MyJButton("Level 1",       MyJFrame.screen_Width * 2 / 3, MyJFrame.screen_Height / 4 + 200);
      drawingLevel2        = new MyJButton("Level 2",       MyJFrame.screen_Width * 2 / 3, MyJFrame.screen_Height / 4 + 300);

      games                = new MyJLabel("Games",          MyJFrame.screen_Width / 2,     MyJFrame.screen_Height / 4);
      reachTheHome         = new MyJLabel("Reach The Home", MyJFrame.screen_Width / 3,     MyJFrame.screen_Height / 4 + 100);
      drawing              = new MyJLabel("Drawing",        MyJFrame.screen_Width * 2 / 3, MyJFrame.screen_Height / 4 + 100);

      mainMenu.addActionListener(buttonListener);

      reachTheHomeLevel1.addActionListener(buttonListener);
      reachTheHomeLevel2.addActionListener(buttonListener);
      reachTheHomeLevel3.addActionListener(buttonListener);

      drawingLevel1.addActionListener(buttonListener);
      drawingLevel2.addActionListener(buttonListener);

      home.setEnabled(false);
      home.setVisible(false);

      add(mainMenu);
      add(reachTheHomeLevel1);
      add(reachTheHomeLevel2);
      add(reachTheHomeLevel3);
      add(drawingLevel1);
      add(drawingLevel2);

      add(games);
      add(reachTheHome);
      add(drawing);

   }

   @Override
   protected void paintComponent (Graphics g) {
      super.paintComponent(g);
      g.drawImage(MyJFrame.welcomeImage, welcomeX, welcomeY, MyJPanel.imageObserver);
   }

   private class MyJLabel extends JLabel{
      public MyJLabel (String str, int xLocation, int yLocation) {
         super(str);

         final int WIDTH  = MyJFrame.screen_Width / 4;
         final int HEIGHT = MyJFrame.screen_Height / 10;

         setBounds(xLocation - (WIDTH / 2), yLocation - (HEIGHT / 2), WIDTH, HEIGHT);
         setHorizontalAlignment(CENTER);
         setVerticalTextPosition(CENTER);
         setFont( new Font(Font.DIALOG, Font.BOLD, 25));
         setOpaque(true);
         setBackground(Color.GREEN);
      }
   }
}
