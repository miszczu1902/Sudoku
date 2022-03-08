package pl.first.firstjava.exceptions;

import java.util.ResourceBundle;

public class JdbcConnectionException extends JdbcDaoException {
    public JdbcConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getLocalizedMessage() {
        ResourceBundle bundle = ResourceBundle.getBundle("pl.first.firstjava.Language");
        return bundle.getObject("conFail").toString();
    }
}
