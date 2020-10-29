/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import controller.SendMail;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MainScene {

    private int heigth;
    private int width;

    public MainScene(int heigth, int width) {
        this.heigth = heigth;
        this.width = width;
    }

    public Scene getMainScene() {
        BorderPane layout = new BorderPane();

        Button sendButton = new Button("Send");
        Button settingsButton = new Button("Settings");
        Button getButton = new Button("Get");

        HBox buttons = new HBox();
        buttons.setSpacing(10);
        buttons.getChildren().add(sendButton);
        buttons.getChildren().add(settingsButton);

        VBox vBoxLeft = new VBox();
        vBoxLeft.getChildren().add(getButton);
        VBox vBoxRight = new VBox();
        TextArea textArea = new TextArea();
        vBoxRight.getChildren().add(new TextField(""));
        vBoxRight.getChildren().add(new TextArea(""));

        layout.setTop(buttons);
        layout.setLeft(vBoxLeft);
        layout.setRight(textArea);

       
        sendButton.addEventHandler(MouseEvent.MOUSE_CLICKED, sendButtonClicked);

        Scene scene = new Scene(layout, heigth, width);
        return scene;
    }

    EventHandler<MouseEvent> sendButtonClicked = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            SendMail newMail = new SendMail("jtwebapps@gmail.com", "Java Test Subject", "Java Test Message");
            newMail.send();
        }
    };
}
