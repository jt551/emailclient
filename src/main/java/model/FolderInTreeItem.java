/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.scene.control.TreeItem;
import javax.mail.Message;

/**
 *
 * @author juhat
 */
public class FolderInTreeItem<String> extends TreeItem{
    /**
     * Class to represent one folder in account and hold its messages.
     */
    private String name;
    private Message[] emailsInFolder;

    public FolderInTreeItem(String name) {
        super(name);
        this.name = name;
        
    }
    
    
}
