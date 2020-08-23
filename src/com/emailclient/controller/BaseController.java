package com.emailclient.controller;

import com.emailclient.EmailManager;
import com.emailclient.view.ViewFactory;

public class BaseController {
    protected EmailManager emailManager;
    protected ViewFactory viewFactory;
    private String fxmlFile;

    public BaseController(EmailManager emailManager, ViewFactory viewFactory, String fxmlFile) {
        this.emailManager = emailManager;
        this.viewFactory = viewFactory;
        this.fxmlFile = fxmlFile;
    }

    public String getFxmlName() {
        return fxmlFile;
    }
}
