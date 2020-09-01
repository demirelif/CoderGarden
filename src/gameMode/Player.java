package gameMode;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

public class Player implements Locatable
{
   public ArrayList<Image> characters = new ArrayList<Image>();
   Image characterImage;
   int x;
   int y;
   int gridX;
   int gridY;
   public Player (int levelNum)
   {
      setStartingLocationX();
      setStartingLocationY(levelNum);

      if (levelNum == 1)
         this.setGridY(2);
      else
         this.setGridY(0);

      this.setGridX(0);

   }


   public String getChImage(int levelNum)
   {
      String characterImageName = "/character" + levelNum + ".png";
      return characterImageName;
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

   public int getGridX()
   {
      return this.gridX;
   }

   public int getGridY()
   {
      return this.gridY;
   }

   public void setGridX(int xNew)
   {
      this.gridX = xNew;
   }

   public void setGridY(int yNew)
   {
      this.gridY = yNew;
   }

   public void moveRight()
   {
      this.setX(this.getX() + 100); //CHANGE WITH GUI CODES
      this.setGridX(this.getGridX() + 1);
   }

   public void moveLeft()
   {
      this.setX(this.getX() - 100); //CHANGE WITH GUI CODES
      this.setGridX(this.getGridX() - 1);
   }

   public void moveUp()
   {
      this.setY(this.getY() - 100); //CHANGE WITH GUI CODES
      this.setGridY(this.getGridY() - 1);
   }

   public void moveDown()
   {
      this.setY(this.getY()+ 100); //CHANGE WITH GUI CODES
      this.setGridY(this.getGridY() + 1);
   }

   public void setStartingLocationX()
   {
      this.setX(0);
   }
   public void setStartingLocationY(int levelNum)
   {
      if(levelNum == 1)
      {
         this.setY(200);
      }
      else
      {
         this.setY(0);
      }

   }
}