package de.schad.alarm.java.view;

import de.schad.alarm.java.controller.AlarmController;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.List;


public class AlarmPanel implements Panel {

    private BorderPane borderPane;
    private VBox vBox;
    private Label headline;
    private Button newAlarm;
    private ListView<HBox> boxListView;

    private static AlarmPanel instance = null;
    private AlarmPanel() {
        this.borderPane = new BorderPane();
        this.vBox = new VBox();
        this.headline = new Label("Weckzeiten");
        this.newAlarm = new Button();
        this.boxListView  = new ListView<>();
        initialize();
    }
    public static AlarmPanel getInstance() {
        if(instance == null) {
            instance = new AlarmPanel();
        }
        return instance;
    }

    @Override
    public void initialize() {
        borderPane.setPrefWidth(400);
        borderPane.setStyle("-fx-background-color: #8ee4af");

        TranslateTransition tt = new TranslateTransition(Duration.millis(200), newAlarm);

        Image image = new Image("de/schad/alarm/resources/icons/add_white.png");
        ImageView imageView = new ImageView(image);
        newAlarm.setGraphic(imageView);
        newAlarm.setPrefSize(45, 50);
        newAlarm.getStylesheets().add("de/schad/alarm/resources/css/alarm-panel.css");
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

        vBox.getChildren().add(boxListView);

        borderPane.setTop(topHBox);
        borderPane.setCenter(vBox);
        borderPane.setBottom(bottomHBox);
    }

    @Override
    public BorderPane getUI() {
        return borderPane;
    }

    public Button getNewAlarm() {
        return newAlarm;
    }

    public void setBoxListViewItems(ObservableList list) {
        this.boxListView.setItems(list);
    }
}
