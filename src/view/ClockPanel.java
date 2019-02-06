package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class ClockPanel {

    private AnchorPane anchorPane;
    private Label timeLabel;
    private SidePanel sidePanel;

    public ClockPanel() {
        this.anchorPane = new AnchorPane();
        this.timeLabel = new Label();
        initializeClockPanel();
    }

    public ClockPanel(SidePanel sidePanel) {
        this.anchorPane = new AnchorPane();
        this.timeLabel = new Label();
        this.sidePanel = sidePanel;
        initializeClockPanel();
    }

    private void initializeClockPanel() {
        anchorPane.getChildren().add(timeLabel);
        anchorPane.setPrefWidth(400);
        anchorPane.setStyle("-fx-background-color: #8ee4af");
    }

    public AnchorPane getClockPane() {
        return anchorPane;
    }

    public Label getTimeLabel() {
        return timeLabel;
    }

}
