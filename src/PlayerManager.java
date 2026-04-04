import java.util.Scanner;

public class PlayerManager {

    private String player1Name;
    private String player2Name;
    private char player1Symbol;
    private char player2Symbol;
    private int currentPlayer;

    private final Scanner sc;

    // Constructor receives Scanner
    public PlayerManager(Scanner sc) {
        this.sc = sc;
    }

    public void setupPlayers() {

        System.out.print("Enter Player 1 name: ");
        player1Name = sc.nextLine();

        // VALIDATE Player 1 symbol
        while (true) {
            System.out.print(player1Name + ", choose X or O: ");
            String input = sc.nextLine().toUpperCase();
            if (input.isEmpty()) {System.out.println("Invalid input. Enter X or O."); continue;}
            player1Symbol = input.charAt(0);

            if (player1Symbol == 'X' || player1Symbol == 'O') {
                break;
            }
            System.out.println("Invalid input. Enter X or O.");
        }

        System.out.print("Enter Player 2 name: ");
        player2Name = sc.nextLine();

        // VALIDATE Player 2 symbol
        while (true) {
            System.out.print(player2Name + ", choose X or O: ");
            String input = sc.nextLine().toUpperCase();
            if (input.isEmpty()) {System.out.println("Invalid input. Enter X or O."); continue;}
            player2Symbol = input.charAt(0);

            if ((player2Symbol == 'X' || player2Symbol == 'O') && player2Symbol != player1Symbol) {
                break;
            }
            System.out.println("Invalid or already taken. Choose the other symbol.");
        }

        // X goes first
        currentPlayer = (player1Symbol == 'X') ? 1 : 2;
    }

    public String getCurrentPlayerName() {
        return (currentPlayer == 1) ? player1Name : player2Name;
    }

    public char getCurrentPlayerSymbol() {
        return (currentPlayer == 1) ? player1Symbol : player2Symbol;
    }

    public void switchTurn() {
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
    }
}