package pl.comp.view;

import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RewardView {
    public static void showReward() {
        ResourceBundle bundle = ResourceBundle.getBundle("lang.App");
        Stage stage = new Stage();
        VBox comp = new VBox();
        Label text = new Label(bundle.getString("win"));
        comp.getChildren().add(text);
        text.getStyleClass().add("popupLabel");

        String ceeses = Scenes.begin.getClass().getResource("popupStyle.css").toExternalForm();
        Scene stageScene = new Scene(comp, 400, 400);
        stage.setScene(stageScene);
        stageScene.getStylesheets().add(ceeses);
        stage.setResizable(false);
        stage.show();
    }
}
