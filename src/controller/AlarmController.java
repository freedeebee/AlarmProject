package controller;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import view.AlarmPanel;
import view.NewAlarmPanel;


public class AlarmController {

    private AlarmPanel alarmPanel;
    private NewAlarmPanel newAlarmPanel;
    private MainController mainController = MainController.getInstance();

    public AlarmController() {
        this.alarmPanel = AlarmPanel.getInstance();
        this.newAlarmPanel = new NewAlarmPanel();
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
    }


}
