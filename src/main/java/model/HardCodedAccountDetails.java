/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Properties;

/**
 * Class for proving needed email account details for development purposes.
 * Removed when local db for settings is implemented.
 */
public class HardCodedAccountDetails implements AccountDetailsProvider{
    private Properties account;
    
    public HardCodedAccountDetails(){
        this.account = new Properties();
        this.account.put("firstname", "jaykoe");
        this.account.put("lastname", "tili");
        this.account.put("username", "tili@gmail.com");
        this.account.put("password", "passwordhere");
    }
    
    @Override
    public String getAccountDetail(String detail) {
        return account.getProperty(detail);
    }
}
