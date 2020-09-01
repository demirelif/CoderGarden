package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author omerozbekler
 * @version 10.05.2018
 * TextbookPanel - This program
 */
public class TextbookPanel extends MyJPanel{

   JButton hints;
   JButton questions;

   private ButtonListener buttonListener;

   public TextbookPanel () {
      setLayout( null );

      home.setEnabled(true);
      home.setVisible(true);

      buttonListener  = new ButtonListener() {
         @Override
         public void actionPerformed (ActionEvent e) {

            if ( e.getSource() == hints )
               MyJPanel.changePanel(TextbookPanel.this, new HintsPanel());
            else if ( e.getSource() == questions )
               MyJPanel.changePanel(TextbookPanel.this, new QuestionsPanel());
         }
      };

      hints        = new MyJButton("", (int)(MyJFrame.screen_Width * 0.35) ,(int)(MyJFrame.screen_Height * 0.4));
      questions    = new MyJButton("", (int)(MyJFrame.screen_Width * 0.63) ,(int)(MyJFrame.screen_Height * 0.4));

      hints.setSize((int)(MyJFrame.screen_Width * 0.24) ,(int)(MyJFrame.screen_Height * 0.06));
      questions.setSize((int)(MyJFrame.screen_Width * 0.24) ,(int)(MyJFrame.screen_Height * 0.06));

      hints.addActionListener(buttonListener);
      questions.addActionListener(buttonListener);

      add(hints);
      add(questions);

      hints.setOpaque(false);
      hints.setContentAreaFilled(false);
      hints.setBorderPainted(false);

      questions.setOpaque(false);
      questions.setContentAreaFilled(false);
      questions.setBorderPainted(false);
   }

   @Override
   protected void paintComponent (Graphics g) {
      super.paintComponent(g);
      g.drawImage(MyJFrame.textbookImage, 0, 0, MyJPanel.imageObserver);
   }
}
