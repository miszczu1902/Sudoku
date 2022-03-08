package pl.first.firstjava.exceptions;

import java.util.ResourceBundle;

public class JdbcReadException extends JdbcExecuteQueryException {
    public JdbcReadException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getLocalizedMessage() {
        ResourceBundle bundle = ResourceBundle.getBundle("pl.first.firstjava.Language");
        return bundle.getObject("readFail").toString();
    }
}
