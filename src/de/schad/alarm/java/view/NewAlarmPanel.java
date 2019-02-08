package de.schad.alarm.java.view;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class NewAlarmPanel implements Panel {

    private AnchorPane anchorPane;
    private BorderPane borderPane;
    private Button backBtn;
    private Button saveTimeBtn;
    private HBox hBox;
    private JFXComboBox<String> hourBox;
    private JFXComboBox<String> minuteBox;

    public NewAlarmPanel() {
        this.anchorPane = new AnchorPane();
        this.backBtn = new Button();
        this.saveTimeBtn = new Button();
        this.borderPane = new BorderPane();
        this.hBox = new HBox();
        this.hourBox = new JFXComboBox<>();
        this.minuteBox = new JFXComboBox<>();
        initialize();
    }

    @Override
    public void initialize() {

        AnchorPane.setLeftAnchor(borderPane, 0.0);
        AnchorPane.setTopAnchor(borderPane, 0.0);
        AnchorPane.setRightAnchor(borderPane, 0.0);
        AnchorPane.setBottomAnchor(borderPane, 0.0);
        anchorPane.getChildren().add(borderPane);
        anchorPane.getStylesheets().add("de/schad/alarm/resources/css/new-alarm.css");

        borderPane.setStyle("-fx-background-color: #8ee4af");

        Image image = new Image("de/schad/alarm/resources/icons/arrow_back.png");
        ImageView imageView = new ImageView(image);
        backBtn.setGraphic(imageView);
        backBtn.setMaxSize(20,20);
        backBtn.getStyleClass().add("button");

        Image saveImage = new Image("de/schad/alarm/resources/icons/save.png");
        ImageView saveImageView = new ImageView(saveImage);
        saveTimeBtn.setGraphic(saveImageView);
        saveTimeBtn.setMinSize(50,50);
        saveTimeBtn.getStyleClass().add("button");



        initializeComboboxes();

        BorderPane.setAlignment(saveTimeBtn, Pos.CENTER_RIGHT);
        borderPane.setTop(backBtn);
        borderPane.setCenter(hBox);
        borderPane.setBottom(saveTimeBtn);
    }

    @Override
    public AnchorPane getUI() {
        return anchorPane;
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
        Label colon = new Label(":");
        colon.setMinWidth(10);
        colon.setAlignment(Pos.CENTER);

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

        hBox.getChildren().addAll(hourBox, colon, minuteBox);
        hBox.setAlignment(Pos.CENTER);
    }

}
