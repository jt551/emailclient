/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Properties;
import javax.mail.Session;
import javax.mail.Store;

/**
 *
 * @author juhat
 */
public class EmailAccount {
    private String address;
    private String password;
    
    private Database database;
    
    private Properties serverProperties;
    
    private Store store;
    
    private Session session;
    
    private EmailFolderInTree rootFolder;
    
    
    public EmailAccount(String address, String password, Database database) {
        this.address = address;
        this.password = password;
        this.serverProperties = new Properties();
        this.rootFolder = new EmailFolderInTree(address);
        this.database = database;
        this.setServerProperties(this.database.getPropertiesByName("gmail"));                
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Properties getServerProperties() {
        return serverProperties;
    }

    public void setServerProperties(Properties serverProperties) {
        this.serverProperties = serverProperties;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public EmailFolderInTree getRootFolder() {
        return rootFolder;
    }

    public Session getSession() {
        return this.session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
    
    
}
