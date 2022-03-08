package pl.first.firstjava.exceptions;

public class SudokuFieldSetValueException extends IllegalArgumentException {
    public SudokuFieldSetValueException(String s) {
        super(s);
    }
}
