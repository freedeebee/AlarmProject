package de.schad.alarm.java.controller;

import javafx.application.Platform;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import de.schad.alarm.java.view.AlarmPanel;
import de.schad.alarm.java.view.ClockPanel;
import de.schad.alarm.java.view.SettingPanel;
import de.schad.alarm.java.view.SidePanel;

public class SidePanelController {

    private SidePanel sidePanel;
    private MainController mainController = MainController.getInstance();
    private ClockPanel clockPanel;
    private SettingPanel settingPanel;
    private AlarmPanel alarmPanel;

    public SidePanelController() {
        this.sidePanel = SidePanel.getInstance();
        this.settingPanel = new SettingPanel();
        this.clockPanel = new ClockPanel();
        this.alarmPanel = AlarmPanel.getInstance();
        initialize();
    }

    public void initialize() {
        HBox shutdownBtn = sidePanel.getButtonMap().get("shutdown");
        shutdownBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> Platform.exit());

        HBox settingsBtn = sidePanel.getButtonMap().get("settings");
        settingsBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            mainController.changeInterface(settingPanel.getUI());

        });

        HBox clockBtn = sidePanel.getButtonMap().get("showAlarm");
        clockBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            mainController.changeInterface(clockPanel.getUI());
            ClockController clockController = new ClockController(clockPanel);
        });

        HBox alarmBtn = sidePanel.getButtonMap().get("newAlarm");
        alarmBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            mainController.changeInterface(alarmPanel.getUI());
        });
    }
}
