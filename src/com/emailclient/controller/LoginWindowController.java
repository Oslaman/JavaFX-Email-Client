package com.emailclient.controller;

import com.emailclient.EmailManager;
import com.emailclient.controller.services.LoginService;
import com.emailclient.module.EmailAccount;
import com.emailclient.view.ViewFactory;
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
            loginService.start();
            loginService.setOnSucceeded(event -> {
                EmailLoginResult emailLoginResult = loginService.getValue();
                switch (emailLoginResult){
                    case SUCCESS:
                        System.out.println(emailField.getText());
                        viewFactory.showMainWindow();
                        Stage stage = (Stage) emailField.getScene().getWindow();
                        viewFactory.closeStage(stage);
                        break;
                    /*case FAILED_AUTH:
                        errorLabel.setText("Wrong email/password");
                        return;
                    case UNKNOWN_ERROR:
                        errorLabel.setText("Unknown error");
                        return;
                    default: return;*/
                }
            });
        }
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

