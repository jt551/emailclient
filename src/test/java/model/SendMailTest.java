/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.SendMail;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import model.HardCodedServerDetails;
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

    private EmailAccount emailAccount;

    private Session session;
    
    private MimeMessage message;
    
    private SendMail sendMail;

    public SendMailTest() {
    }

    @Before
    public void setUp() {
        emailAccount = new EmailAccount("account@gmail.com", "pw");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testConstructorMethod() {
        this.sendMail = new SendMail(emailAccount);
        assertEquals("account@gmail.com", sendMail.getAddress());
    }
    
    
}

