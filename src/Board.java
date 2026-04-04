// Name & ID: Eseosa Travis Eweka  - 101583426
// Name & ID: Bank Aghedo          - 101534808
// Name & ID: Zuheb Mohamud        - 101555019
// Name & ID: Affan Telek          - 101565764
// Name & ID: Kamran Omar          - 101564373

public class Board {

    private final char[][] grid;

    // Fills every cell with a space on startup
    public Board() {
        grid = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = ' ';
            }
        }
    }

    // Prints the board with row and column labels so players know what to enter
    public void printBoard() {
        System.out.println("  0 1 2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(grid[i][j]);
                if (j < 2) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < 2) {
                System.out.println("  -----");
            }
        }
    }

    // Returns true only if the cell is in bounds and not already taken
    public boolean isValidMove(int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            return false;
        }
        return grid[row][col] == ' ';
    }

    // Places the symbol — double-checks validity before writing
    public void placeSymbol(int row, int col, char symbol) {
        if (isValidMove(row, col)) {
            grid[row][col] = symbol;
        }
    }

    // Returns true when there are no empty cells left
    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == ' ') return false;
            }
        }
        return true;
    }

    // Checks all 3 rows for a winning line
    public boolean checkRows(char symbol) {
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] == symbol && grid[i][1] == symbol && grid[i][2] == symbol) {
                return true;
            }
        }
        return false;
    }

    // Checks all 3 columns for a winning line
    public boolean checkCols(char symbol) {
        for (int j = 0; j < 3; j++) {
            if (grid[0][j] == symbol && grid[1][j] == symbol && grid[2][j] == symbol) {
                return true;
            }
        }
        return false;
    }

    // Checks both diagonals for a winning line
    public boolean checkDiagonals(char symbol) {
        boolean mainDiag = (grid[0][0] == symbol && grid[1][1] == symbol && grid[2][2] == symbol);
        boolean antiDiag = (grid[0][2] == symbol && grid[1][1] == symbol && grid[2][0] == symbol);
        return mainDiag || antiDiag;
    }

    // Returns true if the given symbol has won by any line
    public boolean checkWin(char symbol) {
        return checkRows(symbol) || checkCols(symbol) || checkDiagonals(symbol);
    }

    // Exposes the raw array so MinimaxAI can evaluate the board state
    public char[][] getGrid() {
        return grid;
    }
}
