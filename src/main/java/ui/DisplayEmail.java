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
     * Gets an email from show() method and starts a task to display content.
     * <p>
     * When all done set on succeed method puts content to webengine.
     */
    private WebEngine webEngine;
    private StringBuffer stringBuffer;
    private Message message;
    
    public DisplayEmail(WebEngine webEngine) {        
        this.webEngine = webEngine;
        this.stringBuffer = new StringBuffer();
        // if succeeded
        setOnSucceeded(s -> {
            //code if Service succeeds
            displayEmail();
        });

        // if failed
        setOnFailed(fail -> {
            //code it Service fails
        });

        //if cancelled
        setOnCancelled(cancelled->{
            //code if Service get's cancelled
        });
    }

    public void setMessage(Message message){
        this.message = message;
    }

    @Override
    protected Task createTask() {
        return new Task(){
            @Override
            protected Object call() throws Exception {
                try{
                    readMessageContentToBuffer();
                } catch(Exception e){
                    e.printStackTrace();
                }
                return null;
            }
            
        };
    }
    
    private void readMessageContentToBuffer() throws IOException, MessagingException{
        stringBuffer.setLength(0);
        if(this.message.getContentType().contains("multipart")){
            readMultiPartEmailToBuffer();
        } else{
        stringBuffer.append(this.message.getContent().toString());
        }
    }

    private void displayEmail() {
        /**
         * After success load content to webengine.
         */
        webEngine.loadContent(stringBuffer.toString());
    }

    private void readMultiPartEmailToBuffer() throws MessagingException, IOException {
        Multipart multipart = (Multipart)message.getContent();
        int bodyparts = multipart.getCount();
        for(int i=bodyparts-1;i>=0;i--){
            BodyPart bodypart = multipart.getBodyPart(i);
            if(!bodypart.getContentType().contains("multipart")){
                stringBuffer.append(bodypart.getContent().toString());
            }
        }
    }
    
}
