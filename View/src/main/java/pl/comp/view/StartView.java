package pl.comp.view;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import pl.comp.view.db.DatabaseOperations;
import pl.first.firstjava.dao.Dao;
import pl.first.firstjava.dao.FileSudokuBoardDao;
import pl.first.firstjava.dao.JdbcSudokuBoardDao;
import pl.first.firstjava.dao.SudokuBoardDaoFactory;
import pl.first.firstjava.exceptions.DaoException;
import pl.first.firstjava.exceptions.FileReadException;
import pl.first.firstjava.exceptions.JdbcExecuteQueryException;
import pl.first.firstjava.exceptions.LoadSceneFailException;
import pl.first.firstjava.model.SudokuBoard;

public class StartView {

    private static final Logger logger = Logger.getLogger(StartView.class.getName());
    private DatabaseOperations operations = new DatabaseOperations();
    @FXML
    private ChoiceBox<DifficultyLevel> difficultyChoiceOption;
    public Locale locale;
    public Label authors;
    @FXML
    private ListView<String> listView = new ListView<>();
    private Authors authorsClass = new Authors();
    private ResourceBundle bundle = ResourceBundle.getBundle("lang.App");
    private FXMLLoader fxmlLoader = Scenes.sudoku.getFxmlLoader(bundle);

    @FXML
    private void initialize() throws SQLException {
        for (var difficulty : DifficultyLevel.values()) {
            difficultyChoiceOption.getItems().add(difficulty);
        }
        difficultyChoiceOption.setValue(DifficultyLevel.LATWY);
        authorsClass.setBundle(bundle);
        authors.setText(bundle.getString("Authors")
                + authorsClass.getObject("1") + " & "
                + authorsClass.getObject("2"));
        listView.getItems().addAll(this.operations.selectAll());
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    protected void onStartButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = Scenes.sudoku.getFxmlLoader(bundle);
            Scene scene = Scenes.getJavaFxScene(fxmlLoader);
            SudokuView sudokuSceneController = fxmlLoader.getController();
            sudokuSceneController.prepareSudokuBoardToGame(difficultyChoiceOption.getValue());
            Scenes.begin.load(Scenes.getStageFromEvent(event), scene);
            logger.info(bundle.getString("start"));
        } catch (IOException e) {
            Scenes.showSceneErrorAlert();
            logger.info(
                    String.valueOf(new LoadSceneFailException(bundle.getString("loadfail"),
                            e)));
        }
    }

    @FXML
    protected void onLoadBoardClickButtonFromFile(ActionEvent event) throws IOException {
        File file2 = new File("lastBoard.txt");
        File file3 = new File("clonedLastBoard.txt");
        if (file2.length() != 0 && file3.length() != 0) {
            try {
                Dao<SudokuBoard> file = SudokuBoardDaoFactory.getFileDao("lastBoard");
                Dao<SudokuBoard> cloned = SudokuBoardDaoFactory.getFileDao("clonedLastBoard");
                FXMLLoader fxmlLoader = Scenes.sudoku.getFxmlLoader(bundle);
                Scene scene = Scenes.getJavaFxScene(fxmlLoader);
                SudokuView sudokuSceneController = fxmlLoader.getController();
                sudokuSceneController.prepareSudokuBoardToGameFromFile((FileSudokuBoardDao) cloned,
                        (FileSudokuBoardDao) file);
                Scenes.begin.load(Scenes.getStageFromEvent(event), scene);
                logger.info(bundle.getString("fileloaded"));
            } catch (DaoException e) {
                Scenes.showSceneErrorAlert();
                logger.info(new FileReadException(e).getMessage());
            }
        }
    }

    @FXML
    protected void prepareSudokuBoardToGameFromDataBase(ActionEvent event) throws IOException {
        if (this.listView.getSelectionModel().getSelectedItem() != null) {
            try {
                Dao<SudokuBoard> jdbcFile = SudokuBoardDaoFactory.getDatabaseDao(
                        this.listView.getSelectionModel().getSelectedItem());
                FXMLLoader fxmlLoader = Scenes.sudoku.getFxmlLoader(bundle);
                Scene scene = Scenes.getJavaFxScene(fxmlLoader);
                SudokuView sudokuSceneController = fxmlLoader.getController();
                sudokuSceneController.prepareSudokuBoardToGameFromDataBase(
                        (JdbcSudokuBoardDao) jdbcFile);
                Scenes.begin.load(Scenes.getStageFromEvent(event), scene);
                logger.info(bundle.getString("fileloaded"));
            } catch (DaoException e) {
                Scenes.showSceneErrorAlert();
                logger.info(new JdbcExecuteQueryException(
                        e.getLocalizedMessage(), e).getMessage());
            }
        }
    }


    @FXML
    void polish(ActionEvent event) {
        try {
            locale = new Locale("pl");
            Locale.setDefault(new Locale("pl"));
            bundle = ResourceBundle.getBundle("Lang.App", locale);
            fxmlLoader = Scenes.begin.getFxmlLoader(bundle);
            Scene scene = Scenes.getJavaFxScene(fxmlLoader);
            Scenes.begin.load(Scenes.getStageFromEvent(event), scene);
            logger.info(bundle.getString("polishset"));
        } catch (IOException e) {
            Scenes.showSceneErrorAlert();
            logger.info(String.valueOf(
                    new LoadSceneFailException(bundle.getString("loadfail"), e)));
        }
    }

    @FXML
    void english(ActionEvent event) {
        try {
            locale = new Locale("en");
            Locale.setDefault(new Locale("en"));
            bundle = ResourceBundle.getBundle("Lang.App", locale);
            fxmlLoader = Scenes.begin.getFxmlLoader(bundle);
            Scene scene = Scenes.getJavaFxScene(fxmlLoader);
            Scenes.begin.load(Scenes.getStageFromEvent(event), scene);
            logger.info(bundle.getString("englishset"));
        } catch (IOException e) {
            Scenes.showSceneErrorAlert();
            logger.info(String.valueOf(
                    new LoadSceneFailException(bundle.getString("loadfail"), e)));
        }
    }
}

