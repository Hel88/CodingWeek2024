package eu.telecomnancy.codingweek;

import eu.telecomnancy.codingweek.controllers.SceneController;
import eu.telecomnancy.codingweek.utils.User_utils;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    private SceneController sceneController;
    private User_utils user_utils;
    

    @Override
    public void start(Stage stage) {
        try {
            this.sceneController = new SceneController(stage, this);
            this.user_utils = User_utils.getInstance();

        } catch (Exception e) {
            System.out.println("Error while loading the scene controller");
        }
    }

    public static void main(String[] args) {
        launch();
    }

    public SceneController getSceneController() {
        return sceneController;
    }

    public User_utils getUserUtils() {
        return user_utils;
    }
}