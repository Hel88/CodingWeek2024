package eu.telecomnancy.codingweek.global;

public class Messages {

    private int id;
    private String message;
    private String expediteur;

    public Messages() {}

    public Messages(int id, String message, String expediteur) {
        this.id = id;
        this.message = message;
        this.expediteur = expediteur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(String expediteur) {
        this.expediteur = expediteur;
    }
}
