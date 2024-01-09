package eu.telecomnancy.codingweek.utils;

public class Annonce {

    private int id;
    private String titre;
    private String categorie;
    private String description;
    private int prix;
    private String referent;

    public Annonce(int id, String titre, String categorie, String description, int prix, String referent) {
        
        this.id = id;
        this.titre = titre;
        this.categorie = categorie;
        this.description = description;
        this.prix = prix;
        this.referent = referent;

    }

   


    //Getters et setters -------------------------------

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReferent() {
        return referent;
    }

    public void setReferent(String referent) {
        this.referent = referent;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public String getCategorie() {
        return categorie;
    }

    public int getPrix() {
        return prix;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }


    public void setPrix(int prix) {
        this.prix = prix;
    }


    
}
