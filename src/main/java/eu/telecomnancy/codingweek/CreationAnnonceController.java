package eu.telecomnancy.codingweek;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreationAnnonceController {
    
    private HelloApplication app;
    @FXML
    private TextField titre;
    @FXML
    private TextArea description;
    @FXML
    private TextField prix;
    

    public CreationAnnonceController(HelloApplication app) {
        this.app = app;
    }

    @FXML
    public void addAnnonce(){
        System.out.println("Creation Annonce");
        System.out.println("Titre : " + titre.getText());
        System.out.println("Description : " + description.getText());
        System.out.println("Prix : " + prix.getText());
    }
}
