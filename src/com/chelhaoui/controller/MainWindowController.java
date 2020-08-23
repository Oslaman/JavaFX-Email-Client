package com.chelhaoui.controller;

import com.chelhaoui.EmailManager;
import com.chelhaoui.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class MainWindowController extends BaseController {

    @FXML
    private TreeView<?> emailsTreeView;

    @FXML
    private WebView emailsWebView;

    @FXML
    private TableView<?> emailsTableView;

    public MainWindowController(EmailManager emailManager, ViewFactory viewFactory, String fxmlFile) {
        super(emailManager, viewFactory, fxmlFile);
    }

    @FXML
    void onActionLogOut() {
        viewFactory.showLoginWindow();
        Stage stage= (Stage) emailsTableView.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @FXML
    void optionsAction() {
        viewFactory.showOptionsWindow();
    }

}
