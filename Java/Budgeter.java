// This program will ask the user for all their income and expenses then calculate
// his/her total income, expenses, and their net income. It will also state which 
// spending category the user falls into based on their net income. 


import java.util.*;

public class Budgeter {
   // You may adjust the DAYSINMONTH constant depending on 
   // how many days there are in the current month
   public static final int DAYSINMONTH = 31;
   
   public static void main(String[] args) {
      Scanner console = new Scanner(System.in);
      
      introductoryMessage();
      double totalIncome = getIncomeOrExpense(console, "income");
      double totalExpenses = getIncomeOrExpense(console, "expense");
      double netIncome = netIncome(totalIncome, totalExpenses); 
      totalIncomeAndExpenses(totalIncome, "income");
      totalIncomeAndExpenses(totalExpenses, "expenses"); 
      netIncomeStatement(netIncome);
      userCategory(netIncome); 
   }
     
   // Prints an intro message briefly explaining the program
   public static void introductoryMessage() {
      System.out.println("This program asks for your monthly income and");
      System.out.println("expenses, then tells you your net monthly income.");
      System.out.println();
   }
   
   // Asks the user how many categories of income/expenses then 
   // asks for each income/expense amount based on the number of categories
   //
   // Scanner console - the Scanner to use for inputs
   // String type - to print out either income or expenses 
   public static double getIncomeOrExpense(Scanner console, String type) {
      double total = 0;
      int monthlyOrDaily = 1;
      
      if (type == "expense") {
         System.out.print("Enter 1) monthly or 2) daily expenses? ");
         monthlyOrDaily = console.nextInt();
      }
      
      System.out.print("How many categories of " + type + "? ");
      int numCategories = console.nextInt();
      
      for (int i = 1; i <= numCategories; i++) {
         System.out.print("    Next " + type + " amount? $");
         double amount = console.nextDouble();
         total = total + amount;
      }
      System.out.println();
      
      if (monthlyOrDaily == 2) {
         return total * DAYSINMONTH;
      } else {
         return total;
      }
   }
   
   // Returns net income rounded to two decimal places
   //
   // double amount - net income to round
   public static double roundAmount(double amount) {
      return Math.round(amount * 100.0) / 100.0;
   }
   
   // Returns calculated net income 
   //
   // double totalIncome - total income used to calculate net income
   // double totalExpenses - total expense used to calculate net income
   public static double netIncome(double totalIncome, double totalExpenses) {
      return totalIncome - totalExpenses;
   }
   
   // Calculates and print the user's total income, expenses, and
   // income/expense per day
   //
   // double totalIncomeOrExpense - total income/expense to print and to calculate per day
   // String type - to print out either income or expenses 
   public static void totalIncomeAndExpenses(double totalIncomeOrExpense, String type) {
      double totalPerDay = totalIncomeOrExpense / DAYSINMONTH;
      
      System.out.println("Total " + type + " = $" + roundAmount(totalIncomeOrExpense) + " ($" + 
            roundAmount(totalPerDay) + "/day)");
   }
   
   // Prints the user's net income in a statement
   //
   // double netIncome - net income to print out in a statement
   public static void netIncomeStatement(double netIncome) {
      System.out.println();
      
      if (netIncome >= 0) {
         System.out.println("You earned $" + roundAmount(netIncome) + 
               " more than you spent this month.");
      } else {   // (netIncome <= 0)
         System.out.println("You spent $" + Math.abs(roundAmount(netIncome)) + 
               " more than you earned this month.");
      } 
   }
   
   // Prints which spending category the user falls into based on
   // their net income along with a comment about their spending habits
   //
   // double netIncome - net income used to round and put user into a category
   public static void userCategory(double netIncome) {
      double netIncomeRounded = roundAmount(netIncome);
      
      if (netIncomeRounded > 250.00) {
         System.out.println("You're a big saver.");
         System.out.println("Congratulations on saving money! You're very responsible.");
      } else if (netIncomeRounded > 0.00) {
         System.out.println("You're a saver.");
         System.out.println("Great job saving some money! It's okay to go out once in a while.");
      } else if (netIncomeRounded > -250.00) {
         System.out.println("You're a spender.");
         System.out.println("It's okay, continue to live your youth! Play now, save more later!");
      } else {   // netIncomeRounded < -250.00
         System.out.println("You're a big spender.");
         System.out.println("Live your life to the fullest, but maybe you should finally think");
         System.out.println("about saving a bit of cash for retirement.");
      }      
   }
      
}
