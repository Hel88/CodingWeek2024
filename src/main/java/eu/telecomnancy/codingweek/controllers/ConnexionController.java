package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ConnexionController {

    // Fields
    private final Application app;
    @FXML 
    private TextField pwd;
    @FXML
    private TextField userName;


    // Constructor
    public ConnexionController(Application app) {
        this.app = app;
    }


    // Methods
    @FXML
    public void connexion() throws IOException {
        // Method related to the authentication of a user

        // Retrieve the entered username and password
        String enteredUsername = userName.getText();
        String enteredPassword = pwd.getText();

        // Check if the username exists
        if (app.getUserUtils().doesUserExist(enteredUsername)) {
            if (app.getUserUtils().checkPassword(enteredUsername, enteredPassword)) {
                app.getSceneController().switchToMonProfil();
            } else {
                showAlert("Mauvais mot de passe");
            }
        } else {
            showAlert("Le nom d'utilisateur n'existe pas");
        }
    }

    private void showAlert(String message){
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void inscription(){
        app.getSceneController().switchToInscription();
    }
    
}
