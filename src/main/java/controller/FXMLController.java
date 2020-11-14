/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.SendMail;
import model.GetMail;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * FXML Controller class
 *
 * @author juhat
 */
public class FXMLController implements Initializable {
    private GetMail getMail;
    private SendMail sendMail;
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void sendButtonHandler(){
        System.out.println("sendButtonHandler..");
        sendMail = new SendMail("jtwebapps@gmail.com", "Java Test Subject", "Java Test Message");
        sendMail.send();
    }
    
    public void getButtonHandler(){        
        System.out.println("getButtonHandler..");
        getMail.get();
    }
    
}
