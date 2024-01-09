package eu.telecomnancy.codingweek;

import eu.telecomnancy.codingweek.controllers.SceneController;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    private SceneController sceneController;
    

    @Override
    public void start(Stage stage) {

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