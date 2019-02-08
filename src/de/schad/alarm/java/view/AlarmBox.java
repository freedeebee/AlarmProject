package de.schad.alarm.java.view;

import com.jfoenix.controls.JFXToggleButton;
import de.schad.alarm.java.model.AlarmTime;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class AlarmBox implements Panel{

    private HBox alarmBox;
    private AnchorPane anchorPane;
    private Label timeLabel;
    private JFXToggleButton toggleAlarmButton;
    private Button deleteButton;
    private AlarmTime alarmTime;

    public AlarmBox(AlarmTime time) {
        this.alarmBox = new HBox();
        this.anchorPane = new AnchorPane();
        this.timeLabel = new Label();
        this.toggleAlarmButton = new JFXToggleButton();
        this.deleteButton = new Button();
        this.alarmTime = time;
        initialize();
    }

    @Override
    public void initialize() {
        anchorPane.getStylesheets().add("de/schad/alarm/resources/css/alarm-box.css");
        String hours = alarmTime.getTime().substring(0,2);
        String minutes = alarmTime.getTime().substring(2,4);
        timeLabel.setText(hours + ":" + minutes);
        toggleAlarmButton.selectedProperty().setValue(true);
        Image image = new Image("de/schad/alarm/resources/icons/delete.png");
        ImageView imageView = new ImageView(image);
        deleteButton.setGraphic(imageView);
        deleteButton.getStyleClass().add("delete-button");
        alarmBox.setStyle("-fx-background-color: white");
        timeLabel.setFont(new Font(20));

        anchorPane.setMaxHeight(30);
        AnchorPane.setRightAnchor(alarmBox, 0.0);
        AnchorPane.setLeftAnchor(alarmBox, 0.0);
        AnchorPane.setLeftAnchor(timeLabel, 5.0);
        AnchorPane.setTopAnchor(timeLabel, 5.0);
        AnchorPane.setBottomAnchor(timeLabel, 5.0);
        AnchorPane.setRightAnchor(deleteButton, 5.0);
        AnchorPane.setTopAnchor(deleteButton, 5.0);
        AnchorPane.setBottomAnchor(deleteButton, 5.0);
        AnchorPane.setRightAnchor(toggleAlarmButton, 40.0);
        AnchorPane.setTopAnchor(toggleAlarmButton, 5.0);
        AnchorPane.setBottomAnchor(toggleAlarmButton, 5.0);
        anchorPane.getChildren().addAll(timeLabel, alarmBox, toggleAlarmButton, deleteButton);
    }

    @Override
    public AnchorPane getUI() {
        return anchorPane;
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
