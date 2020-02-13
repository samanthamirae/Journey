// This program will come up with a number between 1 and the max number set (100 is default)
// and prompts the player to guess the number until he/she guesses the correct number.
// The program will also provide hints as to whether the number is high or lower than the 
// guessed number and print out the overall stats of the game.


import java.util.*;

public class GuessingGame {
   // You may adjust the MAX_NUMBER to the highest  
   // number you'd like to include in the game.
   public static final int MAX_NUMBER = 100;
   
   public static void main(String[] args) {
      Scanner console = new Scanner(System.in);
      Random rand = new Random();
   
      // Prints intro message
      haikuIntro();
      
      // Initializing / declaring variables
      int totalGames = 0;
      int totalGuesses = 0;
      int bestGame = 1000000;
      String firstLetterInput;
      
      // Runs the game until user inputs that they don't want to play again
      do {
         int numGuesses = game(console, rand);
         totalGames++;
         totalGuesses = totalGuesses + numGuesses;
         
         System.out.print("Do you want to play again? ");
         String playAgain = console.next();
         firstLetterInput = playAgain.substring(0 , 1);
         System.out.println(); 
         
         // Finding the best game
         if (numGuesses < bestGame) {
            bestGame = numGuesses;
         }
      }  while (firstLetterInput.equalsIgnoreCase("y"));
      
      // Prints overall results
      overallStats(totalGames, totalGuesses, bestGame);
   }
   
   // Prints an intro message in the form of a haiku
   public static void haikuIntro() {
      System.out.println("This is 'Guessing Game'.");
      System.out.println("Can you think of the number?");
      System.out.println("Best of luck to you!");
      System.out.println();
   }
   
   // Comes up with a number between 1 and the max number and prompts the user to guess
   // the number while providing hints if the number is lower or higher.
   // Once user gets it correct, states how many guesses it took to get it right.
   // Returns the number of guesses that the user took.
   //
   // Scanner console - to prompt then input for number guesses
   // Random rand - to randomize the chosen number in the game
   public static int game(Scanner console, Random rand) {
      int number = rand.nextInt(MAX_NUMBER) + 1;
      int guess = 0;
      int numGuesses = 0;
      
      System.out.println("I'm thinking of a number between 1 and " + MAX_NUMBER + "...");
      System.out.println("*** HINT: The answer is " + number);
      // Asks the player for their guesses and gives a hint
      while (number != guess) { 
         System.out.print("Your guess? ");
         guess = console.nextInt();
         numGuesses++;
         
         if (number < guess) {
            System.out.println("It's lower.");
         } else if (number > guess) {
            System.out.println("It's higher.");
         }       
      }
      
      // States that the player guessed the correct number and the number of guesses it took 
      if (numGuesses == 1) {
         System.out.println("You got it right in 1 guess!");
      } else {
         System.out.println("You got it right in " + numGuesses + " guesses!"); 
      }
      return numGuesses;
   }
   
   // Provides the overall results of the game, including total games played, total guesses
   // from all games, the average number of guesses per game, and the user's best game
   //
   // int totalGames - the total number of games played 
   // int totalGuesses - the total number of guesses from all games 
   // int bestGame - the number of guesses from the game with the least number of guesses
   public static void overallStats(int totalGames, int totalGuesses, int bestGame) {
      System.out.println("Overall results:");
      System.out.println("Total games   = " + totalGames);
      System.out.println("Total guesses = " + totalGuesses);
      System.out.println("Guesses/game  = " + roundToTenth((double)totalGuesses / totalGames));
      System.out.println("Best game     = " + bestGame);   
   }
   
   // Returns the decimal number rounded to one decimal place
   //
   // double number - the number to round
   public static double roundToTenth(double number) {
      return Math.round(number * 10) / 10.0;
   }
   
}
