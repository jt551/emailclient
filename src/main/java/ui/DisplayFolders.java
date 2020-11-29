/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import controller.FXMLController;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import model.FolderInTreeItem;

/**
 *
 * @author juhat
 */
public class DisplayFolders {
    /**
    * Class for displaying Email accounts folder structure.
    * <p>
    * Gets TreeView item as a constructor parameter and appends it with folders found in email account.
    * <p>
    * Currently only adding inbox.
    * <p>
    * TODO : Loop thru all folders and sub folders and add them to TreeView.
    */
    @FXML
    private TreeView<String> leftTreeView;
    
    private TreeItem<String> folders;

    public DisplayFolders(TreeView treeView) {
        /**
         * Set treeview from FXML Controller to this.
         * <p>
         * Add empty root that wont be displayed.
         */
        this.leftTreeView = treeView;
        this.folders = new TreeItem<String>("");
        this.leftTreeView.setRoot(folders);
        this.leftTreeView.setShowRoot(false);
    }

    public void setFolders(TreeItem<String> folders) {
        this.folders = folders;
    }

    public TreeItem<String> getFolders() {
        return folders;
    }

    public void setNewPrimaryFolder(String account) {
        FolderInTreeItem<String> newTreeItem = new FolderInTreeItem<String>(account);
        this.folders.getChildren().add(newTreeItem);
    }
    
    public void addFolderToTree(String name){
        FolderInTreeItem<String> treeItem = new FolderInTreeItem<String>(name);
        this.folders.getChildren().get(0).setExpanded(true);
        this.folders.getChildren().get(0).getChildren().add(treeItem);
    }
}
