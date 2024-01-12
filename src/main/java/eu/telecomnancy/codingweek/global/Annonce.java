package eu.telecomnancy.codingweek.global;

import eu.telecomnancy.codingweek.utils.DataUsersUtils;

import java.io.IOException;

public class Annonce {

    private int id;
    private String titre;
    private String categorie;
    private String description;
    private int prix;
    private String referent;
    private boolean actif;
    private int planning;

    public Annonce(int id, String titre, String categorie, String description, int prix, String referent, boolean actif, int planning) {

        this.id = id;
        this.titre = titre;
        this.categorie = categorie;
        this.description = description;
        this.prix = prix;
        this.referent = referent;
        this.actif = actif;
        this.planning = planning;
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

    public String getVille() throws IOException {
        return DataUsersUtils.getInstance().getUserByUserName(referent).getCity();
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

    public int getPlanning() {
        return planning;
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

    public boolean getActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public void setPlanning(int planning) {
        this.planning = planning;
    }
}
