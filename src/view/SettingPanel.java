package view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class SettingPanel {

    private VBox vBox;

    public SettingPanel() {
        this.vBox = new VBox();
        initialize();
    }

    public void initialize() {
        vBox.setPrefWidth(400);
        vBox.setStyle("-fx-background-color: white");
    }

    public VBox getUI() {
        return vBox;
    }
}
