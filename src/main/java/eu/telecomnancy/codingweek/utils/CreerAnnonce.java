package eu.telecomnancy.codingweek.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import org.json.JSONObject;

public class CreerAnnonce {
    

    public CreerAnnonce() {}

    public Annonce nouvelleAnnonce(String titre, String categorie, String description, int prix, String referent, boolean actif) {
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

        //récupération du plus grand id
        Set<String> keys = existingData.keySet();
        keys.remove("id_annonce");
        int idMax = 0;
        for (String key : keys){
            if (Integer.parseInt(key)>idMax){
                idMax = Integer.parseInt(key);
            }
        }

        //int id = existingData.length();
        int id = idMax+1;
        Annonce annonce = new Annonce(id, titre, categorie, description, prix, referent, actif);
       
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("titre", annonce.getTitre());
        jsonObject.put("categorie", annonce.getCategorie());
        jsonObject.put("description", annonce.getDescription());
        jsonObject.put("prix", annonce.getPrix());
        jsonObject.put("referent", annonce.getReferent());
        jsonObject.put("actif", annonce.getActif());
        //ajout de l'annonce au json
        existingData.put(annonce.getId()+"", jsonObject);

        //Écrire les données mises à jour dans users.json
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(existingData.toString(2)); // Le '2' est pour le niveau d'indentation
        } catch (IOException e) {
            e.printStackTrace();
        }

        return annonce;
        }

}
