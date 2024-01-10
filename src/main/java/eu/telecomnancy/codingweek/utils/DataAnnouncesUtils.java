package eu.telecomnancy.codingweek.utils;

import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;

public class DataAnnouncesUtils {

    // Fields
    private final String filePath;
    private static DataAnnouncesUtils instance;
    private final JSONObject data;

    // Private constructor to prevent instantiation
    private DataAnnouncesUtils() throws IOException {
        FileAccess fileAccess = new FileAccess();
        this.filePath = fileAccess.getPathOf("announces.json");
        File file = new File(filePath);
        String fileContent = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        data = new JSONObject(fileContent);
    }

    // Public method to get the instance of the singleton
    public static synchronized DataAnnouncesUtils getInstance() throws IOException {
        if (instance == null) {
            instance = new DataAnnouncesUtils();
        }
        return instance;
    }

    // Methods
    public void addAnnonce(String titre, String description, String prix, String categorie, String referent) throws IOException {
        // Method related to the creation of a new announce

        // Create a JSON object with user information
        JSONObject annonceObject = new JSONObject();
        annonceObject.put("titre", titre);
        annonceObject.put("description", description);
        annonceObject.put("prix", prix);
        annonceObject.put("categorie", categorie);
        annonceObject.put("referent", referent);
        annonceObject.put("actif", true);

        // Get the id of the new announce
        int id = newId();

        // Write the new announce in the JSON file
        data.put(String.valueOf(id), annonceObject);
        FileWriter file = new FileWriter(filePath);
        file.write(data.toString());
        file.flush();
    }

    public ArrayList<Annonce> getAnnonces() throws IOException {
        // Method related to the display of the announces

        ArrayList<Annonce> annonces = new ArrayList<>();

        // Get the announces
        for (String key : data.keySet()) {
            JSONObject annonce = data.getJSONObject(key);
            annonces.add(new Annonce(Integer.parseInt(key), annonce.getString("titre"), annonce.getString("categorie"), annonce.getString("description"), annonce.getInt("prix"), annonce.getString("referent"), annonce.getBoolean("actif")));
        }
        return annonces;
    }

    public ArrayList<Annonce> getAnnonceByUsername(String username) throws IOException {
        // Method related to the display of the announces of a user

        ArrayList<Annonce> annonces = new ArrayList<>();

        // Get the announces of the user
        for (String key : data.keySet()) {
            JSONObject annonce = data.getJSONObject(key);
            if (annonce.getString("referent").equals(username)) {
                annonces.add(new Annonce(Integer.parseInt(key), annonce.getString("titre"), annonce.getString("categorie"), annonce.getString("description"), annonce.getInt("prix"), annonce.getString("referent"), annonce.getBoolean("actif")));
            }
        }
        return annonces;
    }

    private int newId() {
        // Method related to the creation of a new announce

        // Get the id of the last announce
        int id = 0;
        for (String key : data.keySet()) {
            int currentId = Integer.parseInt(key);
            if (currentId > id) {
                id = currentId;
            }
        }

        // Return the id of the new announce
        return id + 1;
    }
}