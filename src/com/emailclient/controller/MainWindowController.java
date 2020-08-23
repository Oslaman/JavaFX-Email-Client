package com.emailclient.controller;

import com.emailclient.EmailManager;
import com.emailclient.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController implements Initializable {

    @FXML
    private TreeView<String> emailsTreeView;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpEmailsTreeView();
    }

    private void setUpEmailsTreeView() {
        emailsTreeView.setRoot(emailManager.getRootFolder());
        emailsTreeView.setShowRoot(false);
    }
}
