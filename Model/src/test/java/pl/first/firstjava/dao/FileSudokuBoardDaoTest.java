package pl.first.firstjava.dao;

import org.apache.commons.lang3.SystemUtils;
import org.junit.jupiter.api.Test;
import pl.first.firstjava.exceptions.FileReadException;
import pl.first.firstjava.exceptions.FileWriteException;
import pl.first.firstjava.model.BacktrackingSudokuSolver;
import pl.first.firstjava.model.SudokuBoard;

import static org.junit.jupiter.api.Assertions.*;

public class FileSudokuBoardDaoTest {
    private SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
    private SudokuBoard newSudokuBoard = null;
    private Dao<SudokuBoard> file;


    @Test
    public void testReadAndWriteMethod() {
        sudokuBoard.solveGame();
        try (Dao<SudokuBoard> file = SudokuBoardDaoFactory.getFileDao("testFile")) {
            file.write(sudokuBoard);
            newSudokuBoard = file.read();
            assertEquals(newSudokuBoard, sudokuBoard);
        } catch (java.lang.Exception e) {
            System.out.println("exception test");
        }
    }

    @Test
    void testFileNotFound() {
        assertThrows(FileReadException.class, () -> {
            SudokuBoardDaoFactory.getFileDao("yyyy").read();
        });
    }

    @Test
    void testWriteIOException() {
        if (SystemUtils.IS_OS_WINDOWS) {
            file = SudokuBoardDaoFactory.getFileDao("?");
        } else if (SystemUtils.IS_OS_LINUX) {
            file = SudokuBoardDaoFactory.getFileDao("/");
        } else {
            file = SudokuBoardDaoFactory.getFileDao("?");
        }
        assertThrows(FileWriteException.class, () -> {
            file.write(sudokuBoard);
        });
    }
}
