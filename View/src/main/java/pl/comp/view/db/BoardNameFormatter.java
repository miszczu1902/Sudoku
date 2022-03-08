package pl.comp.view.db;

import java.util.regex.Pattern;
import javafx.scene.control.TextFormatter;

public class BoardNameFormatter extends TextFormatter<Object> {
    public BoardNameFormatter() {
        super(change -> {
            if (Pattern.compile("[0-9a-zA-Z]{1,20}|^$").matcher(change.getControlNewText())
                    .matches()) {
                return change;
            } else {
                return null;
            }
        });
    }
}
