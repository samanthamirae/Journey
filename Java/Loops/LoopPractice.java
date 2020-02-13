public class LoopPractice {
   public static void main(String[] args) {
//      spellOut("hello");
   
      printPowers(2, 100);
      printPowers(10, 100000);
   }
   
//    pubic static String doubleLetters(String word) {
//    
//    
//    }
   
   // "hello" -> "h-e-l-l-o"
   public static void spellOut(String word) {
      System.out.print(word.charAt(0));
      for (int i = 1; i < word.length(); i++) {
         System.out.print("-" + word.charAt(i));
      }
      System.out.println();
   }
   
   // print out the powers of base up to the first
   //    power of base that is greater than last
   // 2, 4, 6, 8, 16, 32, 64
   public static void printPowers(int base, int last) {
      // indefinite loop
      int power = base;
      System.out.print(power);    // post
      while (power < last) {
         System.out.print(", ");    // wire 
         power *= base;             // post
         System.out.print(power); // wire
      }
      System.out.println();
   }
   
   public static void printPowers2(int base, int last) {
   
   
   }

   public static int sumAll(Scanner console) {
      int total = 0;
      System.out.print("Enter an integer (-1 to stop) : ");
      int number = console.nextInt();
      while (number != -1) {
         total += number;
         System.out.print("Enter an integer (-1 to stop): ");
         number = console.nextInt();
      }
      return total;
   
   }

}
