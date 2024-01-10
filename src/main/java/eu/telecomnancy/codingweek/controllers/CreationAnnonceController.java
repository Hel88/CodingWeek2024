package eu.telecomnancy.codingweek.controllers;

import java.io.IOException;

import eu.telecomnancy.codingweek.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class CreationAnnonceController implements Observer{

    private Application app;
    private String categorie;
    private String action;
    @FXML
    private TextField titre;
    @FXML
    private TextArea description;
    @FXML
    private TextField prix;
    @FXML
    private Button AddAnnonce;
    @FXML
    private Label label;
    @FXML
    private Label messageErreur;


    public CreationAnnonceController(Application app, String action) {
        this.app = app;
        this.action = action;
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

    public void modifierAnnonce(){

    }

    @FXML
    public void addAnnonce() throws IOException {
        
        // comme la page fxml se charge de la modification et de la création, le bouton doit changer d'action selon ce qu'on veut faire
        if (action == "creation") {
            creerAnnonce();
        }
        else if (action == "modification") {
            modifierAnnonce();
        }

    }

    @Override
    public void update(String type) {
        if (type == "annonce") {
            categorie = app.getCategorieAnnonceACreer();
            System.out.println(app.getCategorieAnnonceACreer());
        }
        if (type == "categorie") {
            label.setText("Modifier votre annonce");
            AddAnnonce.setText("Modifier");
        }
    }
}
