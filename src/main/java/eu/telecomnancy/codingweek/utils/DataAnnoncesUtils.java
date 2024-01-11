package eu.telecomnancy.codingweek.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;

import org.json.JSONObject;

public class DataAnnoncesUtils {

    // Fields
    private final String filePath;
    private static DataAnnoncesUtils instance;
    private final JSONObject data;

    // Private constructor to prevent instantiation
    private DataAnnoncesUtils() throws IOException {
        FileAccess fileAccess = new FileAccess();
        this.filePath = fileAccess.getPathOf("annonces.json");
        File file = new File(filePath);
        String fileContent = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        data = new JSONObject(fileContent);
    }

    // Public method to get the instance of the singleton
    public static synchronized DataAnnoncesUtils getInstance() throws IOException {
        if (instance == null) {
            instance = new DataAnnoncesUtils();
        }
        return instance;
    }

    // Methods
    public void addAnnonce(String titre, String description, String prix, String categorie, String referent) throws IOException {
        // Method related to the creation of a new annonce

        // Create a JSON object with user information
        JSONObject annonceObject = new JSONObject();
        annonceObject.put("titre", titre);
        annonceObject.put("description", description);
        annonceObject.put("prix", prix);
        annonceObject.put("categorie", categorie);
        annonceObject.put("referent", referent);
        annonceObject.put("actif", true);

        // Get the id of the new annonce
        int id = newId();

        // Write the new annonce in the JSON file
        data.put(String.valueOf(id), annonceObject);
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(data.toString());
            file.flush();
        }
    }

    public void deleteAnnonce(String id) throws IOException {
        data.remove(id);
        // Write the updated  in the JSON file
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(data.toString());
            file.flush();
        }
    }

    public void modifyAnnonce(int id, String titre, String description, String prix, String categorie, String referent, boolean actif) throws IOException {
        // Method related to the modification of an annonce
        
        // Create a JSON object with user information
        JSONObject annonceObject = new JSONObject();
        annonceObject.put("titre", titre);
        annonceObject.put("description", description);
        annonceObject.put("prix", prix);
        annonceObject.put("categorie", categorie);
        annonceObject.put("referent", referent);
        annonceObject.put("actif", actif);

        // Write the new annonce in the JSON file
        data.put(String.valueOf(id), annonceObject);
        FileWriter file = new FileWriter(filePath);
        file.write(data.toString());
        file.flush();

    }

    public ArrayList<Annonce> getAnnonces() throws IOException {
        // Method related to the display of the annonces

        ArrayList<Annonce> annonces = new ArrayList<>();

        // Get the announces
        for (String key : data.keySet()) {
            JSONObject annonce = data.getJSONObject(key);
            annonces.add(new Annonce(Integer.parseInt(key), annonce.getString("titre"), annonce.getString("categorie"), annonce.getString("description"), annonce.getInt("prix"), annonce.getString("referent"), annonce.getBoolean("actif")));
        }
        return annonces;
    }

    public ArrayList<Annonce> getAnnoncesByUsername(String username) throws IOException {
        // Method related to the display of the annonces of a user

        ArrayList<Annonce> annonces = new ArrayList<>();

        // Get the annonces of the user
        for (String key : data.keySet()) {
            JSONObject annonce = data.getJSONObject(key);
            if (annonce.getString("referent").equals(username)) {
                annonces.add(new Annonce(Integer.parseInt(key), annonce.getString("titre"), annonce.getString("categorie"), annonce.getString("description"), annonce.getInt("prix"), annonce.getString("referent"), annonce.getBoolean("actif")));
            }
        }
        return annonces;
    }

    private int newId() {
        // Method related to the creation of a new annonce

        // Get the id of the last annonce
        int id = 0;
        for (String key : data.keySet()) {
            int currentId = Integer.parseInt(key);
            if (currentId > id) {
                id = currentId;
            }
        }

        // Return the id of the new annonce
        return id + 1;
    }
}