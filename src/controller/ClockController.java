package controller;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import model.Clock;
import view.ClockPanel;

public class ClockController {

    private Clock clock;
    private ClockPanel clockPanel;

    private Label timeLabel;
    private AnchorPane clockPane;

    private boolean isNerdy = false;

    public ClockController(ClockPanel clockPanel) {
        this.clockPanel = clockPanel;
        this.clock = new Clock();
        initialize();
    }

    private ChangeListener<String> clockListener = (obs, oldVal, newVal) -> {

        timeLabel = clockPanel.getTimeLabel();
        clockPane = clockPanel.getClockPane();
        Platform.runLater(() -> {
            timeLabel.setText(newVal);
            if (isNerdy) {
                clockPane.setStyle("-fx-background-color: " + newVal);
                timeLabel.setTextFill(Color.valueOf("#5cdb95"));
            } else {
                clockPane.setStyle("-fx-background-color: #8ee4af");
                timeLabel.setTextFill(Color.valueOf("#05386b"));
            }
        });
    };

    public void initialize() {
        clock.getTimeProperty().addListener(clockListener);
        Thread t = new Thread(clock);
        t.setDaemon(true);
        t.start();
    }
}
