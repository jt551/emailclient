/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;

/**
 *
 * @author juhat
 */
public class LoginToMailServer extends Service {

    EmailAccount emailAccount;
    GetFolders getFolders;
    private Label userMessageLabel;

    public LoginToMailServer(EmailAccount emailAccount, Label userMessageLabel) {
        this.emailAccount = emailAccount;
        this.userMessageLabel = userMessageLabel;

        setOnSucceeded(s -> {
            if (getFolders != null) {
                getFolders.start();
            }
        });
        setOnFailed(s -> {
            Platform.runLater(
                    () -> {
                        userMessageLabel.setText("Login failed.");
                    }
            );
        });
    }

    public void login() {

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailAccount.getAddress(), emailAccount.getPassword());
            }
        };

        try {
            Session session = Session.getInstance(emailAccount.getServerProperties(), authenticator);
            Store store = session.getStore("imaps");

            store.connect(emailAccount.getServerProperties().getProperty("imapHost"), emailAccount.getAddress(), emailAccount.getPassword());
            emailAccount.setStore(store);
            emailAccount.setSession(session);
            this.getFolders = new GetFolders(emailAccount.getStore(), emailAccount.getRootFolder());
        } catch (NoSuchProviderException | AuthenticationFailedException e) {
            Platform.runLater(
                    () -> {
                        userMessageLabel.setText(e.getMessage());
                    }
            );
        } catch (MessagingException ex) {
            Platform.runLater(
                    () -> {
                        userMessageLabel.setText(ex.getMessage());
                    }
            );
        }
    }

    @Override
    protected Task createTask() {
        return new Task() {
            @Override
            protected Object call() throws MessagingException {
                login();
                return null;
            }

        };
    }

}
