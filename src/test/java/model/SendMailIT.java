/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juhat
 */
public class SendMailIT {
    private EmailAccount emailAccount;
    private Session session;    
    private MimeMessage message;    
    private SendMail sendMail;
    private Database database;
    public SendMailIT() {
    }
    
    @Before
    public void setUp() {
        this.emailAccount = new EmailAccount("account@gmail.com", "pw", database);
        this.session = emailAccount.getSession();
        this.sendMail = new SendMail(emailAccount);
    }

    /**
     * Test of createEmail method, of class SendMail.
     */
    @Test
    public void testCreateEmail() throws IOException, MessagingException {
        
        String to = "test@gmail.com";
        String subject = "testsubject";
        String content = "testcontent";
        
        sendMail.createEmail(to, subject, content);
        
        assertEquals(sendMail.getMessage().getContent().toString(), "testcontent");
    }

    /**
     * Test of send method, of class SendMail.
     */
    @Test
    public void testSend() {
        
    }

    

    /**
     * Test of getSession method, of class SendMail.
     */
    @Test
    public void testGetSession() {
        
    }
    
}
