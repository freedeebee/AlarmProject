package de.schad.alarm.java.view;

import com.jfoenix.controls.JFXToggleButton;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public class ClockPanel implements Panel {

    private AnchorPane anchorPane;
    private BorderPane borderPane;
    private JFXToggleButton toggleButton;
    private Label timeLabel;

    private static ClockPanel instance = null;
    private ClockPanel() {
        this.anchorPane = new AnchorPane();
        this.borderPane = new BorderPane();
        this.toggleButton = new JFXToggleButton();
        this.timeLabel = new Label();
        initialize();
    }
    public static ClockPanel getInstance() {
        if(instance == null) {
            instance = new ClockPanel();
        }
        return instance;
    }

    @Override
    public void initialize() {

        timeLabel.setFont(new Font(40));
        borderPane.setCenter(timeLabel);
        AnchorPane.setTopAnchor(toggleButton, 0.0);
        AnchorPane.setRightAnchor(toggleButton, 0.0);
        AnchorPane.setLeftAnchor(borderPane, 0.0);
        AnchorPane.setTopAnchor(borderPane, 0.0);
        AnchorPane.setRightAnchor(borderPane, 0.0);
        AnchorPane.setBottomAnchor(borderPane, 0.0);
        anchorPane.getChildren().addAll(borderPane, toggleButton);
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
