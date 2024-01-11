package eu.telecomnancy.codingweek.utils;

public class Transaction {

    // Fields
    private int id;
    private int idAnnonce;
    private String idClient;
    private String status;

    // Constructor
    public Transaction(int id, int idAnnonce, String idClient, String status) {
        this.id = id;
        this.idAnnonce = idAnnonce;
        this.idClient = idClient;
        this.status = status;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getIdAnnonce() {
        return idAnnonce;
    }

    public String getIdClient() {
        return idClient;
    }

    public String getStatus() {
        return status;
    }


    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setIdAnnonce(int idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Methods
    public String toString() {
        return "Transaction [id=" + id + ", idAnnonce=" + idAnnonce + ", idClient=" + idClient + ", status=" + status + "]";
    }
}
