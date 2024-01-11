package eu.telecomnancy.codingweek.utils;

import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class DataNoteUtils {

    // Fields
    private final String filePath;
    private static DataNoteUtils instance;
    private final JSONObject data;

    // Private constructor to prevent instantiation
    private DataNoteUtils() throws IOException {
        FileAccess fileAccess = new FileAccess();
        this.filePath = fileAccess.getPathOf("notes.json");
        File file = new File(filePath);
        String fileContent = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        data = new JSONObject(fileContent);
    }

    // Public method to get the instance of the singleton
    public static synchronized DataNoteUtils getInstance() throws IOException {
        if (instance == null) {
            instance = new DataNoteUtils();
        }
        return instance;
    }

    // Methods
    public void addNote(int id, int note, String Commentaire, String usernameReferent, String usernameClient, int annonceId) throws IOException {
        // Method related to the creation of a new note

        // Create a JSON object with note information
        JSONObject noteObject = new JSONObject();
        noteObject.put("id", id);
        noteObject.put("note", note);
        noteObject.put("Commentaire", Commentaire);
        noteObject.put("usernameReferent", usernameReferent);
        noteObject.put("usernameClient", usernameClient);
        noteObject.put("annonceId", annonceId);

        // Add the note to the JSON object
        data.put(Integer.toString(id), noteObject);
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(data.toString());
            file.flush();
        }
    }

    public ArrayList<Note> getNotes() throws IOException {
        // Method related to the display of the notes

        ArrayList<Note> notes = new ArrayList<>();

        // Get the notes
        for (String key : data.keySet()) {
            JSONObject note = data.getJSONObject(key);
            notes.add(new Note(Integer.parseInt(key), note.getInt("note"), note.getString("Commentaire"), note.getString("usernameReferent"), note.getString("usernameClient"), note.getInt("annonceId")));
        }
        return notes;
    }

    public ArrayList<Note> getNotesByUser(User user) throws IOException {
        // Method related to the display of the notes of a user

        ArrayList<Note> notes = new ArrayList<>();

        // Get the notes of the user
        Scanner scanner = new Scanner(user.getEval());
        scanner.useDelimiter(",");
        while (scanner.hasNext()) {
            String id = scanner.next();
            JSONObject note = data.getJSONObject(id);
            notes.add(new Note(Integer.parseInt(id), note.getInt("note"), note.getString("Commentaire"), note.getString("usernameReferent"), note.getString("usernameClient"), note.getInt("annonceId")));
        }
        return notes;
    }
}
