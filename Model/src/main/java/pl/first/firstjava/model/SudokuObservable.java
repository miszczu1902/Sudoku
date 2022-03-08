package pl.first.firstjava.model;

import java.io.Serializable;

public interface SudokuObservable extends Serializable {
    void addObserver(SudokuObserver observer);

    void removeObserver(SudokuObserver observer);
}
