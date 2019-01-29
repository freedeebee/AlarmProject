package view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class SettingPanel {

    private VBox vBox;
    private SidePanel sidePanel;
    private Scene settingsScene;

    public SettingPanel(SidePanel sidePanel) {
        this.sidePanel = sidePanel;
        this.vBox = new VBox();
        initialize();
    }

    public void initialize() {
        vBox.setPrefWidth(400);
        vBox.setStyle("-fx-background-color: red");
    }

    public Scene getSettingsScene() {
        BorderPane root = new BorderPane();
        root.setLeft(sidePanel.getSidePaneVBox());
        root.setCenter(vBox);
        return new Scene(root);
    }
}
