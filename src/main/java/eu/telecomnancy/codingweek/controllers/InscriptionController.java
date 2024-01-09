package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class InscriptionController {

    private final String filePath = "src/main/resources/eu/telecomnancy/codingweek/users.json";
    private Application app;
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

    public InscriptionController(Application app) {
        this.app = app;
    }

    @FXML
    public void inscription() {
        String password = passwordField.getText();
        String email = emailField.getText();
        String lastName = lastNameField.getText();
        String firstName = firstNameField.getText();
        String address = addressField.getText();
        String city = cityField.getText();
        String userName = userNameField.getText();

        // Create a JSON object with user information
        JSONObject userObject = new JSONObject();

        // Vérifier si le nom d'utilisateur est déjà utilisé
        if (isUserNameUnique(userName)) {
            // Utilisation du nom d'utilisateur comme clé pour chaque utilisateur
            userObject.put("UserName", userName);
            String hashedPassword = hashPassword(password);
            userObject.put("password", hashedPassword);
            userObject.put("email", email);
            userObject.put("last_name", lastName);
            userObject.put("first_name", firstName);
            userObject.put("address", address);
            userObject.put("city", city);
            userObject.put("planning", 0);
            userObject.put("announces", 0);
            userObject.put("eval", 0);

            // Read existing content from users.json
            JSONObject existingData = new JSONObject();
            try {
                String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
                existingData = new JSONObject(fileContent);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Ajouter le nouvel objet utilisateur aux données existantes
            existingData.put(userName, userObject);

            // Écrire les données mises à jour dans users.json
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                fileWriter.write(existingData.toString(2)); // Le '2' est pour le niveau d'indentation
            } catch (IOException e) {
                e.printStackTrace();
            }

            app.getSceneController().switchToConnexion();

        } else {
            // Afficher une alerte si le nom d'utilisateur n'est pas unique
            showAlert("Nom d'utilisateur non unique", "Le nom d'utilisateur est déjà utilisé. Veuillez choisir un autre nom.");
        }
    }

    private boolean isUserNameUnique(String userName) {
        // Vérifier l'unicité du nom d'utilisateur
        try {
            String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONObject existingData = new JSONObject(fileContent);

            return !existingData.has(userName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String hashPassword(String password) {
        // Utiliser BCrypt pour hasher le mot de passe
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
