package model;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ui.MainScene;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        MainScene mainScene = new MainScene(640, 480);
        
        stage.setScene(mainScene.getMainScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}