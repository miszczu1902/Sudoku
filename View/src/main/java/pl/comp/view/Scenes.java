package pl.comp.view;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public enum Scenes {
    begin("start-view.fxml"), sudoku("sudoku-view.fxml");

    public static int WIDTH = 640;
    public static int HEIGHT = 640;

    public String file;

    Scenes(String file) {
        this.file = file;
    }

    ResourceBundle bundle;

    FXMLLoader getFxmlLoader(ResourceBundle bundle) {
        this.bundle = bundle;
        return new FXMLLoader(getClass().getResource(file), bundle);
    }

    FXMLLoader getFxmlLoader() {
        return new FXMLLoader(getClass().getResource(file));
    }

    public static Scene getJavaFxScene(FXMLLoader fxmlLoader) throws IOException {
        return new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
    }

    public void load(Stage stage, Scene scene) {
        String ceeses = Scenes.begin.getClass().getResource("style.css").toExternalForm();
        scene.getStylesheets().add(ceeses);
        stage.setScene(scene);
        stage.setScene(scene);
    }

    public static void showSceneErrorAlert() {
        ResourceBundle bundle = java.util.ResourceBundle.getBundle("lang.App");
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(bundle.getString("load1"));
        alert.setHeaderText(bundle.getString("problem"));
        alert.setContentText(bundle.getString("noload"));
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("OK");
            }
        });
    }

    static Stage getStageFromEvent(ActionEvent event) {
        Node node = (Node) event.getSource();
        return (Stage) node.getScene().getWindow();
    }
}
