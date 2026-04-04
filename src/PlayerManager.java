// Name & ID: Eseosa Travis Eweka  - 101583426
// Name & ID: Bank Aghedo          - 101534808
// Name & ID: Zuheb Mohamud        - 101555019
// Name & ID: Affan Telek          - 101565764
// Name & ID: Kamran Omar          - 101564373

import java.util.Scanner;

public class PlayerManager {

    private String  player1Name;
    private String  player2Name;    // set to "Computer" in 1-player mode
    private char    player1Symbol;
    private char    player2Symbol;
    private int     currentPlayer;  // 1 = player1, 2 = player2 / AI
    private int     mode;           // 1 = vs computer, 2 = two human players
    private Scanner sc;

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
        // X always goes first regardless of who picked it
        currentPlayer = (player1Symbol == 'X') ? 1 : 2;
    }

    // Human picks their name and symbol. Computer gets whatever is left.
    private void setupOnePlayer() {
        System.out.print("Enter your name: ");
        player1Name = sc.nextLine();

        while (true) {
            System.out.print(player1Name + ", choose X or O: ");
            String input = sc.nextLine().toUpperCase();

            if (input.isEmpty()) {
                System.out.println("Invalid input. Enter X or O.");
                continue;
            }
            player1Symbol = input.charAt(0);
            if (player1Symbol == 'X' || player1Symbol == 'O') break;
            System.out.println("Invalid input. Enter X or O.");
        }

        player2Symbol = (player1Symbol == 'X') ? 'O' : 'X';
        player2Name   = "Computer";
        System.out.println("Computer will play as " + player2Symbol + ".");
    }

    // Both players pick their own names and symbols. Duplicate symbols are rejected.
    private void setupTwoPlayers() {
        System.out.print("Enter Player 1 name: ");
        player1Name = sc.nextLine();

        while (true) {
            System.out.print(player1Name + ", choose X or O: ");
            String input = sc.nextLine().toUpperCase();

            if (input.isEmpty()) {
                System.out.println("Invalid input. Enter X or O.");
                continue;
            }
            player1Symbol = input.charAt(0);
            if (player1Symbol == 'X' || player1Symbol == 'O') break;
            System.out.println("Invalid input. Enter X or O.");
        }

        System.out.print("Enter Player 2 name: ");
        player2Name = sc.nextLine();

        while (true) {
            System.out.print(player2Name + ", choose X or O: ");
            String input = sc.nextLine().toUpperCase();

            if (input.isEmpty()) {
                System.out.println("Invalid or already taken. Choose the other symbol.");
                continue;
            }
            player2Symbol = input.charAt(0);
            if ((player2Symbol == 'X' || player2Symbol == 'O') && player2Symbol != player1Symbol) break;
            System.out.println("Invalid or already taken. Choose the other symbol.");
        }
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

    // Returns true when it is the computer's turn in 1-player mode
    public boolean isAITurn() {
        return (mode == 1) && (currentPlayer == 2);
    }
}
