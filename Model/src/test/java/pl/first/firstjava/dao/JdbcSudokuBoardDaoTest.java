package pl.first.firstjava.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.first.firstjava.exceptions.JdbcExecuteQueryException;
import pl.first.firstjava.model.SudokuBoard;

class JdbcSudokuBoardDaoTest {

    private SudokuBoard sudokuBoard = new SudokuBoard();

    @BeforeEach
    void setUp() {
        Dao<SudokuBoard> dbBoard = SudokuBoardDaoFactory.getDatabaseDao(
                "sudokuBoard");
    }

    @Test
    void testReadWrite() throws IOException {
        sudokuBoard.solveGame();
        Files.deleteIfExists(Paths.get("database.db"));
        try (Dao<SudokuBoard> dbBoard =
                     SudokuBoardDaoFactory.getDatabaseDao("sudokuBoard")) {
            dbBoard.write(sudokuBoard);
            SudokuBoard sudokuBoard1 = dbBoard.read();
            assertEquals(sudokuBoard, sudokuBoard1);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Test
    void testJdbcExecuteQueryException() {
        sudokuBoard.solveGame();
        try (Dao<SudokuBoard> dbBoard =
                     SudokuBoardDaoFactory.getDatabaseDao("sudokuBoard")) {
            assertThrows(JdbcExecuteQueryException.class,
                    () -> dbBoard.write(sudokuBoard));
        } catch (Exception e) {
            e.getMessage();
        }
    }
}