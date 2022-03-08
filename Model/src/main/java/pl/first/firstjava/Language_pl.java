package pl.first.firstjava;

import java.io.Serializable;
import java.util.ListResourceBundle;

public class Language_pl extends ListResourceBundle implements Serializable {

    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"outOfField", "Musi byc <0,9>"},
                {"emptyList", "Lista jest pusta!"},
                {"cloneFailed", "Nie udalo się sklonowac"},
                {"conFail", "Nie połączono z bazą danych!"},
                {"queryFail", "Nie wykonano kwerendy"},
                {"readFail", "Nie wykonano kwerendy (nie odczytano plku)"},
                {"writeFail", "Nie wykonano kwerendy (nie zapisano plku)"},
                {"closeFail", "Nie zamknięto połączenia z baża danych"},
                {"same", "Plansza o takiej nazwie jest już w bazie!"}
        };
    }
}
