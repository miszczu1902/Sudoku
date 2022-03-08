package pl.first.firstjava.exceptions;

public class JdbcTableExistsException extends JdbcDaoException {
    public JdbcTableExistsException(Throwable cause) {
        super(cause);
    }
}
