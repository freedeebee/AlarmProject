package de.schad.alarm.java.view;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ClockPanel implements Panel {

    private AnchorPane anchorPane;
    private Label timeLabel;

    public ClockPanel() {
        this.anchorPane = new AnchorPane();
        this.timeLabel = new Label();
        initialize();
    }

    @Override
    public void initialize() {
        anchorPane.getChildren().add(timeLabel);
        anchorPane.setPrefWidth(400);
        anchorPane.setStyle("-fx-background-color: #8ee4af");
    }

    @Override
    public AnchorPane getUI() {
        return anchorPane;
    }

    public Label getTimeLabel() {
        return timeLabel;
    }

}
