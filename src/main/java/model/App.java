package model;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
//        MainScene mainScene = new MainScene(640, 480);
//        
//        stage.setScene(mainScene.getMainScene());
//        stage.show();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainFXML.fxml"));
            
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}