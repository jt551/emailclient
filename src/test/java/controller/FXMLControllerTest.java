/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.TreeView;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juhat
 */
public class FXMLControllerTest {
    
    public FXMLControllerTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of initialize method, of class FXMLController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL url = null;
        ResourceBundle rb = null;
        FXMLController instance = new FXMLController();
        instance.initialize(url, rb);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loginButtonHandler method, of class FXMLController.
     */
    @Test
    public void testLoginButtonHandler() {
        System.out.println("loginButtonHandler");
        FXMLController instance = new FXMLController();
        instance.loginButtonHandler();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendButtonHandler method, of class FXMLController.
     */
    @Test
    public void testSendButtonHandler() {
        System.out.println("sendButtonHandler");
        FXMLController instance = new FXMLController();
        instance.sendButtonHandler();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getButtonHandler method, of class FXMLController.
     */
    @Test
    public void testGetButtonHandler() {
        System.out.println("getButtonHandler");
        FXMLController instance = new FXMLController();
        instance.getButtonHandler();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLeftTreeView method, of class FXMLController.
     */
    @Test
    public void testGetLeftTreeView() {
        System.out.println("getLeftTreeView");
        FXMLController instance = new FXMLController();
        TreeView<String> expResult = null;
        TreeView<String> result = instance.getLeftTreeView();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
