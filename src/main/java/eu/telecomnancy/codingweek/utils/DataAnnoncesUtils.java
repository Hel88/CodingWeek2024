package eu.telecomnancy.codingweek.utils;

import com.calendarfx.model.Calendar;
import eu.telecomnancy.codingweek.global.Annonce;
import eu.telecomnancy.codingweek.global.FileAccess;
import eu.telecomnancy.codingweek.global.User;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

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
        String fileContent = Files.readString(file.toPath());
        data = new JSONObject(fileContent);
    }

    // Public method to get the instance of the singleton
    public static synchronized DataAnnoncesUtils getInstance() throws IOException {
        if (instance == null) {
            instance = new DataAnnoncesUtils();
        }
        return instance;
    }

    // Getters
    public String getFilePath() {
        return filePath;
    }

    public JSONObject getData() {
        return data;
    }

    // Methods
    public int addAnnonce(String titre, String description, String prix, String categorie, String referent) throws IOException {
        // Method related to the creation of a new annonce

        // Create a JSON object with user information
        JSONObject annonceObject = new JSONObject();
        annonceObject.put("titre", titre);
        annonceObject.put("description", description);
        annonceObject.put("prix", prix);
        annonceObject.put("categorie", categorie);
        annonceObject.put("referent", referent);
        annonceObject.put("actif", true);
        annonceObject.put("planning", String.valueOf(DataCalendarUtils.getInstance().store(new Calendar("Annonce : " + titre + " - " + referent))));

        // Get the id of the new annonce
        int id = newId();

        // Write the new annonce in the JSON file
        data.put(String.valueOf(id), annonceObject);
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(data.toString());
            file.flush();
        }

        // Add the annonce to the user
        DataUsersUtils.getInstance().addAnnonceToUser(referent, id);
        return id;
    }

    public void deleteAnnonce(String id) throws IOException {
        data.remove(id);
        // Write the updated  in the JSON file
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(data.toString());
            file.flush();
        }
    }

    public int modifyAnnonce(int id, String titre, String description, String prix, String categorie, String referent, boolean actif, int idCalendar) throws IOException {
        // Method related to the modification of an annonce

        // Create a JSON object with user information
        JSONObject annonceObject = new JSONObject();
        annonceObject.put("titre", titre);
        annonceObject.put("description", description);
        annonceObject.put("prix", prix);
        annonceObject.put("categorie", categorie);
        annonceObject.put("referent", referent);
        annonceObject.put("actif", actif);
        annonceObject.put("planning", idCalendar);

        // Write the new annonce in the JSON file
        data.put(String.valueOf(id), annonceObject);
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(data.toString());
            file.flush();
        }
        return id;
    }

    public ArrayList<Annonce> getAnnonces() {
        // Method related to the display of the annonces

        ArrayList<Annonce> annonces = new ArrayList<>();

        // Get the announces
        for (String key : data.keySet()) {
            JSONObject annonce = data.getJSONObject(key);
            annonces.add(new Annonce(Integer.parseInt(key), annonce.getString("titre"), annonce.getString("categorie"), annonce.getString("description"), annonce.getInt("prix"), annonce.getString("referent"), annonce.getBoolean("actif"), annonce.getInt("planning")));
        }
        return annonces;
    }

    public Annonce getAnnonce(int id) throws IOException {
        // Method related to the display of the annonces

        JSONObject annonce = data.getJSONObject(String.valueOf(id));
        return new Annonce(id, annonce.getString("titre"), annonce.getString("categorie"), annonce.getString("description"), annonce.getInt("prix"), annonce.getString("referent"), annonce.getBoolean("actif"), annonce.getInt("planning"));
    }

    public ArrayList<Annonce> getAnnoncesByUsername(String username) throws IOException {
        // Method related to the display of the annonces of a user

        ArrayList<Annonce> annonces = new ArrayList<>();
        User user = DataUsersUtils.getInstance().getUserByUserName(username);

        Scanner scanner = new Scanner(user.getAnnonces());
        scanner.useDelimiter(",");
        while (scanner.hasNext()) {
            String id = scanner.next();
            JSONObject annonce = data.getJSONObject(id);
            annonces.add(new Annonce(Integer.parseInt(id), annonce.getString("titre"), annonce.getString("categorie"), annonce.getString("description"), annonce.getInt("prix"), annonce.getString("referent"), annonce.getBoolean("actif"), annonce.getInt("planning")));
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
