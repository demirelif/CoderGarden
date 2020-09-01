package com;

import gameMode.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author omerozbekler
 * @version 13.05.2018
 * DrawingPanel - This program
 */
public class DrawingPanel extends MyJPanel{
   // properties
   private Image backgroundImage;

   //icons
   private ImageIcon homeIcon;
   private ImageIcon trashIcon;
   private ImageIcon hintIcon;

   //buttons
   protected JButton begin;
   protected JButton seeCode;
   protected JButton toMain; //to main menu button
   protected JButton trash;
   protected JButton hint;

   //game buttons
   protected JButton move;
   protected JButton square;
   protected JButton draw;
   protected JButton startLoop;
   protected JButton endLoop;
   protected JButton turn; //rotate left

   //spinners
   public JSpinner jspinner1;
   public JSpinner jspinner2;
   public JSpinner jspinner3;
   public JSpinner jspinner4;

   //textarea
   public JTextArea jTextArea;

   //button listener
   ButtonListener buttonListener;

   //label
   protected JLabel explanation; //explanation of the game

   //game panel
   protected D_GamePanel game;

   boolean state;

   //modelling
   int levelNum;
   DButtonCollection buttons;
   DLevel level;
   CodeBlocks codeBlocks;
   Pencil pencil;

   // constructor
   public DrawingPanel( final int levelNum) {
      //initializing model objects
      this.levelNum = levelNum;
      level = new DLevel( levelNum );
      //codeBlocks = level.getCodeBlocks();
      pencil = level.getPencil();
      buttons = level.getCollection();
      state = false;


      //creating images
      backgroundImage = new ImageIcon(this.getClass().getResource("/images/background.jpg")).getImage();
      //homeIcon        = new ImageIcon(this.getClass().getResource("home.png"));
      trashIcon        = new ImageIcon(this.getClass().getResource("/testpackage/oie_1O5Yl6I6QD5V.png"));
      hintIcon        = new ImageIcon(this.getClass().getResource("/testpackage/bugi.png"));

      //setting size of the icons
      Image img = hintIcon.getImage();
      Image newimg = img.getScaledInstance(195, 195,  Image.SCALE_SMOOTH);
      hintIcon = new ImageIcon(newimg);

      img = trashIcon.getImage();
      newimg = img.getScaledInstance(60, 75,  Image.SCALE_SMOOTH);
      trashIcon = new ImageIcon(newimg);


      //initializing button listener
      buttonListener = new ButtonListener();

      //setting label
      explanation = new JLabel( level.getExplanation() );
      // create a line border with the specified color and width
      Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
      explanation.setBorder(border);
      explanation.setSize( new Dimension (523, 66));
      explanation.setFont( new Font(Font.DIALOG, Font.TYPE1_FONT, 12));
      explanation.setLocation( 100, ( MyJFrame.screen_Height / 6 ) * 5);


      //setting buttons
//      toMain = new DButton( "MAIN MENU", homeIcon, 5, 30 );
//      toMain.addActionListener( buttonListener );
//      toMain.setLocation(
      begin = new DButonGUI( "BEGIN", 1, 40);
      begin.setBackground(new Color(0, 0, 0, 255));
      begin.addActionListener( buttonListener );
      begin.setLocation( MyJFrame.screen_Width / 2 + 50, MyJFrame.screen_Height / 14);
      seeCode = new DButonGUI( "SEE REAL CODE", 2, 40 );
      seeCode.addActionListener( buttonListener );
      seeCode.setLocation( MyJFrame.screen_Width - 20, MyJFrame.screen_Height / 14);
      trash = new DButonGUI( "CLEAR PSEUDOCODE", trashIcon, 5, 0 );
      trash.addActionListener( buttonListener );
      trash.setLocation( MyJFrame.screen_Width / 2 + 100, ( MyJFrame.screen_Height / 6 ) * 5);
      hint = new DButonGUI( "", hintIcon, 4, 0 );
      hint.addActionListener( buttonListener );
      hint.setLocation( MyJFrame.screen_Width - 100, ( MyJFrame.screen_Height / 6 ) * 5);

      //game buttons
      move = new DButonGUI( "Move", 6, 0);
      move.setPreferredSize(new Dimension(82, 50));
      move.addActionListener( buttonListener );
      square = new DButonGUI( "Square", 6, 0);
      square.setPreferredSize(new Dimension(82, 50));
      square.addActionListener( buttonListener );
      startLoop = new DButonGUI( "", 6, 0);
      startLoop.setPreferredSize(new Dimension(128, 50));
      startLoop.addActionListener( buttonListener );
      endLoop = new DButonGUI( "End Loop", 6, 0);
      endLoop.setPreferredSize(new Dimension(128, 50));
      endLoop.addActionListener( buttonListener );
      turn = new DButonGUI( "", 6, 0);
      turn.setPreferredSize(new Dimension(128, 50));
      turn.addActionListener( buttonListener );

      draw = new DButonGUI( "", 6, 0);
      draw.setPreferredSize(new Dimension(128, 50));
      draw.addActionListener( buttonListener );

      //setting spinners
      jspinner1  = new JSpinner();
      jspinner2  = new JSpinner();
      jspinner3  = new JSpinner();
      jspinner4  = new JSpinner();
      jspinner1.setModel( new javax.swing.SpinnerNumberModel(0, 0 , null, 10));
      jspinner2.setModel( new javax.swing.SpinnerNumberModel(0, 0 , null, 10));
      jspinner3.setModel( new javax.swing.SpinnerNumberModel(0, 0 , null, 10));
      jspinner4.setModel( new javax.swing.SpinnerNumberModel(1, 1 , 10, 1));

      //creating game panel
      game = new D_GamePanel(levelNum);
      game.setLocation(50, 150);

      //setting separator
//      separator = new JSeparator( (int) (DFrame.screen_Width / 2 - 0.5), (int) (DFrame.screen_Height / 2 - 0.5),
//                                (int) (DFrame.screen_Width / 2 + 0.5), (int)( DFrame.screen_Height / 2 + 0.5 ));
//      separator.setBackground(new java.awt.Color(0, 0, 0));
//      separator.setForeground(new java.awt.Color(0, 0, 0));
//      separator.setOrientation(SwingConstants.VERTICAL);
//      separator.setLocation( DFrame.screen_Width / 2, DFrame.screen_Height / 2);
//

      //add( toMain );
      add( begin );
      add( seeCode );
      add( trash );
      add( hint );
      add( explanation );
      add( game );
      add( startLoop);
      add( square );

      add( endLoop);
      add( move );
      add( turn );
      add( draw );

      trash.setOpaque(false);

      begin.setLocation    ( (int) (MyJFrame.screen_Width * 0.82), (int) (MyJFrame.screen_Height * 0.07));
      seeCode.setLocation  ( (int) (MyJFrame.screen_Width * 0.82), (int) (MyJFrame.screen_Height * 0.07));
      trash.setLocation    ( (int) (MyJFrame.screen_Width * 0.82), (int) (MyJFrame.screen_Height * 0.07));
      hint.setLocation     ( (int) (MyJFrame.screen_Width * 0.86), (int) (MyJFrame.screen_Height * 0.77));

      setLayout( null );

   }

   // methods
   @Override
   protected void paintComponent (Graphics g) {
      super.paintComponent(g);
      g.drawImage(backgroundImage, 0,0, null);
      g.drawLine( (int) (MyJFrame.screen_Width / 2 - 4), 0,
            (int) (MyJFrame.screen_Width / 2 + 4), (int)( MyJFrame.screen_Height) );
   }

   protected static void changePanel (JPanel currentPanel, JPanel newPanel) {
      JFrame tmp;
      tmp = (JFrame) SwingUtilities.getWindowAncestor( currentPanel );
      tmp.add( newPanel );
      tmp.remove( currentPanel );
      tmp.setVisible(true);
   }

   //inner class
   protected class ButtonListener implements ActionListener {
      public JPanel panel = DrawingPanel.this;;

      @Override
      public void actionPerformed (ActionEvent e) {
//         if ( e.getSource() == hint ) {
//               if ( !(state) ) {
//                  getGraphics().drawImage(new ImageIcon(this.getClass().getResource(level.getHint(levelNum))).getImage(), (int)(MyJFrame.screen_Width* 0.72), (int)(MyJFrame.screen_Height* 0.77), 179, 177, MyJPanel.imageObserver);
//                  state = true;
//               }
//               else {
//                  repaint();
//                  state = false;
//               }
//            }
         if ( e.getSource() == begin ) {
            level.start(codeBlocks.getAdded());
         } else if ( e.getSource() == seeCode ) {
            if( seeCode.getText().equals("SEE REAL CODE"))
            {
               seeCode.setText("HIDE REAL CODE");
               codeBlocks.getRealCode(codeBlocks.getAdded());
               jTextArea.setText(codeBlocks.toString());
            }
            else
            {
               seeCode.setText("SEE REAL CODE");
               jTextArea.setText("");
               jTextArea.setOpaque(false);
            }
         } else if ( e.getSource() == trash ) {
            codeBlocks.clear();
         } else if ( e.getSource() == startLoop ) {
            int n = (Integer)(jspinner1.getValue());
            Buton button = new Buton("loop" , n);
            codeBlocks.addButton(button);
         } else if ( e.getSource() == draw ) {
            codeBlocks.addButton(buttons.getButton(8));
         } else if ( e.getSource() == endLoop ) {
            codeBlocks.addButton(buttons.getButton(7));
         } else if ( e.getSource() == square ) {
            codeBlocks.addButton(buttons.getButton(5));
         } else if ( e.getSource() == move ) {
            codeBlocks.addButton(buttons.getButton(1));
         } else if ( e.getSource() == turn ) {
            codeBlocks.addButton(buttons.getButton(3));
         }



      }
   }

}
