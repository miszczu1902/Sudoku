package pl.first.firstjava;


import java.io.Serializable;
import java.util.ListResourceBundle;

public class Language_en extends ListResourceBundle implements Serializable {

    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"outOfField", "Must be <0,9>"},
                {"emptyList", "List is empty!"},
                {"cloneFailed", "Clone failed!"},
                {"conFail", "Cannot connect with database!"},
                {"queryFail", "Query didn't execute"},
                {"readFail", "Query didn't execute (file didn't read)"},
                {"writeFail", "Query didn't execute (file didn't write)"},
                {"closeFail", "Cannot close database connection!"},
                {"same", "In base exists that board (name)!"}
        };
    }
}