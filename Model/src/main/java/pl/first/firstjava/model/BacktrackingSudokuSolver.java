package pl.first.firstjava.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BacktrackingSudokuSolver implements SudokuSolver {
    private List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

    public BacktrackingSudokuSolver() {
    }

    public void solve(SudokuBoard board) {
        backtracking(board);
    }

    private boolean backtracking(SudokuBoard board) {
        Collections.shuffle(numbers);
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (board.get(row, column) == 0) {
                    for (int i = 0; i < numbers.size(); i++) {
                        if (this.isCorrectInBoard(numbers.get(i), row, column, board)) {
                            board.set(row, column, numbers.get(i));
                            if (backtracking(board)) {
                                return true;
                            } else {
                                board.set(row, column, 0);
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean correctRow(int number, int row, SudokuBoard board) {
        for (int i = 0; i < 9; i++) {
            if (board.get(row, i) == number) {
                return true;
            }
        }
        return false;
    }

    private boolean correctColumn(int number, int column, SudokuBoard board) {
        for (int i = 0; i < 9; i++) {
            if (board.get(i, column) == number) {
                return true;
            }
        }
        return false;
    }

    private boolean correctSquare(int number, int row, int column, SudokuBoard board) {
        int squareRow = row - row % 3;
        int squareCol = column - column % 3;

        for (int i = squareRow; i < squareRow + 3; i++) {
            for (int j = squareCol; j < squareCol + 3; j++) {
                if (board.get(i, j) == number) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isCorrectInBoard(int number, int row, int column, SudokuBoard board) {
        return !correctRow(number, row, board)
                && !correctColumn(number, column, board)
                && !correctSquare(number, row, column, board);
    }
}
