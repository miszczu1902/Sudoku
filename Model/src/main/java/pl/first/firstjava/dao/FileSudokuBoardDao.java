package pl.first.firstjava.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import pl.first.firstjava.exceptions.DaoException;
import pl.first.firstjava.exceptions.FileReadException;
import pl.first.firstjava.exceptions.FileWriteException;
import pl.first.firstjava.model.SudokuBoard;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {
    private String filename;

    public FileSudokuBoardDao(String filename) {
        this.filename = filename + ".txt";
    }

    @Override
    public SudokuBoard read() throws DaoException {
        SudokuBoard obj = null;
        try (ObjectInputStream fileReader = new ObjectInputStream(
                new FileInputStream(filename))) {
            obj = (SudokuBoard) fileReader.readObject();
            return obj;
        } catch (ClassNotFoundException | IOException e) {
            throw new FileReadException(e);
        }
    }

    @Override
    public void write(SudokuBoard obj) throws DaoException {
        try (ObjectOutputStream fileWriter = new ObjectOutputStream(
                new FileOutputStream(filename))) {
            fileWriter.writeObject(obj);
        } catch (IOException e) {
            throw new FileWriteException(e);
        }
    }

    @Override
    public void close() {
    }
}
