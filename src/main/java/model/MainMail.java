/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import ui.DisplayFolders;

/**
 *
 * @author juhat
 */
public class MainMail {

    private EmailAccount emailAccount;
    private LoginToMailServer loginToMailServer;
    private GetFolders getFolders;
    private DisplayFolders displayFolders;

    private AccountDetailsProvider accountDetailsProvider;
    private ServerDetailsProvider serverDetailsProvider;

    private Store store;

    private Folder inbox;

    private ObservableList<Message> messages;

    public MainMail(String username, String password, TreeView treeView) {
        this.accountDetailsProvider = new HardCodedAccountDetails();
        this.serverDetailsProvider = new HardCodedServerDetails();
        this.emailAccount = new EmailAccount(username, password);
        this.displayFolders = new DisplayFolders(treeView);
    }

    public void login() throws MessagingException {
        this.loginToMailServer = new LoginToMailServer(emailAccount);
        loginToMailServer.login();
        getFolders();
    }

    private Store getStore() {
        try {
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(accountDetailsProvider.getAccountDetail("username"), accountDetailsProvider.getAccountDetail("password"));
                }
            };
            Session session = Session.getInstance(serverDetailsProvider.getServerProperties(), authenticator);
            Store store = session.getStore("imaps");
            store.connect(serverDetailsProvider.getServerProperties().getProperty("inboxHost"), accountDetailsProvider.getAccountDetail("username"), accountDetailsProvider.getAccountDetail("password"));

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return store;
    }

    public AccountDetailsProvider getAccountDetailsProvider() {
        return accountDetailsProvider;
    }

    public ServerDetailsProvider getServerDetailsProvider() {
        return serverDetailsProvider;
    }

    private Folder getInbox() {

        try {
            System.out.println(serverDetailsProvider.getServerProperties().getProperty("inboxHost"));
            System.out.println("aa");
            store.connect(serverDetailsProvider.getServerProperties().getProperty("inboxHost"), accountDetailsProvider.getAccountDetail("username"), accountDetailsProvider.getAccountDetail("password"));
            System.out.println("bb");
            return store.getFolder("inbox");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void getInboxMessages() throws MessagingException {
        this.messages = FXCollections.observableArrayList();
        try {
            if (inbox.getType() != Folder.HOLDS_FOLDERS) {
                inbox.open(Folder.READ_ONLY);
                int folderSize = inbox.getMessageCount();
                System.out.println("inbox msg count is : " + folderSize);
                for (int i = folderSize; i > 0; i--) {
                    messages.add(inbox.getMessage(i));
                }
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        printMessages();
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
        this.getFolders = new GetFolders(emailAccount.getStore());
        this.inbox = getFolders.getInboxFolder();

        //Set folders for ui
        //TreeItem treeItem = new TreeItem(emailAccount.getAddress());
        //treeItem.setExpanded(true);
        //treeItem.getChildren().add("INBOX");
        this.displayFolders.setNewPrimaryFolder(emailAccount.getAddress());
        this.displayFolders.addFolderToTree(inbox.getName());
        //Get messages in folders
        getInboxMessages();
    }
}