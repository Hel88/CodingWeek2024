package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MonAnnonceController {
    
    private Application app;
    @FXML
    private Label titre;
    @FXML
    private Label description;
    @FXML
    private Label prix;

    

    public MonAnnonceController(Application app) {
        this.app = app;
    }

    @FXML
    public void modifierAnnonce(){
        // app.getSceneController().switchToModifierAnnonce();
    }

    @FXML
    public void supprimerAnnonce(){
    }
    
}
