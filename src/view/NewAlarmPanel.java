package view;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class NewAlarmPanel {

    private BorderPane borderPane;
    private Button backBtn;
    private HBox hBox;

    public NewAlarmPanel() {
        this.backBtn = new Button();
        this.borderPane = new BorderPane();
        this.hBox = new HBox();
        initializeClockPanel();
    }

    private void initializeClockPanel() {
        borderPane.setMinWidth(400);
        borderPane.setStyle("-fx-background-color: #8ee4af");

        Image image = new Image("resources/icons/arrow_back.png");
        ImageView imageView = new ImageView(image);
        backBtn.setGraphic(imageView);
        backBtn.setMaxSize(20,20);
        backBtn.getStylesheets().add("resources/css/new-alarm.css");
        backBtn.getStyleClass().add("back-button");

        borderPane.setTop(backBtn);
    }

    public BorderPane getUI() {
        return borderPane;
    }

    public Button getBackBtn() {
        return backBtn;
    }

}
