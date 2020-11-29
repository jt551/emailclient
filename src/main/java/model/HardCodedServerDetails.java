/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Properties;

/**
 * Class for proving needed email server details for development purposes.
 * Removed when local db for settings is implemented.
 */
public class HardCodedServerDetails implements ServerDetailsProvider{
    private Properties serverProperties;
        
    public HardCodedServerDetails(){
        this.serverProperties = new Properties();
        this.serverProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        this.serverProperties.put("imapHost", "imap.gmail.com");
        this.serverProperties.put("mail.smtp.auth", true);
        this.serverProperties.put("mail.smtp.starttls.enable", true);
        this.serverProperties.put("mail.smtp.host", "smtp.gmail.com");
        this.serverProperties.put("mail.smtp.port", "587");
        this.serverProperties.put("mail.store.protocol", "imaps");
    }
    
    @Override
    public Properties getServerProperties(){
        return this.serverProperties;
    }
    
    public void setServerProperties(Properties serverProperties) {
        this.serverProperties = serverProperties;
    }
}
