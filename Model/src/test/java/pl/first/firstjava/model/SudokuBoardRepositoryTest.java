package pl.first.firstjava.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SudokuBoardRepositoryTest {

    SudokuBoardRepository repository;

    @BeforeEach
    void setUp() {
        repository = new SudokuBoardRepository();
    }

    @Test
    void testCreateEmpty() throws CloneNotSupportedException {
        repository = new SudokuBoardRepository();
        SudokuBoard empty = repository.createEmptyBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertEquals(empty.get(i, j), 0);
            }
        }
    }

}