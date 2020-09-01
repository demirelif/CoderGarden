package gameMode;

public class ItemBasket
{
   // properties
   Item[] basket;
   int size;

   // constructor
   public ItemBasket()
   {
      size = 3;
      setItems();
   }

   // methods

   public void setItems()
   {
      Item apple1 = new Item( "apple", 2, 0);
      Item apple2 = new Item( "apple", 5, 0);
      Item apple3 = new Item( "apple", 5, 2);
      basket = new Item[]{ apple1, apple2, apple3};
   }

   public boolean isFull()
   {
      for ( int i = 0; i < basket.length; i ++)
      {
         if ( ! ( get(i) ).picked )
         {
            return false;
         }
      }
      return true;
   }

   public Item get(int index)
   {
      return basket[index];
   }

   public int getSize()
   {
      return size;
   }

   public int left()
   {
      int space = 3;
      for (int i = 0 ; i < basket.length ; i++)
      {
         if (basket[i].getPicked()) {
            space--;
         }
      }
      return space;
   }
}