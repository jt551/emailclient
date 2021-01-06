/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juhat
 */
public class EmailTest {
    private Email email;
    private Message msg;
    private Session session;
    public EmailTest() {
    }
    
    @Before
    public void setUp() throws AddressException, MessagingException {       
        msg = new MimeMessage(session);
        
        msg.setFrom(new InternetAddress("from@test.com"));
        msg.setSentDate(new Date());
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress("to@test.com"));        
        msg.setSubject("I'm just testing the e-mail sender");
        msg.setText("Sorry to bother you, but I'm testing the e-mail sender");
        msg.saveChanges();
        this.email = new Email(msg);
    }

    /**
     * Test of getSender method, of class Email.
     */
    @Test
    public void testGetSender() {
        assertEquals("from@test.com", email.getSender());
    }

    /**
     * Test of getSubject method, of class Email.
     */
    @Test
    public void testGetSubject() {
        assertEquals("I'm just testing the e-mail sender", email.getSubject());
    }

    /**
     * Test of getTo method, of class Email.
     */
    @Test
    public void testGetTo() {
        
        assertEquals("to@test.com", email.getTo());
        
    }

    /**
     * Test of getMessage method, of class Email.
     */
    @Test
    public void testGetMessage() {
        try{
        Message m = email.getMessage();
        assertEquals("from@test.com", m.getFrom()[0].toString());
        } catch(MessagingException e){
            e.printStackTrace();
        }
    }

    /**
     * Test of getDate method, of class Email.
     */
    @Test
    public void testGetDate() {        
        assertNotNull(email.getDate());
    }

    /**
     * Test of getSize method, of class Email.
     */
    @Test
    public void testGetSize() {
        int size = email.getSize();
        assertNotNull(size);
    }

    /**
     * Test of setDate method, of class Email.
     */
    @Test
    public void testSetDate() {
        Date date = new Date(0);
        email.setDate(date);
        assertEquals(date, email.getDate());
    }

    /**
     * Test of setMessage method, of class Email.
     */
    @Test
    public void testSetMessage() throws AddressException, MessagingException {
        Message testmsg = new MimeMessage(session);
        
        testmsg.setFrom(new InternetAddress("abc@abc.com"));
        testmsg.setSentDate(new Date());
        testmsg.setRecipient(Message.RecipientType.TO, new InternetAddress("abc@abc.com"));        
        testmsg.setSubject("test");
        testmsg.setText("test");
        testmsg.saveChanges();
        email.setMessage(testmsg);
        assertEquals("test", email.getMessage().getSubject());
    }

    /**
     * Test of setSender method, of class Email.
     */
    @Test
    public void testSetSender() {
        email.setSender("settest@gmail.com");
        assertEquals("settest@gmail.com", email.getSender());
    }


    /**
     * Test of setSubject method, of class Email.
     */
    @Test
    public void testSetSubject() {
       email.setSubject("settest@gmail.com");
        assertEquals("settest@gmail.com", email.getSubject());
    }

    /**
     * Test of setTo method, of class Email.
     */
    @Test
    public void testSetTo() {
        email.setTo("settest@gmail.com");
        assertEquals("settest@gmail.com", email.getTo());
    }

    /**
     * Test of toString method, of class Email.
     */
    @Test
    public void testToString() {        
        assertTrue(email.toString().length() > 10);        
    }
    
}
