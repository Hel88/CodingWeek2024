package eu.telecomnancy.codingweek.controllers;

import java.io.IOException;

import eu.telecomnancy.codingweek.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class CreationAnnonceController implements Observer{

    private Application app;
    private String categorie;
    @FXML
    private TextField titre;
    @FXML
    private TextArea description;
    @FXML
    private TextField prix;

    @FXML
    private Label messageErreur;


    public CreationAnnonceController(Application app) {
        this.app = app;
        app.addObserver(this);
    }

    @FXML
    public void addAnnonce() throws IOException {

        // Check if the price is an integer
        if (!prix.getText().matches("[0-9]+")) {
            messageErreur.setText("Le prix doit être un nombre entier.");
            return;
        }

        // Check if the title is empty
        if (titre.getText().isEmpty()) {
            messageErreur.setText("Le titre ne peut pas être vide.");
            return;
        }


        // Create the new annnounce
        app.getDataAnnoncesUtils().addAnnonce(titre.getText(), description.getText(), prix.getText(), categorie, app.getMainUser().getUserName());
        

        app.notifyObservers("annonce");
        app.getSceneController().switchToMesAnnonces();
        //A FAIRE: refresh
    }

    @Override
    public void update(String type) {
        if (type == "annonce") {
            categorie = app.getCategorieAnnonceACreer();
            System.out.println(app.getCategorieAnnonceACreer());
        }
    }
}
