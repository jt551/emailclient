/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import javax.mail.Message;
import javax.mail.MessagingException;

/**
 *
 * @author juhat
 */
public class Email {

    private String sender;
    private String to;
    private String subject;
    private Date date;
    private int size;
    private Message message;    

    protected Email(Message message) throws MessagingException {
        this.sender = message.getFrom()[0].toString();
        this.to = message.getRecipients(Message.RecipientType.TO)[0].toString();
        this.subject = message.getSubject();
        this.date = message.getSentDate();
        this.size = message.getSize();
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public String getSubject() {
        return subject;
    }

    public String getTo() {
        return to;
    }

    public Message getMessage() {
        return message;
    }
    
    public Date getDate() {
        return date;
    }

    public int getSize() {
        return size;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return("Email from " + sender + " subject: " + subject + " time: " + date + " size: " + size);
    }
    
}
