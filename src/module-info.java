module EmailClient {
    requires javafx.web;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.mail;
    requires activation;

    opens com.emailclient;
    opens com.emailclient.view;
    opens com.emailclient.controller;
}