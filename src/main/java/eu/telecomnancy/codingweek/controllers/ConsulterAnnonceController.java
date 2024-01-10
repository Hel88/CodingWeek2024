package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;
import eu.telecomnancy.codingweek.utils.Annonce;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class ConsulterAnnonceController implements Observer{

    private Application app;
    private Annonce annonce;
    @FXML
    private Label titre;
    @FXML
    private Label description;
    @FXML
    private Label user;
    
    public ConsulterAnnonceController(Application app) {
        this.app = app;
        app.addObserver(this);
    }

    public void initialize() {
        annonce = app.getAnnonceAffichee();
        if (annonce != null) {
            titre.setText(annonce.getTitre());
            description.setText(annonce.getDescription());
            user.setText(annonce.getReferent());
        }
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
    public void consulterProfil(){
        app.getSceneController().switchToMonProfil();
    }

    @FXML
    public void reserver() throws IOException {
        app.getDataTransactionUtils().addTransaction(String.valueOf(annonce.getId()), app.getMainUser().getUserName(), "En attente");
    }
}
