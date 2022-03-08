package pl.first.firstjava.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.first.firstjava.exceptions.EmptyListException;
import pl.first.firstjava.exceptions.SudokuFieldSetValueException;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuFieldTest {

    private SudokuField sudokuField;
    private SudokuField sudokuField2;

    @BeforeEach
    void setUp() {
        sudokuField = new SudokuField();
        sudokuField = new SudokuField(new SudokuBoard(
                new BacktrackingSudokuSolver()));
    }

    @Test
    void testConstructors() {
        assertThrows(SudokuFieldSetValueException.class, () -> {
            sudokuField = new SudokuField(10);
        });
        assertThrows(SudokuFieldSetValueException.class, () -> {
            sudokuField = new SudokuField(-1);
        });
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        assertThrows(SudokuFieldSetValueException.class, () -> {
            sudokuField = new SudokuField(-1, sudokuBoard);
        });
        assertThrows(SudokuFieldSetValueException.class, () -> {
            sudokuField = new SudokuField(11, sudokuBoard);
        });
    }

    @Test
    void testGetSetMethods() {
        sudokuField = new SudokuField(new SudokuBoard(
                new BacktrackingSudokuSolver()));
        sudokuField.setFieldValue(5);
        assertEquals(sudokuField.getFieldValue(), 5);
        assertThrows(SudokuFieldSetValueException.class, () -> {
            sudokuField.setFieldValue(10);
        });
        assertThrows(SudokuFieldSetValueException.class, () -> {
            sudokuField.setFieldValue(-1);
        });
    }

    @Test
    void testAddRemoveGetObserverMethods() {
        SudokuBoard sudokuBoard = new SudokuBoard(
                new BacktrackingSudokuSolver());
        sudokuField = new SudokuField();
        assertThrows(EmptyListException.class, () -> {
            sudokuField.removeObserver(sudokuBoard);
        });
        sudokuField.addObserver(sudokuBoard);
        assertEquals(sudokuField.getObservers().size(), 1);
        sudokuField.removeObserver(sudokuBoard);
        assertEquals(sudokuField.getObservers().size(), 0);

    }

    @Test
    void testToStringMethod() {
        assertNotNull(sudokuField.toString());
    }

    @Test
    void testClone() throws CloneNotSupportedException {
        sudokuField = new SudokuField(9);
        sudokuField2 = sudokuField.clone();
        assertEquals(sudokuField.equals(sudokuField2), sudokuField2.equals(sudokuField));
        assertEquals(sudokuField.getObservers(), sudokuField2.getObservers());
    }

    @Test
    void comapreToTest() throws CloneNotSupportedException {
        sudokuField = new SudokuField(4);
        assertThrows(NullPointerException.class, () -> {
            sudokuField.compareTo(null);
        });
        sudokuField2 = sudokuField.clone();
        assertEquals(sudokuField.compareTo(sudokuField2), 0);

        sudokuField2.setFieldValue(3);
        assertEquals(sudokuField.compareTo(sudokuField2), -1);

        sudokuField2.setFieldValue(5);
        assertEquals(sudokuField.compareTo(sudokuField2), 1);

    }

    @Test
    void testEqualsAndHashCodeMethod() {
        sudokuField = new SudokuField(9);
        sudokuField2 = new SudokuField(8);
        assertTrue(sudokuField.equals(sudokuField));
        assertEquals(sudokuField.equals(sudokuField2),
                sudokuField2.equals(sudokuField));
        sudokuField.setFieldValue(8);
        assertEquals(sudokuField.equals(sudokuField2),
                sudokuField2.equals(sudokuField));
        assertEquals(sudokuField.hashCode(),
                sudokuField2.hashCode());
        assertFalse(sudokuField.equals(null));
        SudokuBoard sudokuBoard = new SudokuBoard(
                new BacktrackingSudokuSolver());
        sudokuField.addObserver(sudokuBoard);
        assertFalse(sudokuField.equals(sudokuField)
                && sudokuField.equals(sudokuBoard));
        sudokuField = new SudokuField(9);
        if (sudokuField.hashCode() != sudokuField2.hashCode()) {
            assertNotEquals(sudokuField, sudokuField2);
        }


    }
}
