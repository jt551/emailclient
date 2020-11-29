/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Store;

/**
 *
 * @author juhat
 */
public class GetFolders {
    private Store store;

    public GetFolders(Store store) {
        this.store = store;
    }
    
    public Folder[] getAllFoldersAsList() throws MessagingException{
        Folder[] folders = store.getDefaultFolder().list();
        return folders;
    }
    
    public Folder getInboxFolder(){
        try{
        return store.getFolder("inbox");
        } catch(MessagingException e){
            e.printStackTrace();
        }
        return null;
    }
}
