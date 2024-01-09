package eu.telecomnancy.codingweek;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class ConnexionController {

    private HelloApplication app;
    @FXML 
    private TextField pwd;
    @FXML
    private TextField userName;

    public ConnexionController(HelloApplication app) {
        this.app = app;

    }


    @FXML
    public void connexion(){
        // Retrieve the entered username and password
        String enteredUsername = userName.getText();
        String enteredPassword = pwd.getText();

        // Load users from the JSON file
        List<User> users = loadUsersFromJson("src/main/resources/eu/telecomnancy/codingweek/Users.json");

        // Find the user with the entered username
        Optional<User> optionalUser = users.stream()
                .filter(user -> user.getUserName().equals(enteredUsername))
                .findFirst();

        if (optionalUser.isPresent()) {
            // User found, now check the password
            User user = optionalUser.get();
            String storedHashedPassword = user.getPassword();

            // Use BCrypt to verify the entered password against the stored hashed password
            if (BCrypt.checkpw(enteredPassword, storedHashedPassword)) {
                // Authentication successful
                System.out.println("Authentication successful");
                app.getSceneController().switchToMonProfil();
            } else {
                // Authentication failed
                System.out.println("Authentication failed");
            }
        } else {
            // User not found
            System.out.println("User not found");
        }
    }


    // Méthode pour charger les utilisateurs depuis le fichier JSON
    private List<User> loadUsersFromJson(String jsonFilePath) {
        List<User> userList = new ArrayList<>();

        try {
            // Lisez le contenu du fichier en tant que chaîne
            String jsonString = new String(Files.readAllBytes(Paths.get(jsonFilePath)), StandardCharsets.UTF_8);

            // Parsez l'objet JSON à partir de la chaîne
            JSONObject jsonObject = new JSONObject(jsonString);

            // Itérez sur les clés de l'objet racine (qui sont les noms d'utilisateurs)
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String username = keys.next();
                JSONObject userJson = jsonObject.getJSONObject(username);
                User user = createUserFromJson(userJson);
                userList.add(user);
            }

        } catch (IOException e) {
            e.printStackTrace();  // Gérez l'exception selon les besoins de votre application
        }

        return userList;
    }

    // Méthode d'aide pour créer un objet User à partir d'un objet JSONObject
    private User createUserFromJson(JSONObject userJson) {
        return new User(
                userJson.getString("UserName"),
                userJson.getString("password"),
                userJson.getString("first_name"),
                userJson.getString("last_name"),
                userJson.getString("email"),
                userJson.getString("address"),
                userJson.getString("city"),
                userJson.getInt("announces"),
                userJson.getInt("planning"),
                userJson.getInt("eval")
        );
    }

    @FXML
    public void inscription(){
        app.getSceneController().switchToInscription();
    }
    
}
