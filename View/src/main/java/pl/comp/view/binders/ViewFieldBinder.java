package pl.comp.view.binders;

import javafx.beans.property.SimpleIntegerProperty;
import pl.first.firstjava.model.SudokuField;

public class ViewFieldBinder extends SimpleIntegerProperty {
    public ViewFieldBinder(SudokuField sudokuField) {
        super(sudokuField.getFieldValue());
        this.addListener((v, oldValue, newValue) -> sudokuField.setFieldValue(newValue.intValue()));
    }
}
