package eu.telecomnancy.codingweek.utils;

import eu.telecomnancy.codingweek.Application;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;


public class DataUsersUtils {

    // Fields
    public String filePath;
    private static DataUsersUtils instance;
    private JSONObject data;

    // Private constructor to prevent instantiation
    private DataUsersUtils() throws IOException {
        try {
            filePath = IOUtils.toString(Objects.requireNonNull(getClass().getResource("users.json")), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        data = new JSONObject(filePath);
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
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Add password bytes to digest
            md.update(password.getBytes());

            // Get the hash's bytes
            byte[] bytes = md.digest();

            // This bytes[] has bytes in decimal format. Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
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
}