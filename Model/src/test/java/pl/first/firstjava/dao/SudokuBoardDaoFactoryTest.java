package pl.first.firstjava.dao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardDaoFactoryTest {

    @Test
    public void getFileDaoTest() {
        assertNotNull(SudokuBoardDaoFactory.getFileDao("xxx"));
    }

    @Test
    void getDatabaseDaoTest() {
        assertNotNull(SudokuBoardDaoFactory.getDatabaseDao("xxx"));
    }
}
