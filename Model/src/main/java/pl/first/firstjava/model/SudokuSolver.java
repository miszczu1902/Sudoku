package pl.first.firstjava.model;

import java.io.Serializable;

public interface SudokuSolver extends Serializable {
    void solve(SudokuBoard board);
}
