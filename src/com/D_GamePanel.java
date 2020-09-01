package com;

import gameMode.DLevel;
import gameMode.Pencil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;
import java.util.ArrayList;

/**
 * @author omerozbekler
 * @version 13.05.2018
 * D_GamePanel - This program
 */
public class D_GamePanel extends JPanel implements ActionListener {
   private Image backgroundImage;
   Image pencilImage;
   DLevel level;
   ArrayList<Integer> pencilMovementX;
   ArrayList<Integer> pencilMovementY;
   Pencil pencil;
   Timer timer;
   int i;
   JLabel pencilLabel;

   // constructors
   public D_GamePanel(int levelNum) {
      backgroundImage = new ImageIcon(this.getClass().getResource("/level" + levelNum +".PNG")).getImage();
      backgroundImage.getScaledInstance( 557, 300,  Image.SCALE_SMOOTH);

      pencilLabel=  new JLabel();
      pencilLabel.setIcon(new ImageIcon(this.getClass().getResource("/pencil.png")));



      level = new DLevel( levelNum);

      level.getPencilX().add(100);
      level.getPencilY().add(100);

      pencil  = level.getPencil();
      pencil.setX(pencil.getX());
      pencil.setY(pencil.getY());

      pencilMovementX = level.getPencilX();
      pencilMovementY = level.getPencilY();
      System.out.println(pencilMovementX);


      setLayout(null);
      add(pencilLabel);
      pencilLabel.setLocation(pencil.getX(), pencil.getY());
      pencilLabel.setSize(new Dimension(30 , 45));
      pencilLabel.setBounds(pencil.getX(), pencil.getY() , 30 , 45);

      i = 0;

      setOpaque(true);
      setBounds(50, 150,557,300);
      setPreferredSize(new Dimension(557, 300));
      timer = new Timer( 100, this);
      timer.start();

   }

   // methods
   @Override
   protected void paintComponent (Graphics g) {
      super.paintComponent(g);
      g.drawImage(backgroundImage, 0,0, null);
    //  g.drawImage(pencilImage, pencilMovementX.get(i),  pencilMovementY.get(i) , 30 , 45,null);
   }

   public void actionPerformed(ActionEvent e) {
      nextIndex();
//      for ( int k = 0; k<level.getPencilX().size(); k++ ) {
         pencilLabel.setLocation(pencilMovementX.get(i), pencilMovementY.get(i));
 //        pencilLabel.setLocation(150, 150);
         // pencil.setY(pencilMovementY.get(i));
         repaint();
   //   }


   }

   private int nextIndex()
   {
      if( i < pencilMovementX.size() - 1)
         i++;
      return i;
   }


}
