package pl.comp.view.binders;

import java.util.regex.Pattern;
import javafx.scene.control.TextFormatter;

public class FieldFormatter extends TextFormatter<Object> {
    public FieldFormatter() {
        super(change -> {
            if (Pattern.compile("[1-9]|^$").matcher(change.getControlNewText()).matches()) {
                return change;
            } else {
                return null;
            }
        });
    }

}
