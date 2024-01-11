package eu.telecomnancy.codingweek.utils;

import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class DataUsersUtils {

    // Fields
    private final String filePath;
    private static DataUsersUtils instance;
    private final JSONObject data;

    // Private constructor to prevent instantiation
    private DataUsersUtils() throws IOException {
        FileAccess fileAccess = new FileAccess();
        this.filePath = fileAccess.getPathOf("users.json");
        File file = new File(filePath);
        String fileContent = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        data = new JSONObject(fileContent);
    }

    // Public method to get the instance of the singleton
    public static synchronized DataUsersUtils getInstance() throws IOException {
        if (instance == null) {
            instance = new DataUsersUtils();
        }
        return instance;
    }

    // Methods
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

    public void addUser(String userName, String password, String email, String lastName, String firstName, String address, String city, String planning) throws IOException {
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
        userObject.put("planning", planning);
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

    public String hashPassword(String password) {
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Add password bytes to digest
            md.update(password.getBytes());

            // Get the hash's bytes
            byte[] bytes = md.digest();

            // These bytes[] has bytes in decimal format. Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }

            // Get complete hashed password in hex format
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkPassword(String userName, String password) throws IOException {
        // Method related to the authentication of a user
        User user = getUserByUserName(userName);
        String hashedPassword = hashPassword(password);
        return user.getPassword().equals(hashedPassword);
    }

    public void updateUser(User User) throws IOException {
        // Method related to the modification of a user

        // Create a JSON object with user information
        JSONObject userObject = new JSONObject();
        userObject.put("password", User.getPassword());
        userObject.put("email", User.getEmail());
        userObject.put("lastName", User.getLastName());
        userObject.put("firstName", User.getFirstName());
        userObject.put("address", User.getAddress());
        userObject.put("city", User.getCity());
        userObject.put("announces", User.getAnnounces());

        // Add the user to the JSON file
        data.put(User.getUserName(), userObject);
        FileWriter file = new FileWriter(filePath);
        file.write(data.toString());
        file.flush();
    }

    public int getCalendarOf(String userName) throws IOException {
        // Method related to the modification of a user

        // Create a JSON object with user information
        JSONObject userObject = data.getJSONObject(userName);
        return userObject.getInt("planning");
    }
}