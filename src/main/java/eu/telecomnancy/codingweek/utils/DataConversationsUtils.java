package eu.telecomnancy.codingweek.utils;

import eu.telecomnancy.codingweek.global.Conversations;
import eu.telecomnancy.codingweek.global.FileAccess;
import eu.telecomnancy.codingweek.global.Note;
import eu.telecomnancy.codingweek.global.User;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class DataConversationsUtils {

    private final String filePath;
    private static DataConversationsUtils instance;
    private final JSONObject data;

    private DataConversationsUtils() throws IOException {
        FileAccess fileAccess = new FileAccess();
        this.filePath = fileAccess.getPathOf("conversations.json");
        File file = new File(filePath);
        String fileContent = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        data = new JSONObject(fileContent);
    }

    public static synchronized DataConversationsUtils getInstance() throws IOException {
        if (instance == null) {
            instance = new DataConversationsUtils();
        }
        return instance;
    }

    public void addConversation(String user1, String user2, String idMessages) throws IOException {
        int id = checkIfConversationExist(user1, user2);

        if(id == -1){
            id = newId();
        }
        else {
            JSONObject conversationObject = data.getJSONObject(Integer.toString(id));
            idMessages = conversationObject.getString("idMessages") + "," + idMessages;
        }

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

    public void addIdMessagesToConversation(int id, String idMessages) throws IOException {
        JSONObject conversationObject = data.getJSONObject(Integer.toString(id));
        idMessages = conversationObject.getString("idMessages") + "," + idMessages;
        conversationObject.put("idMessages", idMessages);
        data.put(Integer.toString(id), conversationObject);
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(data.toString());
            file.flush();
        }
    }

    public void addIdMessagesToConversation(Conversations conversations, String idMessages) throws IOException {
        JSONObject conversationObject = data.getJSONObject(Integer.toString(conversations.getId()));
        idMessages = conversationObject.getString("idMessages") + "," + idMessages;
        conversationObject.put("idMessages", idMessages);
        data.put(Integer.toString(conversations.getId()), conversationObject);
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(data.toString());
            file.flush();
        }
    }

    public int checkIfConversationExist(String user1, String user2) throws IOException {
        for (String key : data.keySet()) {
            JSONObject conversationObject = data.getJSONObject(key);
            String user3 = conversationObject.getString("user1");
            String user4 = conversationObject.getString("user2");
            if ((user1.equals(user3) && user2.equals(user4)) || (user1.equals(user4) && user2.equals(user3))) {
                return conversationObject.getInt("id");
            }
        }
        return -1;
    }

    public ArrayList<Conversations> getConversations() throws IOException {
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

    public ArrayList<Conversations> getConversationsByUser(String username) throws IOException {
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
