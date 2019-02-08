package de.schad.alarm.java.controller;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import de.schad.alarm.java.view.ClockPanel;
import de.schad.alarm.java.view.SidePanel;

/**
 * Handles all of the Controller dependencies and initializes them
 */
public class MainController {

    private BorderPane root;
    private Scene scene;
    private SidePanel sidePanel;
    private ClockPanel clockPanel;

    private static MainController instance = null;
    private MainController() {
        this.root = new BorderPane();
        this.scene = new Scene(root, 600, 400);
        this.sidePanel = SidePanel.getInstance();
        this.clockPanel = ClockPanel.getInstance();
    }
    public static MainController getInstance() {
        if(instance == null) {
            instance = new MainController();
        }
        return instance;
    }

    public void initialize() {
        root.setCenter(clockPanel.getUI());
        root.setLeft(sidePanel.getUI());
        root.getStylesheets().add("de/schad/alarm/resources/css/main.css");
        root.getStyleClass().add("root");

        // creation of controller instances to trigger their initialization
        SidePanelController sidePanelController = new SidePanelController();
        ClockController clockController = ClockController.getInstance();
        AlarmController alarmController = new AlarmController();
        SettingController settingController = new SettingController();
    }

    public Scene getScene() {
        return scene;
    }

    /**
     * Switches the interface next to the SidePanel
     * @param pane
     */
    public void changeInterface(Pane pane) {
        root.setCenter(pane);
    }

}
