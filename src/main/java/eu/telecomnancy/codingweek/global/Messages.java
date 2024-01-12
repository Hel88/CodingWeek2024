package eu.telecomnancy.codingweek.global;

public class Messages {

    private int id;
    private String message=null;
    private String expediteur;
    private int idConversation;

    public Messages() {}

    public Messages(int id, String message, String expediteur, int idConversation) {
        this.id = id;
        this.message = message;
        this.expediteur = expediteur;
        this.idConversation = idConversation;
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

    public int getIdConversation() {
        return idConversation;
    }

    public void setIdConversation(int idConversation) {
        this.idConversation = idConversation;
    }
}
