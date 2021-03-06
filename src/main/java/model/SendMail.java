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
 * Used by SendFXMLController EventHandler, that passes required parameters from user interface. 
 * Creates new email as mime message and sends it to server.
 */

public class SendMail {

    private EmailAccount emailAccount;

    private Session session;
    
    private MimeMessage message;
    /**
    * SendMail constructor.    
    * @param emailAccount for session.
    */
    public SendMail(EmailAccount emailAccount) {
        this.emailAccount = emailAccount;
        this.session = emailAccount.getSession();
    }
    /**
    * Create a new email.
    * Create new MimeMessage with multipart content.
    * @param to from sendFXMLController fields
    * @param subject from sendFXMLController fields
    * @param content from sendFXMLController fields
    * TODO: file attachment as multipart.
    */
    public void createEmail(String to, String subject, String content) {
        try {
            this.session = emailAccount.getSession();
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
    /**
     * Send email.
     * Create transport object and use it to send newly created email.
     */
    public void send(){
        try {
            this.session = emailAccount.getSession();           
            Transport transport = session.getTransport();
            transport.connect();
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();            
        } catch (MessagingException ex) {
            ex.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public String getAddress(){
        return emailAccount.getAddress();
    }

    public MimeMessage getMessage() {
        return message;
    }

    public Session getSession() {
        return session;
    }
    /**
     * Create email body from HTML editors content as multipart body. 
     * @param content
     * @return multipart
     * @throws MessagingException 
     */
    private Multipart createMultipart(String content) throws MessagingException {
        
        Multipart multipart = new MimeMultipart();
        BodyPart body = new MimeBodyPart();
        
        body.setContent(content, "text/html");
        multipart.addBodyPart(body);
        
        return multipart;
    }
    
}
