package eu.telecomnancy.codingweek;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ConsulterAnnonceController {

    private HelloApplication app;
    @FXML
    private Label titre;
    @FXML
    private Label description;
    @FXML
    private Label user;
    
    public ConsulterAnnonceController(HelloApplication app) {
        this.app = app;
    }

    @FXML
    public void versMessagerie(){
        //app.getSceneController().switchToMessagerie();
    }

    @FXML
    public void consulterProfil(){
        app.getSceneController().switchToMonProfil();
    }

    @FXML
    public void reserver(){
        //TODO
    }
}
