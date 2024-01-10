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

    public void creerAnnonce() throws NumberFormatException, IOException{
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
