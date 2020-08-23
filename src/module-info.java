module EmailClient {
    requires javafx.web;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.mail;
    requires activation;

    opens com.chelhaoui;
    opens com.chelhaoui.view;
    opens com.chelhaoui.controller;
}