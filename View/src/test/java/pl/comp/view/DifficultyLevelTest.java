package pl.comp.view;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import pl.first.firstjava.model.BacktrackingSudokuSolver;
import pl.first.firstjava.model.SudokuBoard;

class DifficultyLevelTest {
    SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
    DifficultyLevel difficultyLevel;

    private int countBlankFields() {
        int blankFields = 0;
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (this.sudokuBoard.get(x, y) == 0) {
                    blankFields++;
                }
            }
        }
        return blankFields;
    }

    @Test
    void testLATWY() {
        sudokuBoard.solveGame();
        difficultyLevel = DifficultyLevel.LATWY;
        difficultyLevel.clearFields(sudokuBoard);
        assertEquals(this.countBlankFields(), 10);
    }

    @Test
    void testSREDNI() {
        sudokuBoard.solveGame();
        difficultyLevel = DifficultyLevel.SREDNI;
        difficultyLevel.clearFields(sudokuBoard);
        assertEquals(this.countBlankFields(), 30);
    }

    @Test
    void testTRUDNY() {
        sudokuBoard.solveGame();
        difficultyLevel = DifficultyLevel.TRUDNY;
        difficultyLevel.clearFields(sudokuBoard);
        assertEquals(this.countBlankFields(), 50);
    }

}