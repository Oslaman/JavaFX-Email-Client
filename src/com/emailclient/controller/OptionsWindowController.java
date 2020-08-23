package com.emailclient.controller;

import com.emailclient.EmailManager;
import com.emailclient.view.ColorTheme;
import com.emailclient.view.FontSize;
import com.emailclient.view.ViewFactory;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class OptionsWindowController extends BaseController implements Initializable {

    @FXML
    private Slider fontSizePicker;

    @FXML
    private ChoiceBox<ColorTheme> themePicker;

    public OptionsWindowController(EmailManager emailManager, ViewFactory viewFactory, String fxmlFile) {
        super(emailManager, viewFactory, fxmlFile);
    }

    @FXML
    void applyButtonAction() {
        viewFactory.setColorTheme(themePicker.getValue());
        viewFactory.setFontSize(FontSize.values()[(int) (fontSizePicker.getValue())]);
        System.out.println(viewFactory.getFontSize()+" "+viewFactory.getColorTheme());
        viewFactory.updateStyle();
    }

    @FXML
    void cancelButtonAction() {
        Stage stage= (Stage) fontSizePicker.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpColorTheme();
        setUpFontSize();
    }

    private void setUpFontSize() {
        fontSizePicker.setMin(0);
        fontSizePicker.setMax(FontSize.values().length-1);
        fontSizePicker.setMajorTickUnit(1);
        fontSizePicker.setMinorTickCount(0);
        fontSizePicker.setBlockIncrement(1);
        fontSizePicker.setSnapToTicks(true);
        fontSizePicker.setShowTickLabels(true);
        fontSizePicker.setShowTickMarks(true);
        fontSizePicker.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {
                int i=object.intValue();
                return FontSize.values()[i].toString();
            }

            @Override
            public Double fromString(String string) {
                return null;
            }
        });
        /*fontSizePicker.valueProperty().addListener((abs, oldVal, newVal) -> {
            fontSizePicker.setValue(newVal.intValue());
        });*/
    }

    private void setUpColorTheme() {
        themePicker.setItems(FXCollections.observableArrayList(ColorTheme.values()));
        themePicker.setValue(viewFactory.getColorTheme());
    }
}
