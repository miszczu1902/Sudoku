package pl.first.firstjava.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BacktrackingSudokuSolverTest {

    private SudokuBoard board1;
    private SudokuBoard board2;
    private BacktrackingSudokuSolver solver;

    @BeforeEach
    void setUp() {
        solver = new BacktrackingSudokuSolver();
    }

    @Test
    public void testSolveMethod() {

        solver = new BacktrackingSudokuSolver();
        board1 = new SudokuBoard(solver);
        board2 = new SudokuBoard(solver);

        solver.solve(board1);
        solver.solve(board2);

        int countRepeat = 0;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board1.get(i, j) == board2.get(i, j)) {
                    countRepeat++;
                }
            }
        }

        assertNotEquals(countRepeat, 81);
    }

    @Test
    public void testIsCorrectRowMethod() {
        solver = new BacktrackingSudokuSolver();
        board1 = new SudokuBoard(solver);
        solver.solve(board1);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int m = j + 1; m < 9; m++) {
                    if (board1.get(i, j) != board1.get(i, m)) {
                        assertNotEquals(board1.get(i, j), board1.get(i, m));
                    }
                }
            }
        }
    }

    @Test
    void testIsCorrectColumnMethod() {
        solver = new BacktrackingSudokuSolver();
        board1 = new SudokuBoard(solver);
        solver.solve(board1);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int m = j + 1; m < 9; m++) {
                    if (board1.get(i, j) != board1.get(m, j)) {
                        assertNotEquals(board1.get(i, j), board1.get(m, j));
                    }
                }
            }
        }
    }

    @Test
    public void testIsCorrectInBoardMethod() {
        solver = new BacktrackingSudokuSolver();
        board1 = new SudokuBoard(solver);
        solver.solve(board1);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int field = 0; field < 9; field++) {
                    for (int field1 = field + 1; field1 < 9; field1++) {
                        if (board1.get(i * 3 + (field / 3), j * 3 + (field % 3)) !=
                                board1.get(i * 3 + (field1 / 3), j * 3 + (field1 % 3))) {
                            assertNotEquals(board1.get(i * 3 + (field / 3), (j * 3 + (field % 3)))
                                    , board1.get(i * 3 + (field1 / 3), j * 3 + (field1 % 3)));
                        }
                    }
                }
            }
        }
    }
}
