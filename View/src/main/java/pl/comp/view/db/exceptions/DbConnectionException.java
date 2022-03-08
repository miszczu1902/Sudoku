package pl.comp.view.db.exceptions;

import java.sql.SQLException;

public class DbConnectionException extends SQLException {
    public DbConnectionException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
