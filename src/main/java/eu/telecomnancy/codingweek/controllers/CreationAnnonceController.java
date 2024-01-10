package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreationAnnonceController {
    
    private Application app;
    @FXML
    private TextField titre;
    @FXML
    private TextArea description;
    @FXML
    private TextField prix;
    

    public CreationAnnonceController(Application app) {
        this.app = app;
    }

    @FXML
    public void addAnnonce(){
        // System.out.println("Creation Annonce");
        // System.out.println("Titre : " + titre.getText());
        // System.out.println("Description : " + description.getText());
        // System.out.println("Prix : " + prix.getText());

        //A FAIRE: VERIFIER QUE LE PRIX EST BIEN UN INT

        CreerAnnonce creerAnnonce = new CreerAnnonce();
        //A FAIRE: récupérer la catégorie, le référent
        if (app.getMainUser() == null) {
            String referent = "Anonyme";
        }
        else{
            String referent = app.getMainUser().getUserName();
            creerAnnonce.nouvelleAnnonce(titre.getText(), "catégorie", description.getText(), Integer.parseInt(prix.getText()), referent, true);
        }
        
        System.out.println("dcvfzddvffe");
        app.getSceneController().switchToMesAnnonces();
        System.out.println("sddfgfefrfb");
        //A FAIRE: refresh
    }
}
