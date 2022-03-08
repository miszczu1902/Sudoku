package pl.comp.view.binders;

import javafx.util.converter.NumberStringConverter;

public class FieldStringConverter extends NumberStringConverter {
    @Override
    public Number fromString(String value) {
        if (value.length() == 0) {
            return 0;
        }
        return super.fromString(value);
    }

    @Override
    public String toString(Number value) {
        if (value.intValue() == 0) {
            return "";
        }
        return super.toString(value);
    }
}
