import controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        MainController mainController = new MainController(primaryStage);
        mainController.initializeGUIElements();
        mainController.initializeControllers();
        primaryStage.setTitle("AlarmClock");
        primaryStage.setScene(mainController.getScene());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}