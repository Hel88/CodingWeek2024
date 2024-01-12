package eu.telecomnancy.codingweek.utils;

import eu.telecomnancy.codingweek.global.Conversations;
import eu.telecomnancy.codingweek.global.FileAccess;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class DataConversationsUtils {

    private final String filePath;
    private static DataConversationsUtils instance;
    private final JSONObject data;

    private DataConversationsUtils() throws IOException {
        FileAccess fileAccess = new FileAccess();
        this.filePath = fileAccess.getPathOf("conversations.json");
        File file = new File(filePath);
        String fileContent = Files.readString(file.toPath());
        data = new JSONObject(fileContent);
    }

    public static synchronized DataConversationsUtils getInstance() throws IOException {
        if (instance == null) {
            instance = new DataConversationsUtils();
        }
        return instance;
    }

    public void addConversation(String user1, String user2, String idMessages) throws IOException {
        int id = newId();

        JSONObject conversationObject = new JSONObject();
        conversationObject.put("id", id);
        conversationObject.put("user1", user1);
        conversationObject.put("user2", user2);
        conversationObject.put("idMessages", idMessages);

        data.put(Integer.toString(id), conversationObject);
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(data.toString());
            file.flush();
        }
    }

    public ArrayList<Conversations> getConversations() {
        ArrayList<Conversations> conversations = new ArrayList<>();
        for (String key : data.keySet()) {
            JSONObject conversationObject = data.getJSONObject(key);
            int id = conversationObject.getInt("id");
            String user1 = conversationObject.getString("user1");
            String user2 = conversationObject.getString("user2");
            String idMessages = conversationObject.getString("idMessages");
            conversations.add(new Conversations(id, user1, user2, idMessages));
        }
        return conversations;
    }

    public ArrayList<Conversations> getConversationsByUser(String username) {
        ArrayList<Conversations> conversations = new ArrayList<>();
        for (String key : data.keySet()) {
            JSONObject conversationObject = data.getJSONObject(key);
            int id = conversationObject.getInt("id");
            String user1 = conversationObject.getString("user1");
            String user2 = conversationObject.getString("user2");
            String idMessages = conversationObject.getString("idMessages");
            if (user1.equals(username) || user2.equals(username)) {
                conversations.add(new Conversations(id, user1, user2, idMessages));
            }
        }
        return conversations;
    }

    public int newId() {
        int id = 0;
        for (String key : data.keySet()) {
            int keyInt = Integer.parseInt(key);
            if (keyInt > id) {
                id = keyInt;
            }
        }
        return id + 1;
    }

}
