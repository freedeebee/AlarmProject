package de.schad.alarm.java.controller;

import com.jfoenix.controls.JFXToggleButton;
import de.schad.alarm.java.model.Clock;
import de.schad.alarm.java.view.AlarmBox;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import de.schad.alarm.java.model.AlarmMemory;
import de.schad.alarm.java.model.AlarmTime;
import de.schad.alarm.java.view.AlarmPanel;
import de.schad.alarm.java.view.NewAlarmPanel;

public class AlarmController {

    private AlarmPanel alarmPanel;
    private NewAlarmPanel newAlarmPanel;
    private MainController mainController = MainController.getInstance();
    private AlarmMemory alarmMemory = AlarmMemory.getInstance();
    private String selectedTimeValue;
    private ObservableList<HBox> boxesList = FXCollections.observableArrayList();

    public AlarmController() {
        this.alarmPanel = AlarmPanel.getInstance();
        this.newAlarmPanel = new NewAlarmPanel();
        this.selectedTimeValue = "000000";
        initialize();
    }

    private void initialize() {
        Button newAlarmBtn = alarmPanel.getNewAlarm();
        newAlarmBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> mainController.changeInterface(newAlarmPanel.getUI()));

        Button backFromNewAlarm = newAlarmPanel.getBackBtn();
        backFromNewAlarm.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> mainController.changeInterface(alarmPanel.getUI()));

        Button saveAlarmBtn = newAlarmPanel.getSaveTimeBtn();
        saveAlarmBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {

            AlarmTime time = new AlarmTime(selectedTimeValue);
            AlarmBox box = new AlarmBox(time);

            alarmMemory.addAlarmTime(time);
            boxesList.add(box.getUI());

            JFXToggleButton onOffSwitch = box.getToggleAlarmButton();
            onOffSwitch.addEventFilter(MouseEvent.MOUSE_CLICKED, event1 -> box.getAlarmTime().toggleActive());

            box.getDeleteButton().addEventFilter(MouseEvent.MOUSE_CLICKED, event2 -> {
                alarmMemory.removeAlarmTime(box.getAlarmTime());
                boxesList.remove(box.getUI());
            });

            mainController.changeInterface(alarmPanel.getUI());
        });

        ComboBox<String> hourBox = newAlarmPanel.getHourBox();
        ComboBox<String> minuteBox = newAlarmPanel.getMinuteBox();

        hourBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            String selectedMinutes = minuteBox.getSelectionModel().getSelectedItem();
            selectedTimeValue = String.format("%02d", (int) newValue) + selectedMinutes + "00";
        });

        minuteBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            String selectedHours = hourBox.getSelectionModel().getSelectedItem();
            String minutesString = String.format("%02d", (int) newValue);
            selectedTimeValue = selectedHours + minutesString + "00";
        });

        boxesList.addListener((ListChangeListener<HBox>) c -> alarmPanel.setBoxListViewItems(boxesList));
    }
}
