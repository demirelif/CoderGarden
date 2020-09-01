package gameMode;

import java.awt.Image;
import java.awt.Toolkit;

public class Item implements Locatable {
   // properties
   String name;
   String imageName;
   Image img;
   Boolean picked;
   int x;
   int y;

   // constructor
   public Item (String name, int x, int y) {
      this.name = name;
      imageName = name + ".jpg";
      picked = false;
      setX(x);
      setY(y);
   }

   // methods
   public void pickItem (int x, int y) {
      if ( !picked ) {
         if ( (getX() == x) && (getY() == y) ) {
            picked = true;
         }
      }
   }

   public Boolean getPicked () {
      return picked;
   }

   public void setImage () {
      img = Toolkit.getDefaultToolkit().getImage(imageName);
   }

   public int getX () {
      return x;
   }

   public int getY () {
      return y;
   }

   public void setX (int newX) {
      x = newX;
   }

   public void setY (int newY) {
      y = newY;
   }
}