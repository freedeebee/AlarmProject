package de.schad.alarm.java.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SettingPanel implements Panel {

    private AnchorPane anchorPane;
    private VBox vBox;
    private JFXRadioButton defaultAlarm;
    private JFXRadioButton customAlarm;
    private JFXButton fileChooserBtn;
    private Label fileChooserLabel;
    private HBox fileChooserBox;
    private JFXButton applyBtn;
    private JFXButton cancelBtn;
    private HBox applyCancelBox;

    private static SettingPanel instance = null;
    private SettingPanel() {
        this.anchorPane = new AnchorPane();
        this.vBox = new VBox();
        this.defaultAlarm = new JFXRadioButton("Default Alarm");
        this.customAlarm = new JFXRadioButton("Eigene MP3:");
        this.fileChooserBtn = new JFXButton("Durchsuchen");
        this.fileChooserLabel = new Label();
        this.fileChooserBox = new HBox();
        this.applyBtn = new JFXButton("Apply");
        this.cancelBtn = new JFXButton("Cancel");
        this.applyCancelBox = new HBox();
        initialize();
    }
    public static SettingPanel getInstance() {
        if(instance == null) {
            instance = new SettingPanel();
        }
        return instance;
    }

    @Override
    public void initialize() {

        AnchorPane.setTopAnchor(vBox, 10.0);
        AnchorPane.setRightAnchor(vBox, 10.0);
        AnchorPane.setBottomAnchor(vBox, 10.0);
        AnchorPane.setLeftAnchor(vBox, 10.0);
        anchorPane.getChildren().add(vBox);

        final ToggleGroup group = new ToggleGroup();

        defaultAlarm.setPadding(new Insets(10));
        defaultAlarm.setToggleGroup(group);

        customAlarm.setPadding(new Insets(10));
        customAlarm.setToggleGroup(group);

        fileChooserBtn.setStyle("-fx-background-color: #379683");
        applyBtn.setStyle("-fx-background-color: #5cdb95");
        cancelBtn.setStyle("-fx-background-color: #8ee4af");

        fileChooserBox.setPadding(new Insets(10, 10, 10 , 40));
        fileChooserBox.setSpacing(10);
        fileChooserBox.setAlignment(Pos.CENTER_LEFT);

        applyCancelBox.setPadding(new Insets(10));
        applyCancelBox.setSpacing(10);
        applyCancelBox.setAlignment(Pos.CENTER_LEFT);

        fileChooserBox.getChildren().addAll(fileChooserBtn, fileChooserLabel);
        applyCancelBox.getChildren().addAll(applyBtn, cancelBtn);
        vBox.getChildren().addAll(defaultAlarm, customAlarm, fileChooserBox, applyCancelBox);

    }

    @Override
    public AnchorPane getUI() {
        return anchorPane;
    }

    public JFXButton getFileChooserBtn() {
        return fileChooserBtn;
    }

    public JFXRadioButton getDefaultAlarm() {
        return defaultAlarm;
    }

    public JFXRadioButton getCustomAlarm() {
        return customAlarm;
    }

    public JFXButton getApplyBtn() {
        return applyBtn;
    }

    public JFXButton getCancelBtn() {
        return cancelBtn;
    }

    public Label getFileChooserLabel() {
        return fileChooserLabel;
    }
}
