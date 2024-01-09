package eu.telecomnancy.codingweek;

import eu.telecomnancy.codingweek.controllers.SceneController;
import eu.telecomnancy.codingweek.utils.DataUsersUtils;
import eu.telecomnancy.codingweek.utils.User;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    private SceneController sceneController;
    private DataUsersUtils dataUsersUtils;
    private User mainUser;
    

    @Override
    public void start(Stage stage) {
        try {
            this.sceneController = new SceneController(stage, this);
            this.dataUsersUtils = DataUsersUtils.getInstance();
            this.mainUser = null;
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

    public DataUsersUtils getDataUsersUtils() {
        return dataUsersUtils;
    }

    public User getMainUser() {
        return mainUser;
    }

    public void setMainUser(User mainUser) {
        this.mainUser = mainUser;
    }
}