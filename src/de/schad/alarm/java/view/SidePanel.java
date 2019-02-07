package de.schad.alarm.java.view;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public class SidePanel implements Panel {

    private VBox sidePane;
    private HashMap<String, HBox> buttonMap;

    private static SidePanel instance = null;
    private SidePanel() {
        buttonMap = new HashMap<>();
        initialize();
    }
    public static SidePanel getInstance() {
        if(instance == null) {
            instance = new SidePanel();
        }
        return instance;
    }

    @Override
    public void initialize() {
        sidePane = new VBox();
        sidePane.setPrefWidth(50);
        sidePane.setStyle("-fx-background-color: #edf5e1");

        buttonMap.put("shutdown", createButton("icon-shutdown.png"));
        buttonMap.put("settings", createButton("icon-settings.png"));
        buttonMap.put("newAlarm", createButton("icon-newAlarm.png"));
        buttonMap.put("alarmOn", createButton("icon-alarmOn.png"));
        buttonMap.put("alarmOff", createButton("icon-alarmOff.png"));
        buttonMap.put("showAlarm", createButton("icon-showAlarm.png"));

        sidePane.getChildren().add(buttonMap.get("newAlarm"));
        sidePane.getChildren().add(buttonMap.get("alarmOn"));
        sidePane.getChildren().add(buttonMap.get("alarmOff"));
        sidePane.getChildren().add(buttonMap.get("showAlarm"));
        sidePane.getChildren().add(buttonMap.get("settings"));
        sidePane.getChildren().add(buttonMap.get("shutdown"));

    }

    @Override
    public VBox getUI() {
        return sidePane;
    }

    private HBox createButton(String icon) {
        Image image = new Image("de/schad/alarm/resources/icons/" + icon);
        ImageView imageView = new ImageView(image);
        Button btn = new Button();
        btn.setGraphic(imageView);
        btn.setPrefSize(45, 50);
        btn.getStyleClass().add("side-panel-button");
        btn.getStylesheets().add("de/schad/alarm/resources/css/side-panel.css");
        Pane hoverEffect = new Pane();
        hoverEffect.setPrefSize(5, 50);
        hoverEffect.getStyleClass().add("side-panel-hover");
        btn.getStylesheets().add("de/schad/alarm/resources/css/side-panel.css");
        HBox hBox = new HBox(hoverEffect, btn);
        createHoverEffect(hoverEffect, btn);
        return hBox;
    }

    private void createHoverEffect(Pane hoverPane, Button btn) {
        btn.setOnMouseEntered(value -> {
            hoverPane.setStyle("-fx-background-color: #05386b");
        });
        btn.setOnMouseExited(value -> {
            hoverPane.setStyle("-fx-background-color: #edf5e1");
        });
    }

    public HashMap<String, HBox> getButtonMap() {
        return buttonMap;
    }
}
