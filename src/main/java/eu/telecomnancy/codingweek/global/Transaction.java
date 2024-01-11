package eu.telecomnancy.codingweek.global;

public class Transaction {

    // Fields
    private int id;
    private int idAnnonce;
    private String idClient;
    private String status;
    private int planning;

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

    public int getPlanning() {
        return planning;
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

    public void setPlanning(int planning) {
        this.planning = planning;
    }

    // Methods
    public String toString() {
        return "Transaction [id=" + id + ", idAnnonce=" + idAnnonce + ", idClient=" + idClient + ", status=" + status + "]";
    }
}
