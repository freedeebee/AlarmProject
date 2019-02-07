package de.schad.alarm.java.controller;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import de.schad.alarm.java.model.AlarmMemory;
import de.schad.alarm.java.model.AlarmTime;
import de.schad.alarm.java.view.AlarmPanel;
import de.schad.alarm.java.view.NewAlarmPanel;

import java.util.ArrayList;
import java.util.List;


public class AlarmController {

    private AlarmPanel alarmPanel;
    private NewAlarmPanel newAlarmPanel;
    private MainController mainController = MainController.getInstance();
    private AlarmMemory alarmMemory = AlarmMemory.getInstance();
    private String selectedTimeValue;
    private List<HBox> boxesList = new ArrayList<>();

    public AlarmController() {
        this.alarmPanel = AlarmPanel.getInstance();
        this.newAlarmPanel = new NewAlarmPanel();
        this.selectedTimeValue = "000000";
        initialize();
    }

    private void initialize() {
        Button newAlarmBtn = alarmPanel.getNewAlarm();
        newAlarmBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            mainController.changeInterface(newAlarmPanel.getUI());
        });

        Button backFromNewAlarm = newAlarmPanel.getBackBtn();
        backFromNewAlarm.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            mainController.changeInterface(alarmPanel.getUI());
        });

        Button saveAlarmBtn = newAlarmPanel.getSaveTimeBtn();
        saveAlarmBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            AlarmTime time = new AlarmTime(selectedTimeValue);
            alarmMemory.addAlarmTime(time);

            boxesList.add(alarmPanel.createAlarmBox(time.getTime()));

            alarmPanel.createAlarmList(boxesList);

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
    }


}
