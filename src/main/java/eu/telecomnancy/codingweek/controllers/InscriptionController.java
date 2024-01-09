package eu.telecomnancy.codingweek.controllers;


import eu.telecomnancy.codingweek.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;


public class InscriptionController {

    // Fields
    private final Application app;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField userNameField;


    // Constructor
    public InscriptionController(Application app) {
        this.app = app;
    }


    // Methods
    @FXML
    public void inscription() throws IOException {
        // Method related to the creation of a new user

        // Retrieve the entered information
        String password = passwordField.getText();
        String email = emailField.getText();
        String lastName = lastNameField.getText();
        String firstName = firstNameField.getText();
        String address = addressField.getText();
        String city = cityField.getText();
        String userName = userNameField.getText();

        // Check if the username is already in use
        if (app.getUserUtils().isUserNameUnique(userName)) {
            // Add the user to the JSON file
            app.getUserUtils().addUser(userName, password, email, lastName, firstName, address, city);
            app.getSceneController().switchToConnexion();
        } else {
            showAlert();
        }
    }

    private void showAlert() {
        // Method related to the display of an error message if the username is already in use
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Ce nom d'utilisateur est déjà utilisé");
        alert.showAndWait();
    }
}
