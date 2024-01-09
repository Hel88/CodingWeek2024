package eu.telecomnancy.codingweek;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;


public class Annonce {

    private int id;
    private String titre;
    private String categorie;
    private String description;
    private String prix;
    private String referent;

    public Annonce(String titre, String categorie, String description, String prix, String referent) {
        //lecture du json
        String filePath = "src/main/resources/eu/telecomnancy/codingweek/annonces.json";
        JSONObject existingData = new JSONObject();
            try {
                String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
                existingData = new JSONObject(fileContent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        
        this.id = existingData.length();

        this.titre = titre;
        this.categorie = categorie;
        this.description = description;
        this.prix = prix;
        this.referent = referent;

        //ajouterAuJson(this);

    }

    public void ajouterAuJson(Annonce annonce) {

        
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", annonce.getId());
        jsonObject.put("titre", annonce.getTitre());
        jsonObject.put("categorie", annonce.getCategorie());
        jsonObject.put("description", annonce.getDescription());
        jsonObject.put("prix", annonce.getPrix());
        jsonObject.put("referent", annonce.getReferent());

        //lecture du json
        String filePath = "src/main/resources/eu/telecomnancy/codingweek/annonces.json";
        JSONObject existingData = new JSONObject();
            try {
                String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
                existingData = new JSONObject(fileContent);
            } catch (IOException e) {
                e.printStackTrace();
            }

        //ajout de l'annonce au json
        existingData.put(annonce.getId()+"", jsonObject);

        //Écrire les données mises à jour dans users.json
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(existingData.toString(2)); // Le '2' est pour le niveau d'indentation
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public String getPrix() {
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


    public void setPrix(String prix) {
        this.prix = prix;
    }


    
}
