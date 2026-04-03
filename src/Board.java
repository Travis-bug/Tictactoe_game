public class Board {

    // --- PERSON 1: MODULE 1 (Board & Display) ---

    // TODO: 1. Declare 2D char array here (don't initialize it yet)
    private final char[][] grid;

    public Board() {
        // TODO: 2. Initialize 'grid' as a 3x3 char array
        grid = new char [3] [3];
        // TODO: 3. Use nested loops (row then col) to fill every spot in the array with a space character: ' '
        for (int i = 0; i < 3; i++ ) {
          for (int j = 0; j < 3; j++) {
              grid[i][j] = ' ';
          }
        }
    }

    public void printBoard() {
        // TODO: 1. Print the top column labels (e.g., "  0 1 2")
        System.out.println(" 0 1 2");
        // TODO: 2. Use a loop to go through each row
        for (int i = 0; i < 3; i++) {
            // TODO: 3. Print the row label number first
            System.out.print(i + " ");
            // TODO: 4. Use an inner loop to print each cell's value from 'grid'
            for (int j = 0; j < 3; j++) {
                // TODO: 5. Print the vertical separators "|" between columns
                System.out.print(grid[i][j]);
                if (j < 2) {
                    System.out.print(" | ");
                }
            }
                // TODO: 6. Print the horizontal separators "-----" between rows
                System.out.println();
                    if(i < 2) {
                        System.out.println("---------");
                    }
        }
    }

    public boolean isValidMove(int row, int col) {
        // TODO: 1. Check if 'row' and 'col' are within the bounds of the array (0 to 2). If out of bounds, return false.
        if (row < 0 || row > 2 || col > 2 || col < 0) {
            return false;
        }
        // TODO: 2. If they are in bounds, check if the grid at [row][col] is currently empty (contains ' ').
        else {
            if (grid[row][col] == ' ') {
                // TODO: 3. Return true if empty, false if already taken.
                return true;
            } else return false;
        }
    }        // NOTE: the if statement can be simplified into: return (grid[row][col] == ' '  ) I am leaving it in for clarity


    public void placeSymbol(int row, int col, char symbol) {
        // TODO: 1. Call your isValidMove(row, col) method to double-check.
        if (isValidMove(row, col)) {
            // TODO: 2. If valid, assign the 'symbol' to grid[row][col].
            grid[row][col] = symbol;
        }
    }

    public boolean isBoardFull() {
        // TODO: 1. Use nested loops to check every single cell in the grid.
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // TODO:  2. If you find a cell that contains an empty space (' '), immediately return false (the board is not full).
                    if (grid[i][j] == ' ') {
                        return false;
                    }
                }
            }
        // TODO: 3. If the loops finish completely without finding any empty spaces, return true.
        return true;
    }











    // --- PERSON 2 (Banks) : MODULE 2 (Win Detection) ---

    public boolean checkRows(char symbol) {
        // TODO: 1. Loop from i = 0 to 2 (the 3 rows).
        for(int i = 0; i < 3; i++) {
            // TODO: 2. In each iteration, check if grid[i][0] AND grid[i][1] AND grid[i][2] all equal the 'symbol'.
            if ( grid[i][0] == symbol && grid[i][1] == symbol && grid[i][2] == symbol) {
                // TODO: 3. If any row matches completely, return true.
                return true;
            }
        } // TODO: 4. If the loop finishes with no match, return false.
        return false;
    }

    public boolean checkCols(char symbol) {
        // TODO: 1. Loop from j = 0 to 2 (the 3 columns).
        for(int j = 0; j < 3; j++) {
            // TODO: 2. In each iteration, check if grid[0][j] AND grid[1][j] AND grid[2][j] all equal the 'symbol'.
            if (grid[0][j] == symbol && grid[1][j] == symbol && grid[2][j] == symbol) {
                // TODO: 3. If any column matches completely, return true.
                return true;
            }
        } // TODO: 4. If the loop finishes with no match, return false.
        return false; // placeholder return
    }

    public boolean checkDiagonals(char symbol) {
        // TODO: 1. Check the first diagonal: grid[0][0], grid[1][1], and grid[2][2]. Are they all equal to 'symbol'?
        boolean firstDiagonal = (grid[0][0] == symbol && grid[1][1] == symbol && grid[2][2] == symbol);
        // TODO: 2. Check the second diagonal: grid[0][2], grid[1][1], and grid[2][0]. Are they all equal to 'symbol'?
        boolean secondDiagonal =  (grid[0][2] == symbol && grid[1][1] == symbol && grid[2][0] == symbol);
          return firstDiagonal || secondDiagonal;
    } // NOTE: set the checks into a boolean variable and auto return it, if the checks are true, the booleans return true because the assignment conditions are met, else they return false


    public boolean checkWin(char symbol) {
        // TODO: 1. Call checkRows(symbol), checkCols(symbol), and checkDiagonals(symbol).
    if (checkRows(symbol) || checkCols(symbol) || checkDiagonals(symbol)) {
        // TODO: 2. If ANY of those three methods return true, then this method should return true (the player won).
        return true;
    }
    else
        // TODO: 3. Otherwise, return false.
        return false; // placeholder return
    }



    // --- UTILITY METHOD FOR PERSON 4 (Minimax AI) ---

    public char[][] getGrid() {
        // TODO:  Person 4's AI needs to evaluate the board state. Provide a way for them to access the array.
        return grid;
    }
}