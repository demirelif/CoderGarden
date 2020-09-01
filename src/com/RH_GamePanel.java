package com;

import com.MyJFrame;
import com.MyJPanel;
import gameMode.ItemBasket;
import gameMode.Player;
import gameMode.Level;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author omerozbekler
 * @version 12.05.2018
 * RH_GamePanel - This program
 */

public class RH_GamePanel extends JPanel implements ActionListener {
   //Properties
   private  Image background;
   private Player player;
   JLabel chImageLabel;
   Timer timer;
   private ArrayList<Integer> moveXArray;
   private ArrayList<Integer> moveYArray;
   int i;
   ItemBasket itemB ;
   Level level;

   //Constructor
   public RH_GamePanel(Level level)
   {
      i = -1;
      this.level = level;
      setSize( new Dimension(600,400));
      player = new Player(level.getLevelNum());
      setLocation(50,150);
      this.background = level.getMap();


      setLayout(null);
      //this.character = player.characterImage;
      chImageLabel = new JLabel();
      chImageLabel.setIcon(new ImageIcon(this.getClass().getResource(player.getChImage(level.getLevelNum()))));

      System.out.println(player.getX());
      System.out.println(player.getY());
      chImageLabel.setOpaque(false);
      chImageLabel.setVisible(true);
      chImageLabel.setBounds(player.getX(), player.getY(), 100,100);
      add( chImageLabel );
      setVisible(true);
      timer = new Timer(100, this);
      moveXArray = level.getMovementX();
      moveYArray = level.getMovementY();
      itemB = level.getBasket();
      timer.start();
   }
   @Override
   protected void paintComponent (Graphics g) {
      super.paintComponent(g);
      g.drawImage(background, 0,0,this.getWidth(),this.getHeight(),  MyJPanel.imageObserver);
      g.setColor(Color.GREEN);
      g.setFont(new java.awt.Font("Tahoma", 1, 26));

      for (int i = 0; i < itemB.getSize() ; i++)
      {
         if (itemB.get(i).getPicked())
         {
            g.drawString("√" , itemB.get(i).getX() * 100 + 42 ,itemB.get(i).getY() * 100 + 63);
         }
      }



   }

   @Override
   public void actionPerformed(ActionEvent e) {
      nextIndex();
      chImageLabel.setLocation( moveXArray.get(i),  moveYArray.get(i));
      repaint();
   }

   private int nextIndex()
   {
      if( i < moveXArray.size() - 1)
         i++;
      return i;
   }





}


