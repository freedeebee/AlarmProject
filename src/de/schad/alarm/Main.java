package de.schad.alarm;

import de.schad.alarm.java.controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Alarm Clock Java FX Project
 *
 * @author Dennis Schad
 * @version 1.0
 *
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        MainController mainController = MainController.getInstance();
        mainController.initialize();
        primaryStage.setTitle("AlarmClock");
        primaryStage.setScene(mainController.getScene());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}