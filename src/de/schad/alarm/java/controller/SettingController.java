package de.schad.alarm.java.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import de.schad.alarm.java.model.Setting;
import de.schad.alarm.java.view.ClockPanel;
import de.schad.alarm.java.view.SettingPanel;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SettingController {

    private final String ALARM_PATH_KEY = "alarmpath";
    private final String RADIO_KEY = "radio";

    private SettingPanel settingPanel = SettingPanel.getInstance();
    private Setting setting;
    private String alarmPathProperty;
    private String radioProperty;


    public SettingController() {
        this.setting = new Setting();
        this.alarmPathProperty = setting.readProperty(ALARM_PATH_KEY);
        this.radioProperty = setting.readProperty(RADIO_KEY);
        initialize();
    }

    public void initialize() {
        JFXButton fileChooserBtn = settingPanel.getFileChooserBtn();
        fileChooserBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if(radioProperty != null && radioProperty.equals("custom")) {
                FileChooser fileChooser = new FileChooser();
                File file = fileChooser.showOpenDialog(new Stage());
                if (file != null) {
                    String path = file.getAbsolutePath();
                    alarmPathProperty = path;
                    settingPanel.getFileChooserLabel().setText(path);
                }
            }
        });

        JFXRadioButton defaultAlarm = settingPanel.getDefaultAlarm();
        defaultAlarm.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            radioProperty = "default";
        });

        JFXRadioButton customAlarm = settingPanel.getCustomAlarm();
        customAlarm.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            radioProperty = "custom";
        });

        String readRadioProperty = setting.readProperty(RADIO_KEY);
        if(readRadioProperty != null) {
            if(radioProperty.equals("default")) {
                defaultAlarm.setSelected(true);
            } else {
                customAlarm.setSelected(true);
            }
        } else {
            defaultAlarm.setSelected(true);
        }

        String readAlarmProperty = setting.readProperty(ALARM_PATH_KEY);
        if(readAlarmProperty != null) {
            settingPanel.getFileChooserLabel().setText(readAlarmProperty);
        }

        JFXButton applyBtn = settingPanel.getApplyBtn();
        applyBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            applyChanges();
            MainController.getInstance().changeInterface(ClockPanel.getInstance().getUI());
        });

        JFXButton cancelBtn = settingPanel.getCancelBtn();
        cancelBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            MainController.getInstance().changeInterface(ClockPanel.getInstance().getUI());
        });
    }

    public void applyChanges() {
        Map<String, String> settingsMap = new HashMap<>();
        if(alarmPathProperty != null) settingsMap.put(ALARM_PATH_KEY, alarmPathProperty);
        if(radioProperty != null) settingsMap.put(RADIO_KEY, radioProperty);
        setting.writeProperty(settingsMap);
    }

}
