package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author omerozbekler
 * @version 10.05.2018
 * HintsPanel - This program
 */
public class HintsPanel extends MyJPanel {

   JButton turnBack;
   JButton lecture1;
   JButton lecture2;
   JButton lecture3;
   JButton lecture4;
   JButton lecture5;

   JLabel label;

   JTextArea area;

   private ButtonListener buttonListener;

   public HintsPanel () {
      setLayout(null);

      label = new JLabel();
      label.setFont(new Font("Serif", Font.BOLD, 25));
      label.setHorizontalAlignment(SwingConstants.CENTER);
      label.setBounds((int) (MyJFrame.screen_Width * 0.51), (int) (MyJFrame.screen_Height * 0.17),
            (int) (MyJFrame.screen_Width * 0.24), MyJFrame.screen_Height * 1 / 12);
      add(label);

      home.setEnabled(true);
      home.setVisible(true);

      buttonListener = new ButtonListener() {
         @Override
         public void actionPerformed (ActionEvent e) {

            if ( e.getSource() == turnBack )
               MyJPanel.changePanel(HintsPanel.this, new TextbookPanel());
            else if ( e.getSource() == lecture1 ) {
               label.setText("Fundamental Data Types");
               area.setText(readFile("fdt"));
            } else if ( e.getSource() == lecture2 ) {
               label.setText("Loops");
               area.setText(readFile("loops"));
            } else if ( e.getSource() == lecture3 ) {
               label.setText("Decisions");
               area.setText(readFile("dec"));
            } else if ( e.getSource() == lecture4 ) {
               label.setText("Array Lists");
               area.setText(readFile("arrays"));
            } else if ( e.getSource() == lecture5 ) {
               label.setText("Methods");
               area.setText(readFile("methods"));
            }
         }
      };

      turnBack = new MyJButton("", (int) (MyJFrame.screen_Width * 0.11), (int) (MyJFrame.screen_Height * 0.24));
      lecture1 = new MyJButton("Fundamental Data Types", (int) (MyJFrame.screen_Width * 0.35), (int) (MyJFrame.screen_Height * 0.30));
      lecture2 = new MyJButton("Loops", (int) (MyJFrame.screen_Width * 0.35), (int) (MyJFrame.screen_Height * 0.69));
      lecture3 = new MyJButton("Decisions", (int) (MyJFrame.screen_Width * 0.35), (int) (MyJFrame.screen_Height * 0.56));
      lecture4 = new MyJButton("Array Lists", (int) (MyJFrame.screen_Width * 0.35), (int) (MyJFrame.screen_Height * 0.82));
      lecture5 = new MyJButton("Methods", (int) (MyJFrame.screen_Width * 0.35), (int) (MyJFrame.screen_Height * 0.43));

      turnBack.setSize((int) (MyJFrame.screen_Width * 0.07), MyJFrame.screen_Height * 1 / 9);
      lecture1.setSize((int) (MyJFrame.screen_Width * 0.24), MyJFrame.screen_Height * 1 / 12);
      lecture2.setSize((int) (MyJFrame.screen_Width * 0.24), MyJFrame.screen_Height * 1 / 12);
      lecture3.setSize((int) (MyJFrame.screen_Width * 0.24), MyJFrame.screen_Height * 1 / 12);
      lecture4.setSize((int) (MyJFrame.screen_Width * 0.24), MyJFrame.screen_Height * 1 / 12);
      lecture5.setSize((int) (MyJFrame.screen_Width * 0.24), MyJFrame.screen_Height * 1 / 12);

      turnBack.addActionListener(buttonListener);
      lecture1.addActionListener(buttonListener);
      lecture2.addActionListener(buttonListener);
      lecture3.addActionListener(buttonListener);
      lecture4.addActionListener(buttonListener);
      lecture5.addActionListener(buttonListener);

      turnBack.setOpaque(false);
      turnBack.setContentAreaFilled(false);
      turnBack.setBorderPainted(false);

      lecture1.setContentAreaFilled(false);
      lecture2.setContentAreaFilled(false);
      lecture3.setContentAreaFilled(false);
      lecture4.setContentAreaFilled(false);
      lecture5.setContentAreaFilled(false);

      area = new JTextArea(20, 20);
      area.setBounds((int) (MyJFrame.screen_Width * 0.53), (int) (MyJFrame.screen_Height * 0.28),
            (int) (MyJFrame.screen_Width * 0.21), (int) (MyJFrame.screen_Height * 0.55));
      area.setLineWrap(true);
      area.setOpaque(false);
      area.setEditable(false);
      area.setFont(new Font("Serif", Font.BOLD, 18));
      add(area);

      add(turnBack);
      add(lecture1);
      add(lecture2);
      add(lecture3);
      add(lecture4);
      add(lecture5);

   }

   @Override
   protected void paintComponent (Graphics g) {
      super.paintComponent(g);
      g.drawImage(MyJFrame.hintsImage, 0, 0, MyJPanel.imageObserver);
   }

   String readFile (String fileName) {
      if ( fileName == "fdt" )
         return "By convention, variable names \nshould start with a lowercase letter." + "\n" +
               "The assignment operator = does not denote mathematical equality." + "\n" +
               "You cannot change the value of a \nvariable that is defined as final." + "\n" +
               "Use the Scanner class to read \nkeyboard input in a console window." + "\n" +
               "String positions are counted starting\nwith 0." + "\n" +
               "Use the substring method to extract a part of a string.";
      else if ( fileName == "loops" )
         return "A loop executes instructions \nrepeatedly while a condition is true." + "\n" +
               "The for loop is used when a value \nruns from a starting point to an \nending " +
               "point with a constant \nincrement or decrement. " + "\n" +
               "You can use a Boolean variable to \ncontrol a loop." + "\n" +
               "To compute an average, keep a total\nand a count of all values." + "\n" +
               "If your goal is to find a match, exit \nthe loop when the match is found.";
      else if ( fileName == "dec" )
         return "The if statement allows a program\nto carry out different actions \ndepending on the situation.\n" +
               "Use relational operators (< <= > >= == !=) to compare numbers.\n" +
               "Do not use the == operator to \ncompare strings. Use the equals \nmethod instead.\n" +
               "Multiple if statements can be \ncombined to evaluate complex \ndecisions.\n" +
               "It is a good idea to design test cases before implementing a program.\n" +
               "To invert a condition, use the ! (not)\noperator.";
      else if ( fileName == "methods" )
         return "A method is a named sequence of \ninstructions.\n" +
               "Arguments are supplied when a \nmethod is called.\n" +
               "The return value is the result that \nthe method computes.\n" +
               "Use a return type of void to indicate that a method does not return a \nvalue.\n" +
               "A method may require simpler \nmethods to carry out its work.\n" +
               "A recursive computation solves a \nproblem by using the solution of the\nsame " +
               "problem with simpler inputs.";
      else if ( fileName == "arrays" )
         return "An array collects a sequence of \nvalues of the same type.\n" +
               "Use the expression array.length to \nfind the number of elements in an \narray.\n" +
               "An array index must be at least zero and less than the size of the array.\n" +
               "You can use the enhanced for loop \nto visit all elements of an array.\n" +
               "An array list stores a sequence of \nvalues whose size can change.\n" +
               "Use the get and set methods to \naccess an array list element " +
               "at a \ngiven index.";
      else return null;
   }
}
