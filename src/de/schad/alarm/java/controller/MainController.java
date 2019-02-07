package de.schad.alarm.java.controller;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import de.schad.alarm.java.view.ClockPanel;
import de.schad.alarm.java.view.SidePanel;

public class MainController {

    private BorderPane root;
    private Scene scene;
    private SidePanel sidePanel;
    private ClockPanel clockPanel;

    private static MainController instance = null;
    private MainController() {
        this.root = new BorderPane();
        this.scene = new Scene(root);
        this.sidePanel = SidePanel.getInstance();
        this.clockPanel = new ClockPanel();
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

        SidePanelController sidePanelController = new SidePanelController();
        ClockController clockController = new ClockController(clockPanel);
        AlarmController alarmController = new AlarmController();
    }

    public Scene getScene() {
        return scene;
    }

    public void changeInterface(Pane pane) {
        root.setCenter(pane);
    }

}
