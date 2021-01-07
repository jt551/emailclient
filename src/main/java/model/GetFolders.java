/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Store;

/**
 *
 * @author juhat
 */
public class GetFolders extends Service {

    private Store store;
    private EmailFolderInTree rootFolder;
    
    public GetFolders(Store store, EmailFolderInTree rootFolder) {
        this.store = store;
        this.rootFolder = rootFolder;
    }

    public Folder[] getAllFoldersAsList() throws MessagingException {
        Folder[] folders = store.getDefaultFolder().list();
        return folders;
    }

    public void getAllFolders() throws MessagingException {
        //
        Folder[] folders = store.getDefaultFolder().list();
        getAllFoldersToTreeItem(folders, rootFolder);
    }

    private void getAllFoldersToTreeItem(Folder[] folders, EmailFolderInTree rootFolder) throws MessagingException {
        for (Folder folder : folders) {
            EmailFolderInTree<String> treeFolder = new EmailFolderInTree<String>(folder.getName());
            rootFolder.getChildren().add(treeFolder);
            
            // Check if folder has another folders inside
            if (folder.getType() == Folder.HOLDS_FOLDERS) {
                Folder[] subFoldersAsList = folder.list();
                // Recursive call for subfolders
                getAllFoldersToTreeItem(subFoldersAsList, rootFolder);
            }

            getEmailsInFolder(folder, treeFolder);
        }
    }

    private void getEmailsInFolder(Folder folder, EmailFolderInTree treeFolder) throws MessagingException {
        if (folder.getType() != Folder.HOLDS_FOLDERS) {
            folder.open(Folder.READ_ONLY);
            int msgCount = folder.getMessageCount();
            for (int i = msgCount; i > 0; i--) {
                Email email = new Email(folder.getMessage(i));
                treeFolder.addEmail(email);
            }
        }
    }
    
    @Override
    protected Task createTask() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                try {
                    getAllFolders();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

        };
    }
}
