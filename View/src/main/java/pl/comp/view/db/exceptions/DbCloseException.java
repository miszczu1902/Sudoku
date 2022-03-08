package pl.comp.view.db.exceptions;

import java.sql.SQLException;

public class DbCloseException extends SQLException {
    public DbCloseException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
