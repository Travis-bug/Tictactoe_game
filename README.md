# COMP2080 — Tic-Tac-Toe with AI

**Data Structures and Algorithms · George Brown College · Winter 2026**
**Instructor:** Andrew Rudder
**Due:** Friday, April 3rd 2026 

> **🛑 Strict array-only rule**
> All board state must use `char[][]` or `int[][]`. For moves, use `int[2]` — not `ArrayList`.
> **Do not use** `Stack`, `Queue`, `HashMap`, or any `java.util` collections anywhere in the project.
---

## Table of contents

1. [Game overview](#1-game-overview)
2. [Game workflow](#2-game-workflow)
3. [Code architecture](#3-code-architecture)
4. [Team modules](#4-team-modules)
5. [Submission format](#5-submission-format)
6. [Late penalty](#6-late-penalty)

---

## 1. Game overview

Tic-Tac-Toe is played on a 3×3 grid. Two players (or one human vs the computer) take turns placing their symbol — X or O — in an empty square. The first player to place three of their symbols in a continuous line (row, column, or diagonal) wins. If all nine squares are filled with no winner, the game ends in a draw.

The program must support two modes selectable at startup:

- **2-player** — two human players take turns at the same keyboard.
- **1-player** — one human player versus an intelligent AI that uses the **Minimax algorithm** to always make the optimal move.

---

## 2. Game workflow

### Step 1 — Program entry `Main.java`
Display a welcome banner. Prompt the user to select a mode:
```
1. 1 Player (vs AI)
2. 2 Players
```
Route to the appropriate setup based on their choice.

---

### Step 2 — Player setup `PlayerManager.java`
**2-player mode:** prompt each player for their name and symbol (X or O). Reject duplicate symbol choices.
**1-player mode:** prompt the human player for their name and symbol. The AI is assigned the remaining symbol automatically.
The player holding **X always goes first**, whether that is the human or the AI.

---

### Step 3 — Initialise board `Board.java`
Create a `char[3][3]` array and fill every cell with a space `' '`. Print the empty grid with row and column index labels so players know how to enter coordinates.

Example output:
```
   0     1     2
0 [ ] | [ ] | [ ]
  ----|-----|----
1 [ ] | [ ] | [ ]
  ----|-----|----
2 [ ] | [ ] | [ ]
```

---

### Step 4 — Game loop `GameController.java` + `MinimaxAI.java`
Repeat until the game is over:

1. Display the current board state.
2. Determine whose turn it is.
   - **Human's turn:** Prompt for a row number (0–2) and a column number (0–2). Validate the input — reject out-of-bounds values and already-occupied cells. Re-prompt until a valid move is entered.
   - **AI's turn (1-player mode):** Call `MinimaxAI.getBestMove()`. Display a message such as `"Computer plays at row X, col Y"`. Place the symbol automatically with no player input.
3. Place the symbol on the board via `Board.placeSymbol()`.
4. Proceed to the win/draw check below.

---

### Step 5 — Win and draw detection `Board.java`
After every move, check the board state:

- **Win:** check all 3 rows, all 3 columns, and both diagonals for three identical symbols in a line. If found, the current player wins.
- **Draw:** if `isBoardFull()` returns true and no win was detected, the game ends in a draw.
- **Continue:** if neither condition is met, switch to the next player and return to Step 4.

---

### Step 6 — Display result `GameController.java` / `Main.java`
Print the final board. Announce the winner by name and symbol, or declare a draw. Optionally prompt `"Play again? (y/n)"` and restart from Step 1 if selected.

---

## 3. Code architecture

```
Main.java
  └── GameController.java
        ├── Board.java          (char[3][3] — the only data structure)
        ├── PlayerManager.java
        └── MinimaxAI.java      (1-player mode only)
```

### `Main.java`
Entry point. Reads the mode selection from `Scanner`. Instantiates `Board`, `PlayerManager`, and `GameController`. Calls `GameController.startGame()`.

### `GameController.java`
Owns the main game loop. Alternates turns between players using `PlayerManager.switchTurn()`. On each iteration: prints the board, collects or computes a move, places it, checks win/draw, and breaks the loop when the game ends. Prints the final result.

### `Board.java`
Encapsulates the `char[3][3]` grid and all board operations.

| Method | Purpose |
|---|---|
| `printBoard()` | Renders the grid with row/col index labels |
| `isValidMove(row, col)` | Returns true if the cell is in bounds and empty |
| `placeSymbol(row, col, symbol)` | Places a char on the board after validating the move |
| `isBoardFull()` | Returns true when no `' '` cells remain |
| `checkRows(symbol)` | Checks all 3 rows for a win |
| `checkCols(symbol)` | Checks all 3 columns for a win |
| `checkDiagonals(symbol)` | Checks both diagonals for a win |
| `checkWin(symbol)` | Combines the three check methods above |
| `getGrid()` | Returns the raw `char[][]` for use by `MinimaxAI` |

### `PlayerManager.java`
Stores player names and symbol assignments. Tracks whose turn it is. Provides `getCurrentPlayer()` and `switchTurn()`. Enforces the X-goes-first rule at initialisation.

### `MinimaxAI.java`
Implements the Minimax algorithm. Called only in 1-player mode.

```java
// Core recursive method signature
int minimax(char[][] board, int depth, boolean isMaximising)

// Entry point — returns the best move as int[2] {row, col}
int[] getBestMove(char[][] board)
```

Scoring convention: `+10` if the AI wins, `-10` if the human wins, `0` for a draw. Depth can optionally be subtracted from the score to prefer faster wins.

> `getBestMove` iterates over all empty cells using nested `for` loops on the `char[][]` directly — no `ArrayList` or other collection is used to store candidate moves.

---

## 4. Team modules

### Person 1 — Module 1: Board & Display
**File:** `Board.java`
**Marks:** Core (10/15)

Tasks:
- Declare and initialise `char[3][3] grid` with `' '` in the constructor using nested loops.
- Implement `printBoard()` with row/col labels and `|` / `---` separators.
- Implement `isValidMove(int row, int col)`.
- Implement `placeSymbol(int row, int col, char symbol)`.
- Implement `isBoardFull()`.

---

### Person 2 — Module 2: Win Detection
**File:** `Board.java` (win detection methods)
**Marks:** Core (10/15)

Tasks:
- Implement `checkRows(char symbol)` — loop `i = 0` to **2** (inclusive), check `grid[i][0..2]`.
- Implement `checkCols(char symbol)` — loop `j = 0` to **2** (inclusive), check `grid[0..2][j]`.
- Implement `checkDiagonals(char symbol)` — use `&&` (not `||`) for each diagonal check.
- Implement `checkWin(char symbol)` — OR the three methods together.
- Test all 8 winning lines manually before integrating.

---

### Person 3 — Module 3: Player & Game Flow
**Files:** `PlayerManager.java`, `GameController.java`
**Marks:** Core (10/15)

Tasks:
- Prompt for player name(s) and symbol, enforce X-goes-first, store in `PlayerManager`.
- Implement `switchTurn()`.
- Write the main game loop in `GameController` calling Board and PlayerManager methods.
- Handle invalid user input (out-of-range coordinates, occupied cells) with re-prompts.

---

### Person 4 — Module 4: Minimax AI
**File:** `MinimaxAI.java`
**Marks:** +5/15

Tasks:
- Implement `minimax(char[][] board, int depth, boolean isMaximising)` recursively.
- Base cases: check `Board.checkWin()` for either symbol, and `Board.isBoardFull()` for draw.
- Implement `getBestMove(char[][] board)` iterating empty cells with nested `for` loops only.
- Use `int[2]` to return the best `{row, col}` — no `ArrayList`.
- Integrate into `GameController` for 1-player mode.

---

### Person 5 — Module 5: Integration, Testing & Submission
**Files:** `Main.java`, all files (comments + packaging)
**Marks:** Both deliverables / zero late-penalty risk

Tasks:
- Wire all modules in `Main.java` with mode selection logic.
- Run full end-to-end tests: 2-player game (all outcomes), 1-player vs AI (win, lose, draw).
- Add all student names and student numbers as a comment block at the top of every `.java` file.
- Paste all code neatly into the submission document template.
- Zip **only** the `.java` class files — not the entire project folder.
- Submit before midnight on April 3rd.

---

## 5. Submission format

Two items must be submitted per group (one submission total):

1. **Completed submission document** — the template provided with the project, with all group member names at the top and all code pasted in neatly.
2. **Zipped `.java` source files** — zip only the Java classes you created. Do not include the full project. All student names and numbers must appear as a comment at the top of each class.

> Failing to follow the submission format results in an automatic deduction of **2 marks**.

---

## 6. Late penalty

| Submission time | Maximum mark |
|---|---|
| By 11:59 PM, April 3rd | 15 / 15 |
| After midnight, April 4th | 10.5 / 15 (−30%) |
| After midnight, April 5th | 6 / 15 (−60%) |

