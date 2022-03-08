package pl.first.firstjava.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardTest {
    private SudokuBoard sudokuBoard;
    private SudokuBoard sudokuBoard2;
    private BacktrackingSudokuSolver solver;

    @BeforeEach
    public void setUp() {
        solver = new BacktrackingSudokuSolver();
        sudokuBoard = new SudokuBoard(solver);
    }

    private void setSudoku() {
        solver = new BacktrackingSudokuSolver();
        sudokuBoard = new SudokuBoard(solver);
        sudokuBoard.solveGame();
        sudokuBoard2 = new SudokuBoard(solver);
        sudokuBoard.solveGame();
    }

    @Test
    void getSetMethodTest() {
        assertEquals(sudokuBoard.get(0, 0), 0);
        sudokuBoard.set(0, 0, 9);
        assertEquals(sudokuBoard.get(0, 0), 9);
    }

    @Test
    void solveGameMethodTest() {
        setSudoku();
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                assertNotEquals(sudokuBoard.get(x, y), 0);
            }
        }
    }

    @Test
    void testGetFieldMethod() {
        setSudoku();
        SudokuField sudokuField = sudokuBoard.getField(2, 3);
        assertNotEquals(sudokuField, null);
    }

    @Test
    void testGetRowMethod() {
        setSudoku();
        for (int i = 0; i < 9; i++) {
            SudokuRow sudokuRow = sudokuBoard.getRow(i);
            assertNotEquals(sudokuRow, null);
        }
    }

    @Test
    void testGetColumnMethod() {
        setSudoku();
        for (int i = 0; i < 9; i++) {
            SudokuColumn sudokuColumn = sudokuBoard.getColumn(i);
            assertNotEquals(sudokuColumn, null);
        }
    }

    @Test
    void testGetBoxMethod() {
        setSudoku();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                SudokuBox sudokuBox = sudokuBoard.getBox(i, j);
                assertNotEquals(sudokuBox, null);
            }
        }
    }

    @Test
    void testCorrectBoardMethod() {
        sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                sudokuBoard.set(x, y, 4);
            }
        }
        for (int i = 0; i < 9; i++) {
            assertFalse(sudokuBoard.getRow(i).verify());
        }
        for (int i = 0; i < 9; i++) {
            assertFalse(sudokuBoard.getColumn(i).verify());
        }
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                assertFalse(sudokuBoard.getBox(x, y).verify());
            }
        }
    }

    @Test
    void testIsSetCorrectBoardMethod() {
        setSudoku();
        assertTrue(sudokuBoard.isCorrectBoard());
    }

    @Test
    void testToStringMethod() {
        setSudoku();
        assertNotEquals(sudokuBoard.toString().length(), 0);

    }

    @Test
    void testEqualsAndHashCodeMethod() {
        setSudoku();
        assertTrue(sudokuBoard.equals(sudokuBoard));
        assertEquals(sudokuBoard.equals(sudokuBoard2),
                sudokuBoard2.equals(sudokuBoard));
        assertNotEquals(sudokuBoard.hashCode(),
                sudokuBoard2.hashCode());
        sudokuBoard2 = sudokuBoard;
        assertEquals(sudokuBoard.equals(sudokuBoard2),
                sudokuBoard2.equals(sudokuBoard));
        assertEquals(sudokuBoard.hashCode(),
                sudokuBoard2.hashCode());
        assertFalse(sudokuBoard.equals(null));
        assertFalse(sudokuBoard.equals(sudokuBoard)
                && sudokuBoard.equals(sudokuBoard.getRow(0)));
        setSudoku();
        if (sudokuBoard.hashCode() != sudokuBoard2.hashCode()) {
            assertNotEquals(sudokuBoard, sudokuBoard2);
        }
    }

    @Test
    void testClone() {
        setSudoku();
        SudokuBoard sudokuBoard1 = sudokuBoard.clone();
        assertEquals(sudokuBoard1.equals(sudokuBoard), sudokuBoard.equals(sudokuBoard1));
        sudokuBoard1.set(0, 0, 0);
        assertNotEquals(sudokuBoard1, sudokuBoard);
    }

    @Test
    void testToStringFieldsValues() {
        setSudoku();
        assertEquals(sudokuBoard.toStringFieldsValues().length(), 81);
    }

    @Test
    void testFieldsValuesToString() {
        setSudoku();
        String tmp = sudokuBoard.toStringFieldsValues();
        sudokuBoard2.fieldsValuesFromString(tmp);
        String tmp2 = sudokuBoard2.toStringFieldsValues();
        assertEquals(tmp, tmp2);
    }
}
