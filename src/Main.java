// Name & ID: Eseosa Travis Eweka  - 101583426
// Name & ID: Bank Aghedo          - 101534808
// Name & ID: Zuheb Mohamud        - 101555019
// Name & ID: Affan Telek          - 101565764
// Name & ID: Kamran Omar          - 101564373

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=====================================");
        System.out.println("       Welcome to Tic-Tac-Toe        ");
        System.out.println("=====================================");
        System.out.println("  1. 1 Player (vs Computer)");
        System.out.println("  2. 2 Players");

        int mode = -1; // REVIEW: check this later it might be rudundant
        while (true) {
            System.out.print("Select a mode (1 or 2): ");
            if (sc.hasNextInt()) {
                mode = sc.nextInt();
                sc.nextLine();
                if (mode == 1 || mode == 2) break;
            } else {
                sc.next();
            }
            System.out.println("Invalid choice. Please enter 1 or 2.");
        }

        Board         board = new Board();
        PlayerManager pm    = new PlayerManager(sc, mode);
        pm.setupPlayers();

        GameController game = new GameController(board, pm, sc);
        game.startGame();

        sc.close();
    }
}
