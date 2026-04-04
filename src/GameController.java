// Name & ID: Eseosa Travis Eweka  - 101583426
// Name & ID: Bank Aghedo          - 101534808
// Name & ID: Zuheb Mohamud        - 101555019
// Name & ID: Affan Telek          - 101565764
// Name & ID: Kamran Omar          - 101564373

import java.util.Scanner;

public class GameController {

    private final Board         board;
    private final PlayerManager players;
    private final Scanner       sc;

    public GameController(Board board, PlayerManager players, Scanner sc) {
        this.board   = board;
        this.players = players;
        this.sc      = sc;
    }

    public void startGame() {

        while (true) {

            board.printBoard();

            String name   = players.getCurrentPlayerName();
            char   symbol = players.getCurrentPlayerSymbol();
            int    row    = -1;
            int    col    = -1;

            if (players.isAITurn()) {

                // AI computes its move — no input needed from the user
                int[] bestMove = MinimaxAI.getBestMove(board.getGrid(), symbol);
                row = bestMove[0];
                col = bestMove[1];
                System.out.println("Computer plays at row " + row + ", col " + col + ".");

            } else {

                // Human turn — keep asking until a valid cell is entered
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
                    sc.nextLine();

                    if (board.isValidMove(row, col)) {
                        break;
                    }
                    System.out.println("That cell is already taken or out of range. Try again.");
                }
            }

            // Place the symbol — same path for both human and AI
            board.placeSymbol(row, col, symbol);

            // Check for a win after every move
            if (board.checkWin(symbol)) {
                board.printBoard();
                System.out.println(name + " (" + symbol + ") wins!");
                break;
            }

            // Check for a draw after every move
            if (board.isBoardFull()) {
                board.printBoard();
                System.out.println("It's a draw!");
                break;
            }

            players.switchTurn();
        }
    }
}
