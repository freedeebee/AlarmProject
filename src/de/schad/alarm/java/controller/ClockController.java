package de.schad.alarm.java.controller;

import com.jfoenix.controls.JFXToggleButton;
import de.schad.alarm.java.model.Clock;
import de.schad.alarm.java.view.ClockPanel;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class ClockController {

    private Clock clock;
    private ClockPanel clockPanel;

    private Label timeLabel;
    private AnchorPane clockPane;

    private boolean isNerdy = false;

    private static ClockController instance = null;
    private ClockController() {
        this.clockPanel = ClockPanel.getInstance();
        this.clock = new Clock();
        initialize();
    }
    public static ClockController getInstance() {
        if(instance == null) {
            instance = new ClockController();
        }
        return instance;
    }

    /**
     * Listener for a ticking clock in nerdy and normal version
     */
    private ChangeListener<String> clockListener = (obs, oldVal, newVal) -> {

        timeLabel = clockPanel.getTimeLabel();
        clockPane = clockPanel.getUI();
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

        JFXToggleButton toggleButton = clockPanel.getToggleButton();
        toggleButton.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {

            if(isNerdy) {
                clock.getHexTimeProperty().removeListener(clockListener);
                clock.getTimeProperty().addListener(clockListener);
            } else {
                clock.getTimeProperty().removeListener(clockListener);
                clock.getHexTimeProperty().addListener(clockListener);
            }

            isNerdy = !isNerdy;

        });
    }

    public void sleep() {
        clock.sleep();
    }

    public void alarmOff() {
        clock.alarmOff();
    }
}
