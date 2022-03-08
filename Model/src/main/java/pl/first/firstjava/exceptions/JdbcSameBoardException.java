package pl.first.firstjava.exceptions;

import java.util.ResourceBundle;

public class JdbcSameBoardException extends JdbcExecuteQueryException {
    public JdbcSameBoardException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getLocalizedMessage() {
        ResourceBundle bundle = ResourceBundle.getBundle("pl.first.firstjava.Language");
        return bundle.getObject("same").toString();
    }

    public JdbcSameBoardException() {
        super();
    }
}
