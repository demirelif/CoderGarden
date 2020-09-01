package gameMode;

import javax.swing.*;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * Buttons in all games.
 * @author softAware
 */

public class Buton{

   // properties
   private Image  buttonImage;
   private Boolean isClickable;
   private String buttonName;
   private String buttonImageName;
   private String codePiece;
   private int loopCount;

   // constructor
   public Buton( String name)
   {
      buttonName = name;
      buttonImageName = "/testpackage/" + name + ".png";
      isClickable = false;
      setLoopCount(-4);
   }

   public Buton( String name, int lc)
   {
      buttonName = name;
      buttonImageName = "/testpackage/" + name + ".png";
      isClickable = false;
      this.loopCount = lc;
      setLoopCount(lc);
   }


   // methods
   public void setClickable(boolean b){
      isClickable = b;
   }

   public boolean getClickable(){
      return isClickable;
   }



   public void setButtonName( String name){
      buttonName = name;
   }

   public String getButtonImage(){
      return buttonImageName;
   }

   public String getButtonName(){
      return buttonName;
   }

   public void setCodePiece( String codePiece)
   {
//      if (this.buttonName == "loop") {
//         System.out.println(loopCount);
//         this.codePiece = "for (int i = 0 ; i <= " + this.getLoopCount() + " ; i++) {";
//      }
//      else
         this.codePiece = codePiece ;
   }

   public String getCodePiece()
   {
      return codePiece;
   }

   public void setLoopCount(int lc)
   {
      this.loopCount = lc;
   }

   public int getLoopCount()
   {
      return loopCount;
   }
}