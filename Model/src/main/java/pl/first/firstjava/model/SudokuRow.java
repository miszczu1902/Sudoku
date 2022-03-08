package pl.first.firstjava.model;

public class SudokuRow extends SudokuCheckPart {
    public SudokuRow(SudokuField[] fields) {
        super(fields);
    }

    @Override
    public SudokuRow clone() throws CloneNotSupportedException {
        return (SudokuRow) super.clone();
    }
}
