package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ConsulterAnnonceController {

    private Application app;
    private String id;
    @FXML
    private Label titre;
    @FXML
    private Label description;
    @FXML
    private Label user;
    
    public ConsulterAnnonceController(Application app) {
        this.app = app;
    }

    public void setId(String id){
        this.id = id;
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
