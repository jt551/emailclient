/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javax.mail.Message;

/**
 *
 * @author juhat
 */
public class EmailFolderInTree<String> extends TreeItem{
    /**
     * Class to represent one folder in account and hold its messages.
     */
    private String name;
    private ObservableList<Email> emailsInFolder;

    public EmailFolderInTree(String name) {
        super(name);
        this.name = name;
        this.emailsInFolder = FXCollections.observableArrayList();
    }
    
    public void addEmail(Email email){
        emailsInFolder.add(email);
    }
    
    public List getEmailsInFolder(){
        return emailsInFolder;
    }
    
    public String getName(){
        return name;
    }
}
