/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Class for sending an email.
 * Used by FXMLController EventHandler, that passes required parameters from user interface. 
 * 
 */

public class SendMail {

    private EmailAccount emailAccount;

    private Session session;
    
    private MimeMessage message;
    /**
    * SendMail constructor.
    * Requires three parameters from UI.
    * @param Emailaccount emailAccount message recipient from user interface text field.
    *  
    * 
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
            this.message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailAccount.getAddress()));
            message.setRecipients(
                    Message.RecipientType.TO,
                    to
            );
            message.setSubject(subject);
            Multipart multipart = createMultipart(content);
            message.setContent(multipart);            
        } catch (Exception e) {
            //TODO: add event to logfile            
            e.printStackTrace();
        }
    }
    
    public void send(){
        try {
            System.out.println("SendMail send() start");
            Transport transport = session.getTransport();
            transport.connect();
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("SendMail send() end");
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

    private Multipart createMultipart(String content) throws MessagingException {
        
        Multipart multipart = new MimeMultipart();
        BodyPart body = new MimeBodyPart();
        
        body.setContent(content, "text/html");
        multipart.addBodyPart(body);
        
        return multipart;
    }

    

    
    
}
