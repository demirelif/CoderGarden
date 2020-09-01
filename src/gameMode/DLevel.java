package gameMode;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author omerozbekler
 * @version 13.05.2018
 * DLevel - This program
 */
public class DLevel {
   // properties
   private int levelNum;
   private String explanation;
   private Image map;
   private Pencil pencil;
   private DButtonCollection collection;
   ArrayList<Integer> squareX = new ArrayList<>();
   ArrayList<Integer> pencilMoveX = new ArrayList<>();
   ArrayList<Integer> pencilMoveY = new ArrayList<>();
   private boolean over;
   int x;
   int y;
   final int firstX = 100;
   final int firstY = 100;
   boolean turn;
   CodeBlocks codeBlocks;
   ArrayList<Integer> lineX = new ArrayList<>();
   ArrayList<Integer> lineY = new ArrayList<>();
   int length;

   // constructor
   public DLevel( int levelNum){
      this.levelNum = levelNum;
      collection = new DButtonCollection();
      setExplanation( levelNum);
      setClickable( levelNum);
      pencil = new Pencil(levelNum);
      over = false;
      turn = true;
      this.x = firstX;
      this.y = firstY;
      pencil.setX(100); // GUI
      pencil.setY(100);
      codeBlocks = new CodeBlocks();
   }
   // methods

   public void setSquare(){
      squareX.add( pencil.getX() );
   }

   public String getHint (int levelNum) {
      return "/hint" + levelNum + ".png ";
   }

   public CodeBlocks getCodeBlocks () {
      return codeBlocks;
   }

   public ArrayList<Integer> getSquareX(){
      return squareX;
   }
   public int getSquareY(){
      return firstY;
   }
   public void setPencilLocations(){
      pencilMoveX.add(pencil.getX());
      pencilMoveY.add(pencil.getY());
   }


   public ArrayList<Integer> getPencilX(){
      return pencilMoveX;
   }
   public ArrayList<Integer> getPencilY(){
      return pencilMoveY;
   }

   public ArrayList<Integer> getLineX(){
      return lineX;
   }
   public ArrayList<Integer> getLineY(){
      return lineY;
   }
   public void setLineLocations(){
      lineX.add(x);
      lineY.add(y);
   }

   public boolean lastTurn(){ // x ise false y ise true (siradaki, dondurulecek olan)
      return turn;
   }

   public void turnX(){
      turn = true;
   }

   public void turnY(){
      turn = false;
   }

   public Pencil getPencil(){
      return pencil;
   }

   public DButtonCollection getCollection(){
      return collection;
   }


   public void setMapImage(){
      String mapImageName = "levelMap" + levelNum;
      map = Toolkit.getDefaultToolkit().getImage( mapImageName);
   }



   public void setClickable( int levelNum){
      if ( levelNum == 1){
         (collection.buttons[2]).setClickable(true);
         (collection.buttons[3]).setClickable(true);
      }

      if ( levelNum == 2){
         (collection.buttons[4]).setClickable(true);
         (collection.buttons[5]).setClickable(true);
      }
   }

   public void setExplanation( int levelNum){
      if ( levelNum == 1){
         explanation = "Draw the given image by using draw square method";
      }
      if ( levelNum == 2){
         explanation = "Implement the draw square method";
      }
   }

   public String getExplanation(){
      return explanation;
   }

   public boolean isGameOver(){
      return over;
   }
   public void gameOver( int number){
      if ( number < 0 ) {
         over = true;
         for ( int i = 0; i < collection.length(); i++){
            collection.buttons[i].setClickable(false);
         }
      }
      else {
         Level newLevel = new Level(number);
      }
   }

   public void start( ArrayList<Buton> added){

      for ( int i = 0; i < added.size(); i++)
      {
         if ( ( added.get(i) ).getButtonName() == "square"){
            setSquare();
            pencil.setX( pencil.getX() + ( added.get(i) ).getLoopCount() );
            setPencilLocations();
         }

         if ( ( added.get(i) ).getButtonName() == "move"){
            int move = (added.get(i)).getLoopCount();
            pencil.setX( pencil.getX() + move);
            setPencilLocations();
         }


         if ( (added.get(i) ).getButtonName() == "draw")
         {
            System.out.println("DRAW");
            setLineLocations();
            pencil.setX(x);
            pencil.setY(y);
         }

         if ( (added.get(i) ).getButtonName() == "turn")
         {
            // int length = (added.get(i)).number;
            int length = 100;

            System.out.println("TURN and l " + length);
            if ( turn ) // x degisir
            {
               if ( x == (length + firstX) )
               {
                  x = x - length;
               }
               else {
                  x = x + length;
               }
               turnY();
            }
            else if ( !turn ) // y degisir
            {
               if ( y == (length + firstY) )
               {
                  y = y - length;
               }
               else {

                  y = y + length;
               }
               turnX();
            }
         }

         if ( ( added.get(i) ).getButtonName() == "loop")
         {
            int count = ( added.get(i) ).getLoopCount();
            ArrayList<Buton> insideOfLoop = new ArrayList<Buton>();
            for ( int j = i + 1 ; j < added.size() && (( added.get(i) ).getButtonName()) != "endLoop" ; j++)
               insideOfLoop.add( added.get(j) );
            for ( int k = 0 ; k < count-1 ; k++)
               start( insideOfLoop);  //oha
         }

      }
   }
}
