package gameMode;

import java.util.ArrayList;

public class CodeBlocks {
   private int index;
   public boolean isIndented;
   public ArrayList<Buton> added;
   public ArrayList<String> realCode;
   public final int INDENTATION_LEVEL = 30; //CHANGE WITH GUI CODES

   public CodeBlocks () {
      added = new ArrayList<Buton>();
      realCode = new ArrayList<String>();
      this.isIndented = false;
   }

   public ArrayList<Buton> getAdded () {
      return this.added;
   }

   public void clear () {
      added.removeAll(added);
      realCode.removeAll(realCode);
   }


   public ArrayList<String> getRealCode (ArrayList<Buton> added) {
      return this.realCode;
   }


   public void addButton (Buton button) {
      if ( button.getButtonName() == "endLoop" )
         this.isIndented = false;

      (this.added).add(button);

      if ( isIndented ) {
         (realCode).add("   " + (button.getCodePiece()));

      } else {
         (realCode).add(button.getCodePiece());
      }

      if ( button.getButtonName() == "loop" )
         this.isIndented = true;

   }


   public String print (ArrayList<Buton> added) {
      String str = "REAL CODE \n\n" +
            "public class Test{ \n" +
            "   public static void main(String[] args) { \n";
      for ( int i = 0; i < added.size(); i++ ) {

         str = str + "      " + realCode.get(i) + "\n";
      }
      str += "   } \n}";
      return str;
   }

}
