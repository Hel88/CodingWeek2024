package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;
import eu.telecomnancy.codingweek.utils.Annonce;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

    @FXML
    public void modifierAnnonce(){
        app.getSceneController().switchToModifierAnnonce();
    }

    @FXML
    public void supprimerAnnonce(){
        //pop up etes-vous sûr de vouloir supprimer cette annonce ?
        Alert alert = new Alert(AlertType.CONFIRMATION);

        alert.setTitle("Confirmation");
        alert.setHeaderText("Etes-vous sûr de vouloir supprimer cette annonce ?");
        alert.showAndWait();
        
        if (alert.getResult().getButtonData().isDefaultButton()) {
            //suppression de l'annonce
            System.out.println("suppression de " + annonce.getTitre());
            //TODO
            app.getSceneController().switchToMesAnnonces();
        }

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
