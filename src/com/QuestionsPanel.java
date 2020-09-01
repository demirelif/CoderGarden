package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author omerozbekler
 * @version 10.05.2018
 * QuestionsPanel - This program
 */
public class QuestionsPanel extends MyJPanel {

   JButton turnBack;
   JButton solution;
   JButton question1;
   JButton question2;
   JButton question3;
   JButton question4;
   JButton question5;
   JButton question6;

   JLabel label;

   JTextArea questionArea;
   JTextArea solutionArea;
   JTextArea answerArea;

   boolean solutionState;

   private ButtonListener buttonListener;

   public QuestionsPanel () {
      setLayout(null);

      label = new JLabel();
      label.setText("QUESTION 1");
      label.setFont(new Font("Serif", Font.BOLD, 25));
      label.setHorizontalAlignment(SwingConstants.CENTER);
      label.setBounds((int) (MyJFrame.screen_Width * 0.25), (int) (MyJFrame.screen_Height * 0.23),
            (int) (MyJFrame.screen_Width * 0.24), MyJFrame.screen_Height * 1 / 12);
      add(label);

      solutionState = false;

      home.setEnabled(true);
      home.setVisible(true);

      buttonListener = new ButtonListener() {
         @Override
         public void actionPerformed (ActionEvent e) {

            if ( e.getSource() == turnBack )
               MyJPanel.changePanel(QuestionsPanel.this, new TextbookPanel());
            else if ( e.getSource() == question1 ) {
               label.setText("QUESTION 1");
               questionArea.setText(readFile("Q1"));
               solutionArea.setText(readFile("S1"));
               answerArea.setText(readFile("A1"));
            } else if ( e.getSource() == question2 ) {
               label.setText("QUESTION 2");
               questionArea.setText(readFile("Q2"));
               solutionArea.setText(readFile("S2"));
               answerArea.setText(readFile("A2"));
            } else if ( e.getSource() == question3 ) {
               label.setText("QUESTION 3");
               questionArea.setText(readFile("Q3"));
               solutionArea.setText(readFile("S3"));
               answerArea.setText(readFile("A3"));
            } else if ( e.getSource() == question4 ) {
               label.setText("QUESTION 4");
               questionArea.setText(readFile("Q4"));
               solutionArea.setText(readFile("S4"));
               answerArea.setText(readFile("A4"));
            } else if ( e.getSource() == question5 ) {
               label.setText("QUESTION 5");
               questionArea.setText(readFile("Q5"));
               solutionArea.setText(readFile("S5"));
               answerArea.setText(readFile("A5"));
            } else if ( e.getSource() == question6 ) {
               label.setText("QUESTION 6");
               questionArea.setText(readFile("Q6"));
               solutionArea.setText(readFile("S6"));
               answerArea.setText(readFile("A6"));
            } else if ( e.getSource() == solution ) {
               if ( !(solutionState) ) {
                  solutionArea.setVisible(true);
                  solution.setText("HIDE SOLUTION");
               } else {
                  solutionArea.setVisible(false);
                  solution.setText("SHOW SOLUTION");
               }
               solutionState = !solutionState;
            }
         }
      };

      turnBack  = new MyJButton("", (int) (MyJFrame.screen_Width * 0.11), (int) (MyJFrame.screen_Height * 0.24));
      question1 = new MyJButton("", (int) (MyJFrame.screen_Width * 0.35), (int) (MyJFrame.screen_Height * 0.89));
      question2 = new MyJButton("", (int) (MyJFrame.screen_Width * 0.395), (int) (MyJFrame.screen_Height * 0.89));
      question3 = new MyJButton("", (int) (MyJFrame.screen_Width * 0.435), (int) (MyJFrame.screen_Height * 0.89));
      question4 = new MyJButton("", (int) (MyJFrame.screen_Width * 0.475), (int) (MyJFrame.screen_Height * 0.89));
      question5 = new MyJButton("", (int) (MyJFrame.screen_Width * 0.515), (int) (MyJFrame.screen_Height * 0.89));
      question6 = new MyJButton("", (int) (MyJFrame.screen_Width * 0.555), (int) (MyJFrame.screen_Height * 0.89));
      solution  = new MyJButton("", (int) (MyJFrame.screen_Width * 0.675), (int) (MyJFrame.screen_Height * 0.415));


      turnBack.setSize((int) (MyJFrame.screen_Width * 0.07), MyJFrame.screen_Height * 1 / 9);
      question1.setSize((int) (MyJFrame.screen_Height * 0.04), (int) (MyJFrame.screen_Height * 0.04));
      question2.setSize((int) (MyJFrame.screen_Height * 0.04), (int) (MyJFrame.screen_Height * 0.04));
      question3.setSize((int) (MyJFrame.screen_Height * 0.04), (int) (MyJFrame.screen_Height * 0.04));
      question4.setSize((int) (MyJFrame.screen_Height * 0.04), (int) (MyJFrame.screen_Height * 0.04));
      question5.setSize((int) (MyJFrame.screen_Height * 0.04), (int) (MyJFrame.screen_Height * 0.04));
      question6.setSize((int) (MyJFrame.screen_Height * 0.04), (int) (MyJFrame.screen_Height * 0.04));
      solution.setSize((int) (MyJFrame.screen_Width * 0.10), (int) (MyJFrame.screen_Height * 0.06));

      turnBack.addActionListener(buttonListener);
      question1.addActionListener(buttonListener);
      question2.addActionListener(buttonListener);
      question3.addActionListener(buttonListener);
      question4.addActionListener(buttonListener);
      question5.addActionListener(buttonListener);
      question6.addActionListener(buttonListener);
      solution.addActionListener(buttonListener);

      turnBack.setOpaque(false);
      turnBack.setContentAreaFilled(false);
      turnBack.setBorderPainted(false);

      question1.setContentAreaFilled(false);
      question2.setContentAreaFilled(false);
      question3.setContentAreaFilled(false);
      question4.setContentAreaFilled(false);
      question5.setContentAreaFilled(false);
      question6.setContentAreaFilled(false);
      solution.setContentAreaFilled(false);


      questionArea = new JTextArea(20, 20);
      questionArea.setBounds((int) (MyJFrame.screen_Width * 0.25), (int) (MyJFrame.screen_Height * 0.29),
            (int) (MyJFrame.screen_Width * 0.23), (int) (MyJFrame.screen_Height * 0.08));
      questionArea.setLineWrap(true);
      questionArea.setOpaque(false);
      questionArea.setEditable(false);
      questionArea.setFont(new Font("Serif", Font.PLAIN, 14));
      add(questionArea);

      solutionArea = new JTextArea(20, 20);
      solutionArea.setBounds((int) (MyJFrame.screen_Width * 0.52), (int) (MyJFrame.screen_Height * 0.47),
            (int) (MyJFrame.screen_Width * 0.23), (int) (MyJFrame.screen_Height * 0.35));
      solutionArea.setLineWrap(true);
      solutionArea.setOpaque(false);
      solutionArea.setEditable(false);
      solutionArea.setVisible(false);
      solutionArea.setFont(new Font("Serif", Font.PLAIN, 14));
      add(solutionArea);

      answerArea = new JTextArea(20, 20);
      answerArea.setBounds((int) (MyJFrame.screen_Width * 0.25), (int) (MyJFrame.screen_Height * 0.40),
            (int) (MyJFrame.screen_Width * 0.23), (int) (MyJFrame.screen_Height * 0.40));
      answerArea.setLineWrap(true);
      answerArea.setOpaque(false);
      answerArea.setEditable(true);
      answerArea.setFont(new Font("Serif", Font.PLAIN, 14));
      add(answerArea);

      questionArea.setText(readFile("Q1"));
      solutionArea.setText(readFile("S1"));
      answerArea.setText(readFile("A1"));

      solution.setText("SHOW SOLUTION");
      solution.setFont(new Font("Serif", Font.BOLD, 10));

      add(turnBack);
      add(question1);
      add(question2);
      add(question3);
      add(question4);
      add(question5);
      add(question6);
      add(solution);
   }

   @Override
   protected void paintComponent (Graphics g) {
      super.paintComponent(g);
      g.drawImage(MyJFrame.questionsImage, 0, 0, MyJPanel.imageObserver);
   }

   public String readFile (String str)
   {
      if (str == "Q1")
      {
         return "Print squares of the numbers smaller than n.";
      }
      if (str == "Q2")
      {
         return "Convert decimal number into a binary form.";
      }
      if (str == "Q3")
      {
         return "Print right triangle with given size n.";
      }
      if (str == "Q4")
      {
         return "Print if given number n is visible to 3, 5 or both.";
      }
      if (str == "Q5")
      {
         return "Return n! by using iterative method.";
      }
      if (str == "Q6")
      {
         return "Return n! by using recursive method.";
      }


      if (str == "S1")
      {
         return "public void squareNums (int number){ \n   " +
               "int a = 0; \n   " +
               "while (a < 5) \n   " +
               "      System.out.println(Math.pow( a, 2));\n}";
      }

      if (str == "S2")
      {
         return "public static void getBinary(int decimalForm) { \n" +
               "   if(decimalForm > 0) { \n" +
               "      getBinary(decimalForm / 2); \n" +
               "      System.out.print(decimalForm % 2 + \"\");\n   }\n}";
      }

      if (str == "S3")
      {
         return "public static void triangle( int size , String str){ \n" +
               "   for (int i = 0 ; i < size ; i++){\n" +
               "      for (int j = i ; j < size ; j++){\n" +
               "         System.out.print(str);\n      }\n" +
               "      System.out.println(\"\")\n   }\n};";
      }

      if (str == "S4")
      {
         return "public static void divisible(int x){\n" +
               "   if ( x % 3 == 0 && x % 5 == 0)\n" +
               "      System.out.println( x + \" is divisible to 15\");\n" +
            "   else if (x % 5 == 0)\n" +
            "      System.out.println( x + \" is divisible to 5\");\n" +
            "   else\n" +
            "      System.out.println( x + \" is divisible to 3\");\n}";
      }

      if (str == "S5")
      {
         return "public int factorialI( int x){\n" +
               "   int number;\n" +
               "   Int product;\n" +
               "   while ( x > 0 ){\n" +
               "      number = x;\n" +
               "     X--;\n" +
               "     Product = number*x;\n" +
               "   }\n" +
               "}";
      }

      if (str == "S6")
      {
         return "public int factorialR (int number){\n" +
               "   while (number > 0)\n" +
               "      return number * factorialR(number - 1);\n" +
               "}";
      }

      if (str == "A1")
      {
         return "public void squareNums (int number){ \n   ";
      }

      if (str == "A2")
      {
         return "public static void getBinary(int decimalForm){\n   ";
      }

      if (str == "A3")
      {
         return "public static void triangle( int size , String str){\n   ";
      }

      if (str == "A4")
      {
         return "public static void divisible(int x){\n   ";
      }

      if (str == "A5")
      {
         return "public int factorialI( int x){\n   ";
      }

      if (str == "A6")
      {
         return "public int factorialR (int number){\n   ";
      }
      return "ERROR";
   }
}
