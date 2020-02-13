// This program will produce a ASCII artwork of a rocket.
// This program includes a class constant that you may change
// depending on your desired sizing of the rocket.


public class DrawRocket {
   //The SIZE constant will allow you to adjust the size of the rocket
   public static final int SIZE = 3;
   
   public static void main(String[] args) {
      rocketPoint();
      rocketDivider();
      upwardTriangle();
      downwardTriangle();
      rocketDivider();
      downwardTriangle();
      upwardTriangle();
      rocketDivider();
      rocketPoint();
   }
   
   
   //Prints the point (head and tail) of the rocket
   public static void rocketPoint() {
      for (int layers = 1; layers <= 2 * SIZE - 1; layers++) {
         //spaces
         for (int space = SIZE + (SIZE - 1); space >= layers; space--) {
            System.out.print(" ");
         }

         //forward slash
         for (int forwardSlash = 1; forwardSlash <= layers; forwardSlash++) {
            System.out.print("/");
         }
            
         //stars
         System.out.print("**");
            
         //backslash
         for (int backSlash = 1; backSlash <= layers; backSlash++) {
            System.out.print("\\");
         }
         System.out.println();         
      }
   }
   
   //Prints the line where the pieces of the rocket connects
   public static void rocketDivider() {
      System.out.print("+");
      //print equals
      for (int equalSign = 1; equalSign <= SIZE * 2; equalSign++) {
         System.out.print("=");
         //print stars
         for (int star = 1; star <= 1; star++) {
            System.out.print("*");
         }
      }
      System.out.println("+");
   }


   //Prints the part of the rocket's body with the triangles pointed up
   public static void upwardTriangle() {      
      for (int sets = 1; sets <= SIZE; sets++) {
         System.out.print("|");
         //repeat
         for (int repeat = 1; repeat <= 2; repeat++) {
            //dots
            for (int dot = SIZE - 1; dot >= sets; dot--) {
               System.out.print(".");
            }
            //slashes
            for (int slashes = 1; slashes <= sets ; slashes++) {
               System.out.print("/\\");
            }
            //dots
            for (int dot = SIZE - 1; dot >= sets ; dot--) {
               System.out.print(".");
            }
         }
         System.out.println("|");    
      }
   }
   
   
   //Prints the part of the rocket's body with the triangles pointed down
   public static void downwardTriangle() {      
      for (int sets = 1; sets <= SIZE; sets++) {
         System.out.print("|");
         //repeat
         for (int repeat = 1; repeat <=2; repeat++) {
            //dots
            for (int dot = 1; dot <= sets - 1; dot++) {
               System.out.print(".");
            }
            //slashes
            for (int slashes = SIZE; slashes >= sets ; slashes--) {
               System.out.print("\\/");
            }
            //dots
            for (int dot = 1; dot <= sets - 1; dot++) {
               System.out.print(".");
            }
         }
         System.out.println("|");    
      }
   }
}
