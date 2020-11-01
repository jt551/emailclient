/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Properties;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class HardCodedServerDetailsTest {
    HardCodedServerDetails serverDetails;
    @Before
    public void setUp() {
        serverDetails = new HardCodedServerDetails();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testGetServerProperties() {
        assertEquals(serverDetails.getServerProperties().getProperty("mail.smtp.host"), "smtp.gmail.com");
    }
    
    @Test
    public void testSetServerProperties() {
        Properties serverProperties = new Properties();
        serverProperties.put("mail.smtp.host", "smtp-mail.outlook.com");        
        serverDetails.setServerProperties(serverProperties);
        
        assertEquals(serverDetails.getServerProperties().getProperty("mail.smtp.host"), "smtp-mail.outlook.com");
    }
    
}
