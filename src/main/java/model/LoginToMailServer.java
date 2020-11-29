/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;

/**
 *
 * @author juhat
 */
public class LoginToMailServer {

    EmailAccount emailAccount;

    public LoginToMailServer(EmailAccount emailAccount) {
        this.emailAccount = emailAccount;

    }

    public void login() {
        
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailAccount.getAddress(), emailAccount.getPassword());
            }
        };
           
        try {
            Session session = Session.getInstance(emailAccount.getServerProperties(), authenticator);
            Store store = session.getStore("imaps");
            
            store.connect(emailAccount.getServerProperties().getProperty("imapHost"), emailAccount.getAddress(), emailAccount.getPassword());
            emailAccount.setStore(store);
            
            System.out.println("---------------------");
            System.out.println("----  Logged in  ----");
            System.out.println("---------------------");
            
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (AuthenticationFailedException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
