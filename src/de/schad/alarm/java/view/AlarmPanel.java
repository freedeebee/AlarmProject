package de.schad.alarm.java.view;

import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;



public class AlarmPanel implements Panel {

    private AnchorPane anchorPane;
    private AnchorPane bottomAnchorPane;
    private BorderPane borderPane;
    private VBox vBox;
    private Label headline;
    private Button newAlarm;
    private ListView<HBox> boxListView;

    private static AlarmPanel instance = null;
    private AlarmPanel() {
        this.anchorPane = new AnchorPane();
        this.bottomAnchorPane = new AnchorPane();
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

        AnchorPane.setBottomAnchor(newAlarm, 5.0);
        AnchorPane.setRightAnchor(newAlarm, 5.0);
        AnchorPane.setLeftAnchor(borderPane, 0.0);
        AnchorPane.setTopAnchor(borderPane, 0.0);
        AnchorPane.setRightAnchor(borderPane, 0.0);
        AnchorPane.setBottomAnchor(borderPane, 0.0);

        bottomAnchorPane.setMinHeight(60);
        anchorPane.setPrefWidth(400);

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

        headline.setFont(new Font(20));

        HBox headerHBox = new HBox();
        headerHBox.setAlignment(Pos.CENTER);
        headerHBox.getChildren().add(headline);
        headerHBox.setMinHeight(50);

        vBox.setStyle("-fx-background-color: transparent");
        boxListView.setStyle("-fx-background-color: transparent");
        boxListView.setBackground(
                new Background(new BackgroundFill(Color.valueOf("FFFFFF"), null, null))
        );
        boxListView.setPadding(new Insets(0));
        vBox.getChildren().add(boxListView);

        borderPane.setTop(headerHBox);
        borderPane.setCenter(vBox);
        borderPane.setBottom(bottomAnchorPane);
        bottomAnchorPane.getChildren().add(newAlarm);
        anchorPane.getChildren().add(borderPane);
    }

    @Override
    public AnchorPane getUI() {
        return anchorPane;
    }

    public Button getNewAlarm() {
        return newAlarm;
    }

    public void setBoxListViewItems(ObservableList list) {
        this.boxListView.setItems(list);
    }
}
