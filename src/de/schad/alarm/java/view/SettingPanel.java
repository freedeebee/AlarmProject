package de.schad.alarm.java.view;

import javafx.scene.layout.VBox;

public class SettingPanel implements Panel {

    private VBox vBox;

    public SettingPanel() {
        this.vBox = new VBox();
        initialize();
    }

    @Override
    public void initialize() {
        vBox.setPrefWidth(400);
        vBox.setStyle("-fx-background-color: white");
    }

    @Override
    public VBox getUI() {
        return vBox;
    }
}
