/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import model.Database;
import model.EmailAccount;
import model.SendMail;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juhat
 */
public class SendFXMLControllerTest {
    EmailAccount emailAccount;
    SendMail sendMail;
    SendFXMLController sendFXMLController;
    Database database;
    Label label;
    public SendFXMLControllerTest() {
    }
    
    @Before
    public void setUp() {
        database = new Database();
        emailAccount = new EmailAccount("testuser@test.com", "1234", database);
        this.sendMail = new SendMail(emailAccount);
        this.sendFXMLController = new SendFXMLController(sendMail);
    }

    /**
     * Test of sendButtonHandler method, of class SendFXMLController.
     */
    
    @Test
    public void testSendButtonHandler() {
        
    }

    /**
     * Test of cancelButtonHandler method, of class SendFXMLController.
     */
    @Test
    public void testCancelButtonHandler() {
        
    }

    
}
