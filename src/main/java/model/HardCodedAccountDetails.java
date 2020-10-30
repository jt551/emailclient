/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Properties;

/**
 *
 * @author juhat
 */
public class HardCodedAccountDetails implements AccountDetailsProvider{
    Properties account;
    
    public HardCodedAccountDetails(){
        this.account = new Properties();
        this.account.put("firstname", "jaykoe");
        this.account.put("lastname", "tili");
        this.account.put("username", "jaykoe.tili@gmail.com");
        this.account.put("password", "secretpassword");
    }
    
    @Override
    public String getAccountDetail(String detail) {
        return account.getProperty(detail);
    }
}
