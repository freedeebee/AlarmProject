package view;

import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


public class AlarmPanel {

    private BorderPane borderPane;
    private VBox vBox;
    private Label headline;
    private Button newAlarm;

    private static AlarmPanel instance = null;
    private AlarmPanel() {
        this.borderPane = new BorderPane();
        this.vBox = new VBox();
        this.headline = new Label("Weckzeiten");
        this.newAlarm = new Button();
        initialize();
    }
    public static AlarmPanel getInstance() {
        if(instance == null) {
            instance = new AlarmPanel();
        }
        return instance;
    }

    public void initialize() {
        borderPane.setPrefWidth(400);
        borderPane.setStyle("-fx-background-color: #8ee4af");

        Image image = new Image("resources/icons/add_white.png");
        ImageView imageView = new ImageView(image);

        TranslateTransition tt = new TranslateTransition(Duration.millis(200), newAlarm);


        newAlarm.setGraphic(imageView);
        newAlarm.setPrefSize(45, 50);
        newAlarm.getStylesheets().add("resources/css/alarm-panel.css");
        newAlarm.getStyleClass().add("add-button");
        newAlarm.setOnMouseEntered(value -> {
            tt.setByX(-2);
            tt.setByY(-2);
            tt.play();
        });
        newAlarm.setOnMouseExited(value -> {
            tt.setByX(2);
            tt.setByY(2);
            tt.play();
        });

        HBox topHBox = new HBox();
        topHBox.setMinSize(400, 30);
        headline.setMinWidth(400);
        headline.setAlignment(Pos.CENTER);
        topHBox.getChildren().add(headline);

        Pane spacerBottom = new Pane();
        HBox bottomHBox = new HBox();
        spacerBottom.setMinSize(340, 60);
        bottomHBox.getChildren().addAll(spacerBottom, newAlarm);

        borderPane.setTop(topHBox);
        borderPane.setCenter(vBox);
        borderPane.setBottom(bottomHBox);
    }

    public BorderPane getUI() {
        return borderPane;
    }

    public Button getNewAlarm() {
        return newAlarm;
    }
}
