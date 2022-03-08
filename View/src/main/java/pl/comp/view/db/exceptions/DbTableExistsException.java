package pl.comp.view.db.exceptions;

import java.sql.SQLException;

public class DbTableExistsException extends SQLException {
    public DbTableExistsException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
