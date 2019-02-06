package controller;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.ClockPanel;
import view.SidePanel;

public class MainController {



    private Stage primaryStage;
    private BorderPane root;
    private Scene scene;
    private SidePanel sidePanel;
    private ClockPanel clockPanel;

    private static MainController instance = null;
    private MainController() {
        this.root = new BorderPane();
        this.scene = new Scene(root);
        this.sidePanel = new SidePanel();
        this.clockPanel = new ClockPanel();
    }
    public static MainController getInstance() {
        if(instance == null) {
            instance = new MainController();
        }
        return instance;
    }

    public void initialize() {
        root.setCenter(clockPanel.getClockPane());
        root.setLeft(sidePanel.getSidePaneVBox());

        SidePanelController sidePanelController = new SidePanelController();
        ClockController clockController = new ClockController(clockPanel);
    }

    public Scene getScene() {
        return scene;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void changeInterface(Pane pane) {
        root.setCenter(pane);
    }

}
