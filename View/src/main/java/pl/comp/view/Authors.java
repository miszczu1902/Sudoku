package pl.comp.view;

import java.util.ListResourceBundle;
import java.util.ResourceBundle;

public class Authors extends ListResourceBundle {
    private ResourceBundle bundle = ResourceBundle.getBundle("lang.App");

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"1", bundle.getString("Author1")},
                {"2", bundle.getString("Author2")}
        };
    }
}
