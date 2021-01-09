Basic Email Client  
Java Maven Project for ohte20 course in Helsinki Open University.  
https://github.com/jt551/emailclient  
[![<jt551>](https://circleci.com/gh/jt551/emailclient.svg?style=shield)](https://circleci.com/gh/jt551/emailclient)  
[![codecov](https://codecov.io/gh/jt551/emailclient/branch/main/graph/badge.svg?token=SK7PA5BVQN)](https://codecov.io/gh/jt551/emailclient)  
[![Maintainability](https://api.codeclimate.com/v1/badges/2855f35eb5dda00f2b37/maintainability)](https://codeclimate.com/github/jt551/emailclient/maintainability)  

Use only gmail account that contains no valuable information at this stage.  
  
You can login to a account and program gets all email folders with messages (IMAP). 
Prior to login you can change/save email server settings by pressing settings button or reset them to default. 
Selecting a folder populates tableview with emails and selecting an email it shows its content in webengine window.  
Send new email opens new window and it sends an email.  
  
Todo:  
Write javamail mock tests.  
Handle file attachments.  
Messagecount listeners to folders or inbox atleast and add new messages to folder.  
Option to save password encrypted in database file.  
Upgrade UI.  
![alt text](https://github.com/jt551/emailclient/blob/main/screenshot.png)  
![alt text](https://github.com/jt551/emailclient/blob/main/screenshot2.png)  
