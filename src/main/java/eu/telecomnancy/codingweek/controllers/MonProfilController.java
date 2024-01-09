package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MonProfilController {

    private Application app;
    @FXML
    private Label username;
    @FXML
    private Label email;
    @FXML
    private Label adresse;
    @FXML
    private Label ville;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;

    public MonProfilController(Application app) {
        this.app = app;
    }

    @FXML
    public void modifierProfil(){

    }

    @FXML
    public void supprimerProfil(){
    }
    
}
