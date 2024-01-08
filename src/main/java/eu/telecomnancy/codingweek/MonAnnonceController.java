package eu.telecomnancy.codingweek;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MonAnnonceController {
    
    private HelloApplication app;
    @FXML
    private Label titre;
    @FXML
    private Label description;
    @FXML
    private Label prix;

    

    public MonAnnonceController(HelloApplication app) {
        this.app = app;
    }

    @FXML
    public void modifierAnnonce(){
        //app.switchToModifierAnnonce();
    }

    @FXML
    public void supprimerAnnonce(){
    }
    
}
