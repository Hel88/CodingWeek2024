package eu.telecomnancy.codingweek.controllers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import eu.telecomnancy.codingweek.Application;
import eu.telecomnancy.codingweek.utils.Annonce;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class OffresEtDemandesController {

    private Application app;
    private String type;
    private ArrayList<Annonce> annonces = new ArrayList<Annonce>();
    @FXML
    private VBox services;
    @FXML
    private VBox materiel;
    @FXML
    private Label serviceLabel;
    @FXML
    private Label materielLabel;


    public OffresEtDemandesController(Application app, String type) {
        this.app = app;
        this.type = type;
    
    }

    public void setType(String type){
        this.type = type;
    }

    public void initialize(){

        //initialiser les annonces, lire dans le json
        // Read existing content from users.json
        String filePath = null;
        try {
            filePath = IOUtils.toString(this.getClass().getResource("annonces.json"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JSONObject existingData = new JSONObject(filePath);

        //parcourir le json et ajouter les annonces à la liste
        for (int i=1;i<existingData.length();i++){
            JSONObject annonce = existingData.getJSONObject(i+"");
            this.annonces.add(new Annonce(i,annonce.getString("titre"), annonce.getString("categorie"), annonce.getString("description"), annonce.getInt("prix"), annonce.getString("referent"), annonce.getBoolean("actif")));
        }


        // savoir si on doit afficher les offres ou les demandes

        if (type.equals("Offre")){
            serviceLabel.setText("Offres de services");
            materielLabel.setText("Offres de matériel");
        }
        else if (type.equals("Demande")){
            serviceLabel.setText("Demandes de services");
            materielLabel.setText("Demandes de matériel");
        }

        //afficher les annonces

        for (Annonce annonce : this.annonces){
            HBox hbox = new HBox();
            hbox.setStyle("-fx-background-color: #eeeeee; prefHeight=\"279.0\"");

            Label titre = new Label(annonce.getTitre());
            titre.setPrefWidth(300);
            titre.setPrefHeight(10);
            titre.setWrapText(true);

            Label prix = new Label(annonce.getPrix()+"");
            prix.setPrefWidth(100);
            prix.setPrefHeight(10);
            prix.setWrapText(true);

            Button details = new Button();
            details.setText("Voir les détails");
            details.setOnAction(e -> app.getSceneController().switchToConsulterAnnonce(annonce.getId()));

            hbox.getChildren().addAll(titre, prix, details);

            // choisir dans quelle catégorie afficher l'annonce

            if (type.equals("Offre")){
                if (annonce.getCategorie().equals("OffreMateriel")){
                    materiel.getChildren().add(hbox);
                }
                else if (annonce.getCategorie().equals("OffreService")){
                    services.getChildren().add(hbox);
                }
            }
            else if (type.equals("Demande")){
                if (annonce.getCategorie().equals("DemandeMateriel")){
                    materiel.getChildren().add(hbox);
                }
                else if (annonce.getCategorie().equals("DemandeService")){
                    services.getChildren().add(hbox);
                }
            }
        }
    }
}
