package com.emailclient;

import com.emailclient.module.EmailAccount;
import javafx.scene.control.TreeItem;

public class EmailManager {
    private TreeItem<String> rootFolder = new TreeItem<String>("");

    public TreeItem<String> getRootFolder() {
        return rootFolder;
    }

    public void addEmailAccount(EmailAccount email){
        TreeItem<String> treeItem = new TreeItem<String>(email.getEmail());
        treeItem.setExpanded(true);
            treeItem.getChildren().add(new TreeItem<String>("Inbox"));
            treeItem.getChildren().add(new TreeItem<String>("Spam"));
            treeItem.getChildren().add(new TreeItem<String>("Favourites"));
            treeItem.getChildren().add(new TreeItem<String>("Sent Emails"));
        rootFolder.getChildren().add(treeItem);
    }
}
