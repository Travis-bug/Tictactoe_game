import java.util.Scanner;

public class GameController {

    private final  Board board;
    private final PlayerManager players;
    private final Scanner sc;

    // Constructor gets Scanner
    public GameController(Board board, PlayerManager players, Scanner sc) {
        this.board = board;
        this.players = players;
        this.sc = sc;
    }

    public void startGame() {

        while (true) {

            board.printBoard();

            String name = players.getCurrentPlayerName();
            char symbol = players.getCurrentPlayerSymbol();
            int row = -1;
            int col = -1;


            // AI TURN — call MinimaxAI, no keyboard input needed
            if (players.isAITurn()) {

                int[] bestMove = MinimaxAI.getBestMove(board.getGrid(), symbol); // NOTE: this will work after adding the MinMax ai
                row = bestMove[0];
                col = bestMove[1];
                System.out.println("Computer plays at row " + row + ", col " + col + ".");



                // HUMAN TURN — prompt and validate input
                while (true) {
                    System.out.print(name + " (" + symbol + ") enter row and column: ");

                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid input. Enter numbers only.");
                        sc.next();
                        continue;
                    }
                    row = sc.nextInt();

                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid input. Enter numbers only.");
                        sc.next();
                        continue;
                    }
                    col = sc.nextInt();
                    sc.nextLine(); // clear buffer after nextInt

                    if (board.isValidMove(row, col)) {
                        break;
                    } else {
                        System.out.println("Invalid move. Cell is occupied or out of range. Try again.");
                    }
                }
            }


            // TODO: PLACE SYMBOL — same path for both human and AI

            board.placeSymbol(row, col, symbol);

            // WIN CHECK
            if (board.checkWin(symbol)) {
                board.printBoard();
                System.out.println(name + " (" + symbol + ") wins!");
                break;
            }

            // DRAW CHECK
            if (board.isBoardFull()) {
                board.printBoard();
                System.out.println("It's a draw!");
                break;
            }

            players.switchTurn();
        }
    }
}