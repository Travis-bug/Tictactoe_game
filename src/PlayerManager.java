import java.util.Scanner;

public class PlayerManager {

    private String player1Name;
    private String player2Name;       // "Computer" in 1-player mode
    private char   player1Symbol;
    private char   player2Symbol;
    private int    currentPlayer;     // 1 = player1, 2 = player2/AI
    private int    mode;              // 1 = one-player vs AI, 2 = two-player
    private Scanner sc;

    // Constructor receives the shared Scanner and the chosen game mode
    public PlayerManager(Scanner sc, int mode) {
        this.sc   = sc;
        this.mode = mode;
    }

    public void setupPlayers() {

        if (mode == 1) {
            setupOnePlayer();
        } else {
            setupTwoPlayers();
        }

        // X always goes first regardless of mode
        currentPlayer = (player1Symbol == 'X') ? 1 : 2;
    }

    // ---------------------------------------------------------------
    // 1-PLAYER SETUP
    // Human picks name + symbol. AI gets the remaining symbol.
    // ---------------------------------------------------------------
    private void setupOnePlayer() {

        System.out.print("Enter your name: ");
        player1Name = sc.nextLine();

        // Validate human symbol choice
        while (true) {
            System.out.print(player1Name + ", choose X or O: ");
            String input = sc.nextLine().toUpperCase();

            if (input.isEmpty()) {
                System.out.println("Invalid input. Enter X or O.");continue;}
            player1Symbol = input.charAt(0);

            if (player1Symbol == 'X' || player1Symbol == 'O') {break;}
            System.out.println("Invalid input. Enter X or O.");
        }

        // AI automatically gets the other symbol
        player2Symbol = (player1Symbol == 'X') ? 'O' : 'X';
        player2Name   = "Computer";

        System.out.println("Computer will play as " + player2Symbol + ".");
    }

    // ---------------------------------------------------------------
    // 2-PLAYER SETUP
    // Both humans pick their own names and symbols.
    // ---------------------------------------------------------------
    private void setupTwoPlayers() {

        // Player 1 name
        System.out.print("Enter Player 1 name: ");
        player1Name = sc.nextLine();

        // Player 1 symbol — validated
        while (true) {
            System.out.print(player1Name + ", choose X or O: ");
            String input = sc.nextLine().toUpperCase();

            if (input.isEmpty()) {System.out.println("Invalid input. Enter X or O.");continue;}
            player1Symbol = input.charAt(0);

            if (player1Symbol == 'X' || player1Symbol == 'O') {
                break;
            }
            System.out.println("Invalid input. Enter X or O.");
        }

        // Player 2 name
        System.out.print("Enter Player 2 name: ");
        player2Name = sc.nextLine();

        // Player 2 symbol — validated, must differ from Player 1
        while (true) {
            System.out.print(player2Name + ", choose X or O: ");
            String input = sc.nextLine().toUpperCase();

            if (input.isEmpty()) {System.out.println("Invalid or already taken. Choose the other symbol.");continue;}

            player2Symbol = input.charAt(0);

            if ((player2Symbol == 'X' || player2Symbol == 'O') && player2Symbol != player1Symbol) {
                break;
            }
            System.out.println("Invalid or already taken. Choose the other symbol.");
        }
    }

    // ---------------------------------------------------------------
    // NOTE: to self these are ACCESSORS
    // ---------------------------------------------------------------
    public String getCurrentPlayerName() {
        return (currentPlayer == 1) ? player1Name : player2Name;
    }

    public char getCurrentPlayerSymbol() {
        return (currentPlayer == 1) ? player1Symbol : player2Symbol;
    }

    public void switchTurn() {
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
    }

    // Tells rhe GameController whether the current turn belongs to the AI or not .
    // In 2-player mode this always returns false.
    public boolean isAITurn() {
        return (mode == 1) && (currentPlayer == 2);
    }
}
