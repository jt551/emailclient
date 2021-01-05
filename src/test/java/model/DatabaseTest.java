/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Label;
import org.junit.After;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juhat
 */
public class DatabaseTest {
    private Database db;
    public DatabaseTest() {

    }

    @Before
    public void setUp() {
        this.db = new Database();
    }
    
    @After
    public void reset(){
        db.resetProperties();
    }

    /**
     * Test of connect method, of class Database.
     */
    @Test
    public void testConstructor() {        
        db = new Database();
        assertEquals(db.getDatabaseName(), "settings.db");
        assertNull(db.getUserMessageLabel());
    }

    @Test
    public void testConnect() {
        db = new Database();
        db.connect();
        db.init();
        assertNotNull(db.getConn());
    }

    /**
     * Test of getPropertiesByName method, of class Database.
     */
    @Test
    public void testGetPropertiesByName() {        
        Properties result = db.getPropertiesByName("gmail");
        assertEquals(result.getProperty("mail.smtps.host"), "smtp.gmail.com");
    }

    /**
     * Test of saveProperties method, of class Database.
     */
    @Test
    public void testSaveProperties() {
        String name = "gmail";
        String emailAddress = "test";
        String imapHost = "a";
        String imapProtocol = "a";
        String mailProtocol = "a";
        String mailHost = "a";
        String mailAuth = "a";
        String outgoingHost = "a";
        
        db.saveProperties(name, emailAddress, imapHost, imapProtocol, mailProtocol, mailHost, mailAuth, outgoingHost);
        Properties result = db.getPropertiesByName("gmail");
        assertEquals("a", result.getProperty("mail.smtps.host"));

    }
    

    /**
     * Test of resetProperties method, of class Database.
     */
    @Test
    public void testResetProperties() {        
        db.resetProperties();
        Properties newresult = db.getPropertiesByName("gmail");
        assertEquals(newresult.getProperty("mail.smtps.host"), "smtp.gmail.com");
    }

    /**
     * Test of close method, of class Database.
     */
    @Test
    public void testClose() {

        
        db.connect();
        Connection conn = db.getConn();
        try {
            assertEquals(false, conn.isClosed());
        } catch (SQLException ex) {

        }

        try {
            conn.close();
            assertEquals(true, conn.isClosed());
        } catch (SQLException ex) {

        }
    }

}
