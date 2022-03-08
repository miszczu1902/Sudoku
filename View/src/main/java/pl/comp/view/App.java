package pl.comp.view;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static final Logger logger = Logger.getLogger(App.class.getName());
    private static final ResourceBundle bundle = ResourceBundle.getBundle("lang.App");

    static {
        try (FileInputStream fis = new FileInputStream(
                "View\\src\\main\\resources\\logging.properties")) {
            LogManager.getLogManager().readConfiguration(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void start(Stage stage) throws IOException {
        Locale.setDefault(new Locale("pl"));
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("start-view.fxml"), bundle);
        Scene scene = new Scene(fxmlLoader.load(), Scenes.WIDTH, Scenes.HEIGHT);
        String ceeses = this.getClass().getResource("style.css").toExternalForm();
        scene.getStylesheets().add(ceeses);
        stage.setTitle(bundle.getString("GameTitle"));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        logger.info(bundle.getString("init"));
        launch();
    }
}