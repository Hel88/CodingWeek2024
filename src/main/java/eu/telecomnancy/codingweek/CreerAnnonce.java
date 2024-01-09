package eu.telecomnancy.codingweek;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;

public class CreerAnnonce {
    

    public CreerAnnonce() {}

    public Annonce nouvelleAnnonce(String titre, String categorie, String description, int prix, String referent) {
        //crée une nouvelle annonce avec le bon id et l'ajoute au json
        
        //lecture du json
        String filePath = "src/main/resources/eu/telecomnancy/codingweek/annonces.json";
        JSONObject existingData = new JSONObject();
            try {
                String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
                existingData = new JSONObject(fileContent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        int id = existingData.length();
        Annonce annonce = new Annonce(id, titre, categorie, description, prix, referent);
       
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", annonce.getId());
        jsonObject.put("titre", annonce.getTitre());
        jsonObject.put("categorie", annonce.getCategorie());
        jsonObject.put("description", annonce.getDescription());
        jsonObject.put("prix", annonce.getPrix());
        jsonObject.put("referent", annonce.getReferent());
        //ajout de l'annonce au json
        existingData.put(annonce.getId()+"", jsonObject);

        //Écrire les données mises à jour dans users.json
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(existingData.toString(2)); // Le '2' est pour le niveau d'indentation
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Annonce(id, titre, categorie, description, prix, referent);
        }

}
