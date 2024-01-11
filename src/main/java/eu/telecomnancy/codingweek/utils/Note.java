package eu.telecomnancy.codingweek.utils;

public class Note {

    private int id;
    private int note;
    private String Commentaire;
    private String usernameReferent;
    private String usernameClient;
    private int annonceId;

    public Note() {
    }

    public Note(int id, int note, String Commentaire, String usernameReferent, String usernameClient, int annonceId) {
        this.id = id;
        this.note = note;
        this.Commentaire = Commentaire;
        this.usernameReferent = usernameReferent;
        this.usernameClient = usernameClient;
        this.annonceId = annonceId;
    }

    public int getId() {
        return id;
    }

    public int getNote() {
        return note;
    }

    public String getCommentaire() {
        return Commentaire;
    }

    public String getUsernameReferent() {
        return usernameReferent;
    }

    public String getUsernameClient() {
        return usernameClient;
    }

    public int getAnnonceId() {
        return annonceId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public void setCommentaire(String Commentaire) {
        this.Commentaire = Commentaire;
    }

    public void setUsernameReferent(String usernameReferent) {
        this.usernameReferent = usernameReferent;
    }

    public void setUsernameClient(String usernameClient) {
        this.usernameClient = usernameClient;
    }

    public void setAnnonceId(int annonceId) {
        this.annonceId = annonceId;
    }

    @Override
    public String toString() {
        return "Note{" + "id=" + id + ", note=" + note + ", Commentaire=" + Commentaire + ", usernameReferent=" + usernameReferent + ", usernameClient=" + usernameClient + ", annonceId=" + annonceId + '}';
    }

}
