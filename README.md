# Tic-Tac-Toe Team Assignment

> **🛑 STRICT ARRAY-ONLY RULE**
> All board states must use `char[][]` or `int[][]`. For moves, use `int[2]` (not `ArrayList`). **Do not use** `Stack`, `Queue`, `HashMap`, or any `java.util` collections. Violating this rule caps your maximum mark at **7.5/15**.

-----

## 🏗 Code Architecture

  * **`Main.java`** *(Entry Point)*
      * Reads mode choice. Creates the Board, Players, and Controller. Starts the game.
      * **↓ calls ↓**
  * **`GameController.java`** *(Game Loop)*
      * Alternates turns. Calls human input or AI move. Checks win/draw after each move. Prints the final result.
      * **↓ uses ↓**
  * **Core Components:**
      * **`Board.java`**: Houses the `char[3][3]` array. Handles `printBoard()`, `placeSymbol()`, `isValidMove()`, `isBoardFull()`, and `checkWin()`.
      * **`PlayerManager.java`**: Stores names and symbols. Handles turn switching and enforces the "X-goes-first" rule.
      * **`MinimaxAI.java`**: Contains the `minimax()` recursive method and `getBestMove()` to return optimal `[row, col]`.

-----

## 🔄 Game Workflow

1.  **Program Entry — `main()`** (`Main.java`)
      * Display the welcome banner.
      * Ask the user: "1 player (vs AI)" or "2 player". Route to the correct game mode.
2.  **Player Setup** (`PlayerManager.java`)
      * Prompt for player name(s) and symbol choice (X or O).
      * Assign the other symbol automatically. The player with **X always goes first**.
3.  **Initialise Board** (`Board.java`, `char[][]`)
      * Create a `char[3][3]` array and fill it with empty markers.
      * Print the empty grid with row/column indices shown.
4.  **Game Loop — Turn by Turn** (`GameController.java`, `MinimaxAI.java`)
      * Display the current board.
      * *Human's turn:* Prompt for row + column, validate the input, and place the symbol.
      * *AI's turn (1-player mode):* Run Minimax and place the optimal symbol automatically.
5.  **Check Win / Draw** (`Board.java`)
      * Check all 3 rows, 3 columns, and 2 diagonals for 3-in-a-row.
      * If all 9 squares are filled with no winner → Draw.
      * If neither → Continue loop.
6.  **Display Result & End Game** (`Main.java`, `GameController.java`)
      * Print the final board, announce the winner (name + symbol) or declare a draw.
      * Optionally prompt "Play again?".

-----

## 👥 Team Modules & Task Breakdown

| Member | Module Focus | Tasks | Marks / Tag |
| :--- | :--- | :--- | :--- |
| **Person 1** | **Module 1**<br>Board & Display | • Create `Board.java` with `char[3][3]` array<br>• `printBoard()` — display grid with row/col labels<br>• `isValidMove(row, col)` — bounds + empty check<br>• `placeSymbol(row, col, symbol)`<br>• `isBoardFull()` for draw detection | **10/15 marks**<br>`Core` |
| **Person 2** | **Module 2**<br>Win Detection | • `checkRows(symbol)` — 3 horizontal checks<br>• `checkCols(symbol)` — 3 vertical checks<br>• `checkDiagonals(symbol)` — 2 diagonal checks<br>• `checkWin(symbol)` — combines all above<br>• Thorough testing of all 8 win conditions | **10/15 marks**<br>`Core` |
| **Person 3** | **Module 3**<br>Player & Flow | • Prompt + store player names and symbols<br>• Enforce X goes first rule<br>• `switchTurn()` — alternate between players<br>• Main game loop in `GameController.java`<br>• Handle invalid row/col input gracefully | **10/15 marks**<br>`Core` |
| **Person 4** | **Module 4**<br>Minimax AI | • `minimax(board, depth, isMaximising)` recursion<br>• Score system: +10 AI wins, -10 human wins, 0 draw<br>• `getBestMove()` — iterate empty cells, pick max score<br>• Pass board state as array at each call (no objects)<br>• Integrate into 1-player game loop | **+5/15 marks**<br>`AI` |
| **Person 5** | **Module 5**<br>Integration & Submission | • Wire all modules together in `Main.java`<br>• Mode selection logic (1-player vs 2-player)<br>• End-to-end tests: 2p full game & 1p vs AI<br>• Add student names/numbers as comments to all files<br>• Paste code neatly into the submission document<br>• Zip **only** the `.java` classes (not the whole project) | **Deliverables**<br>`Zero late risk` |
