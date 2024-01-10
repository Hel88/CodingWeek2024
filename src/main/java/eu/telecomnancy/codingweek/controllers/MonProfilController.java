package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;
import eu.telecomnancy.codingweek.utils.Annonce;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.ArrayList;

public class MonProfilController implements Observer {

    private Application app;
    @FXML
    private Label username;
    @FXML
    private Label email;
    @FXML
    private Label address;
    @FXML
    private Label city;
    @FXML
    private Label lastName;
    @FXML
    private Label firstName;
    @FXML
    private Label eval;

    public MonProfilController(Application app) {
        this.app = app;
        app.addObserver(this);
        }

    @FXML
    public void modifierProfil(){
        app.notifyObservers("user");
        app.getSceneController().switchToModifierProfil();
    }

    @FXML
    public void supprimerProfil() throws IOException {
        //pop up etes-vous sûr de vouloir supprimer cette annonce ?
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Confirmation");
        alert.setHeaderText("Etes-vous sûr de vouloir supprimer cette annonce ?");
        alert.showAndWait();

        if (alert.getResult().getButtonData().isDefaultButton()) {
            ArrayList<Annonce> annonces = app.getDataAnnoncesUtils().getAnnoncesByUsername(app.getMainUser().getUserName());
            for (Annonce annonce : annonces) {
                app.getDataAnnoncesUtils().deleteAnnonce(annonce.getId()+"");
            }
            app.getDataUsersUtils().deleteUser(app.getMainUser().getUserName());
            app.setMainUser(null);
            app.notifyObservers("connexion");
            app.getSceneController().switchToConnexion();
        }
    }

        @Override
    public void update(String type){
        if (type == "user"){
            if (app.getMainUser() == null) return;
            username.setText(app.getMainUser().getUserName());
            email.setText(app.getMainUser().getEmail());
            address.setText(app.getMainUser().getAddress());
            city.setText(app.getMainUser().getCity());
            lastName.setText(app.getMainUser().getLastName());
            firstName.setText(app.getMainUser().getFirstName());
            eval.setText(app.getMainUser().getEval()+"");
        }
    }
    
}
