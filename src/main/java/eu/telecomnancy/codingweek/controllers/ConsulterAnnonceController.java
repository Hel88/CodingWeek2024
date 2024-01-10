package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;
import eu.telecomnancy.codingweek.utils.Annonce;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ConsulterAnnonceController implements Observer{

    private Application app;
    private Annonce annonce;
    @FXML
    private Label titre;
    @FXML
    private Label description;
    @FXML
    private Label name;
    @FXML
    private Label prix;
    @FXML
    private Label categorie;
    @FXML
    private Label lieu;
    
    public ConsulterAnnonceController(Application app) {
        this.app = app;
        app.addObserver(this);
    }

    public void initialize() {
        annonce = app.getAnnonceAffichee();
        if (annonce == null){return;} 
            titre.setText(annonce.getTitre());
            description.setText(annonce.getDescription());
            name.setText(annonce.getReferent());
            prix.setText(annonce.getPrix()+"");
            categorie.setText(annonce.getCategorie());
            
        
    }

    public void update(String type) {
        if (type == "annonce") {
            initialize();
        }
    }

    @FXML
    public void versMessagerie(){
        //app.getSceneController().switchToMessagerie();
    }

    @FXML
    public void reserver(){
        //TODO
    }
}
