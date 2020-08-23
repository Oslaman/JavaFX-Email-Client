package com.chelhaoui.view;

import com.chelhaoui.EmailManager;
import com.chelhaoui.controller.BaseController;
import com.chelhaoui.controller.LoginWindowController;
import com.chelhaoui.controller.MainWindowController;
import com.chelhaoui.controller.OptionsWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ViewFactory {
    private EmailManager emailManager;
    private ArrayList<Stage> activeStages;
    //View Options Handling
    private ColorTheme colorTheme = ColorTheme.Default;
    private FontSize fontSize=FontSize.Medium;

    public ColorTheme getColorTheme() {
        return colorTheme;
    }

    public void setColorTheme(ColorTheme colorTheme) {
        this.colorTheme = colorTheme;
    }

    public FontSize getFontSize() {
        return fontSize;
    }

    public void setFontSize(FontSize fontSize) {
        this.fontSize = fontSize;
    }


    public ViewFactory(EmailManager emailManager) {
        this.emailManager = emailManager;
        activeStages =new ArrayList<Stage>();
    }

    public void showLoginWindow(){
        BaseController controller=new LoginWindowController(emailManager,this,"LoginWindow.fxml");
        initializeStage(controller);

    }

    public void showMainWindow(){
        BaseController controller=new MainWindowController(emailManager,this,"MainWindow.fxml");
        initializeStage(controller);

    }

    public void showOptionsWindow(){
        BaseController controller=new OptionsWindowController(emailManager,this,"OptionsWindow.fxml");
        initializeStage(controller);

    }

    public void initializeStage(BaseController baseController){
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource(baseController.getFxmlName()));
        fxmlLoader.setController(baseController);

        Parent parent;
        try{
            parent =fxmlLoader.load();
        }catch (IOException e){
            e.printStackTrace();
            return;
        }

        Scene scene =new Scene(parent);
        Stage stage=new Stage();
        stage.setScene(scene);
        activeStages.add(stage);
        stage.show();
    }

    public void closeStage(Stage stage){
        stage.close();
        activeStages.remove(stage);
    }

    public void updateStyle() {
        for(Stage stage:activeStages){
            Scene scene=stage.getScene();
            scene.getStylesheets().clear();
            scene.getStylesheets().add(getClass().getResource(FontSize.getCssPath(fontSize)).toExternalForm());
            scene.getStylesheets().add(getClass().getResource(ColorTheme.getCssPath(colorTheme)).toExternalForm());
        }
    }
}
