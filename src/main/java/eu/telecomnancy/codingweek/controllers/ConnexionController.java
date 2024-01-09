package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ConnexionController {

    private Application app;
    @FXML 
    private TextField pwd;
    @FXML
    private TextField mail;

    public ConnexionController(Application app) {
        this.app = app;

    }


    @FXML
    public void connexion(){
        System.out.println("Connexion");
        System.out.println("Mail : " + mail.getText());
        System.out.println("Password : " + pwd.getText());
        app.getSceneController().switchToCreationAnnonce();
    }

    @FXML
    public void inscription(){
        app.getSceneController().switchToInscription();
    }
    
}
