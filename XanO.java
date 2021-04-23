/**
 * 5 in a row Tic-Tac-Toe (Part 2, Finishing)
 * Project for CS 214 students. Spring 2021.
 *
 * @author of the project: Gregory Baramidze
 */
import java.awt.*;
import java.util.Random;
import java.util.Arrays;

public class XanO{ //Change the class name using your last and first names

    //Constants for defining different modes:
    private static final int START = 0, X_TURN = 1, O_TURN = 2, X_WIN = 3, O_WIN = 4,  DRAW = 7;

    //Size of the board will be from MIN_COOR to MAX_COOR on both axis:
    private static final double MIN_COOR=0, MAX_COOR=15;

    //present x and y mouse click
    private static double mouse_x = 0, mouse_y = 0,start_x = 0, start_y = 0, end_x = 0, end_y = 0;



    /**
     * THE CODE FOR THESE METHODS CAN BE COPIED FROM PROJECT 2
     *
     * drawGrid()
     *
     * printBoard(int[][] board)
     * drawX(int xLoc, int yLoc)
     * drawO(int xLoc, int yLoc)
     * tryPlacingMark(double x, double y, int[][] board, int mode)
     * nextTurn(int mode)
     *
     *
     * updateStatus(int mode) becomes updateStatusBar(int mode, int scoreX, int scoreO) and has to be modified
     *
     */



    /**
     * CAN COPY YOUR OLD CODE here (if correct) 
     *
     * The method draws the grid (pick your color, and pen radius).
     * The grid should be drawn within the window from MIN_COOR to MAX_COOR (0 to 15 in our case)
     * with cell size 1. In other words, vertical and horizontal lines are use corresponding coordinates between 0 and 15.
     *
     * Notice, that you have the "button" on top and the status bar at the bottom. Don't draw lines on those!
     */
    private static void drawGrid() {
        //...Your implementation goes here
        for(int i = 0;i < 16;i++){
            StdDraw.line(i,MIN_COOR,i,MAX_COOR);
        }
        for(int i = 0;i < 16;i++){
            StdDraw.line(MIN_COOR, i , MAX_COOR, i);
        }
        System.out.println("_______________________________________________________________-");
    }

    /**
     * CAN COPY YOUR OLD CODE here (if correct) 
     *
     * The method is for testing purposes mostly.
     * Whenever one clicks on the status line, this method is called
     * to print the board array.
     * (Calling this method is taken care of, you just need to print the board here).
     *
     * Print "." instead of 0-s, "X" instead of 1-s, and "O" instead of 2-s
     */
    private static void printBoard(int[][] board) {
        //...Your implementation goes here
        for (int i = 0;i < board.length;i++ ){
            for (int j = 0;j < board.length;j++){
                if(board[i][j] == 0){
                    System.out.print(" . ");
                }
                else if(board[i][j] == 1){
                    System.out.print(" X ");
                }
                else if(board[i][j] == 2){
                    System.out.print(" O ");
                }
            }
            System.out.println();
        }
    }


    /**
     * CAN COPY YOUR OLD CODE here (if correct)
     *
     * Here, you simply need to draw two lines to form an X in the middle of given cell "coordinates":
     * xLoc is the integer "x-coordinate" of a cell, and
     * yLoc is the integer "y-coordinate" of a cell.
     * They both are an integer between 0 to 14 (inclusive).
     *
     * Use BOOK_BLUE color for the lines.
     */
    private static void drawX(int xLoc, int yLoc){
        //...Your implementation goes here
        //StdDraw.setXscale(0,20);
        //StdDraw.setYscale(0,20);
        StdDraw.setPenRadius(0.005);
        StdDraw.line(xLoc+0.1,yLoc+0.1,xLoc+0.9,yLoc+0.9);
        StdDraw.line(xLoc+0.1,yLoc+0.9,xLoc+0.9,yLoc+0.1);

        //  StdDraw.line(StdDraw.mouseX(),StdDraw.mouseY()+1,StdDraw.mouseX()+1,StdDraw.mouseY());
    }



    /**
     * CAN COPY YOUR OLD CODE here (if correct)
     *
     * Here, you simply need to draw a circle in the center of the location with coordinates xLoc and yLoc.
     * Use BOOK_RED color for the circle.
     */
    private static void drawO(int xLoc, int yLoc){
        //...Your implementation goes here
        StdDraw.setPenRadius(0.005);

        StdDraw.circle(xLoc+0.50, yLoc+0.50,0.42);
    }






    /**
     * CAN COPY YOUR OLD CODE here (if correct)
     *
     * First, check if the cell at the given mouse click coordinates (x and y) is available,
     * i.e., if the appropriate board[row][col] element is equal to 0.
     * <You have to figure out how to go from mouse coordinates to boards coordinates (rows and columns)>
     *
     * If it is available, place 1 in the appropriate board element if it's X-s turn,
     * and 2 if it's O-s turn.
     *
     * The method should return true, if mark could be placed,
     * or false if the cell is unavailable.
     *
     * This method should not draw anything at all! It just works with array board[][].
     */
    private static boolean tryPlacingMark(double x, double y, int[][] board, int mode){
        //...Your implementation goes here
        int col = (int)x;//as the Mouse position for x is in double value,I am using an int such that index matches the array index
        int row = 14 - (int)y;
        System.out.println(row+" "+col);//Index starts from 0 in an array, but here the Y axis pointer of the mouse returns decreasing values from top, hence subtracting with the array.length-1 to this pointer.
        if(board[row][col] == 0 && mode == X_TURN){
            board[row][col] = 1;
            return true;
        }
        if(board[row][col] == 0 && mode == O_TURN){
            board[row][col] = 2;
            return true;
        }

        return false;
    }


    /**
     * CAN COPY YOUR OLD CODE here (if correct)
     *
     * The method gets the current mode as an input and should switch the "turn" modes,
     * i.e., it should return a mode of the next turn (O_TURN after X_TURN, or vice-versa).
     *
     * If the input mode is not a "turn" mode, it should just return the same mode back.
     */
    private static int nextTurn(int mode){
        //...Your implementation goes here
        switch (mode){
            case X_TURN: return O_TURN;
            case O_TURN: return  X_TURN;
            default:
                return mode;

        }
    }




    /**
     *  CAN COPY YOUR OLD CODE from updateStatus() method and modify it as needed
     *
     * This method should redraw the appropriate text in the status bar depending on the mode.
     * E.g., if it's X-s turn, it needs to show text "X-s TURN" in blue,
     * and if it's O-s turn, it needs to show text "O-s TURN" in red.
     *
     * Additionally, the method should always show the current score
     * in the left part of the status bar as shown in pictures in project description.
     */
    private static void updateStatusBar(int mode, int scoreX, int score) {
        StdDraw.setPenColor(250, 250, 250);  //Almost white color.
        //Redraw rectangle on top of the status bar (i.e. erase everything on it):
        StdDraw.filledRectangle((MIN_COOR+MAX_COOR)/2, MIN_COOR-0.5, (MIN_COOR+MAX_COOR)/2, 0.45);
        Font font = new Font("Arial", Font.BOLD, 24); //set the font
        StdDraw.setFont(font);
        //...
        //...Your implementation goes here
        //...
        if(mode == X_TURN){
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.text((MIN_COOR+MAX_COOR)/2, MIN_COOR-0.5, "X-s TURN");//These will need to be changed or removed
        }
        else if(mode == O_TURN) {
            StdDraw.setPenColor(StdDraw.RED);//These will need to be changed or removed
            StdDraw.text((MIN_COOR + MAX_COOR) / 2, MIN_COOR - 0.5, "O-s TURN");//These will need to be changed or removed
        }

    }




    /**
     * TO BE IMPLEMENTED BY YOU
     *
     * This method checks if we have a win on the board
     * (i.e. if there are 5 of the same marks next to each other
     * in any column, row, or diagonal).
     * If it is, DRAW A RED LINE accross the winning 5 marks AND return true.
     * If there is no win on the board, return false.
     */
    private static boolean haveWin(int[][] board){
        //Check rows:
        int col = (int)getMouseX();//as the Mouse position for x is in double value,I am using an int such that index matches the array index
        int row = 14 - (int)getMouseY();

        if(checkFiveRight(row, col, board)){
            System.out.println("right");
            drawHorizontalRed(row,col);
            return true;
        }
        //Check columns:
        if(checkFiveDown(row, col, board)){
            System.out.println("down");
            drawVerticalRed(row,col);
            return true;
        }

        //Check diagonals (left to right and down):
        if(checkDiagonalRightDown(row, col, board)){
            System.out.println("rd");
            drawDiagonalDown(row,col);
            return true;
        }
        //Check diagonals (left to right and up):
        if(checkDiagonalRightUp(row, col, board)){
            System.out.println("right up");
            drawDiagonalUp(row,col);
            return true;
        }


        return false;
    }


    //========================================
    // POTENTIALLY USEFUL FUNCTION TO BE CALLED FROM haveWin():
     // (You do not have to implement them though. These are just suggestions).

      static boolean checkFiveRight(int row, int col, int[][] b){
        int count_x = 0, count_o = 0;
          for(int i = 0;i < b.length; i++){

              if(count_x > 4){
                  end_x = i;
                  start_x = i - 5;
                  return true;
              }
              if(b[row][i] == 1){
                    count_x++;
              }
              else{
                  count_x = 0;
              }
          }
          for(int i = 0;i < b.length; i++){
              if(count_o > 4){
                  end_x = i;
                  start_x = i - 5;
                  return true;
              }
              if(b[row][i] == 2){
                  count_o++;
              }
              else{
                  count_o = 0;
              }
          }
        return false;
      }

    static boolean checkFiveDown(int row, int col, int[][] b){
        int count_x = 0, count_o = 0;
        for(int i = 0;i < b.length; i++){
            if(count_x > 4){
                start_y = i -5;
                end_y = i;
                return true;
            }
            if(b[i][col] == 1){
                count_x++;
            }
            else{
                count_x = 0;
            }
        }
        for(int i = 0;i < b.length; i++){
            if(count_o > 4){
                start_y = i -5;
                end_y = i;
                return true;
            }
            if(b[i][col] == 2){
                count_o++;
            }
            else{
                count_o = 0;
            }
        }
        return false;
      }

    static  boolean checkDiagonalRightDown(int row, int col, int[][] b){
          int count_x = 0, count_o = 0;

          for(int i = row, j = col; i < b.length && j < b.length; i++,j++){
              if(count_x > 4){
                  System.out.println(end_x+" "+start_x+" "+start_y+" "+end_y+" "+col+" "+row);

                  start_x = (int)mouse_x;
                  end_x = start_x + 5;
                  start_y = (int)mouse_y + 1 ;
                  end_y = start_y - 5;
                  return true;
              }
              if(b[i][j] == 1){
                  count_x++;
              }
              else {
                  count_x = 0;
              }

          }
          count_x = 0;


        for(int i = row, j = col; i >=0 && j >= 0; i--,j--){

            if(count_x > 4){
                end_x = col + 1 ;
                start_x = col - 4 ;
                start_y = (int)mouse_y + 5;
                end_y = (int)mouse_y;
                return true;
            }
            if(b[i][j] == 1){
                count_x++;
            }
            else {
                count_x = 0;
            }

        }
        for(int i = row, j = col; i < b.length && j < b.length; i++,j++){
            if(count_o > 4){
                start_x = (int)mouse_x;
                end_x = start_x + 5;
                start_y = (int)mouse_y + 1 ;
                end_y = start_y - 5;
                return true;
            }
            if(b[i][j] == 2){
                count_o++;
            }
            else {
                count_o = 0;
            }

        }

        count_o = 0;

        for(int i = row, j = col; i >=0 && j >= 0; i--,j--){

            if(count_o > 4){
                end_x = col + 1 ;
                start_x = col - 4 ;
                start_y = (int)mouse_y + 5;
                end_y = (int)mouse_y;
                return true;
            }
            if(b[i][j] == 2){
                count_o++;
            }
            else {
                count_o = 0;
            }

        }

          return false;

      }

    static  boolean checkDiagonalRightUp(int row, int col, int[][] b){
          int count_x = 0, count_o = 0;

          for(int i = row, j = col; i < b.length && j > 0; i++,j--){
              if(count_x > 4){


                  end_x = (int)mouse_x +1 ;
                  start_x = (int)mouse_x  - 4;
                  start_y = (int)mouse_y - 4;
                  end_y = (int)mouse_y + 1;
                  return true;
              }
              if(b[i][j] == 1){
                  count_x++;
              }
              else {
                  count_x = 0;
              }

          }
        count_x = 0;
        for(int i = row, j = col; i >= 0 && j < b.length; i--,j++){

            if(count_x > 4){
                end_x = (int)mouse_x  + 5;
                start_x = (int)mouse_x ;
                start_y = (int)mouse_y  ;
                end_y = (int)mouse_y + 5 ;
                return true;
            }
            if(b[i][j] == 1){
                count_x++;
            }
            else {
                count_x = 0;
            }

        }
        for(int i = row, j = col; i < b.length && j > 0; i++,j--){
            if(count_o > 4){
                end_x = (int)mouse_x +1 ;
                start_x = (int)mouse_x  - 4;
                start_y = (int)mouse_y - 4;
                end_y = (int)mouse_y + 1;
                return true;
            }
            if(b[i][j] == 2){
                count_o++;
            }
            else {
                count_o = 0;
            }

        }
        count_o = 0;
        for(int i = row, j = col; i >=0 && j < b.length; i--,j++){

            if(count_o > 4){
                end_x = (int)mouse_x  + 5;
                start_x = (int)mouse_x ;
                start_y = (int)mouse_y  ;
                end_y = (int)mouse_y + 5 ;
                return true;
            }
            if(b[i][j] == 2){
                count_o++;
            }
            else {
                count_o = 0;
            }

        }
          return false;

      }

      static void drawHorizontalRed(int row, int col){

          StdDraw.setPenRadius(0.015);
          start_y = (int)mouse_y;
          StdDraw.setPenColor(Color.red);
          StdDraw.line(start_x + 0.15,start_y + 0.5,end_x - 0.15,start_y + 0.5);
         }

        static void drawVerticalRed(int row, int col){
            StdDraw.setPenRadius(0.015);
            StdDraw.setPenColor(Color.red);
            row = (int) mouse_x;
            StdDraw.line(row + 0.5 ,15-start_y ,row + 0.5 ,15 - end_y );

        }

        static void drawDiagonalDown(int row, int col){
            StdDraw.setPenRadius(0.015);
            StdDraw.setPenColor(Color.red);
            StdDraw.line(start_x , start_y ,end_x , end_y  );

        }

        static void drawDiagonalUp(int row, int col){
            StdDraw.setPenRadius(0.015);
            StdDraw.setPenColor(Color.red);
            StdDraw.line(start_x , start_y ,end_x , end_y  );

        }





    //===============================================================================
    // DO NOT CHANGE CODE BELOW THIS LINE
    //===============================================================================  

    /**
     * This method erases everything from the drawing window,
     * and redraws grid, button, status line
     *
     * DON'T CHANGE IT!
     */
    private static void resetBoard(int [][] b){

        StdDraw.clear(); //Erase the whole window

        drawGrid();   //redraw the grid
        drawButton(); //redraw the buton
    }


    /**
     * Given the mode, decides which mark to draw,
     * and calls either drawX() or drawO() methods, passing them
     * the coordinates of the mouse click, mx and my.
     *
     * DON'T CHANGE IT!
     */
    private static void drawMark(double mx, double my, int mode){
        if (mode==X_TURN)
            drawX(mx, my);
        else if (mode==O_TURN)
            drawO(mx, my);
    }

    /**
     * Simply redirects to drawX(int, int) method.
     * DON'T CHANGE IT!
     */
    private static void drawX(double xx, double yy){
        drawX((int)xx, (int)yy);
    }

    /**
     * Simply redirects to drawO(int, int) method.
     * DON'T CHANGE IT!
     */
    private static void drawO(double xx, double yy){
        drawO((int)xx, (int)yy);
    }

    /**
     * The method draws the "button"
     * DON'T CHANGE IT!
     */
    private static void drawButton() {
        //Draw two rectangles to represent a "button" on top, with height of the button = 1.5: 
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        StdDraw.filledRectangle((MIN_COOR+MAX_COOR)/2, MAX_COOR+0.75, (MIN_COOR+MAX_COOR)/2, 0.75);
        //Draw inner rectangle inside the button of slightly different color:
        StdDraw.setPenColor(200, 200, 200);
        StdDraw.filledRectangle((MIN_COOR+MAX_COOR)/2, MAX_COOR+.75, (MIN_COOR+MAX_COOR)/2-0.2, 0.6);
        //Write "Roll Dice" on the button:
        Font font = new Font("Arial", Font.BOLD, 24);
        StdDraw.setFont(font);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.text(7.5, 15.75, "New Game");
    }

    /**
     * Imitates a button being "pushed" by briefly changing the color
     * DON'T CHANGE IT!
     */
    private static void showButtonPushed() {
        StdDraw.setPenColor(220, 220, 220);
        //StdDraw.filledRectangle(0.95, 0.75, 0.92, 0.22);        
        StdDraw.filledRectangle((MIN_COOR+MAX_COOR)/2, MAX_COOR+0.75, (MIN_COOR+MAX_COOR)/2-0.2, 0.6);
        Font font = new Font("Arial", Font.BOLD, 24);
        StdDraw.setPenColor(150, 150, 255);
        StdDraw.setFont(font);
        //StdDraw.text(0.95, 0.75, "Roll Dice");
        StdDraw.text((MIN_COOR+MAX_COOR)/2, MAX_COOR+0.75, "New Game");
        StdDraw.show();
        StdDraw.pause(177);
        drawButton();
        StdDraw.show();
    }
     static void setMouseXY(double mx, double my) {
        mouse_x = mx;
        mouse_y = my;
    }

    static double getMouseX(){
        return mouse_x;
    }

    static double getMouseY(){
        return mouse_y;
    }






    public static void main(String[] args) {
        // set the scale of the coordinate system
        StdDraw.setXscale(MIN_COOR, MAX_COOR);//x-axis
        StdDraw.setYscale(MIN_COOR-1, MAX_COOR+1.5);  //Leave space for button on top and status line at the bottom.

        double mx, my;//Variables to hold the x,y coordinated of the location of the mouse click

        //Initializing score variables:
        int scoreX=0;
        int scoreO = 0;

        //The mode variable describes which mode the game is in: starting mode, or "X-s turn" mode, and so on.
        int mode = START; //Begin in this mode.


        //Initialize empty board array (all zeros):
        int[][] board = new int[15][15];
        //We will assume that 0 means empty cell, 1 means X, and 2 means O

        //Initialize the graphical board:
        drawButton();
        //Write starting message on the board:
        Font font = new Font("Arial", Font.BOLD, 24);
        StdDraw.setFont(font);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.text((MIN_COOR+MAX_COOR)/2, (MIN_COOR+MAX_COOR)/2, "PUSH \"New Game\" button to start.");



        //The loop that is waiting for mouse clicks:
        while (true) {
            if (StdDraw.isMousePressed()) {  //if there is a mouse click, process it and follow the main game logic

                mx = StdDraw.mouseX(); //x-coordinate of the mouse
                my = StdDraw.mouseY(); //y-coordinate of the mose
                setMouseXY(mx,my);
                StdDraw.pause(200); //Pauses for a bit after a mouse click, to avoid multiple mouse events. DO NOT REMOVE!

                //================================================================================ 
                //Main game logic: different action is taken on mouse click depending on the mode:
                //================================================================================
                if (mode == X_TURN || mode==O_TURN) {//if it is either X-s turn or O-s turn (i.e. the game has started and have not ended yet)
                    if (my>MAX_COOR){//check if top Button is pushed
                        showButtonPushed();//just a visual effect
                        resetBoard(board); //Erases everything and redraws grid, button, status line
                        mode = X_TURN;
                        board = new int[15][15]; //reinitialize the board array
                        updateStatusBar(mode, scoreX, scoreO); //update the status bar at the bottom
                        continue;
                    }

                    if (my<MIN_COOR){//Status line in clicked
                        printBoard(board);
                        continue;
                    }

                    System.out.println("mode: PLAY");//For testing purposes
                    System.out.println("Mouse at: " + mx + " " + my);//For testing purpose
                    //Check if you can draw X or O 
                    if (tryPlacingMark(mx, my, board, mode)){//if the place is available 
                        drawMark(mx,my,mode);
                        if (haveWin(board)){//If you have a winning configuration on board
                            if (mode==X_TURN) {
                                mode=X_WIN;
                                scoreX++;
                                StdDraw.setPenColor(StdDraw.BLUE);
                                StdDraw.text((MIN_COOR+MAX_COOR)/2 - 5.25, MIN_COOR-0.5, "X wins!!!");
                                System.out.println("X WINS!!!!!");//For testing purposes
                            }else{// if (mode==O_TURN) 
                                mode=O_WIN;
                                scoreO++;
                                StdDraw.setPenColor(StdDraw.RED);//These will need to be changed or removed
                                StdDraw.text((MIN_COOR + MAX_COOR) - 5.25/ 2, MIN_COOR - 0.5, "O wins!!!");
                                System.out.println("O WINS!!!!!");//For testing purposes
                            }
                        }else{//Nobody won yet
                            mode=nextTurn(mode);
                        }


                        updateStatusBar(mode, scoreX, scoreO);
                    }
                }else if (mode == START || mode == X_WIN || mode == O_WIN) { //(the game is over and someone won it (X-s or O-s)
                    System.out.println("mode: START or WIN");//For testing purposes
                    System.out.println("Mouse at: " + mx + " " + my);//For testing purposes

                    if (my>MAX_COOR){//Top Button is pushed 
                        showButtonPushed();
                        resetBoard(board); //Erases everything and redraws grid, button, status line
                        mode = X_TURN;
                        board = new int[15][15];
                        updateStatusBar(mode, scoreX, scoreO);
                        continue;
                    }

                } else if (mode == DRAW) {//We ran out of spaces and no one won.
                    System.out.println("mode: DRAW");//For testing purposes
                    System.out.println("Mouse at: " + mx + " " + my);//For testing purposes
                } else {
                    //Do nothing
                    //Other modes may be added later.
                }



                printBoard(board);
                StdDraw.pause(300);//Pauses for a bit more after processing a mouse click, to avoid multiple mouse events. DO NOT REMOVE!

            }// end of if (isMousePressed())

        }// end of while loop   

    }// end of main()




}

/*  StdDraw.setPenColor(StdDraw.BLUE);//These will need to be changed or removed
                StdDraw.text((MIN_COOR + MAX_COOR) - 24.50/ 2, MIN_COOR - 0.5, scoreO +"");
                StdDraw.setPenColor(StdDraw.RED);//These will need to be changed or removed
                StdDraw.text((MIN_COOR + MAX_COOR) - 27.25/ 2, MIN_COOR - 0.5, scoreX +" :");

 */