import java.util.*;

public class DiceWar {
   public static void main(String[] args) {
  //    diceWar(6, 2);
      diceWar(10, 7); 
  //    diceWar(4, 2);
   
   }
   
   public static void diceWar(int max, int target) {
      Random rand = new Random();
      int round = 1;
      int p1Points = 0;
      int p2Points = 0;
      
      System.out.println("Rolling a " + max + "-sided die, need " + target + " to score...");
      
      while (round <= 3 || p1Points == p2Points) {
         System.out.println("Round " + round + ": ");
         
         int p1Roll = rand.nextInt(max) + 1;
         int p2Roll = rand.nextInt(max) + 1;
         
         if (p1Roll >= target) {
            System.out.println("    Player 1 rolled: " + p1Roll + " - POINT!");
            p1Points++;
         } else {
            System.out.println("    Player 1 rolled: " + p1Roll);
            p1Points++;
         }
         
         if (p2Roll >= target) {
            System.out.println("    Player 2 rolled: " + p2Roll + " - POINT!");
            p2Points++;
         } else {
            System.out.println("    Player 2 rolled: " + p2Roll);
            p2Points++;
         }
         
         round++;
      }
      
      System.out.println("Final score - Player 1: " + p1Points + ", Player 2: " + p2Points);
      
      if (p1Points > p2Points) {
         System.out.println("Player 1 wins!");
      } else {
         System.out.println("Player 2 wins!");
      }
   }
}
