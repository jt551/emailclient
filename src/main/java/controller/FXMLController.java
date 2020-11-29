/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;

import model.MainMail;
import model.SendMail;

/**
 * FXML Controller class
 *
 * @author juhat
 */
public class FXMLController implements Initializable {

    private SendMail sendMail;
    private MainMail mainMail;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private ToolBar topToolBar;

    @FXML
    private Label addressLabel;

    @FXML
    private TextField addressField;

    @FXML
    private Label passwordLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button getMailButton;

    @FXML
    private Button sendMailbutton;

    @FXML
    private TreeView<String> leftTreeView;

    @FXML
    private Label userMessageLabel;

    @FXML
    private WebView mainWebView;

    @FXML
    private TableColumn<?, ?> senderColumn;

    @FXML
    private TableColumn<?, ?> subjectColumn;

    @FXML
    private TableColumn<?, ?> timeColumn;

    @FXML
    private TableColumn<?, ?> sizeColumn;

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void loginButtonHandler() {
        try {
            this.mainMail = new MainMail(addressField.getText(), passwordField.getText(), leftTreeView);
            mainMail.login();

        } catch (AuthenticationFailedException e) {
            userMessageLabel.setText("Auth error : " + e.getMessage());
        } catch (MessagingException e) {
            userMessageLabel.setText("Error in login process : " + e.getMessage());
        }
    }

    public void sendButtonHandler() {
        System.out.println("sendButtonHandler..");
        sendMail = new SendMail("test@gmail.com", "Java Test Subject", "Java Test Message");
        sendMail.send();
    }

    public void getButtonHandler() {
        System.out.println("getButtonHandler..");

    }

    public TreeView<String> getLeftTreeView() {
        return leftTreeView;
    }

}
