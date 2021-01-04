/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeView;
import javax.mail.Message;
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

    private ObservableList<Message> messages;


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
        getFolders();
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

    public void printMessages() throws MessagingException {
        if (messages.size() > 0) {
            for (Message msg : messages) {
                System.out.println("");
                System.out.println("-- Mail --");
                System.out.println(msg.getFrom()[0].toString());
                System.out.println(msg.getSubject());
                System.out.println(msg.getSentDate());
                System.out.println("----------");
            }
        }
    }

    private void getFolders() throws MessagingException {
        this.displayFolders.addFolderToTree(emailAccount.getRootFolder());       
    }
}
