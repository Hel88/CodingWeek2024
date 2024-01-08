package eu.telecomnancy.codingweek;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

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