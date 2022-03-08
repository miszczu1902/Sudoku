package pl.first.firstjava.exceptions;

import java.util.ResourceBundle;

public class JdbcCloseConnectionException extends JdbcDaoException {

    public JdbcCloseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getLocalizedMessage() {
        ResourceBundle bundle = ResourceBundle.getBundle("pl.first.firstjava.Language");
        return bundle.getObject("closeFail").toString();
    }
}
