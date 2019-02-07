package de.schad.alarm.java.view;

import com.jfoenix.controls.JFXToggleButton;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ClockPanel implements Panel {

    private AnchorPane anchorPane;
    private JFXToggleButton toggleButton;
    private Label timeLabel;

    public ClockPanel() {
        this.anchorPane = new AnchorPane();
        this.toggleButton = new JFXToggleButton();
        this.timeLabel = new Label();
        initialize();
    }

    @Override
    public void initialize() {
        anchorPane.getChildren().addAll(timeLabel, toggleButton);
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

    public JFXToggleButton getToggleButton() {
        return toggleButton;
    }

}
