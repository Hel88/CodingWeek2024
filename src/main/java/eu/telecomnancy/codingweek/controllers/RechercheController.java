package eu.telecomnancy.codingweek.controllers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import eu.telecomnancy.codingweek.Application;
import eu.telecomnancy.codingweek.utils.Annonce;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RechercheController {
    

    private Application app;
    private ArrayList<Annonce> annonces;
    @FXML
    private VBox resultats;
    @FXML
    private TextField user;
    @FXML
    private TextField categorie;
    @FXML
    private TextField titre;
    @FXML
    private Button valider;

    public RechercheController(Application app) {
        this.app = app;
        annonces = new ArrayList<Annonce>();
    }

    @FXML
    public void valider(){
    System.out.println("valider");

    Pattern userPattern = Pattern.compile(user.getText(), Pattern.CASE_INSENSITIVE);
    Pattern categoriePattern = Pattern.compile(categorie.getText(), Pattern.CASE_INSENSITIVE);
    Pattern titrePattern = Pattern.compile(titre.getText(), Pattern.CASE_INSENSITIVE);

    rechercher(userPattern, categoriePattern, titrePattern);

  }

    

    public void rechercher(Pattern userPattern, Pattern categoriePattern, Pattern titrePattern){

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

        //matcher les annonces avec les critères de recherche et afficher celles qui correspndent

        for (Annonce annonce : this.annonces){
            Matcher userMatcher = userPattern.matcher(annonce.getReferent());
            Matcher categorieMatcher = categoriePattern.matcher(annonce.getCategorie());
            Matcher titreMatcher = titrePattern.matcher(annonce.getTitre());
        
            System.out.println(annonce.getReferent() + userMatcher.find());

            if (annonce.getActif() && (userMatcher.find() || annonce.getReferent()==null ) && (categorieMatcher.find() || annonce.getCategorie()==null) && (titreMatcher.find() || annonce.getTitre()==null) ){
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

                resultats.getChildren().add(hbox);
 
            }
        } 
    } 
}

