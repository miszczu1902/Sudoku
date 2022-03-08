package pl.first.firstjava.model;

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SudokuBoard implements Serializable, SudokuObserver, Cloneable {

    private SudokuField[][] board = new SudokuField[9][9];

    private SudokuSolver sudokuSolver;

    private boolean isCorrectBoard = false;

    public SudokuBoard() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                board[x][y] = new SudokuField(this);
            }
        }
        this.sudokuSolver = new BacktrackingSudokuSolver();
    }

    public SudokuBoard(SudokuSolver sudokuSolver) {

        this.sudokuSolver = sudokuSolver;
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                board[x][y] = new SudokuField(this);
            }
        }
    }

    public void solveGame() {

        sudokuSolver.solve(this);
    }

    private boolean checkBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!this.getColumn(i).verify()
                        || !this.getRow(j).verify()
                        || !this.getBox(i, j).verify()) {
                    return false;
                }
            }
        }
        return true;
    }

    public int get(int x, int y) {
        return board[x][y].getFieldValue();
    }

    public void set(int x, int y, int value) {
        board[x][y].setFieldValue(value);
    }

    public SudokuField getField(int x, int y) {
        return board[x][y];
    }

    public SudokuRow getRow(int y) {
        SudokuField[] fields = new SudokuField[9];
        for (int x = 0; x < 9; x++) {
            fields[x] = new SudokuField(board[y][x].getFieldValue(), this);
        }
        return new SudokuRow(fields);
    }

    public SudokuColumn getColumn(int x) {
        SudokuField[] fields = new SudokuField[9];
        for (int y = 0; y < 9; y++) {
            fields[y] = new SudokuField(board[x][y].getFieldValue(), this);
        }
        return new SudokuColumn(fields);
    }

    public SudokuBox getBox(int x, int y) {
        SudokuField[] fields = new SudokuField[9];
        int boxRow = x - x % 3;
        int boxCol = y - y % 3;

        int position = 0;
        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = boxCol; j < boxCol + 3; j++) {
                fields[position++] = new SudokuField(
                        board[i][j].getFieldValue(), this);
            }
        }
        return new SudokuBox(fields);
    }

    @Override
    public void update() {
        if (checkBoard()) {
            setCorrectBoard(checkBoard());
        }
    }

    public boolean isCorrectBoard() {
        return isCorrectBoard;
    }

    public void setCorrectBoard(boolean correctBoard) {
        isCorrectBoard = correctBoard;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("board", board).toString();
    }

    public String toStringFieldsValues() {
        String toStringFieldValues = "";
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                toStringFieldValues += String.valueOf(this.get(x, y));
            }
        }
        return toStringFieldValues;
    }

    public void fieldsValuesFromString(String valuesAsString) {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                this.set(x, y, Character.getNumericValue(
                        valuesAsString.charAt(x * 9 + y)));
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SudokuBoard that = (SudokuBoard) o;

        return new EqualsBuilder().append(board, that.board).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(board).toHashCode();
    }

    @Override
    public SudokuBoard clone() {
        SudokuBoard clone = new SudokuBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                clone.set(i, j, this.get(i, j));
            }
        }
        return clone;
    }
}