package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;
import eu.telecomnancy.codingweek.utils.Annonce;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MonAnnonceController implements Observer {
    
    private Application app;
    @FXML
    private Label titre;
    @FXML
    private Label description;
    @FXML
    private Label prix;

   private Annonce annonce;

    

    public MonAnnonceController(Application app) {
        this.app = app;
        app.addObserver(this);
    }

    // @FXML
    // public void initialize(){
    //     //gère l'affichage
    //     titre.setText(annonce.getTitre());
    //     description.setText(annonce.getDescription());
    //     prix.setText(annonce.getPrix()+"");
    // }

    @FXML
    public void modifierAnnonce(){
        // app.getSceneController().switchToModifierAnnonce();
    }

    @FXML
    public void supprimerAnnonce(){
    }

    @Override
    public void update(String type) {
        if (type == "annonce") {
            annonce = app.getAnnonceAffichee();
            if (annonce != null) {
                titre.setText(annonce.getTitre());
                description.setText(annonce.getDescription());
                prix.setText(annonce.getPrix()+"");
            }
        }
    }
    
}
