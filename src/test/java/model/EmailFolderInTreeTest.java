/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javafx.collections.ObservableList;
import javax.mail.internet.MimeMessage;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juhat
 */
public class EmailFolderInTreeTest {
    private final String name = "testname";
    private EmailFolderInTree efit;
    
    private Email email;
    
    public EmailFolderInTreeTest() {
    }
    
    @Before
    public void setUp() {
        
    }

    /**
     * Test of addEmail method, getEmailsInFolder method of class EmailFolderInTree.
     */
    @Test
    public void testAddEmailAndGetEmails() {
        efit = new EmailFolderInTree(name);
        efit.addEmail(email);
        
        List emailList = efit.getEmailsInFolder();
        assertEquals(1, emailList.size());
    }

    /**
     * Test of getName method, of class EmailFolderInTree.
     */
    @Test
    public void testGetName() {
        efit = new EmailFolderInTree(name);
        efit.addEmail(email);
        String result = (String)efit.getName();
        assertEquals("testname", result);       
    }
    
}
