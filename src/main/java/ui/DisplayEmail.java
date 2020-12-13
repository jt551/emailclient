/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.io.IOException;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import model.Email;

/**
 *
 * @author juhat
 */
public class DisplayEmail extends Service {

    /**
     * Service Class to display an email thru Webengine.
     * <p>
     * Gets webengine from fxml controller as a constructor parameter.
     * <p>
     * Gets an email from set() method and starts a task to display content with restart().
     * <p>
     * When all done set on succeed method puts content to webengine.
     */
    private WebEngine webEngine;
    private StringBuffer stringBuffer;
    private Message message;
    @FXML
    private Label userMessageLabel;

    public DisplayEmail(WebEngine webEngine, Label userMessageLabel) {
        this.webEngine = webEngine;
        this.stringBuffer = new StringBuffer();
        this.userMessageLabel = userMessageLabel;

        setOnSucceeded(s -> {
            displayEmail();
            userMessageLabel.setText("Display service succeeded");
        });
        setOnFailed(fail -> {
            userMessageLabel.setText("Display service failed");
        });
        setOnCancelled(cancelled -> {
            userMessageLabel.setText("Display service cancelled");
        });
    }

    public void setMessage(Message message) {
        this.message = message;
    }
    /**
     * JavaFX concurrent task for creating stringbuffer from email to the webengine.
     * <p>
     * Based on task result, show email on screen or update info label with error message. 
     * @return 
     */
    @Override
    protected Task createTask() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                try {
                    readMessageContentToBuffer();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

        };
    }
    /**
     * Basic function to load email text/HTML to stringbuffer.
     * <p>
     * Clear stringbuffer -> Read text messages to buffer or relay task to multipart handling.
     * 
     * @throws IOException
     * @throws MessagingException 
     */
    private void readMessageContentToBuffer() throws IOException, MessagingException {
        stringBuffer.setLength(0);
        if (this.message.getContentType().contains("multipart")) {
            Multipart multipart = (Multipart) message.getContent();
            readMultiPartEmailToBuffer(multipart);
        } else {
            stringBuffer.append(this.message.getContent().toString());
        }
    }

    /**
     * After success load content to webengine.
     */
    private void displayEmail() {
        webEngine.loadContent(stringBuffer.toString());
    }
    
    /**
     * Recursive function to loop thru multiparts of email message and append stringbuffer with text content.
     * 
     * @param multipart
     * @throws MessagingException
     * @throws IOException 
     */
    private void readMultiPartEmailToBuffer(Multipart multipart) throws MessagingException, IOException {

        int bodyparts = multipart.getCount();
        for (int i = bodyparts - 1; i >= 0; i--) {
            BodyPart bodypart = multipart.getBodyPart(i);
            if (bodypart.getContentType().contains("multipart")) {
                //recursive call for next multipart
                Multipart newMultipart = (Multipart) bodypart.getContent();
                readMultiPartEmailToBuffer(newMultipart);
            } else if (bodypart.getContentType().contains("text") || bodypart.getContentType().contains("TEXT/HTML") || bodypart.getContentType().contains("mixed")) {
                stringBuffer.append(bodypart.getContent().toString());
            } else {
                // Attached files here somewhere ..?
            }

        }
    }

    /*private void readFromMultipartBodyPart(Bodypart bodypart) throws MessagingException, IOException {
        if (bodypart.getContentType().contains("text") || bodypart.getContentType().contains("TEXT/HTML") || bodypart.getContentType().contains("mixed")) {
            stringBuffer.append(bodypart.getContent().toString());
        } else if (bodypart.getContentType().contains("multipart")) {
            Multipart nextMultiPart = (Multipart) bodypart.getContent();
            readFromMultipartBodyPart(nextMultiPart);
            System.out.println("readFromMultipartBodyPart");
        } else {
            // Attached files here?
        }
    }*/
}
