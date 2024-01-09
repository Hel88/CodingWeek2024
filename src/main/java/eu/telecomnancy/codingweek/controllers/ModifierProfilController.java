package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ModifierProfilController {
    
    private Application app;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private TextField adresse;
    @FXML
    private TextField ville;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private Label note;


    public ModifierProfilController(Application app) {
        this.app = app;
    }

    @FXML
    public void validerModifProfil(){
        //user.setUsername(username.getText());
        //etc
        app.getSceneController().switchToMonProfil();
        System.out.println("validé");
    }
}
