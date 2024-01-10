package eu.telecomnancy.codingweek.controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Set;

import org.json.JSONObject;

import eu.telecomnancy.codingweek.utils.Annonce;
import eu.telecomnancy.codingweek.utils.FileAccess;

public class CreerAnnonce {
    

    public CreerAnnonce() {}

    public Annonce nouvelleAnnonce(String titre, String categorie, String description, int prix, String referent, boolean actif) throws IOException {
        //crée une nouvelle annonce avec le bon id et l'ajoute au json
        
        //lecture du json
        FileAccess fileAccess = new FileAccess();
        String filePath = fileAccess.getPathOf("annonces.json");
        File file = new File(filePath);
        String fileContent = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        JSONObject existingData = new JSONObject(fileContent);

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
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(existingData.toString(2)); // Le '2' est pour le niveau d'indentation
        fileWriter.flush();

        return annonce;
        }

}
