/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.AccountDetails;
import model.AccountDetailsProvider;
import model.ServerDetails;
import model.ServerDetailsProvider;

import model.AccountDetails;
import model.ServerDetails;

public class SendMail {

    private String to;
    private String subject;
    private String messageBody;
    
    private AccountDetailsProvider accountDetailsProvider;
    
    private ServerDetailsProvider serverDetailsProvider;
    
    private Session session;
    
    public SendMail(String to, String subject, String messageBody) {
        this.to = to;
        this.subject = subject;
        this.messageBody = messageBody;
        this.accountDetailsProvider = new AccountDetails();
        this.serverDetailsProvider = new ServerDetails();
        
        this.session = Session.getInstance(serverDetailsProvider.getServerProperties(),
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(accountDetailsProvider.getAccountDetail("username"), accountDetailsProvider.getAccountDetail("password"));
                    }
                });
        System.out.println("SendEmail.constructor() complete");
    }
    
    public void send(){
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(accountDetailsProvider.getAccountDetail("username")));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            message.setSubject(subject);
            message.setText(messageBody);

            Transport.send(message);
            
            //TODO: add event to logfile
            System.out.println("SendEmail.send() complete");

        } catch (Exception e) {
            //TODO: add event to logfile            
            e.printStackTrace();
        }
    }

    public AccountDetailsProvider getAccountDetailsProvider() {
        return accountDetailsProvider;
    }

    public void setAccountDetailsProvider(AccountDetailsProvider accountDetailsProvider) {
        this.accountDetailsProvider = accountDetailsProvider;
    }

    public ServerDetailsProvider getServerDetailsProvider() {
        return serverDetailsProvider;
    }

    public void setServerDetailsProvider(ServerDetailsProvider serverDetailsProvider) {
        this.serverDetailsProvider = serverDetailsProvider;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    

    public Properties getServerDetails() {
        return serverDetailsProvider.getServerProperties();
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

}
