// This program will ask the user if they'd like to create a mad-lib, view a 
// mad-lib, or quit the program. If user chooses create, he/she will have to 
// input the input and output file name, follow the word input prompts by typing 
// various words, and the mad-lib is created and written to an output file. 
// He/she can also view any mad-lib and quit the game whenever they want. The 
// user can choose to create or view as many mad-libs as they'd like before quitting. 


import java.util.*;
import java.io.*;

public class MadLibs {
   public static void main(String[] args) throws FileNotFoundException {
      Scanner console = new Scanner(System.in);
      
      intro();
      String decision = options(console);
     
      while (!decision.equalsIgnoreCase("Q")) {    
         if (decision.equalsIgnoreCase("C")) {
            createGame(console);
            decision = options(console);
         } else {
            viewGame(console);
            decision = options(console);
         } 
      }   
   }
   
   // Prints an intro message describing the game
   public static void intro() {
      System.out.println("Welcome to the game of Mad Libs.");
      System.out.println("I will ask you to provide various words");
      System.out.println("and phrases to fill in a story.");
      System.out.println("The result will be written to an output file.");
      System.out.println();
   }
   
   // Asks the user if they'd like to create or view a mad-lib or
   // quit the game. Returns the decision that the user makes.
   //
   // Scanner console - to prompt the user to input their decision
   public static String options(Scanner console) {
      String decision = "";
      
      while (!decision.equalsIgnoreCase("C") 
             && !decision.equalsIgnoreCase("V") 
             && !decision.equalsIgnoreCase("Q")) {
         System.out.print("(C)reate mad-lib, (V)iew mad-lib, (Q)uit? ");
         decision = console.nextLine();
      } 
      return decision;
    }
   
   // Asks the user for the input file (mad-lib skeleton) and what they'd like 
   // to name the output file where their mad-lib gets written to. Runs the 
   // game then let's the user know that his/her mad-lib has been created.
   //
   // Scanner console - to input the input and output file names that the user provides
   public static void createGame(Scanner console) throws FileNotFoundException {
      System.out.print("Input file name: ");
      String fileInName = console.nextLine(); 
      File fileInput = new File(fileInName);   
         
      while (!fileInput.exists())  {
         System.out.print("File not found. Try again: ");
         fileInName = console.nextLine();
         fileInput = new File(fileInName);
      }
      System.out.print("Output file name: ");
      String fileOutName = console.nextLine();
      PrintStream output = new PrintStream(new File(fileOutName));
      System.out.println();
      
      madLibInputs(console, console, fileInput, output);
      System.out.println("Your mad-lib has been created!");
      System.out.println();
   }
   
   // Detects placeholders from the input file and requests the user to 
   // provide words or phrases depending on what the placeholder is asking.
   // If a placeholder starts with a vowel, the method ensures that the
   // program uses proper grammar by stating "an" rather than "a"
   //
   // Scanner console - the Scanner to use for inputs
   // Scanner fileScanner - the Scanner to use to read the file
   // File input - the File to read
   // PrintStream output - to print the mad-lib onto the output file
   public static void madLibInputs(Scanner console, Scanner fileScanner, File input,
                                   PrintStream output) throws FileNotFoundException {
      fileScanner = new Scanner(input);
      
      while (fileScanner.hasNextLine()) {
         String line = fileScanner.nextLine();{
         Scanner lineScanner = new Scanner(line);
            // Checks each individual line
            while (lineScanner.hasNext()) {
               String word = lineScanner.next();
               
               // If a token is a placeholder
               if (word.startsWith("<") && word.endsWith(">")) {
                  word = word.substring(1, word.length() - 1);  
                   
                  // If a placeholder starts with a vowel
                  if (word.toLowerCase().startsWith("a") || word.toLowerCase().startsWith("e") 
                      || word.toLowerCase().startsWith("i") || word.toLowerCase().startsWith("o") 
                      || word.toLowerCase().startsWith("u")) { 
                     System.out.print("Please type an " + word.replace("-", " ") + ": "); 
                  } else {
                     System.out.print("Please type a " + word.replace("-", " ") + ": ");
                  }
                  word = console.nextLine();
                  output.print(word + " ");
               } else {
                  output.print(word+ " ");
               }
            } 
            output.println();          
         }      
      }
   }
   
   // Asks the user for the file name of the mad-lib she/he would like to see, 
   // then prints out the entire mad-lib for viewing purposes. 
   //
   // Scanner console - the Scanner to use for inputs
   public static void viewGame(Scanner console) throws FileNotFoundException {
      System.out.print("Input file name: ");
      String fileName = console.nextLine();
      File fileOutput = new File(fileName);
      
      while (!fileOutput.exists()) {
         System.out.print("File not found. Try again: ");
         fileName = console.nextLine();
         fileOutput = new File(fileName);
      }
      
      Scanner input = new Scanner(fileOutput);
      System.out.println();
      while (input.hasNextLine()) {
         String line = input.nextLine();
         System.out.println(line);   
      }
      System.out.println();
   }
   
}
