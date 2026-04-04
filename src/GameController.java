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

            int row, col;

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

                sc.nextLine(); // clear buffer

                if (board.isValidMove(row, col)) {
                    break;
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            }

            board.placeSymbol(row, col, symbol);

            if (board.checkWin(symbol)) {
                board.printBoard();
                System.out.println(name + " (" + symbol + ") wins!");
                break;
            }

            if (board.isBoardFull()) {
                board.printBoard();
                System.out.println("It's a draw!");
                break;
            }

            players.switchTurn();
        }
    }
}