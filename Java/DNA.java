// This program will print out a quick intro then ask the user to input an 
// input and output file name. The input file will contain named sequences of 
// nucleotides. This program will then print out information about the nucleotides 
// to the output file. Specifically, the program will count the occurrences of 
// each type of nucleotide (A, C, G, and T) per nucleotide sequence, compute 
// the rounded mass percentage of each nucleotide type, print the codons in each 
// sequence, and predict if the sequence is a protein-coding gene or not. 


import java.io.*;
import java.util.*;

public class DNA {
   public static final int MIN_NUM_CODONS = 5;
   public static final int MIN_C_AND_G_MASS_PERCENTAGE = 30; 
   public static final int NUM_OF_UNIQUE_NUCLEOTIDES = 4;
   public static final int NUM_OF_NUCLEOTIDES_PER_CODON = 3;
   
   public static void main(String[] args) throws FileNotFoundException {
      Scanner console = new Scanner(System.in);
      
      intro();

      // Get input and output file
      System.out.print("Input file name? ");
      String inputFile = console.nextLine();
      System.out.print("Output file name? ");
      String outputFile = console.nextLine();
      
      // Create new File and PrintStream
      File inFile = new File(inputFile);
      PrintStream output = new PrintStream(new File(outputFile));
      System.out.println();
      
      printOutputFile(inFile, output);
   }
   
   // Prints an intro message briefly describing the program
   public static void intro() {
      System.out.println("This program reports information about DNA");
      System.out.println("nucleotide sequences that may encode proteins.");
   }
   
   // Prints all the file outputs for each nucleotide sequence, including the 
   // region name, nucleotides, count of each of the 4 nucleotides, rounded mass 
   // percentage per nucleotide, and if it is a protein-coding gene or not. 
   //
   // File input - the File to read
   // PrintStream output - to print all outputs to the output file
   public static void printOutputFile(File input, PrintStream output) throws FileNotFoundException {
      Scanner fileScanner = new Scanner(input);
      
      while (fileScanner.hasNextLine()) {
         String regionName = fileScanner.nextLine();
         output.println("Region Name: " + regionName);
         
         if (fileScanner.hasNextLine()) {
            String nucleotide = fileScanner.nextLine();
            String nucleotideCaps = nucleotide.toUpperCase();
            output.println("Nucleotides: " + nucleotideCaps);
            
            // DNA strand count method
            int[] count = countNucleotide(nucleotideCaps, "ACGT", NUM_OF_UNIQUE_NUCLEOTIDES);
            output.println("Nuc. Counts: " + Arrays.toString(count));
            
            // Total mass calculation method
            double totalNucSum = totalMassSum(nucleotideCaps);
            double[] massPercentage = massPercentage(count, totalNucSum);
            output.println("Total Mass%: " + Arrays.toString(massPercentage) + " of " 
                  + Math.round(totalNucSum * 10.0) / 10.0);
            
            // Prints out the list of codons
            String[] codonsList = codonsList(nucleotideCaps);
            output.println("Codons List: " + Arrays.toString(codonsList));
            
            // Prints out if the sequence is a protein-coding gene
            String isProtein = isProtein(codonsList, massPercentage);
            output.println("Is Protein?: " + isProtein);
         }
         output.println();      
      } 
   }

   // Calculates the mass percentage of each nucleotide type and returns
   // the array of mass percentages to output
   //
   // int[] dnaCount - the array of the counts used to calculate their mass percentages
   // double totalNucSum - the total mass of the sequence used to calculate each mass
   public static double[] massPercentage(int[] dnaCount, double totalNucSum) {
      double[] massPercentage = new double[NUM_OF_UNIQUE_NUCLEOTIDES];
      double[] masses = {135.128, 111.103, 151.128, 125.107};
      
      for (int i = 0; i < NUM_OF_UNIQUE_NUCLEOTIDES; i++) {
         massPercentage[i] = (double)dnaCount[i] * masses[i];
         massPercentage[i] = Math.round((massPercentage[i] / totalNucSum) * 1000.0) / 10.0;
      }
      return massPercentage;
   }   
   
   // Calculates the total mass of the nucleotide sequence including the dash character
   // that represents "junk" regions in the sequence and returns the total mass
   //
   // String nucleotide - the nucleotide sequence used to calculate its own total mass
   public static double totalMassSum(String nucleotide) {
      // Create new array of counts including the dash 
      int[] nucCount = countNucleotide(nucleotide, "ACGT-", NUM_OF_UNIQUE_NUCLEOTIDES + 1);

      // Find the sum of total mass including the dash
      double totalNucSum = 0.0;
      double[] massesWithDash = {135.128, 111.103, 151.128, 125.107, 100.000};
      
      for (int i = 0; i < NUM_OF_UNIQUE_NUCLEOTIDES + 1; i++) {
         totalNucSum = totalNucSum + nucCount[i] * massesWithDash[i];
      }
      return totalNucSum;
   }
   
   // Prints all codons (trios of nucleotides) found in the sequence of nucleotides
   // and returns an array of all the codons found (including repeats)
   //
   // String nucleotide - the nucleotide sequence used to find all codons (trios
   // of nucleotides) 
   public static String[] codonsList(String nucleotide) {
      String justNucleotides = nucleotide.replace("-", "");
      int numOfCodons = justNucleotides.length() / NUM_OF_NUCLEOTIDES_PER_CODON;
      String[] codons = new String[numOfCodons];
      
      for (int i = 0; i < numOfCodons; i++) {
         codons[i] = justNucleotides.substring(i * NUM_OF_NUCLEOTIDES_PER_CODON, 
               i * NUM_OF_NUCLEOTIDES_PER_CODON + NUM_OF_NUCLEOTIDES_PER_CODON);
      }
      return codons;
   }
   
   // Determines if a sequence of nucleotides is a protein-coding gene, which
   // means if a sequence begins with "ATG", ends with either "TAA", "TAG", or 
   // "TGA", contains at least 5 codons, and if nucleotide type C and G accounts
   // for at least 30% of its total mass combined. Returns "YES" or "NO" if
   // the sequence is a protein-coding gene or not.
   //
   // String[] codon - the array of codons in a sequence used to determine if the
   // sequence is a protein-coding gene 
   // double[] codonMass - the array of each nucleotide type's mass used to 
   // check if type C and G accounts for at least 30% of the total mass combined
   public static String isProtein(String[] codon, double[] codonMass) {
      String isProtein = "NO";
      
      if (codon[0].equals("ATG")) {
         if (codon[codon.length - 1].equals("TAA") 
             || codon[codon.length - 1].equals("TAG")
             || codon[codon.length - 1].equals("TGA")) {
             if (codon.length >= MIN_NUM_CODONS) {
                if (codonMass[1] + codonMass[2] >= MIN_C_AND_G_MASS_PERCENTAGE) {
                   isProtein = "YES";
                }
             } 
          }     
      }
      return isProtein;
   }
   
   // Counts each nucleotide and returns an array of the counts.
   //
   // String nucleotide - the sequence of nucletides to count
   // String dnaStrand - dnaStrand with each type of nucleotide to count
   // int numOfUniqueNucs - number to determine how long the array will be 
   public static int[] countNucleotide(String nucleotide, String dnaStrand, int numOfUniqueNucs) {
      int[] count = new int[numOfUniqueNucs];
      
      for (int i = 0; i < nucleotide.length(); i++) {
         char c = nucleotide.charAt(i);
         int index = dnaStrand.indexOf(c);
         if (index != -1) {
            count[index]++;
         }
      }
      return count;
   }
   
}
