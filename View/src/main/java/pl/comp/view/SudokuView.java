package pl.comp.view;

import static java.util.ResourceBundle.getBundle;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pl.comp.view.binders.FieldFormatter;
import pl.comp.view.binders.FieldStringConverter;
import pl.comp.view.binders.ViewFieldBinder;
import pl.comp.view.db.BoardNameFormatter;
import pl.first.firstjava.dao.Dao;
import pl.first.firstjava.dao.FileSudokuBoardDao;
import pl.first.firstjava.dao.JdbcSudokuBoardDao;
import pl.first.firstjava.dao.SudokuBoardDaoFactory;
import pl.first.firstjava.exceptions.DaoException;
import pl.first.firstjava.exceptions.FileReadException;
import pl.first.firstjava.model.BacktrackingSudokuSolver;
import pl.first.firstjava.model.SudokuBoard;

public class SudokuView {

    private SudokuBoard sudokuBoard;
    private SudokuBoard clonedSudokuBoard;
    private final TextField[][] sudokuGridFields;
    private final ViewFieldBinder[][] viewFieldBinders;
    private GridPane sudokuGrid;
    private static final Logger logger = Logger.getLogger(StartView.class.getName());
    @FXML
    private HBox sudokuGridContainer;
    @FXML
    private TextField dbFieldName;

    public SudokuView() {
        sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuGridFields = new TextField[9][9];
        viewFieldBinders = new ViewFieldBinder[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuGridFields[i][j] = new TextField();
            }
        }
    }

    @FXML
    private void initialize() {
        prepare();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuGrid.add(sudokuGridFields[i][j], i, j);
            }
        }
        this.dbFieldName.setTextFormatter(new BoardNameFormatter());
    }

    @FXML
    private void comingBack(ActionEvent event) {
        try {
            ResourceBundle bundle = getBundle("lang.App");
            FXMLLoader fxmlLoader = Scenes.begin.getFxmlLoader(bundle);
            Stage stage = Scenes.getStageFromEvent(event);
            Scene scene = Scenes.getJavaFxScene(fxmlLoader);
            Scenes.begin.load(stage, scene);
            logger.info(bundle.getString("comeback"));
        } catch (IOException e) {
            Scenes.showSceneErrorAlert();
            logger.info(e.getMessage());
        }
    }

    @FXML
    private void saveBoard() {
        try (Dao<SudokuBoard> file = new FileSudokuBoardDao("lastBoard");
             Dao<SudokuBoard> cloned = new FileSudokuBoardDao("clonedLastBoard")) {
            ResourceBundle bundle = getBundle("lang.App");
            file.write(this.sudokuBoard);
            cloned.write(this.clonedSudokuBoard);
            logger.info(bundle.getString("boardsaved"));
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

    @FXML
    private void saveToDatabase() {
        if (this.dbFieldName.textProperty().getValue() != "") {
            try (Dao<SudokuBoard> jdbcFile = SudokuBoardDaoFactory.getDatabaseDao(
                    dbFieldName.textProperty().getValue())) {
                ResourceBundle bundle = getBundle("lang.App");
                jdbcFile.write(this.sudokuBoard);
                logger.info(bundle.getString("boardsaved"));
            } catch (Exception e) {
                logger.info(e.getMessage());
            }
        }
    }

    @FXML
    protected void checkIsBoardCorrect(ActionEvent event) {
        boolean isSolved = true;
        ResourceBundle bundle = getBundle("lang.App");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (clonedSudokuBoard.get(i, j) != sudokuBoard.get(i, j)) {
                    sudokuGridFields[i][j].setBackground(new Background(
                            new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                    if (isSolved) {
                        isSolved = false;
                        logger.info(bundle.getString("nsolved"));
                    }
                }
            }
        }
        if (isSolved) {
            comingBack(event);
            RewardView.showReward();
            logger.info(bundle.getString("solved"));
        }
    }

    public void prepareSudokuBoardToGame(DifficultyLevel difficulty) {
        this.sudokuBoard.solveGame();
        this.clonedSudokuBoard = sudokuBoard.clone();
        difficulty.clearFields(sudokuBoard);
        this.setEmptyIfZero();
    }

    public void prepareSudokuBoardToGameFromFile(FileSudokuBoardDao orginal,
                                                 FileSudokuBoardDao actual) throws DaoException {
        try {
            this.sudokuBoard = actual.read();
            this.clonedSudokuBoard = orginal.read();
            this.setEmptyIfZero();
            prepareTextBoxes();
            ResourceBundle bundle = ResourceBundle.getBundle("lang.App");
            logger.info(bundle.getString("boardprepared"));
        } catch (FileReadException e) {
            logger.info(e.getMessage());
        }
    }

    public void prepareSudokuBoardToGameFromDataBase(JdbcSudokuBoardDao boardDao)
            throws DaoException {
        try {
            this.sudokuBoard = boardDao.read();
            this.clonedSudokuBoard = boardDao.read();
            this.clonedSudokuBoard.solveGame();
            this.setEmptyIfZero();
            prepareTextBoxes();
            ResourceBundle bundle = ResourceBundle.getBundle("lang.App");
            logger.info(bundle.getString("boardprepared"));
        } catch (FileReadException e) {
            logger.info(e.getMessage());
        }
    }

    private void addColumnsAndRowsToSudokuGrid() {
        sudokuGrid.getColumnConstraints().add(new ColumnConstraints(9));
        sudokuGrid.getRowConstraints().add(new RowConstraints(9));
    }

    private void setGridCellsFixedSize(int size) {
        for (var column : sudokuGrid.getColumnConstraints()) {
            column.setPrefWidth(size);
        }
        for (var row : sudokuGrid.getRowConstraints()) {
            row.setPrefHeight(size);
        }
    }

    public void setEmptyIfZero() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuGridFields[i][j].textProperty()
                        .setValue(String.valueOf(sudokuBoard.get(i, j)));
                if (sudokuBoard.get(i, j) == 0) {
                    sudokuGridFields[i][j].textProperty().setValue("");
                    sudokuGridFields[i][j].getStyleClass().add("notFilled");
                    sudokuBoard.getField(i, j).setEditable(true);
                } else if (clonedSudokuBoard.get(i, j) != sudokuBoard.get(i, j)
                        || sudokuBoard.getField(i, j).isEditable()) {
                    sudokuGridFields[i][j].textProperty()
                            .setValue(String.valueOf(sudokuBoard.get(i, j)));
                    sudokuGridFields[i][j].getStyleClass().add("notFilled");
                } else {
                    sudokuGridFields[i][j].setEditable(false);
                }
            }
        }
        prepareTextBoxes();
    }

    private void prepareTextBoxes() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                viewFieldBinders[i][j] = new ViewFieldBinder(sudokuBoard.getField(i, j));
                Bindings.bindBidirectional(sudokuGridFields[i][j].textProperty(),
                        viewFieldBinders[i][j], new FieldStringConverter());
                sudokuGridFields[i][j].setTextFormatter(new FieldFormatter());
            }
        }
    }

    private void prepare() {
        sudokuGrid = new GridPane();
        sudokuGrid.getStyleClass().add("grid");
        sudokuGridContainer.getChildren().add(sudokuGrid);
        addColumnsAndRowsToSudokuGrid();
        setGridCellsFixedSize(50);
        prepareTextBoxes();
    }
}