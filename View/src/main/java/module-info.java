module pl.comp.view {
    requires javafx.controls;
    requires javafx.fxml;
    requires ModelProject;
    requires javafx.graphics;
    requires org.apache.commons.lang3;
    requires java.logging;
    requires sqlite.jdbc;
    requires java.sql;

    exports pl.comp.view;
    opens pl.comp.view;
    exports pl.comp.view.binders;
    opens pl.comp.view.binders;
}