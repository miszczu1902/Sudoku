package pl.comp.view.db.exceptions;

import java.sql.SQLException;

public class DbExecuteQueryException extends SQLException {
    public DbExecuteQueryException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
