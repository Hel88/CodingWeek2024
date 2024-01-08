package eu.telecomnancy.codingweek;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private SceneController sceneController;
    

    @Override
    public void start(Stage stage) throws IOException {
        
        try {
            this.sceneController = new SceneController(stage, this);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

    public SceneController getSceneController() {
        return sceneController;
    }

}