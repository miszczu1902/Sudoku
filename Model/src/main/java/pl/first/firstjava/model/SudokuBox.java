package pl.first.firstjava.model;

public class SudokuBox extends SudokuCheckPart {
    public SudokuBox(SudokuField[] fields) {
        super(fields);
    }

    @Override
    public SudokuBox clone() throws CloneNotSupportedException {
        return (SudokuBox) super.clone();
    }
}
