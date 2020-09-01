package com;

import gameMode.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLClientInfoException;


public class D_GamePane extends MyJPanel {
   private ImageIcon hintIcon;
   int levelNum;
   DButtonCollection buttons;
   DLevel level;
   Pencil character;
   CodeBlocks codeBlocks;
   boolean hintState, codeState;
   ButtonListener buttonListener;
   boolean indented;
   D_GamePanel d_gamePanel;


   /**
    * Creates new form RHPanel1
    */
   public D_GamePane (final int levelNum) {
      this.levelNum = levelNum;
      level = new DLevel(levelNum);
      character = level.getPencil();
      codeBlocks = level.getCodeBlocks();
      buttons = level.getCollection();
      hintState = false;
      codeState = false;
      indented = false;
      d_gamePanel = new D_GamePanel(levelNum);


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

//               if ( level.isGameOver() ) {
//                  if ( level.win() ) {
//                     int again = JOptionPane.showConfirmDialog(ReachTheHomePanel.this, "Next Level? ", "CONGRATULATIONS", 0);
//                     if ( again == 0 ) {
//                        MyJPanel.changePanel(ReachTheHomePanel.this, new ReachTheHomePanel(level.getLevelNum() + 1));
//                     } else
//                        MyJPanel.changePanel(ReachTheHomePanel.this, new GameMenuPanel());
//                  } else {
//                     int again = JOptionPane.showConfirmDialog(ReachTheHomePanel.this, "Replay? ", "FAILED", 0);
//                     if ( again == 0 ) {
//                        MyJPanel.changePanel(ReachTheHomePanel.this, new ReachTheHomePanel(level.getLevelNum() + 1));
//                     } else
//                        MyJPanel.changePanel(ReachTheHomePanel.this, new GameMenuPanel());
//
//                  }
//               }

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
                MyJPanel.changePanel(D_GamePane.this, new D_GamePane(levelNum));
            } else if ( e.getSource() == draw ) {
                int n = (Integer)(jspinner2.getValue());
               codeBlocks.addButton(buttons.getButton(5));
               drawButtons(buttons.getButton(5));
            } else if ( e.getSource() == square ) {
               int n = (Integer)(jspinner3.getValue());
               codeBlocks.addButton(buttons.getButton(2));
               drawButtons(buttons.getButton(2));
            } else if ( e.getSource() == move ) {
               int n = (Integer)(jspinner4.getValue());
               codeBlocks.addButton(buttons.getButton(3));
               drawButtons(buttons.getButton(3));
            } else if ( e.getSource() == loopButton ) {
               int n = (Integer) jSpinner1.getValue();
               buttons.setLoopContent(n);
               codeBlocks.addButton(buttons.getButton(0));
               buttons.getButton(0).setLoopCount(n);
               drawButtons(buttons.getButton(0));
            } else if ( e.getSource() == endLoopButton ) {
               codeBlocks.addButton(buttons.getButton(1));
               drawButtons(buttons.getButton(1));
            } else if ( e.getSource() == turnButton ) {
               codeBlocks.addButton(buttons.getButton(4));
               drawButtons(buttons.getButton(4));
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
      if ( button.getButtonName() == "square" || button.getButtonName() == "move" || button.getButtonName() == "draw" ) {
         int x = 0;
         if ( codeBlocks.isIndented ) {
            x = 30;
         } else {
            x = 10;
         }

         jPanel2.getGraphics().drawString(button.getButtonName(), x + 2, (codeBlocks.getAdded().size() - 1) * 40 + 20);
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

      add(d_gamePanel);

      hintButton = new javax.swing.JButton();
      beginButton = new javax.swing.JButton();
      realCodeButton = new javax.swing.JButton();
      clearButton = new javax.swing.JButton();


      loopButton = new javax.swing.JButton();
      turnButton = new javax.swing.JButton();
      endLoopButton = new javax.swing.JButton();

      draw = new javax.swing.JButton();
      square = new javax.swing.JButton();
      move = new javax.swing.JButton();
      leftB = new javax.swing.JButton();

      jPanel2 = new javax.swing.JPanel();

      jPanel1 = new javax.swing.JPanel();
      jTextArea = new javax.swing.JTextArea();
      jScrollPane1 = new javax.swing.JScrollPane();

      SpinnerNumberModel model = new SpinnerNumberModel(1, 1, 10, 1);

      jLabel1 = new javax.swing.JLabel();
      jSpinner1 = new javax.swing.JSpinner(model);
      jSeparator1 = new javax.swing.JSeparator();
      jspinner2  = new javax.swing.JSpinner();
      jspinner3  = new javax.swing.JSpinner();
      jspinner4  = new javax.swing.JSpinner();
      jspinner4.setLocation(500,500);

      jspinner2.setModel( new javax.swing.SpinnerNumberModel(0, 0 , null, 10));
      jspinner3.setModel( new javax.swing.SpinnerNumberModel(0, 0 , null, 10));
      jspinner4.setModel( new javax.swing.SpinnerNumberModel(0, 0 , null, 10));


      add(jspinner2);
      add(jspinner3);
      add(jspinner4);


      hintButton.addActionListener(buttonListener);
      beginButton.addActionListener(buttonListener);
      realCodeButton.addActionListener(buttonListener);
      clearButton.addActionListener(buttonListener);
      loopButton.addActionListener(buttonListener);

      turnButton.addActionListener(buttonListener);
      endLoopButton.addActionListener(buttonListener);

      draw.addActionListener(buttonListener);
      square.addActionListener(buttonListener);
      move.addActionListener(buttonListener);
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


      square.setText("square");
      move.setText("move");
      draw.setText("draw");

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


      turnButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/testpackage/turn.png"))); // NOI18N
      turnButton.setBorderPainted(false);
      turnButton.setContentAreaFilled(false);



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

                                                            //.addComponent(square, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)

                                                            .addGroup(layout.createSequentialGroup()

                                                                  .addComponent(square, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)

                                                                  .addComponent(jspinner3, javax.swing.GroupLayout.DEFAULT_SIZE, 37, 50)

                                                            )

                                                      )

                                                      .addGroup(layout.createSequentialGroup()

                                                            .addGap(59, 59, 59)

                                                            //.addComponent(move, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))

                                                            .addGroup(layout.createSequentialGroup()

                                                                  .addComponent(move, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)

                                                                  .addComponent(jspinner4, javax.swing.GroupLayout.DEFAULT_SIZE, 37, 50)

                                                            ))

                                                      .addGroup(layout.createSequentialGroup()

                                                            .addComponent(leftB, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)

                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)

                                                            .addGroup(layout.createSequentialGroup()

                                                                  .addComponent(draw, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)

                                                                  .addComponent(jspinner2, GroupLayout.PREFERRED_SIZE, 37,50)

                                                            )

                                                            .addGap(51, 51, 51)

                                                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))

                                                .addGap(49, 49, 49)

                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

                                                      .addComponent(endLoopButton)

                                                      .addComponent(turnButton))))

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

                        // .addComponent(square, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)

                        .addGroup(layout.createSequentialGroup()

                              .addGroup(layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE)

                                    .addComponent(square, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)

                                    .addComponent(jspinner3, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE

                                    )

                              )

                        )

                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)

                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

                              .addGroup(layout.createSequentialGroup()

                                    .addComponent(turnButton)

                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)

                                    .addComponent(endLoopButton)

                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)

                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))

                              .addGroup(layout.createSequentialGroup()

                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)

                                          .addComponent(leftB, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)

                                          .addGroup(layout.createSequentialGroup()

                                                .addGroup(layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE)

                                                      .addComponent(draw, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)

                                                      .addComponent(jspinner2, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE

                                                      )

                                                )

                                          )

                                    )



                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)



                                    .addGroup(layout.createSequentialGroup()

                                          .addGroup(layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE)

                                                .addComponent(move, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)

                                                .addComponent(jspinner4, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE

                                                )

                                          )

                                    ))

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




//
//      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
//      this.setLayout(layout);
//      layout.setHorizontalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                  .addGroup(layout.createSequentialGroup()
//                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                              .addGroup(layout.createSequentialGroup()
//                                    .addContainerGap()
//                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                                          .addGroup(layout.createSequentialGroup()
//                                                .addGap(10, 10, 10)
//                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                                                      .addGroup(layout.createSequentialGroup()
//                                                            .addGap(58, 58, 58)
//                                                            .addComponent(square, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
//                                                      .addGroup(layout.createSequentialGroup()
//                                                            .addGap(59, 59, 59)
//                                                            .addComponent(move, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
//                                                      .addGroup(layout.createSequentialGroup()
//                                                            .addComponent(leftB, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                                                            .addGroup(layout.createSequentialGroup()
//                                                                  .addComponent(draw, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                                                  .addComponent(jspinner2, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE
//                                                                  ))
//                                                            .addGap(51, 51, 51)
//                                                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
//                                                .addGap(49, 49, 49)
//                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                                                      .addComponent(endLoopButton)
//                                                      .addComponent(turnButton))))
//                                    .addGap(0, 0, Short.MAX_VALUE))
//                              .addGroup(layout.createSequentialGroup()
//                                    .addGap(74, 313, Short.MAX_VALUE)
//                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)))
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
//                              .addGroup(layout.createSequentialGroup()
//                                    .addGap(199, 199, 199))
//                              .addGroup(layout.createSequentialGroup()
//                                    .addGap(18, 18, 18)
//                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                                          .addComponent(beginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                          .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
//                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 337, Short.MAX_VALUE)
//                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                                          .addComponent(realCodeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))))
//                        .addContainerGap())
//      );
//      layout.setVerticalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                  .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 259, Short.MAX_VALUE)
//                        .addComponent(square, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                              .addGroup(layout.createSequentialGroup()
//                                    .addComponent(turnButton)
//                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
//                                    .addComponent(endLoopButton)
//                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
//                              .addGroup(layout.createSequentialGroup()
//                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
//                                          .addComponent(leftB, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
//                                          .addGroup(layout.createSequentialGroup()
//                                                .addComponent(draw, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                                .addComponent(jspinner2, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE
//                                                )))
//                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                                    .addComponent(move, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
//                              .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
//                        .addContainerGap())
//                  .addGroup(layout.createSequentialGroup()
//                        .addContainerGap()
//                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                              .addGroup(layout.createSequentialGroup()
//                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                                          .addComponent(beginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                          .addComponent(realCodeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
//                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                                          .addGroup(layout.createSequentialGroup()
//                                                .addGap(18, 18, 18)
//                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
//                                          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
//                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                                                .addComponent(clearButton)
//                                                .addContainerGap())))
//                              .addComponent(jSeparator1)))
//      );
//
//
//      //LEVEL NUMARASINA GÖRE AYARLA!!!
//





      leftB.setVisible(false);

      if ( levelNum == 1 ) {
         draw.setEnabled(false);
         turnButton.setEnabled(false);
         jspinner2.setEnabled(false);
      }
      else
      {
         square.setEnabled(false);
         jspinner3.setEnabled(false);
         move.setEnabled(false);
         jspinner4.setEnabled(false);
      }


   }// </editor-fold>

//   private void beginButtonActionPerformed (java.awt.event.ActionEvent evt) {
//      level.start(codeBlocks.getAdded());
//   }


   // Variables declaration - do not modify
   private javax.swing.JButton move;
   private javax.swing.JButton leftB;
   private javax.swing.JButton draw;
   private javax.swing.JButton square;

   private javax.swing.JButton hintButton;
   private javax.swing.JButton turnButton;
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
   private javax.swing.JSpinner jspinner2;
   private javax.swing.JSpinner jspinner3;
   private javax.swing.JSpinner jspinner4;

   private javax.swing.JTextArea jTextArea;

   private javax.swing.JPanel jPanel2;
   // End of variables declaration
}