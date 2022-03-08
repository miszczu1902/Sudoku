module ModelProject {
    requires org.apache.commons.lang3;
    requires java.sql;
    requires sqlite.jdbc;

    exports pl.first.firstjava.dao;
    opens pl.first.firstjava.dao;
    exports pl.first.firstjava.model;
    opens pl.first.firstjava.model;
    exports pl.first.firstjava.exceptions;
}