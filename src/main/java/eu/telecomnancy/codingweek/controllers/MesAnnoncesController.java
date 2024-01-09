package eu.telecomnancy.codingweek.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.JSONObject;

import eu.telecomnancy.codingweek.Application;
import eu.telecomnancy.codingweek.utils.Annonce;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MesAnnoncesController {
    
    @FXML
    private VBox VBoxAnnonces;

    private Application app;

    private ArrayList<Annonce> annonces;

    public MesAnnoncesController(Application app) {
        this.app = app;
        annonces = new ArrayList<Annonce>();
        
    }

    public void detailsAnnonce(String id){
        app.getSceneController().switchToMonAnnonce(id);
    }

    public void creerAnnonce(){
        app.getSceneController().switchToCreationAnnonce();
    }
  

    @FXML
    public void initialize(){
        //gère l'affichage

        //AJOUTER VERIF POUR QUE LES ANNONCES CORRESPONDENT AU USER CONNECTE

        synchroJson();
        
        //Affichage de chaque annonce
        
        for (Annonce annonce : this.annonces){
            HBox hbox = new HBox();
            hbox.getChildren().add(new Label(annonce.getTitre()));
            hbox.getChildren().add(new Label(annonce.getPrix()+""));
            Image image = new Image(getClass().getResource("images/florain.jpg").toExternalForm());
            ImageView imagev = new ImageView(image);
            imagev.setFitHeight(15);
            imagev.setFitWidth(15);
            hbox.getChildren().add(imagev);
            
            Button button = new Button();
            button.setText("Voir les détails");
            button.setOnAction((event) -> {
                detailsAnnonce(annonce.getId()+"");
            });
            button.setId(annonce.getId()+"");
            hbox.getChildren().add(button);

            hbox.setStyle("-fx-background-color: #eeeeee;");
            //hbox.setPrefHeight(279.0);synchroJson
            VBoxAnnonces.getChildren().add(hbox);
        }
    }

      public void synchroJson(){
        //synchronise les annonces avec le json

        // Lecture dans le fichier JSON
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
            this.annonces.add(new Annonce(annonce.getInt("id"),annonce.getString("titre"), annonce.getString("categorie"), annonce.getString("description"), annonce.getInt("prix"), annonce.getString("referent"), annonce.getBoolean("actif")));
        }
    }


}
