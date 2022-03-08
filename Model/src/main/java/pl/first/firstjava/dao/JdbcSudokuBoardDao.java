package pl.first.firstjava.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import pl.first.firstjava.exceptions.DaoException;
import pl.first.firstjava.exceptions.JdbcCloseConnectionException;
import pl.first.firstjava.exceptions.JdbcConnectionException;
import pl.first.firstjava.exceptions.JdbcExecuteQueryException;
import pl.first.firstjava.exceptions.JdbcReadException;
import pl.first.firstjava.exceptions.JdbcSameBoardException;
import pl.first.firstjava.exceptions.JdbcTableExistsException;
import pl.first.firstjava.exceptions.JdbcWriteException;
import pl.first.firstjava.model.SudokuBoard;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard> {

    private String filename;
    private String jdbcUrl;

    public JdbcSudokuBoardDao(String filename) {
        this.filename = filename;
        this.jdbcUrl = "jdbc:sqlite:database.db";
    }

    private Connection connect(String jdbcurl) throws DaoException {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(jdbcurl);
            connection.setAutoCommit(false);
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            throw new JdbcConnectionException(e.getLocalizedMessage(), e);
        }
    }

    private void createTable(Connection connection) throws DaoException {
        String createTable = "create table SudokuBoardTable(" + "id integer primary key,"
                + "boardName varchar(20), "
                + "field integer check (field between 0 and 9),"
                + "isEditable integer check (isEditable between 0 and 1))";
        try (Statement statement = connection.createStatement()) {
            statement.execute(createTable);
            connection.commit();
        } catch (SQLException e) {
            throw new JdbcExecuteQueryException(e.getLocalizedMessage(), e);
        }
    }

    private boolean ifTableExists(Connection connection) throws DaoException {
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, "SudokuBoardTable", null);
            return resultSet.next();
        } catch (SQLException e) {
            throw new JdbcTableExistsException(e);
        }
    }

    private boolean ifBoardExists(Connection connection) throws DaoException {
        String selectedData = "select * from SudokuBoardTable where boardName=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectedData)) {
            preparedStatement.setString(1, filename);
            ResultSet resultSet = preparedStatement.executeQuery();
            connection.commit();
            if (resultSet.next()) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new JdbcCloseConnectionException(e.getLocalizedMessage(), e);
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new JdbcSameBoardException(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public SudokuBoard read() throws DaoException {
        SudokuBoard sudokuBoard = new SudokuBoard();
        Connection connection = this.connect(jdbcUrl);
        String selectedData = "select * from " + "SudokuBoardTable" + " where boardName=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectedData)) {
            preparedStatement.setString(1, filename);
            ResultSet resultSet = preparedStatement.executeQuery();
            int x = 0;
            int y = 0;
            while (resultSet.next()) {
                sudokuBoard.set(x, y % 9, resultSet.getInt(3));
                if (resultSet.getInt(4) == 0) {
                    sudokuBoard.getField(x, y % 9).setEditable(false);
                } else {
                    sudokuBoard.getField(x, y % 9).setEditable(true);
                }
                y++;
                if (y % 9 == 0) {
                    x++;
                }
            }
            connection.commit();
        } catch (SQLException e) {
            throw new JdbcReadException(e.getLocalizedMessage(), e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            throw new JdbcCloseConnectionException(e.getLocalizedMessage(), e);
        }
        return sudokuBoard;
    }

    @Override
    public void write(SudokuBoard obj) throws DaoException {
        Connection connection = this.connect("jdbc:sqlite:database.db");
        if (!this.ifTableExists(connection)) {
            this.createTable(connection);
        }
        if (this.ifBoardExists(connection)) {
            return;
        }
        String insertData =
                "insert into SudokuBoardTable("
                        + "boardName ,field, isEditable) values (?,?,?)";
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(insertData)) {
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 9; y++) {
                    preparedStatement.setString(1, filename);
                    if (obj.getField(x, y).isEditable()) {
                        preparedStatement.setInt(2, 0);
                        preparedStatement.setInt(3, 1);
                    } else {
                        preparedStatement.setInt(2, obj.get(x, y));
                        preparedStatement.setInt(3, 0);
                    }
                    preparedStatement.executeUpdate();
                }
            }
            connection.commit();

        } catch (SQLException e) {
            throw new JdbcWriteException(e.getLocalizedMessage(), e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            throw new JdbcCloseConnectionException(
                    e.getLocalizedMessage(), e);
        }
    }

    @Override
    public void close() {
    }
}