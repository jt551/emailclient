/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juhat
 */
public class MainMailIT {
    
    public MainMailIT() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of login method, of class MainMail.
     */
    @Test
    public void testLogin() throws Exception {
        System.out.println("login");
        MainMail instance = null;
        instance.login();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAccountDetailsProvider method, of class MainMail.
     */
    @Test
    public void testGetAccountDetailsProvider() {
        System.out.println("getAccountDetailsProvider");
        MainMail instance = null;
        AccountDetailsProvider expResult = null;
        AccountDetailsProvider result = instance.getAccountDetailsProvider();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getServerDetailsProvider method, of class MainMail.
     */
    @Test
    public void testGetServerDetailsProvider() {
        System.out.println("getServerDetailsProvider");
        MainMail instance = null;
        ServerDetailsProvider expResult = null;
        ServerDetailsProvider result = instance.getServerDetailsProvider();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmailAddress method, of class MainMail.
     */
    @Test
    public void testGetEmailAddress() {
        System.out.println("getEmailAddress");
        MainMail instance = null;
        String expResult = "";
        String result = instance.getEmailAddress();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmailAccount method, of class MainMail.
     */
    @Test
    public void testGetEmailAccount() {
        System.out.println("getEmailAccount");
        MainMail instance = null;
        EmailAccount expResult = null;
        EmailAccount result = instance.getEmailAccount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printMessages method, of class MainMail.
     */
    @Test
    public void testPrintMessages() throws Exception {
        System.out.println("printMessages");
        MainMail instance = null;
        instance.printMessages();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
