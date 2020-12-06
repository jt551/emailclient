/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import model.Email;
import model.EmailFolderInTree;

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
    private TableView<Email> mainTableView;

    @FXML
    private TableColumn<Email, String> senderColumn;

    @FXML
    private TableColumn<Email, String> subjectColumn;

    @FXML
    private TableColumn<Email, Date> timeColumn;

    @FXML
    private TableColumn<Email, Integer> sizeColumn;

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTableViewValues();
        setTreeHandler();
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
        } catch (IOException ex) {
            userMessageLabel.setText("IO Error : " + ex.getMessage());
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
    
    public TableView<Email> getTableView(){
        return this.mainTableView;
    }

    private void setTableViewValues() {
        senderColumn.setCellValueFactory(new PropertyValueFactory<Email, String>("sender"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<Email, String>("subject"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<Email, Date>("date"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<Email, Integer>("size"));
    }

    private void setTreeHandler() {
        leftTreeView.setOnMouseClicked( e -> {
            EmailFolderInTree<String> selectedFolder = (EmailFolderInTree<String>)leftTreeView.getSelectionModel().getSelectedItem();
            mainTableView.setItems((ObservableList<Email>) selectedFolder.getEmailsInFolder());
        }
        );
    }

}
