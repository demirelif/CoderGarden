package gameMode;

import com.MyJPanel;

import javax.swing.*;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

/**
 * This class is for all cross the road game levels.
 *
 * @author softAware
 */
public class Level {

   // properties
   private int levelNum;
   private String explanation;
   private Image map;
   private Image hint;
   private String hintName;
   static ButtonCollection buttons;
   ItemBasket basket;
   int[][] mapGrid;
   Player character;
   CodeBlocks codeBlocks;
   Boolean gameOver;
   ArrayList<Integer> characterMoveX = new ArrayList<Integer>();
   ArrayList<Integer> characterMoveY = new ArrayList<Integer>();

   // constructor
   public Level (int number) {
      levelNum = number;
      buttons = new ButtonCollection();
      setMapImage();
      mapGrid = getMapGrid(levelNum);
      character = new Player(levelNum);
      codeBlocks = new CodeBlocks();
      basket = new ItemBasket();
      gameOver = false;
      setExplanation();
      addMovement(character.getX(), character.getY());
   }

   public ButtonCollection getButtons () {
      return buttons;
   }

   public ItemBasket getBasket () {
      return basket;
   }

   public Player getCharacter () {
      return character;
   }

   public CodeBlocks getCodeBlocks () {
      return codeBlocks;
   }

   // methods


   public void setMapImage () {
      String mapImageName = "/images/level" + levelNum + ".png";
      this.map = new ImageIcon(this.getClass().getResource(mapImageName)).getImage();
   }

   public int[][] getMapGrid (int levelNum) // -1 obstacle, 0 bos yol, +1 varis noktasi
   {
      if ( levelNum == 1 ) {
         return new int[][]{
               {-1, -1, -1, -1, -1, -1},
               {-1, -1, -1, -1, -1, -1},
               {0, 0, 0, 0, 0, 1},
               {-1, -1, -1, -1, -1, -1}
         };
      } else if ( levelNum == 2 ) {
         return new int[][]{
               {0, 0, 0, -1, -1, -1},
               {-1, -1, 0, 0, -1, -1},
               {-1, -1, -1, 0, 0, -1},
               {-1, -1, -1, -1, 0, 1}
         };
      } else if ( levelNum == 3 ) {
         return new int[][]{
               {0, 0, 0, 0, 0, 0},
               {-1, -1, -1, -1, -1, 0},
               {-1, -1, -1, -1, -1, 0},
               {-1, -1, -1, -1, -1, 0}
         };
      } else // bos?
      {
         return new int[][]{
               {0, 0, 0, 0, 0, 0},
               {0, 0, 0, 0, 0, 0},
               {0, 0, 0, 0, 0, 0},
               {0, 0, 0, 0, 0, 0}
         };
      }
   }

   public void setExplanation () {
      if ( levelNum == 1 ) {
         explanation = "Go to gas station before you ran out of fuel!";
      }
      if ( levelNum == 2 ) {
         explanation = "Go to the Death Star without crashing!";
      }
      if ( levelNum == 3 ) {
         explanation = "Get your cart to the cash point and make sure that you collect everyting on your list!";
      }
   }

   public String getExplanation () {
      return explanation;
   }

   public void setHint () {
      hintName = "hint" + levelNum;
   }

   public String getHint (int levelNum) {
      return "/images/hint" + levelNum + ".png ";
   }

   public void addMovement (int x, int y) {
      characterMoveX.add(x);
      characterMoveY.add(y);
   }

   public ArrayList<Integer> getMovementX () {
      return characterMoveX;
   }

   public ArrayList<Integer> getMovementY () {
      return characterMoveY;
   }

   public void start (ArrayList<Buton> added) {
      for ( int i = 0; i < added.size(); i++ ) {
         if ( (added.get(i)).getButtonName() == "right" ) {
            if ( mapGrid[character.getGridY()][character.getGridX() + 1] >= 0 ) {
               character.moveRight();
               addMovement(character.getX(), character.getY());
            } else
               endGame(true);

         }

         if ( (added.get(i)).getButtonName() == "left" ) {
            if ( mapGrid[character.getGridY()][character.getGridX() - 1] >= 0 ) {
               character.moveLeft();
               addMovement(character.getX(), character.getY());
            } else
               endGame(true);

         }

         if ( (added.get(i)).getButtonName() == "up" ) {
            if ( mapGrid[character.getGridY() - 1][character.getGridX()] >= 0 ) {
               character.moveUp();
               addMovement(character.getX(), character.getY());
            } else
               endGame(true);

         }

         if ( (added.get(i)).getButtonName() == "down" ) {
            if ( mapGrid[character.getGridY() + 1][character.getGridX()] >= 0 ) {
               character.moveDown();
               addMovement(character.getX(), character.getY());
            } else
               endGame(true);

         }

         if ( (added.get(i)).getButtonName() == "loop" ) {
            int count = (added.get(i)).getLoopCount();
            System.out.println(count);
            ArrayList<Buton> insideOfLoop = new ArrayList<Buton>();
            for ( int j = i + 1; j < added.size() && ((added.get(i)).getButtonName()) != "endLoop"; j++ )
               insideOfLoop.add(added.get(j));
            for ( int k = 0; k < count - 1; k++ )
               start(insideOfLoop);
         }

         if ( (added.get(i)).getButtonName() == "pick" ) {
            for ( int k = 0; k < basket.getSize(); k++ ) {
               (basket.get(k)).pickItem(character.getGridX(), character.getGridY());
            }
         }
         if ( basket.isFull() )
            System.out.println("Basket is full");
      }
   }

   public Image getMap () {
      return map;
   }

   public int getLevelNum () {
      return levelNum;
   }


   public boolean isGameOver () {
      return gameOver;
   }

   public void endGame (boolean state) {
      if ( state ) {
         gameOver = true;
      }
   }

   public boolean win () {
      if ( levelNum == 3 && character.getGridX() == 5 && character.getGridY() == 3 ) {
         return basket.isFull();
      } else if ( levelNum == 1 ) {
         return (character.getGridX() == 5 && character.getGridY() == 2);
      }
      else
         return (character.getGridX() == 5 && character.getGridY() == 3);
   }
}