// This program will produce an artwork of the cafe
// wall illusion with various rows and grids.
// You are able to change the space between the rows


import java.awt.*;

public class CafeWall {
   // The MORTAR constant will allow you to adjust the 
   // mortar space (number of pixels between rows) 
   public static final int MORTAR = 2;

   public static void main(String[] args) {
      DrawingPanel panel = new DrawingPanel(650, 400);
      panel.setBackground(Color.GRAY); 
      Graphics g = panel.getGraphics();
      
      drawRow(g, 0, 0, 20, 4);
      drawRow(g, 50, 70, 30, 5);
      
      drawGrid(g, 10, 150, 25, 4, 0);
      drawGrid(g, 250, 200, 25, 3, 10);
      drawGrid(g, 425, 180, 20, 5, 10);
      drawGrid(g, 400, 20, 35, 2, 35);
   }

   // Given the x and y coordinates, box size, and number of box pairs,
   // draws the appropriate rows in the cafe wall illusion
   //
   // Graphics g - creation of the drawing pen
   // int x - the x-coordinate of the top left corner of the most left box
   // int y - the y-coordinate of the top left corner of the most left box
   // int boxSize - the height and width (dimensions) of each box
   // int boxPairs - number of pairs of black and white boxes
   public static void drawRow(Graphics g, int x, int y, int boxSize, int boxPairs) {  
      for (int i = 0; i <= boxPairs - 1; i++) {
         // draw black boxes
         g.setColor(Color.BLACK);
         g.fillRect(i * 2 * boxSize + x, y, boxSize, boxSize);
         
         // draw white boxes
         g.setColor(Color.WHITE);
         g.fillRect((i * 2 * boxSize + x) + boxSize, y, boxSize, boxSize);
               
         // draw blue X's inside the black boxes
         g.setColor(Color.BLUE);
         g.drawLine( i * 2 * boxSize + x, y, ( i * 2 * boxSize + x ) + boxSize, y + boxSize );
         g.drawLine( i * 2 * boxSize + x, y + boxSize, ( i * 2 * boxSize + x ) + boxSize, y );
      } 
   }
   
   // Given the x and y coordinates, box size, number of box pairs, and row offset
   // value, draws the appropriate grids in the cafe wall illusion
   //
   // Graphics g - creation of the drawing pen
   // int x - the x-coordinate of the top left corner of the most left box
   // int y - the y-coordinate of the top left corner of the most left box
   // int boxSize - the height and width (dimensions) of each box
   // int boxPairs - number of pairs of black and white boxes and row pairs
   // int rowOffset - number of pixels how far the second row is shifted to the right in each pair
   public static void drawGrid(Graphics g, int x, int y, int boxSize, int boxPairs, 
         int rowOffset) {
      for (int i = 0; i < boxPairs; i++) {
            for (int k = 0; k < 2; k++) { 
               x = x + rowOffset * k;
               drawRow(g, x, y, boxSize, boxPairs);   
               y = y + boxSize + MORTAR;
            }
            x = x - rowOffset;       
      }
   }
   
   
}
