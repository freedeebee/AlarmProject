package de.schad.alarm.java.view;

import com.jfoenix.controls.JFXToggleButton;
import de.schad.alarm.java.model.AlarmTime;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class AlarmBox implements Panel{

    private HBox alarmBox;
    private Label timeLabel;
    private JFXToggleButton toggleAlarmButton;
    private Button deleteButton;
    private AlarmTime alarmTime;

    public AlarmBox(AlarmTime time) {
        this.alarmBox = new HBox();
        this.timeLabel = new Label();
        this.toggleAlarmButton = new JFXToggleButton();
        this.deleteButton = new Button();
        this.alarmTime = time;
        initialize();
    }

    @Override
    public void initialize() {
        String hours = alarmTime.getTime().substring(0,2);
        String minutes = alarmTime.getTime().substring(2,4);
        timeLabel.setText(hours + ":" + minutes);
        toggleAlarmButton.selectedProperty().setValue(true);
        Image image = new Image("de/schad/alarm/resources/icons/delete.png");
        ImageView imageView = new ImageView(image);
        deleteButton.setGraphic(imageView);
        alarmBox.setStyle("-fx-background-color: white");
        alarmBox.setMinWidth(400);
        alarmBox.getChildren().addAll(timeLabel, toggleAlarmButton, deleteButton);
    }

    @Override
    public HBox getUI() {
        return alarmBox;
    }

    public Label getTimeLabel() {
        return timeLabel;
    }

    public JFXToggleButton getToggleAlarmButton() {
        return toggleAlarmButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public AlarmTime getAlarmTime() {
        return alarmTime;
    }
}
