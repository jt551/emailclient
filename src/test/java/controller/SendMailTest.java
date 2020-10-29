/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.SendMail;
import java.util.Properties;
import model.ServerDetails;
import model.ServerDetailsProvider;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juhat
 */
public class SendMailTest {

    SendMail sendMail;    
    Properties testProperties;

    public SendMailTest() {
    }

    @Before
    public void setUp() {
        sendMail = new SendMail("test@gmail.com", "This is a java program test", "Message body");
        testProperties = sendMail.getServerDetails();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testConstructorMethod() {

        assertEquals(sendMail.getTo(), "test@gmail.com");
        assertEquals(sendMail.getSubject(), "This is a java program test");
        assertEquals(sendMail.getMessageBody(), "Message body");
        
        assertEquals(testProperties.get("mail.smtp.host"), "smtp.gmail.com");        
        assertEquals(testProperties.get("mail.smtp.port"), "587");
        assertEquals(testProperties.get("mail.smtp.starttls.enable"), true);       
    }
    
    
}
// TODO review the generated test code and remove the default call to fail.
//fail("The test case is a prototype.");
