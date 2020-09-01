package com;

import javax.swing.*;
import java.awt.*;

/**
 * @author omerozbekler
 * @version 10.04.2018
 * MyJFrame - This program
 */
public class MyJFrame extends JFrame{

   private JPanel panel;
   Dimension screenSize;
   static int screen_Width;
   static int screen_Height;

   static Image backgroundImage;
   static Image tableImage;
   static Image welcomeImage;
   static Image aboutImage;
   static Image textbookImage;
   static Image hintsImage;
   static Image questionsImage;
   static Image resourcesImage;
   static ImageIcon homeIcon;

   public MyJFrame () {
      super();

      screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      screen_Width = (int) (screenSize.getWidth());
      screen_Height = (int) (screenSize.getHeight());

      backgroundImage = new ImageIcon(this.getClass().getResource("/images/background.jpg")).getImage();
      homeIcon        = new ImageIcon(this.getClass().getResource("/images/home.png"));

      tableImage   = new ImageIcon(this.getClass().getResource("/images/table.png")).getImage();
      welcomeImage = new ImageIcon(this.getClass().getResource("/images/welcome.png")).getImage();

      tableImage   = new ImageIcon(tableImage.getScaledInstance((MyJFrame.screen_Width / 3), (int)(MyJFrame.screen_Height / 1.5),  java.awt.Image.SCALE_SMOOTH)).getImage();
      welcomeImage = new ImageIcon(welcomeImage.getScaledInstance((MyJFrame.screen_Width / 2), (int)(MyJFrame.screen_Height / 8.3),  java.awt.Image.SCALE_SMOOTH)).getImage();

      aboutImage   = new ImageIcon(this.getClass().getResource("/images/aboutUs.png")).getImage();
      aboutImage   = new ImageIcon(aboutImage.getScaledInstance(MyJFrame.screen_Width, MyJFrame.screen_Height,  java.awt.Image.SCALE_SMOOTH)).getImage();

      textbookImage   = new ImageIcon(this.getClass().getResource("/images/textbookPanel.png")).getImage();
      textbookImage   = new ImageIcon(textbookImage.getScaledInstance(MyJFrame.screen_Width, MyJFrame.screen_Height,  java.awt.Image.SCALE_SMOOTH)).getImage();

      hintsImage   = new ImageIcon(this.getClass().getResource("/images/hintsPanel.png")).getImage();
      hintsImage   = new ImageIcon(hintsImage.getScaledInstance(MyJFrame.screen_Width, MyJFrame.screen_Height,  java.awt.Image.SCALE_SMOOTH)).getImage();

      questionsImage   = new ImageIcon(this.getClass().getResource("/images/questionsPanel.png")).getImage();
      questionsImage   = new ImageIcon(questionsImage.getScaledInstance(MyJFrame.screen_Width, MyJFrame.screen_Height,  java.awt.Image.SCALE_SMOOTH)).getImage();

      resourcesImage   = new ImageIcon(this.getClass().getResource("/images/resourcesPanel.png")).getImage();
      resourcesImage   = new ImageIcon(resourcesImage.getScaledInstance(MyJFrame.screen_Width, MyJFrame.screen_Height,  java.awt.Image.SCALE_SMOOTH)).getImage();

      //Sets MenuPanel
      panel = new MenuPanel();

      //Sets MyJFrame
      setExtendedState(JFrame.MAXIMIZED_BOTH);
      setUndecorated(true);
      setResizable(false);
      setLayout( new BorderLayout());

      add(panel);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setExtendedState(JFrame.MAXIMIZED_BOTH);
      setUndecorated(true);
      setVisible(true);
   }
}
