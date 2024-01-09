package eu.telecomnancy.codingweek.utils;

public class Annonce {

    private String titre;
    private String description;
    private String categorie;
    private String date;
    private String lieu;
    private String prix;

    public Annonce(String titre, String description, String categorie, String date, String lieu, String prix) {
        this.titre = titre;
        this.description = description;
        this.categorie = categorie;
        this.date = date;
        this.lieu = lieu;
        this.prix = prix;
    }

    public String getTitre() {
        return titre;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getPrix() {
        return prix;
    }
    
    public String getVille(){
        return lieu;
    }
}
