// Name & ID: Eseosa Travis Eweka  - 101583426
// Name & ID: Bank Aghedo          - 101534808
// Name & ID: Zuheb Mohamud        - 101555019
// Name & ID: Affan Telek          - 101565764
// Name & ID: Kamran Omar          - 101564373

public class MinimaxAI {

    // Called by GameController on the computer's turn.
    // Tries every empty cell, scores it with minimax, and returns the best one.
    public static int[] getBestMove(char[][] board, char aiSymbol) {

        char  humanSymbol = (aiSymbol == 'X') ? 'O' : 'X';
        int   bestScore   = Integer.MIN_VALUE;
        int[] bestMove    = {-1, -1};

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = aiSymbol;
                    int score = minimax(board, 0, false, aiSymbol, humanSymbol);
                    board[i][j] = ' ';

                    if (score > bestScore) {
                        bestScore  = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }
        return bestMove;
    }

    // Recursively scores a board state.
    // Positive scores favour the AI, negative scores favour the human.
    // Depth is subtracted so the AI prefers winning sooner rather than later.
    private static int minimax(char[][] board, int depth, boolean isMaximising,
                               char aiSymbol, char humanSymbol) {

        if (checkWin(board, aiSymbol))    return 10 - depth;
        if (checkWin(board, humanSymbol)) return -10 + depth;
        if (isBoardFull(board))           return 0;

        if (isMaximising) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = aiSymbol;
                        int score = minimax(board, depth + 1, false, aiSymbol, humanSymbol);
                        board[i][j] = ' ';
                        if (score > best) best = score;
                    }
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = humanSymbol;
                        int score = minimax(board, depth + 1, true, aiSymbol, humanSymbol);
                        board[i][j] = ' ';
                        if (score < best) best = score;
                    }
                }
            }
            return best;
        }
    }

    // Checks rows, columns, and diagonals on the raw array — no Board object needed
    private static boolean checkWin(char[][] board, char symbol) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) return true;
        }
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == symbol && board[1][j] == symbol && board[2][j] == symbol) return true;
        }
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) return true;
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) return true;
        return false;
    }

    private static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') return false;
            }
        }
        return true;
    }
}
