package controller;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.ClockPanel;
import view.SidePanel;

public class MainController {

    private Stage primaryStage;
    private BorderPane root;
    private Scene scene;
    private SidePanel sidePanel;
    private ClockPanel clockPanel;

    public MainController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.root = new BorderPane();
        this.scene = new Scene(root);
        this.sidePanel = new SidePanel();
        this.clockPanel = new ClockPanel();
    }

    public void initializeControllers() {
        SidePanelController sidePanelController = new SidePanelController(new MainController(primaryStage), sidePanel);
        sidePanelController.initialize();
        ClockController clockController = new ClockController(clockPanel);
        clockController.initialize();
    }

    public void initializeGUIElements() {
        root.setCenter(clockPanel.getClockPane());
        root.setLeft(sidePanel.getSidePaneVBox());
    }

    public Scene getScene() {
        return scene;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
