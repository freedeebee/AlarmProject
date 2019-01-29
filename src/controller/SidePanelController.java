package controller;

import javafx.application.Platform;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import view.ClockPanel;
import view.SettingPanel;
import view.SidePanel;

public class SidePanelController {

    private SidePanel sidePanel;
    private MainController mainController;
    private ClockPanel clockPanel;
    private SettingPanel settingPanel;

    public SidePanelController(MainController mainController, SidePanel sidePanel) {
        this.sidePanel = sidePanel;
        this.mainController = mainController;
        this.settingPanel = new SettingPanel(sidePanel);
        this.clockPanel = new ClockPanel(sidePanel);
    }

    public void initialize() {
        HBox shutdownBtn = sidePanel.getButtonMap().get("shutdown");
        shutdownBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> Platform.exit());

        HBox settingsBtn = sidePanel.getButtonMap().get("settings");
        settingsBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            mainController.getPrimaryStage().setScene(settingPanel.getSettingsScene());
        });

        HBox clockBtn = sidePanel.getButtonMap().get("showAlarm");
        clockBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            mainController.getPrimaryStage().setScene(clockPanel.getClockPanelScene());
        });
    }
}
