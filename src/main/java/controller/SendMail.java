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
import model.HardCodedAccountDetails;
import model.AccountDetailsProvider;
import model.HardCodedServerDetails;
import model.ServerDetailsProvider;

import model.HardCodedAccountDetails;
import model.HardCodedServerDetails;

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
        this.accountDetailsProvider = new HardCodedAccountDetails();
        this.serverDetailsProvider = new HardCodedServerDetails();

        this.session = Session.getInstance(serverDetailsProvider.getServerProperties(),
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(accountDetailsProvider.getAccountDetail("username"), accountDetailsProvider.getAccountDetail("password"));
            }
        });
        System.out.println("SendEmail.constructor() complete");
    }

    public void send() {
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
            
            System.out.println("SendEmail.send() complete");

        } catch (Exception e) {
            //TODO: add event to logfile            
            e.printStackTrace();
        }
    }
    
    public Properties getServerDetails() {
        return serverDetailsProvider.getServerProperties();
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessageBody() {
        return messageBody;
    }
    
}
