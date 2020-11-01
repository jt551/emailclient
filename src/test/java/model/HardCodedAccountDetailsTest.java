/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
public class HardCodedAccountDetailsTest {
    
    HardCodedAccountDetails accountDetails;
    
    public HardCodedAccountDetailsTest() {
    }
    
    
    
    @Before
    public void setUp() {
        accountDetails = new HardCodedAccountDetails();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testGetAccountDetail() {
        
        String result = accountDetails.getAccountDetail("lastname");
        assertEquals("tili", result);
        
    }
    
}
