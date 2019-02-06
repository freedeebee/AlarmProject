package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class ClockPanel {

    private AnchorPane anchorPane;
    private Label timeLabel;

    public ClockPanel() {
        this.anchorPane = new AnchorPane();
        this.timeLabel = new Label();
        initializeClockPanel();
    }

    private void initializeClockPanel() {
        anchorPane.getChildren().add(timeLabel);
        anchorPane.setPrefWidth(400);
        anchorPane.setStyle("-fx-background-color: #8ee4af");
    }

    public AnchorPane getUI() {
        return anchorPane;
    }

    public Label getTimeLabel() {
        return timeLabel;
    }

}
