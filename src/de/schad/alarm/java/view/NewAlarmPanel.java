package de.schad.alarm.java.view;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class NewAlarmPanel implements Panel {

    private BorderPane borderPane;
    private Button backBtn;
    private Button saveTimeBtn;
    private HBox hBox;
    private ComboBox<String> hourBox;
    private ComboBox<String> minuteBox;

    public NewAlarmPanel() {
        this.backBtn = new Button();
        this.saveTimeBtn = new Button("Speichern");
        this.borderPane = new BorderPane();
        this.hBox = new HBox();
        this.hourBox = new ComboBox<>();
        this.minuteBox = new ComboBox<>();
        initialize();
    }

    @Override
    public void initialize() {
        borderPane.setMinWidth(400);
        borderPane.setStyle("-fx-background-color: #8ee4af");

        Image image = new Image("de/schad/alarm/resources/icons/arrow_back.png");
        ImageView imageView = new ImageView(image);
        backBtn.setGraphic(imageView);
        backBtn.setMaxSize(20,20);
        backBtn.getStylesheets().add("de/schad/alarm/resources/css/new-alarm.css");
        backBtn.getStyleClass().add("back-button");

        initializeComboboxes();

        borderPane.setTop(backBtn);
        borderPane.setCenter(hBox);
        borderPane.setBottom(saveTimeBtn);
    }

    @Override
    public BorderPane getUI() {
        return borderPane;
    }

    public Button getBackBtn() {
        return backBtn;
    }

    public Button getSaveTimeBtn() {
        return saveTimeBtn;
    }

    public ComboBox<String> getHourBox() {
        return hourBox;
    }

    public ComboBox<String> getMinuteBox() {
        return minuteBox;
    }

    private void initializeComboboxes() {
        ArrayList<String> hours = new ArrayList<>();
        ArrayList<String> minutes = new ArrayList<>();


        for(int i = 0; i < 24; i++) {
            hours.add(String.format("%02d", i));
        }

        for(int i = 0; i < 60; i++) {
            minutes.add(String.format("%02d", i));
        }

        hourBox.setItems(FXCollections.observableArrayList(hours));
        hourBox.getSelectionModel().selectFirst();


        minuteBox.setItems(FXCollections.observableArrayList(minutes));
        minuteBox.getSelectionModel().selectFirst();

        hBox.getChildren().addAll(hourBox, minuteBox);
    }

}
