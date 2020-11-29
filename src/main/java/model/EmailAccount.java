/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Properties;
import javax.mail.Store;

/**
 *
 * @author juhat
 */
public class EmailAccount {
    private String address;
    private String password;
    
    private Properties serverProperties;
    
    private Store store;

    public EmailAccount(String address, String password) {
        this.address = address;
        this.password = password;
        this.serverProperties = new Properties();
        serverProperties.put("incomingHost", "imap.gmail.com");
        serverProperties.put("mail.store.protocol", "imaps");

        serverProperties.put("mail.transport.protocol", "smtps");
        serverProperties.put("mail.smtps.host", "smtp.gmail.com");
        serverProperties.put("mail.smtps.auth", "true");
        serverProperties.put("outgoingHost", "smtp.gmail.com");
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
    
    
    
    
}
