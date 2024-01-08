package eu.telecomnancy.codingweek;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class InscriptionController {

    private HelloApplication app;
    @FXML
    private TextField pwd;
    @FXML
    private TextField mail;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField adresse;
    @FXML
    private TextField ville;
    @FXML
    private TextField surnom;

    public InscriptionController(HelloApplication app) {
        this.app = app;
    }

    @FXML
    public void inscription(){
        System.out.println("Inscription");
        System.out.println("Mail : " + mail.getText());
        System.out.println("Password : " + pwd.getText());
        System.out.println("Nom : " + nom.getText());
        System.out.println("Prenom : " + prenom.getText());
        System.out.println("Adresse : " + adresse.getText());
        System.out.println("Ville : " + ville.getText());
        System.out.println("Surnom : " + surnom.getText());
    }
    
}
