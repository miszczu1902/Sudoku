package pl.first.firstjava.model;

public class SudokuColumn extends SudokuCheckPart {
    public SudokuColumn(SudokuField[] fields) {
        super(fields);
    }

    @Override
    public SudokuColumn clone() throws CloneNotSupportedException {
        return (SudokuColumn) super.clone();
    }
}

