package eu.telecomnancy.codingweek.controllers;

import java.io.IOException;

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
        //on passe l'annonce à modifier à l'application
        app.setAnnonceAffichee(annonce);
        app.notifyObservers("annonce");
        app.getSceneController().switchToModifierAnnonce();
    }

    @FXML
    public void supprimerAnnonce() throws IOException{
        //pop up etes-vous sûr de vouloir supprimer cette annonce ?
        Alert alert = new Alert(AlertType.CONFIRMATION);

        alert.setTitle("Confirmation");
        alert.setHeaderText("Etes-vous sûr de vouloir supprimer cette annonce ?");
        alert.showAndWait();
        
        if (alert.getResult().getButtonData().isDefaultButton()) {
            //suppression de l'annonce
            app.getDataAnnoncesUtils().deleteAnnonce(annonce.getId()+"");
            app.notifyObservers("annonce");
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
