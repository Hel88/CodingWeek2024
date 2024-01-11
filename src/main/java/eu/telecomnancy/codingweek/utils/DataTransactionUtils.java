package eu.telecomnancy.codingweek.utils;

import com.calendarfx.model.Calendar;
import org.json.JSONObject;
import java.util.Scanner;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;


public class DataTransactionUtils {

    // Fields
    private final String filePath;
    private static DataTransactionUtils instance;
    private final JSONObject data;

    // Private constructor to prevent instantiation
    private DataTransactionUtils() throws IOException {
        FileAccess fileAccess = new FileAccess();
        this.filePath = fileAccess.getPathOf("transactions.json");
        File file = new File(filePath);
        String fileContent = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        data = new JSONObject(fileContent);
    }

    // Public method to get the instance of the singleton
    public static synchronized DataTransactionUtils getInstance() throws IOException {
        if (instance == null) {
            instance = new DataTransactionUtils();
        }
        return instance;
    }

    public ArrayList<Transaction> getTransactions() throws IOException {
        // Method related to the display of the transactions

        ArrayList<Transaction> transactions = new ArrayList<>();

        // Get the transactions
        for (String key : data.keySet()) {
            JSONObject transaction = data.getJSONObject(key);
            transactions.add(new Transaction(Integer.parseInt(key), transaction.getInt("idAnnonce"), transaction.getString("idClient"), transaction.getString("status")));
        }
        return transactions;
    }

    public ArrayList<Transaction> getTransactionsByUser(User user) throws IOException {
        // Method related to the display of the transactions of a user

        ArrayList<Transaction> transactions = new ArrayList<>();

        // Get the transactions of the user
        Scanner scanner = new Scanner(user.getTransactionsReferent());
        scanner.useDelimiter(",");
        while (scanner.hasNext()) {
            String id = scanner.next();
            JSONObject transaction = data.getJSONObject(id);
            transactions.add(new Transaction(Integer.parseInt(id), transaction.getInt("idAnnonce"), transaction.getString("idClient"), transaction.getString("status")));
        }
        return transactions;
    }

    public ArrayList<Transaction> getTransactionsByAnnonce(Annonce annonce) throws IOException {
        // Method related to the display of the transactions of a user

        ArrayList<Transaction> transactions = new ArrayList<>();

        // Get the transactions of the user
        for (String key : data.keySet()) {
            JSONObject transaction = data.getJSONObject(key);
            if (transaction.getString("idAnnonce").equals(annonce.getId()+"")) {
                transactions.add(new Transaction(Integer.parseInt(key), transaction.getInt("idAnnonce"), transaction.getString("idClient"), transaction.getString("status")));
            }
        }
        return transactions;
    }

    // Methods
    public void addTransaction(String idAnnonce, String idClient, String status) throws IOException {
        // Method related to the creation of a new transaction

        // Create a JSON object with user information
        JSONObject transactionObject = new JSONObject();
        transactionObject.put("idAnnonce", idAnnonce);
        transactionObject.put("idClient", idClient);
        transactionObject.put("status", status);
        transactionObject.put("planning", String.valueOf(DataCalendarUtils.getInstance().store(new Calendar("Transaction : " + idAnnonce + " - " + idClient))));

        // Get the id of the new transaction
        int id = newId();

        // Write the new annonce in the JSON file
        data.put(String.valueOf(id), transactionObject);
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(data.toString());
            file.flush();
        }

        // Add the transaction to the user
        DataUsersUtils.getInstance().addTransactionReferentToUser(DataAnnoncesUtils.getInstance().getAnnonce(Integer.parseInt(idAnnonce)).getReferent(), id);
        DataUsersUtils.getInstance().addTransactionClientToUser(idClient, id);
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

    public void accepterTransaction(Transaction transaction) throws IOException {
        // Method related to the acceptance of a transaction

        // Update the status of the transaction
        JSONObject transactionObject = data.getJSONObject(String.valueOf(transaction.getId()));
        transactionObject.put("status", "Acceptée");
        data.put(String.valueOf(transaction.getId()), transactionObject);
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(data.toString());
            file.flush();
        }
    }

    public void refuserTransaction(Transaction transaction) throws IOException {
        // Method related to the refusal of a transaction

        // Update the status of the transaction
        JSONObject transactionObject = data.getJSONObject(String.valueOf(transaction.getId()));
        transactionObject.put("status", "Refusée");
        data.put(String.valueOf(transaction.getId()), transactionObject);
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(data.toString());
            file.flush();
        }
    }

}
