package com.chelhaoui.controller.services;

import com.chelhaoui.EmailManager;
import com.chelhaoui.controller.EmailLoginResult;
import com.chelhaoui.module.EmailAccount;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.mail.*;

public class LoginService extends Service<EmailLoginResult> {

    EmailAccount emailAccount;
    EmailManager emailManager;

    public LoginService(EmailAccount emailAccount, EmailManager emailManager) {
        this.emailAccount = emailAccount;
        this.emailManager = emailManager;
    }

    private EmailLoginResult login(){
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailAccount.getEmail(), emailAccount.getPassword());
            }
        };

        try{
            Session session = Session.getInstance(emailAccount.getProperties(), authenticator);
            Store store = session.getStore("imaps");
            store.connect(emailAccount.getProperties().getProperty("incomingHost"),emailAccount.getEmail(), emailAccount.getPassword());
            emailAccount.setStore(store);
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            return  EmailLoginResult.NETWORK_ERROR;
        } catch(AuthenticationFailedException e) {
            e.printStackTrace();
            return  EmailLoginResult.FAILED_AUTH;
        } catch (MessagingException e) {
            e.printStackTrace();
            return  EmailLoginResult.UNKNOWN_ERROR;
        } catch (Exception e){
            e.printStackTrace();
            return  EmailLoginResult.UNKNOWN_ERROR;
        }

        return EmailLoginResult.SUCCESS;
    }

    @Override
    protected Task<EmailLoginResult> createTask() {
        return new Task<EmailLoginResult>() {
            @Override
            protected EmailLoginResult call() throws Exception {
                return login();
            }
        };
    }
}
