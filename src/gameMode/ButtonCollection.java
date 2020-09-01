package gameMode;

/**
 * This class is to create a button collection.
 * @author softAware
 */

public class ButtonCollection
{
   Buton[] buttons;
   int loopContent;

   Buton loop;

   public ButtonCollection()
   {
      this.buttons = new Buton[10];
      this.buttons = setButtons();
      loopContent = -1;
   }

   public Buton[] setButtons()
   {
      Buton menu  = new Buton( "Main Menu"); // 0
      Buton clear = new Buton( "Clear All"); // 1
      Buton start = new Buton( "Start"); // 2

      Buton right = new Buton("right"); // 3
      right.setCodePiece("moveRight()");

      Buton left = new Buton("left"); // 4
      left.setCodePiece("moveLeft()");

      Buton up = new Buton("up"); // 5
      up.setCodePiece("moveUp()");

      Buton down = new Buton("down"); // 6
      down.setCodePiece("moveDown()");

      loop = new Buton( "loop" , this.loopContent); // 7  // GET THE LOOPCOUNT FROM GUI PART!!
      loop.setCodePiece("for (int i = 0 ; i <= " + this.loopContent + " ; i++) {");


      Buton endLoop = new Buton( "endLoop"); // 8
      endLoop.setCodePiece("}");

      Buton pick = new Buton( "pick"); // 9
      pick.setCodePiece("pick();");

      buttons[0] = menu;
      buttons[1] = clear;
      buttons[2] = start;
      buttons[3] = right;
      buttons[4] = left;
      buttons[5] = up;
      buttons[6] = down;
      buttons[7] = loop;
      buttons[8] = endLoop;
      buttons[9] = pick;

      return buttons;
   }

   public int getLoopContent () {
      return loopContent;
   }

   public void setLoopContent (int lp) {
      this.loopContent = lp;
      loop.setCodePiece("for (int i = 0 ; i <= " + this.loopContent + " ; i++) {");
   }

   public Buton getButton(int index)
   {
      return buttons[index];
   }
}












