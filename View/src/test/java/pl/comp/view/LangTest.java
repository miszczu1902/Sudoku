package pl.comp.view;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.ResourceBundle;

public class LangTest {

    @Test
    public void locale()
    {
        Locale polish = new Locale.Builder().setLanguage("pl").build();
        Locale english = new Locale.Builder().setLanguage("en").build();
        ResourceBundle bundle = ResourceBundle.getBundle("lang.App",english);
        String helloWorld = bundle.getString("helloWorld");
        System.out.println(helloWorld);
        assertEquals(helloWorld,"hello World");
        ResourceBundle bundle2 = ResourceBundle.getBundle("lang.App",polish);
        String helloWorld2 = bundle2.getString("helloWorld");
        assertEquals(helloWorld2,"Witaj swiecie");
    }


}
