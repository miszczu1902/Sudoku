package pl.first.firstjava.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuCheckPartTest {
    private SudokuField[] sudokuFields = new SudokuField[9];
    private SudokuField[] sudokuFields2 = new SudokuField[9];
    private SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());

    private void setSudokuFields() {
        for (int i = 0; i < 9; i++) {
            sudokuFields[i] = new SudokuField(i + 1, sudokuBoard);
            sudokuFields2[i] = new SudokuField(8, sudokuBoard);
        }
    }

    @Test
    void testColumnVerifyMethod() {
        this.setSudokuFields();
        SudokuColumn sudokuColumn = new SudokuColumn(sudokuFields);
        assertEquals(sudokuColumn.verify(), true);
    }

    @Test
    void testRowVerifyMethod() {
        this.setSudokuFields();
        SudokuRow sudokuRow = new SudokuRow(sudokuFields);
        assertEquals(sudokuRow.verify(), true);
    }

    @Test
    void testBoxVerifyMethod() {
        this.setSudokuFields();
        SudokuBox sudokuBox = new SudokuBox(sudokuFields);
        assertEquals(sudokuBox.verify(), true);
    }

    @Test
    void badSetSudokuField() {
        this.setSudokuFields();
        for (int i = 0; i < 9; i++) {
            sudokuFields[i].setFieldValue(3);
        }
        SudokuRow sudokuRow = new SudokuRow(sudokuFields);
        assertEquals(sudokuRow.verify(), false);

    }

    @Test
    void testToStringMethod() {
        this.setSudokuFields();
        SudokuRow row = new SudokuRow(sudokuFields);
        assertNotNull(row.toString());
    }

    @Test
    void testEqualsAndHashCodeMethod() {
        this.setSudokuFields();
        SudokuRow row = new SudokuRow(sudokuFields);
        SudokuRow row2 = new SudokuRow(sudokuFields2);
        assertTrue(row.equals(row));
        assertEquals(row.equals(row2), row2.equals(row));
        row2 = new SudokuRow(sudokuFields);
        assertEquals(row.equals(row2), row2.equals(row));
        assertEquals(row.hashCode(), row2.hashCode());
        assertFalse(row.equals(null));
        SudokuBox box = new SudokuBox(sudokuFields2);
        assertFalse(row.equals(row)
                && row.equals(box));
        row = new SudokuRow(sudokuFields);
        row2 = new SudokuRow(sudokuFields2);
        if (row.hashCode() != row2.hashCode()) {
            assertNotEquals(row, row2);
        }

    }

    @Test
    void testCloneMethod() throws CloneNotSupportedException {
        this.setSudokuFields();
        SudokuBox sudokuBox = new SudokuBox(sudokuFields);
        SudokuBox sudokuBox1 = sudokuBox.clone();
        assertEquals(sudokuBox.equals(sudokuBox1), sudokuBox1.equals(sudokuBox));
        SudokuRow sudokuRow = new SudokuRow(sudokuFields);
        SudokuRow sudokuRow2 = sudokuRow.clone();
        assertEquals(sudokuRow.equals(sudokuRow2), sudokuRow2.equals(sudokuRow2));
        SudokuColumn sudokuColumn = new SudokuColumn(sudokuFields);
        SudokuColumn sudokuColumn2 = sudokuColumn.clone();
        assertEquals(sudokuColumn.equals(sudokuColumn2), sudokuColumn2.equals(sudokuColumn));

    }

}
