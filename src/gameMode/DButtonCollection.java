package gameMode;

/**
 * @author omerozbekler
 * @version 13.05.2018
 * DButtonCollection - This program
 */
public class DButtonCollection {

   // properties
   private Buton loop;
   private Buton endLoop;
   private Buton square;
   private Buton move;
   private Buton turn;
   private Buton draw;
   Buton[] buttons;
   int loopContent;

   // constructor
   public DButtonCollection(){
      setCollection();
      this.loopContent = -4;
   }

   // methods
   public int length(){
      return buttons.length;
   }

   public Buton getButton( int i ) {
      return buttons[i];
   }

   public void setCollection(){
      int count = 5; // Get it from GUI
      loop = new Buton( "loop", count); // 0
      loop.setCodePiece("for (int i = 0 ; i <= " + this.loopContent + " ; i++) {");
      loop.setClickable(true);

      endLoop = new Buton("endLoop"); // 1
      endLoop.setCodePiece( "}");
      endLoop.setClickable(true);

      int size = 100; // Get it from GUI
      square = new Buton( "square", size); // 2
      square.setCodePiece( "drawSquare(" + size + ");");

      int length = 50; // Get it from GUI
      move = new Buton( "move", length); // 3
      move.setCodePiece( "move(" + length + ");");

      turn = new Buton( "turn"); // 4
      turn.setCodePiece( "turn();");

      int side = 100; // Get it from GUI
      draw = new Buton( "draw", side); // 5
      draw.setCodePiece( "drawLine(" + side + ");");

      buttons = new Buton[]{loop, endLoop, square, move, turn, draw};
   }

   public int getLoopContent () {
      return loopContent;
   }

   public void setLoopContent (int lp) {
      this.loopContent = lp;
      loop.setCodePiece("for (int i = 0 ; i <= " + this.loopContent + " ; i++) {");
   }


}
