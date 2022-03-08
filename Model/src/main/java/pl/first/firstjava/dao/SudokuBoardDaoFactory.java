package pl.first.firstjava.dao;

import pl.first.firstjava.model.SudokuBoard;

public class SudokuBoardDaoFactory {

    public static Dao<SudokuBoard> getFileDao(String fileName) {
        return new FileSudokuBoardDao(fileName);
    }

    public static Dao<SudokuBoard> getDatabaseDao(String filename) {
        return new JdbcSudokuBoardDao(filename);
    }
}
