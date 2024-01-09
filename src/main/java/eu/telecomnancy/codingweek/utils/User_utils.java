package eu.telecomnancy.codingweek.utils;

import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class User_utils {

    // Fields
    public final static String filePath = "src/main/resources/eu/telecomnancy/codingweek/users.json";
    private static User_utils instance;
    private JSONObject data = new JSONObject();

    // Private constructor to prevent instantiation
    private User_utils() throws IOException {
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        data = new JSONObject(fileContent);
    }

    // Public method to get the instance of the singleton
    public static synchronized User_utils getInstance() throws IOException {
        if (instance == null) {
            instance = new User_utils();
        }
        return instance;
    }

    public boolean isUserNameUnique(String userName) throws IOException {
        // Method related to the creation of a new user
        // Check if the username is already in use
        return !data.has(userName);
    }

    public boolean doesUserExist(String userName) throws IOException {
        // Method related to the authentication of a user
        // Check if the username exists
        return data.has(userName);
    }

    public void addUser(String userName, String password, String email, String lastName, String firstName, String address, String city) throws IOException {
        // Method related to the creation of a new user

        // Create a JSON object with user information
        JSONObject userObject = new JSONObject();
        String hashedPassword = hashPassword(password);
        userObject.put("password", hashedPassword);
        userObject.put("email", email);
        userObject.put("lastName", lastName);
        userObject.put("firstName", firstName);
        userObject.put("address", address);
        userObject.put("city", city);
        userObject.put("announces", 0);
        userObject.put("planning", 0);
        userObject.put("eval", 0);

        // Add the user to the JSON file
        data.put(userName, userObject);
        FileWriter file = new FileWriter(filePath);
        file.write(data.toString());
        file.flush();
    }

    public User getUserByUserName(String userName) throws IOException {
        // Method related to the authentication of a user
        // Retrieve the User object from the JSON file
        JSONObject userObject = data.getJSONObject(userName);
        return new User(userName, userObject.getString("password"), userObject.getString("email"), userObject.getString("lastName"), userObject.getString("firstName"), userObject.getString("address"), userObject.getString("city"), userObject.getInt("announces"), userObject.getInt("planning"), userObject.getInt("eval"));
    }

    private String hashPassword(String password) {
        // Method related to the hashing of the password
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(String userName, String password) throws IOException {
        // Method related to the authentication of a user
        User user = getUserByUserName(userName);
        return BCrypt.checkpw(password, user.getPassword());
    }
}