/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Class for sending an email.
 * Used by FXMLController EventHandler, that passes required parameters from user interface. 
 * 
 */

public class SendMail {

    private EmailAccount emailAccount;

    private Session session;
    
    private Message message;
    /**
    * SendMail constructor.
    * Requires three parameters from UI.
    * @param to message recipient from user interface text field.
    * @param subject message subject or title from user interface text field.
    * @param messageBody message from user interface text area.
    * 
    * TODO: move session to send()
    */
    public SendMail(EmailAccount emailAccount) {
        this.emailAccount = emailAccount;
        this.session = emailAccount.getSession();
    }
    /**
    * Send created message.
    * Create new MimeMessage and use Transport to send it.
    * 
    * TODO: add session.
    * exceptions back to controller
    */
    public void createEmail(String to, String subject, String content) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailAccount.getAddress()));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            message.setSubject(subject);
            message.setText(content);

            
            
            System.out.println("SendEmail.send() complete");

        } catch (Exception e) {
            //TODO: add event to logfile            
            e.printStackTrace();
        }
    }
    
    public void send(){
        try {
            Transport.send(message);            
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
    
    
    public void setSession(Session session) {
        this.session = session;
    }

    

    public Session getSession() {
        return session;
    }

    

    

    
    
}
