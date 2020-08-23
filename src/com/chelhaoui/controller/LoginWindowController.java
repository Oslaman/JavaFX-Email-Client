package com.chelhaoui.controller;

import com.chelhaoui.EmailManager;
import com.chelhaoui.controller.BaseController;
import com.chelhaoui.controller.services.LoginService;
import com.chelhaoui.module.EmailAccount;
import com.chelhaoui.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginWindowController extends BaseController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label errorLabel;

    public LoginWindowController(EmailManager emailManager, ViewFactory viewFactory, String fxmlFile) {
        super(emailManager, viewFactory, fxmlFile);
    }

    @FXML
    void loginButtonAction() {
        if(validFields()){
            EmailAccount emailAccount = new EmailAccount(emailField.getText(), passwordField.getText());
            LoginService loginService = new LoginService(emailAccount, emailManager);
            loginService.setOnSucceeded(event -> {
                EmailLoginResult emailLoginResult = loginService.getValue();
                switch (emailLoginResult){
                    case SUCCESS:
                        System.out.println(emailField.getText());
                        viewFactory.showMainWindow();
                        Stage stage = (Stage) emailField.getScene().getWindow();
                        viewFactory.closeStage(stage);
                        break;
                }
            });
        }

        System.out.println("button pressed");
    }

    private boolean validFields() {
        if(emailField.getText().isEmpty()) {
            errorLabel.setText("Please fill email field");
            return false;
        }
        if(passwordField.getText().isEmpty()) {
            errorLabel.setText("Please fill password field");
            return false;
        }
        if(emailField.getText().isEmpty() && passwordField.getText().isEmpty()) {
            errorLabel.setText("Please fill the fields");
            return false;
        }

        return true;
    }

}

