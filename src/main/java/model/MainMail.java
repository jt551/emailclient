/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import javafx.scene.control.TreeView;
import javax.mail.MessagingException;
import ui.DisplayFolders;

/**
 *
 * @author juhat
 */
public class MainMail {

    private EmailAccount emailAccount;
    private Database database;
    private LoginToMailServer loginToMailServer;    
    private DisplayFolders displayFolders;    

    private AccountDetailsProvider accountDetailsProvider;
    private ServerDetailsProvider serverDetailsProvider;

    public MainMail(String username, String password, TreeView treeView, Database database) throws IOException {
        this.accountDetailsProvider = new HardCodedAccountDetails();
        this.serverDetailsProvider = new HardCodedServerDetails();
        this.database = database;
        this.emailAccount = new EmailAccount(username, password, this.database);
        this.displayFolders = new DisplayFolders(treeView);
        
    }

    public void login() throws MessagingException {
        this.loginToMailServer = new LoginToMailServer(emailAccount);
        loginToMailServer.start();
        addRootFolderToUI();
    }

    public AccountDetailsProvider getAccountDetailsProvider() {
        return accountDetailsProvider;
    }

    public ServerDetailsProvider getServerDetailsProvider() {
        return serverDetailsProvider;
    }

    
    public String getEmailAddress(){
        return emailAccount.getAddress();
    }
    
    public EmailAccount getEmailAccount() {
        return emailAccount;
    }

    private void addRootFolderToUI() throws MessagingException {
        this.displayFolders.addFolderToTree(emailAccount.getRootFolder());       
    }
}
