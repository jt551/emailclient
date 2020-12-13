/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;
import model.SendMail;

/**
 * SendFXML Controller class
 * <p>
 * On user action "SendButton", pass details from new email window to model Send
 * Mail class.
 *
 */
public class SendFXMLController implements Initializable {

    SendMail sendMail;
    
    public SendFXMLController(SendMail sendMail){
        this.sendMail = sendMail;
    }
    @FXML
    private AnchorPane sendMailAnchorPane;

    @FXML
    private TextField toTextField;

    @FXML
    private Label userMessageLabel;

    @FXML
    private HTMLEditor htmlEditor;

    @FXML
    private TextField subjectTextField;

    @FXML
    private Button sendButton;

    @FXML
    private Button cancelButton;

    @FXML
    void sendButtonHandler() {
        if(toTextField.getText().length() > 5){
        Stage stage = (Stage) sendMailAnchorPane.getScene().getWindow();        
        sendMail.createEmail(toTextField.getText(), subjectTextField.getText(), htmlEditor.getHtmlText());
        sendMail.send();
        stage.close();        
        }else{
            userMessageLabel.setText("Type reciver address to text field.");
        }
        
    }

    @FXML
    void cancelButtonHandler() {
        Stage stage = (Stage) sendMailAnchorPane.getScene().getWindow();

        stage.close();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
