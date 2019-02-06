package controller;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.AlarmPanel;
import view.ClockPanel;
import view.SidePanel;

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
