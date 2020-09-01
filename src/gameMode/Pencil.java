package gameMode;

import java.awt.*;

/**
 * @author omerozbekler
 * @version 13.05.2018
 * Pencil - This program
 */
public class Pencil {

   // properties
   private Image pencilImage;
   private String imageName;
   private int x;
   private int y;

   // constructor
   public Pencil(int levelNum)
   {
      imageName = "/pencil.jpg";
      if (levelNum == 1)
      {
         setX(10);
         setY(150);
      }
      else
      {
         setX(190);
         setY(120);
      }
   }

   // methods
   public void setImage(){
      pencilImage = Toolkit.getDefaultToolkit().getImage( imageName);
   }

   public String getImageName()
   {
      return this.imageName;
   }

   public Image getImage(){
      return pencilImage;
   }

   public int getX()
   {
      return this.x;
   }

   public int getY()
   {
      return this.y;
   }

   public void setX(int xNew)
   {
      this.x = xNew;
   }

   public void setY(int yNew)
   {
      this.y = yNew;
   }
}
