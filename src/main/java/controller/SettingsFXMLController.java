/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Database;

/**
 *
 * @author juhat
 */
public class SettingsFXMLController implements Initializable {

    private Database database;

    public SettingsFXMLController(Database database) {
        this.database = database;
    }

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button resetButton;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField imapServerTextField;

    @FXML
    private TextField imapProtocolTextField;

    @FXML
    private TextField mailProtocolTextField;

    @FXML
    private TextField mailServerTextField;

    @FXML
    private TextField mailAuthTextField;

    @FXML
    private TextField outgoingHostTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    void cancelButtonHandler() {
        Stage stage = (Stage) addressTextField.getScene().getWindow();
        stage.close();
    }

    @FXML
    void resetButtonHandler() {
        database.resetProperties();
        populateTextFields();
    }

    @FXML
    void saveButtonHandler() {
        database.saveProperties(nameTextField.getText(), addressTextField.getText(), imapServerTextField.getText(), imapProtocolTextField.getText(), mailProtocolTextField.getText(), mailServerTextField.getText(), mailAuthTextField.getText(), outgoingHostTextField.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateTextFields();
    }

    private void populateTextFields() {
        Properties p = database.getPropertiesByName("gmail");
        addressTextField.setText(p.getProperty("emailaddress"));
        imapServerTextField.setText(p.getProperty("imapHost"));
        imapProtocolTextField.setText(p.getProperty("mail.store.protocol"));
        mailProtocolTextField.setText(p.getProperty("mail.transport.protocol"));
        mailServerTextField.setText(p.getProperty("mail.smtps.host"));
        mailAuthTextField.setText(p.getProperty("mail.smtps.auth"));
        outgoingHostTextField.setText(p.getProperty("outgoingHost"));
        nameTextField.setText(p.getProperty("name"));
    }
}
