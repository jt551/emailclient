/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
* Class for retrieving emails from email providers server
* Only imap protocol will be used.
* 
* TODO:
* from https://www.codejava.net
* 
* Typically, the steps to connect to a server and download new e-mail messages are as follows:
*-          Prepare a Properties object which holds server settings such as host, port, protocol…
*
*-          Create a session to initiate a working session with the server.
*
*-          Obtain a store from the session by a specific protocol (IMAP or POP3). IMAP is recommended.
*
*-          Connect to the store using a credential (username and password).
*
*-          Gets inbox folder from the store.
*
*-          Open the inbox folder.
*
*-          Retrieve messages from the folder.
*
*-          Fetch details for each message.
*
*-          Close the folder.
*
*-          Close the store.
*/
public class GetMail {
    
    private AccountDetailsProvider accountDetailsProvider;

    private ServerDetailsProvider serverDetailsProvider;
    
    public GetMail(){
        this.accountDetailsProvider = new HardCodedAccountDetails();
        this.serverDetailsProvider = new HardCodedServerDetails();
    }
    public GetMail(AccountDetailsProvider adp, ServerDetailsProvider sdp){
        this.accountDetailsProvider = adp;
        this.serverDetailsProvider = sdp;
    }
    
    public void get(){
        
    }
}
