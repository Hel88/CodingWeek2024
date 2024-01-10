package eu.telecomnancy.codingweek.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import eu.telecomnancy.codingweek.Application;
import eu.telecomnancy.codingweek.utils.Annonce;
import eu.telecomnancy.codingweek.utils.DataAnnoncesUtils;
import eu.telecomnancy.codingweek.utils.FileAccess;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
    private TextField titre;
    @FXML
    private Button valider;
    @FXML
    private CheckBox offreMateriel;
    @FXML
    private CheckBox offreService;
    @FXML
    private CheckBox demandeMateriel;
    @FXML
    private CheckBox demandeService;

    public RechercheController(Application app) {
        this.app = app;
        annonces = new ArrayList<Annonce>();
    }

    @FXML
    public void valider() throws IOException{
    // on réinitialise la liste des annonces et la VBox pour ne pas les afficher en double à chaque clic sur valider
    this.annonces.clear();
    resultats.getChildren().clear();

    // on définit les patterns à rechercher dans les annonces
    Pattern userPattern = Pattern.compile(user.getText(), Pattern.CASE_INSENSITIVE);
    Pattern titrePattern = Pattern.compile(titre.getText(), Pattern.CASE_INSENSITIVE);

    rechercher(userPattern, titrePattern);

  }

    public ArrayList<String> findCategorie(){
        // on définit les catégories à rechercher dans les annonces
        ArrayList<String> categories = new ArrayList<String>();
        if (offreMateriel.isSelected()){
            categories.add("OffreMateriel");
        }
        if (offreService.isSelected()){
            categories.add("OffreService");
        }
        if (demandeMateriel.isSelected()){
            categories.add("DemandeMateriel");
        }
        if (demandeService.isSelected()){
            categories.add("DemandeService");
        }
        return categories;
    }

    

    public void rechercher(Pattern userPattern, Pattern titrePattern) throws IOException{

        // //initialiser les annonces, lire dans le json
        // // Read existing content from users.json
        // //initialiser les annonces, lire dans le json
        // // Read existing content from users.json
        // FileAccess fileAccess = new FileAccess();
        // String filePath = fileAccess.getPathOf("annonces.json");
        // File file = new File(filePath);
        // String fileContent = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        // JSONObject existingData = new JSONObject(fileContent);

        // //parcourir le json et ajouter les annonces à la liste
        // for (int i=1;i<existingData.length()+1;i++){
        //     JSONObject annonce = existingData.getJSONObject(i+"");
        //     System.out.println(annonce);
        //     this.annonces.add(new Annonce(i,annonce.getString("titre"), annonce.getString("categorie"), annonce.getString("description"), annonce.getInt("prix"), annonce.getString("referent"), annonce.getBoolean("actif")));
        // }

        // On charge toutes les annonces
        if (app.getDataAnnoncesUtils() != null) {
            this.annonces = app.getDataAnnoncesUtils().getAnnonces();
        }

        //matcher les annonces avec les critères de recherche et afficher celles qui correspndent
        for (Annonce annonce : this.annonces){
            Matcher userMatcher = userPattern.matcher(annonce.getReferent());
            Matcher titreMatcher = titrePattern.matcher(annonce.getTitre());

            // les definir avant le if parce que sinon ca marche pas (??)
            Boolean userMatch = userMatcher.find();
            Boolean titreMatch = titreMatcher.find();
    
            //condition pour trouver les bonnes annonces
            if (annonce.getActif() && (userMatch || annonce.getReferent()==null) && (titreMatch || annonce.getTitre()==null)
            && (findCategorie().contains(annonce.getCategorie()) || findCategorie().isEmpty())){


                //on affiche dynamiquement les annonces concernées dans la Vbox resultats
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

