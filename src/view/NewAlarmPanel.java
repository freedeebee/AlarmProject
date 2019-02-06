package view;

import javafx.scene.layout.GridPane;

public class NewAlarmPanel {

    private GridPane gridPane;

    public NewAlarmPanel() {
        this.gridPane = new GridPane();
        initializeClockPanel();
    }

    private void initializeClockPanel() {
        gridPane.setMinWidth(400);
        gridPane.setStyle("-fx-background-color: red");
    }

    public GridPane getUI() {
        return gridPane;
    }

}
