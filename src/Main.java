import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Board board = new Board();
        PlayerManager pm = new PlayerManager(sc);
        pm.setupPlayers();

        GameController game = new GameController(board, pm, sc);
        game.startGame();
    }
}