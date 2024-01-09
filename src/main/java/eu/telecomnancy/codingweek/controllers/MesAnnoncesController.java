package eu.telecomnancy.codingweek.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.JSONObject;

import eu.telecomnancy.codingweek.Annonce;
import eu.telecomnancy.codingweek.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MesAnnoncesController {
    
    @FXML
    private VBox VBoxAnnonces;

    private Application app;

    private ArrayList<Annonce> annonces = new ArrayList<Annonce>();

    //private Annonce annonce;

    public MesAnnoncesController(Application app) {
        this.app = app;

        //initialiser les annonces, lire dans le json
        // Read existing content from users.json
        String filePath = "src/main/resources/eu/telecomnancy/codingweek/annonces.json";
        JSONObject existingData = new JSONObject();
        try {
            String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
            existingData = new JSONObject(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //parcourir le json et ajouter les annonces à la liste
        for (int i=1;i<existingData.length();i++){
            JSONObject annonce = existingData.getJSONObject(i+"");
            this.annonces.add(new Annonce(annonce.getString("titre"), annonce.getString("categorie"), annonce.getString("description"), annonce.getString("prix"), annonce.getString("referent")));
        }


    }

    public void detailsAnnonce(){
        app.getSceneController().switchToMonAnnonce();
    }

    public void creerAnnonce(){
        app.getSceneController().switchToCreationAnnonce();
    }

    @FXML
    public void initialize(){

        //afficher les annonces
        for (Annonce annonce : this.annonces){
            HBox hbox = new HBox();
            hbox.getChildren().add(new Label(annonce.getTitre()));
            hbox.getChildren().add(new Label(annonce.getPrix()));
            hbox.getChildren().add(new Label(annonce.getReferent()));
            
            //compléter avec les autres infos, le bouton voir détails

            hbox.setStyle("-fx-background-color: #eeeeee;");
            //hbox.setPrefHeight(279.0);
            VBoxAnnonces.getChildren().add(hbox);
        }
    }


}
