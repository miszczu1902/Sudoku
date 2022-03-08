package pl.first.firstjava.model;

import java.io.Serializable;

public interface SudokuObserver extends Serializable {
    void update();
}
