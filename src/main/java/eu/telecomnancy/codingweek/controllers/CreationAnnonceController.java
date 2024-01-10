package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;


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
        // System.out.println("Creation Annonce");
        // System.out.println("Titre : " + titre.getText());
        // System.out.println("Description : " + description.getText());
        // System.out.println("Prix : " + prix.getText());


        if (prix.getText().matches("[0-9]+") == false) {
            messageErreur.setText("Le prix doit être un nombre entier.");
            return;
        }

        if (titre.getText().length() == 0) {
            messageErreur.setText("Le titre ne peut pas être vide.");
            return;
        }


        CreerAnnonce creerAnnonce = new CreerAnnonce();
        //A FAIRE: récupérer la catégorie, le référent
        String referent = app.getMainUser().getUserName();
        creerAnnonce.nouvelleAnnonce(titre.getText(), categorie, description.getText(), Integer.parseInt(prix.getText()), referent, true);

        referent = app.getMainUser().getUserName();
        if (app.getMainUser() == null) {
            referent = "Anonyme";
        }
        creerAnnonce.nouvelleAnnonce(titre.getText(), "catégorie", description.getText(), Integer.parseInt(prix.getText()), referent, true);
        

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
