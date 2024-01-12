package eu.telecomnancy.codingweek.utils;

import com.calendarfx.model.Calendar;
import eu.telecomnancy.codingweek.global.Annonce;
import eu.telecomnancy.codingweek.global.FileAccess;
import eu.telecomnancy.codingweek.global.Transaction;
import eu.telecomnancy.codingweek.global.User;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;


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
        String fileContent = Files.readString(file.toPath());
        data = new JSONObject(fileContent);
    }

    // Public method to get the instance of the singleton
    public static synchronized DataTransactionUtils getInstance() throws IOException {
        if (instance == null) {
            instance = new DataTransactionUtils();
        }
        return instance;
    }

    public ArrayList<Transaction> getTransactions() {
        // Method related to the display of the transactions

        ArrayList<Transaction> transactions = new ArrayList<>();

        // Get the transactions
        for (String key : data.keySet()) {
            JSONObject transaction = data.getJSONObject(key);
            transactions.add(new Transaction(Integer.parseInt(key), transaction.getInt("idAnnonce"), transaction.getString("idClient"), transaction.getString("status")));
        }
        return transactions;
    }

    public Transaction getTransaction(int id) {
        // Method related to the display of the transactions

        // Get the transactions
        JSONObject transaction = data.getJSONObject(String.valueOf(id));
        Transaction retour = new Transaction(id, transaction.getInt("idAnnonce"), transaction.getString("idClient"), transaction.getString("status"));
        retour.setPlanning(transaction.getInt("planning"));
        return retour;
    }

    public ArrayList<Transaction> getTransactionsByRefentUser(User user) {
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

    public ArrayList<Transaction> getTransactionsByClientUser(User user) throws IOException {
        // Method related to the display of the transactions of a user

        ArrayList<Transaction> transactions = new ArrayList<>();

        // Get the transactions of the user
        Scanner scanner = new Scanner(user.getTransactionsClient());
        scanner.useDelimiter(",");
        while (scanner.hasNext()) {
            String id = scanner.next();
            JSONObject transaction = data.getJSONObject(id);
            transactions.add(new Transaction(Integer.parseInt(id), transaction.getInt("idAnnonce"), transaction.getString("idClient"), transaction.getString("status")));
        }
        return transactions;
    }

    public ArrayList<Transaction> getTransactionsByAnnonce(Annonce annonce) {
        // Method related to the display of the transactions of a user

        ArrayList<Transaction> transactions = new ArrayList<>();

        // Get the transactions of the user
        for (String key : data.keySet()) {
            JSONObject transaction = data.getJSONObject(key);
            if (transaction.getString("idAnnonce").equals(annonce.getId() + "")) {
                Transaction temp = new Transaction(Integer.parseInt(key), transaction.getInt("idAnnonce"), transaction.getString("idClient"), transaction.getString("status"));
                temp.setPlanning(transaction.getInt("planning"));
                transactions.add(temp);
            }
        }
        return transactions;
    }

    // Methods
    public int addTransaction(String idAnnonce, String idClient, String status) throws IOException {
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
        return id;
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

        // Update the solde of the client
        User client = DataUsersUtils.getInstance().getUserByUserName(transaction.getIdClient());
        client.setSolde(client.getSolde() - DataAnnoncesUtils.getInstance().getAnnonce(transaction.getIdAnnonce()).getPrix());
        DataUsersUtils.getInstance().updateUser(client);

        // Update the solde of the referent
        User referent = DataUsersUtils.getInstance().getUserByUserName(DataAnnoncesUtils.getInstance().getAnnonce(transaction.getIdAnnonce()).getReferent());
        referent.setSolde(referent.getSolde() + DataAnnoncesUtils.getInstance().getAnnonce(transaction.getIdAnnonce()).getPrix());
        DataUsersUtils.getInstance().updateUser(referent);
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

    public void noterTransaction(Transaction transaction) throws IOException {
        // Method related to the note of a transaction

        // Update the status of the transaction
        JSONObject transactionObject = data.getJSONObject(String.valueOf(transaction.getId()));
        transactionObject.put("status", "Notée");
        data.put(String.valueOf(transaction.getId()), transactionObject);
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(data.toString());
            file.flush();
        }
    }

    public String getFilePath() {
        return filePath;
    }
}
