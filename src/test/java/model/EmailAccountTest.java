/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Properties;
import javax.mail.Store;
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
public class EmailAccountTest {
    EmailAccount emailAccount;
    Database database;
    public EmailAccountTest() {        
        
    }
    
    @Before
    public void setUp() {
        database = new Database();
        this.emailAccount = new EmailAccount("test@gmail.com", "pw", database);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAddress method, of class EmailAccount.
     */
    @Test
    public void testGetAddress() {        
        String expResult = "test@gmail.com";
        String result = emailAccount.getAddress();
        assertEquals(expResult, result);        
    }

    /**
     * Test of setAddress method, of class EmailAccount.
     */
    @Test
    public void testSetAddress() {        
        String address = "settest@gmail.com";        
        emailAccount.setAddress(address);
        assertEquals(emailAccount.getAddress(), "settest@gmail.com"); 
    }

    /**
     * Test of getPassword method, of class EmailAccount.
     */
    @Test
    public void testGetPassword() {
       String expResult = "pw";
        String result = emailAccount.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPassword method, of class EmailAccount.
     */
    @Test
    public void testSetPassword() {
        String pw = "setpw";        
        emailAccount.setPassword(pw);
        assertEquals(emailAccount.getPassword(), "setpw"); 
    }

    /**
     * Test of getServerProperties method, of class EmailAccount.
     */
    @Test
    public void testGetServerProperties() {        
        Properties result = emailAccount.getServerProperties();
        assertEquals(result.getProperty("imapHost"), "imap.gmail.com");        
    }

    /**
     * Test of setServerProperties method, of class EmailAccount.
     */
    @Test
    public void testSetServerProperties() {
        
        Properties testProperties = new Properties();
        testProperties.put("imapHost", "newImapHost");
        emailAccount.setServerProperties(testProperties);
        assertEquals(emailAccount.getServerProperties().getProperty("imapHost"), "newImapHost");   
    }

    /**
     * Test of getStore method, of class EmailAccount.
     */
    @Test
    public void testGetStore() {
        
    }

    /**
     * Test of setStore method, of class EmailAccount.
     */
    @Test
    public void testSetStore() {
        
    }

    /**
     * Test of getRootFolder method, of class EmailAccount.
     */
    @Test
    public void testGetRootFolder() {
        
    }
    
}
