import java.util.*;
import java.io.*;

public class ImdbSearch {
   public static void main(String[] args) throws FileNotFoundException {
      Scanner console = new Scanner(System.in);
      Scanner inputFile = new Scanner(new File("imdb.txt"));
   
      
      System.out.print("Search term: ");
      String term = console.next();
      
      System.out.print("Output file? ");
      printStream outputFile = new PrintStream(new File(out));
      
      find(term, inputFile, outputFile);
   }
   
   public static void find(String term, Scanner input, PrintStream output) {
      // while there's another line 
      while (input.hasNextLine()) {
         // get next line from Scanner
         String line = input.next();
         // check if line (title?) contains term
         if (line.toLowerCase().contains(term.toLowerCase())) {
            // if so, print the movie
            String highlighted = highlight(line, term);
            output.println(highlighted);
            
         }
      }
   
   }
   
   public static String highlight(String movie, String searchTerm) {
      Scanner tokens = new Scanner(movie);
      String result = "";
      
      while (tokens.hasNext()) {
         String token = tokens.next();
         if (token.equalsIgnoreCase(searchTerm)) {
            result = result + ("**" + token.toUpperCase() + "**" + " ");
         } else {
            result += token + " ";         
         }
      }
      return result;
   
   }

}
