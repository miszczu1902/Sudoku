package pl.first.firstjava.exceptions;

import java.util.ResourceBundle;

public class JdbcExecuteQueryException extends JdbcDaoException {
    public JdbcExecuteQueryException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getLocalizedMessage() {
        ResourceBundle bundle = ResourceBundle.getBundle("pl.first.firstjava.Language");
        return bundle.getObject("queryFail").toString();
    }

    public JdbcExecuteQueryException() {
        super();
    }
}
