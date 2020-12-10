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
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;

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
import ui.DisplayEmail;

/**
 * FXML Controller class
 *
 * @author juhat
 */
public class FXMLController implements Initializable {

    private SendMail sendMail;
    private MainMail mainMail;
    private DisplayEmail displayEmail;

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
    private Button logoutButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button newEmailButton;

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
        this.displayEmail = new DisplayEmail(mainWebView.getEngine(), userMessageLabel);
        setTableHandler();
    }

    @FXML
    void loginButtonHandler() {
        try {
            this.mainMail = new MainMail(addressField.getText(), passwordField.getText(), leftTreeView);
            mainMail.login();
            topToolBarLoggedIn();
        } catch (AuthenticationFailedException e) {
            userMessageLabel.setText("Auth error : " + e.getMessage());
        } catch (MessagingException e) {
            userMessageLabel.setText("Error in login process : " + e.getMessage());
        } catch (IOException ex) {
            userMessageLabel.setText("IO Error : " + ex.getMessage());
        }
    }

    public void newEmailButtonHandler() {
        System.out.println("newEmailButtonHandler..");
        
    }
    
    public void logoutButtonHandler() {
        System.out.println("logoutButtonHandler..");
        
    }

    public void settingsButtonHandler() {
        System.out.println("settingsButtonHandler..");

    }

    public TreeView<String> getLeftTreeView() {
        return leftTreeView;
    }

    public TableView<Email> getTableView() {
        return this.mainTableView;
    }

    private void setTableViewValues() {
        senderColumn.setCellValueFactory(new PropertyValueFactory<Email, String>("sender"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<Email, String>("subject"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<Email, Date>("date"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<Email, Integer>("size"));
    }

    private void setTreeHandler() {
        leftTreeView.setOnMouseClicked(e -> {
            EmailFolderInTree<String> selectedFolder = (EmailFolderInTree<String>) leftTreeView.getSelectionModel().getSelectedItem();
            if (selectedFolder == null) {
                return;
            }
            mainTableView.setItems((ObservableList<Email>) selectedFolder.getEmailsInFolder());
        }
        );
    }

    private void setTableHandler() {
        mainTableView.setOnMouseClicked(e -> {
            Email email = (Email) mainTableView.getSelectionModel().getSelectedItem();
            
             try {
                if (email != null) {
                    userMessageLabel.setText("Show email in webview");
                    displayEmail.setMessage(email.getMessage());
                    displayEmail.restart();
                }
            }catch(Exception ex){
                userMessageLabel.setText(ex.getMessage());
            }
        });
    }

    private void topToolBarLoggedIn() {
        userMessageLabel.setText("Remove login buttons");
        addressLabel.setText(mainMail.getEmailAddress());
        topToolBar.getItems().remove(addressField);
        topToolBar.getItems().remove(passwordLabel);
        topToolBar.getItems().remove(passwordField);
        topToolBar.getItems().remove(loginButton);
        topToolBar.getItems().remove(settingsButton);
        topToolBar.getItems().add(logoutButton);
        logoutButton.setText("Logout");
        topToolBar.getItems().add(new Separator());
        topToolBar.getItems().add(newEmailButton);        
        topToolBar.getItems().add(new Separator());
        topToolBar.getItems().add(settingsButton);
    }
}
