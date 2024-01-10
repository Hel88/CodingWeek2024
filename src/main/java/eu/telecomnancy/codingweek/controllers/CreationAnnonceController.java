package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;
import javafx.fxml.FXML;
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
    

    public CreationAnnonceController(Application app) {
        this.app = app;
        app.addObserver(this);
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
        }
    }
}
