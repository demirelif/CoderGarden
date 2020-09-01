package com;

import gameMode.*;

import java.awt.*;
import javax.swing.*;
/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author proteampc
 */


public class ReachTheHomePanel extends MyJPanel {
   private ImageIcon hintIcon;
   int levelNum;
   ButtonCollection buttons;
   Level level;
   Player character;
   CodeBlocks codeBlocks;
   ItemBasket itemBasket;
   boolean hintState, codeState;
   ButtonListener buttonListener;
   boolean indented;
   RH_GamePanel rh_gamePanel;


   /**
    * Creates new form RHPanel1
    */
   public ReachTheHomePanel (final int levelNum) {
      this.levelNum = levelNum;
      level = new Level(levelNum);
      character = level.getCharacter();
      codeBlocks = level.getCodeBlocks();
      buttons = level.getButtons();
      itemBasket = level.getBasket();
      hintState = false;
      codeState = false;
      indented = false;
      rh_gamePanel = new RH_GamePanel(level);


      buttonListener = new ButtonListener() {
         @Override
         public void actionPerformed (ActionEvent e) {

            if ( e.getSource() == hintButton ) {
               if ( !(hintState) ) {
                  getGraphics().drawImage(new ImageIcon(this.getClass().getResource(level.getHint(levelNum))).getImage(), (int) (MyJFrame.screen_Width * 0.72), (int) (MyJFrame.screen_Height * 0.77), 179, 177, MyJPanel.imageObserver);
               } else {
                  repaint();
               }
               hintState = !(hintState);
            } else if ( e.getSource() == beginButton ) {
               level.start(codeBlocks.getAdded());


                  if ( level.win() ) {
                     int again = JOptionPane.showConfirmDialog(ReachTheHomePanel.this, "Next Level? ", "CONGRATULATIONS", 0);
                     if ( again == 0 ) {
                        MyJPanel.changePanel(ReachTheHomePanel.this, new ReachTheHomePanel(level.getLevelNum() + 1));
                     } else
                        MyJPanel.changePanel(ReachTheHomePanel.this, new GameMenuPanel());
                  } else if (level.isGameOver()){
                     int again = JOptionPane.showConfirmDialog(ReachTheHomePanel.this, "Replay? ", "FAILED", 0);
                     if ( again == 0 ) {
                        MyJPanel.changePanel(ReachTheHomePanel.this, new ReachTheHomePanel(level.getLevelNum() + 1));
                     } else
                        MyJPanel.changePanel(ReachTheHomePanel.this, new GameMenuPanel());
                  }


            } else if ( e.getSource() == realCodeButton ) {
               codeBlocks.getRealCode(codeBlocks.getAdded());
               jTextArea.setText(codeBlocks.print(codeBlocks.getAdded()));
               if ( !(codeState) ) {
                  jScrollPane1.setVisible(true);
                  realCodeButton.setText("Hide Real Code");
               } else {
                  jScrollPane1.setVisible(false);
                  realCodeButton.setText("Show Real Code");
               }
               codeState = !(codeState);
            } else if ( e.getSource() == clearButton ) {
                  MyJPanel.changePanel(ReachTheHomePanel.this, new ReachTheHomePanel(level.getLevelNum()));
            } else if ( e.getSource() == rightB ) {
               codeBlocks.addButton(buttons.getButton(3));
               drawButtons(buttons.getButton(3));
            } else if ( e.getSource() == leftB ) {
               codeBlocks.addButton(buttons.getButton(4));
               drawButtons(buttons.getButton(4));
            } else if ( e.getSource() == upB ) {
               codeBlocks.addButton(buttons.getButton(5));
               drawButtons(buttons.getButton(5));
            } else if ( e.getSource() == downB ) {
               codeBlocks.addButton(buttons.getButton(6));
               drawButtons(buttons.getButton(6));
            } else if ( e.getSource() == loopButton ) {
//               System.out.println((Integer  )jSpinner1.getValue());
               int n = (Integer) jSpinner1.getValue();
               buttons.setLoopContent(n);
               codeBlocks.addButton(buttons.getButton(7));
               buttons.getButton(7).setLoopCount(n);
               drawButtons(buttons.getButton(7));
            } else if ( e.getSource() == endLoopButton ) {
               codeBlocks.addButton(buttons.getButton(8));
               drawButtons(buttons.getButton(8));
            } else if ( e.getSource() == pickButton ) {
               codeBlocks.addButton(buttons.getButton(9));
               drawButtons(buttons.getButton(9));
            }
            jTextArea.setText(codeBlocks.print(codeBlocks.getAdded()));
         }
      };
      initComponents();

   }

   public void drawButtons (Buton button) {
      if ( button.getButtonName() == "endLoop" ) {
         jPanel2.getGraphics().drawRect(10, (codeBlocks.getAdded().size() - 1) * 40, 60, 40);
         jPanel2.getGraphics().drawString("End Loop", 12, (codeBlocks.getAdded().size() - 1) * 40 + 20);
         return;
      }
      if ( button.getButtonName() == "pick" ) {
         int x = 0;
         if ( codeBlocks.isIndented ) {
            x = 30;
         } else {
            x = 10;
         }

         jPanel2.getGraphics().drawString("Pick", x + 2, (codeBlocks.getAdded().size() - 1) * 40 + 20);
         jPanel2.getGraphics().drawRect(x, (codeBlocks.getAdded().size() - 1) * 40, 40, 40);
         return;
      }
      Image img = new ImageIcon(this.getClass().getResource(button.getButtonImage())).getImage();
      if ( button.getButtonName() == "loop" ) {
         jPanel2.getGraphics().drawImage(img, 10, (codeBlocks.getAdded().size() - 1) * 40, 60, 40, MyJPanel.imageObserver);
         jPanel2.getGraphics().drawRect(10, (codeBlocks.getAdded().size() - 1) * 40, 60, 40);
         jPanel2.getGraphics().drawString((Integer) jSpinner1.getValue() + "", 37, (codeBlocks.getAdded().size() - 1) * 40 + 24);

      } else if ( codeBlocks.isIndented ) {
         jPanel2.getGraphics().drawImage(img, 30, (codeBlocks.getAdded().size() - 1) * 40, 40, 40, MyJPanel.imageObserver);
         jPanel2.getGraphics().drawRect(30, (codeBlocks.getAdded().size() - 1) * 40, 40, 40);

      } else {
         jPanel2.getGraphics().drawImage(img, 10, (codeBlocks.getAdded().size() - 1) * 40, 40, 40, MyJPanel.imageObserver);
         jPanel2.getGraphics().drawRect(10, (codeBlocks.getAdded().size() - 1) * 40, 40, 40);
      }
   }


   /**
    * This method is called from within the constructor to initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is always
    * regenerated by the Form Editor.
    */
   @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">
   private void initComponents () {

      add(rh_gamePanel);

      hintButton = new javax.swing.JButton();
      beginButton = new javax.swing.JButton();
      realCodeButton = new javax.swing.JButton();
      clearButton = new javax.swing.JButton();


      loopButton = new javax.swing.JButton();
      pickButton = new javax.swing.JButton();
      endLoopButton = new javax.swing.JButton();

      rightB = new javax.swing.JButton();
      upB = new javax.swing.JButton();
      downB = new javax.swing.JButton();
      leftB = new javax.swing.JButton();

      jPanel2 = new javax.swing.JPanel();

      jPanel1 = new javax.swing.JPanel();
      jTextArea = new javax.swing.JTextArea();
      jScrollPane1 = new javax.swing.JScrollPane();

      SpinnerNumberModel model = new SpinnerNumberModel(1, 1, 10, 1);

      jLabel1 = new javax.swing.JLabel();
      jSpinner1 = new javax.swing.JSpinner(model);
      jSeparator1 = new javax.swing.JSeparator();


      hintButton.addActionListener(buttonListener);
      beginButton.addActionListener(buttonListener);
      realCodeButton.addActionListener(buttonListener);
      clearButton.addActionListener(buttonListener);
      loopButton.addActionListener(buttonListener);

      pickButton.addActionListener(buttonListener);
      endLoopButton.addActionListener(buttonListener);

      rightB.addActionListener(buttonListener);
      upB.addActionListener(buttonListener);
      downB.addActionListener(buttonListener);
      leftB.addActionListener(buttonListener);


      hintButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/testpackage/bugi.png")));
      hintButton.setText("hintButton");
      hintButton.setBounds((int) (MyJFrame.screen_Width * 0.86), (int) (MyJFrame.screen_Height * 0.77), 193, 187);
      hintButton.setBorderPainted(false);
      hintButton.setContentAreaFilled(false);
      add(hintButton);

      beginButton.setBackground(new java.awt.Color(51, 204, 0));
      beginButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
      beginButton.setText("BEGIN");


      realCodeButton.setBackground(new java.awt.Color(51, 204, 0));
      realCodeButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
      realCodeButton.setText("SEE REAL CODE");

      jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
      jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
      jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);


      jLabel1.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
      jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
      jLabel1.setText(level.getExplanation());


      clearButton.setBackground(new java.awt.Color(0, 204, 255));
      clearButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/testpackage/oie_1O5Yl6I6QD5V.png"))); // NOI18N
      clearButton.setBorderPainted(false);
      clearButton.setContentAreaFilled(false);

      rightB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/testpackage/right.png"))); // NOI18N

      upB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/testpackage/up.png"))); // NOI18N
      upB.setBorderPainted(false);
      upB.setContentAreaFilled(false);

      downB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/testpackage/down.png"))); // NOI18N
      downB.setBorderPainted(false);
      downB.setContentAreaFilled(false);

      leftB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/testpackage/left.png"))); // NOI18N
      leftB.setBorderPainted(false);
      leftB.setContentAreaFilled(false);

      rightB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/testpackage/right.png"))); // NOI18N
      rightB.setBorderPainted(false);
      rightB.setContentAreaFilled(false);

      jScrollPane1.setViewportView(jTextArea);
      jScrollPane1.setVisible(false);
      jScrollPane1.setBackground(new Color(160, 200, 255, 255));
      jScrollPane1.setBounds((int) (MyJFrame.screen_Width * 0.78), (int) (MyJFrame.screen_Height * 0.13), (int) (MyJFrame.screen_Width * 0.22), (int) (MyJFrame.screen_Height * 0.63));
      add(jScrollPane1);

      jPanel2.setBackground(new Color(160, 200, 255, 255));
      jPanel2.setPreferredSize(new Dimension((int) (MyJFrame.screen_Width * 0.15), (int) (MyJFrame.screen_Height * 3)));
      jPanel2.setBounds((int) (MyJFrame.screen_Width * 0.57), (int) (MyJFrame.screen_Height * 0.13), (int) (MyJFrame.screen_Width * 0.15), (int) (MyJFrame.screen_Height * 0.63));
      add(jPanel2);


      jTextArea.setBackground(new Color(160, 200, 255, 255));
      jTextArea.setEditable(false);
      jTextArea.setFont(new Font("Serif", Font.BOLD, 15));

      pickButton.setText("Pick");


      endLoopButton.setText("End Loop");


      jPanel1.setOpaque(false);

      loopButton.setText("Loop");

      javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
      jPanel1.setLayout(jPanel1Layout);
      jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(loopButton)
                        .addGap(18, 18, 18)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
      );
      jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                              .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(loopButton))
                        .addGap(33, 33, 33))
      );

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                          .addGroup(layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                      .addGroup(layout.createSequentialGroup()
                                                            .addGap(58, 58, 58)
                                                            .addComponent(upB, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                      .addGroup(layout.createSequentialGroup()
                                                            .addGap(59, 59, 59)
                                                            .addComponent(downB, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                      .addGroup(layout.createSequentialGroup()
                                                            .addComponent(leftB, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(rightB, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(51, 51, 51)
                                                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(49, 49, 49)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                      .addComponent(endLoopButton)
                                                      .addComponent(pickButton))))
                                    .addGap(0, 0, Short.MAX_VALUE))
                              .addGroup(layout.createSequentialGroup()
                                    .addGap(74, 313, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                              .addGroup(layout.createSequentialGroup()
                                    .addGap(199, 199, 199))
                              .addGroup(layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                          .addComponent(beginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 337, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                          .addComponent(realCodeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())
      );
      layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 259, Short.MAX_VALUE)
                        .addComponent(upB, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGroup(layout.createSequentialGroup()
                                    .addComponent(pickButton)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(endLoopButton)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                          .addComponent(leftB, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                          .addComponent(rightB, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(downB, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                  .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                          .addComponent(beginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(realCodeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                          .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(clearButton)
                                                .addContainerGap())))
                              .addComponent(jSeparator1)))
      );


      //LEVEL NUMARASINA GÖRE AYARLA!!!
      if ( levelNum == 1 ) {
         leftB.setEnabled(false);
         upB.setEnabled(false);
         downB.setEnabled(false);
         loopButton.setEnabled(false);
         endLoopButton.setEnabled(false);
         pickButton.setEnabled(false);
         jSpinner1.setEnabled(false);
      }

      if (levelNum == 2)
         pickButton.setEnabled(false);


   }// </editor-fold>

//   private void beginButtonActionPerformed (java.awt.event.ActionEvent evt) {
//      level.start(codeBlocks.getAdded());
//   }


   // Variables declaration - do not modify
   private javax.swing.JButton downB;
   private javax.swing.JButton leftB;
   private javax.swing.JButton rightB;
   private javax.swing.JButton upB;

   private javax.swing.JButton hintButton;
   private javax.swing.JButton pickButton;
   private javax.swing.JButton endLoopButton;
   private javax.swing.JButton beginButton;
   private javax.swing.JButton realCodeButton;
   private javax.swing.JButton clearButton;
   private javax.swing.JButton loopButton;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JPanel jPanel1;

   private javax.swing.JScrollPane jScrollPane1;
   private javax.swing.JScrollPane jScrollPane2;


   private javax.swing.JSeparator jSeparator1;

   private javax.swing.JSpinner jSpinner1;
   private javax.swing.JTextArea jTextArea;

   private javax.swing.JPanel jPanel2;
   // End of variables declaration
}