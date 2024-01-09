package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;
import eu.telecomnancy.codingweek.utils.Annonce;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class OffresController {

    private Application app;
    private ArrayList<Annonce> annonces = new ArrayList<Annonce>();
    @FXML
    private AnchorPane anchorServices;
    @FXML
    private AnchorPane anchorMateriel;



    public OffresController(Application app) {
        this.app = app;
        annonces.add(new Annonce("test service", "a", "OffreService", "a", "a", "a"));
        annonces.add(new Annonce("test materiel", "a", "PretMateriel", "a", "a", "a"));
        annonces.add(new Annonce("test2 materiel", "a", "PretMateriel", "a", "a", "a"));
        annonces.add(new Annonce("test3 materiel", "a", "PretMateriel", "a", "a", "a"));
        annonces.add(new Annonce("test4 materiel", "a", "PretMateriel", "a", "a", "a"));
        annonces.add(new Annonce("test5 materiel", "a", "PretMateriel", "a", "a", "a"));
        annonces.add(new Annonce("test6 materiel", "a", "PretMateriel", "a", "a", "a"));
        annonces.add(new Annonce("test7 materiel", "a", "PretMateriel", "a", "a", "a"));
        annonces.add(new Annonce("test8 materiel", "a", "PretMateriel", "a", "a", "a"));
        annonces.add(new Annonce("test9 materiel", "a", "PretMateriel", "a", "a", "a"));
        annonces.add(new Annonce("test10 materiel", "a", "PretMateriel", "a", "a", "a"));
        annonces.add(new Annonce("test11 materiel", "a", "PretMateriel", "a", "a", "a"));
        annonces.add(new Annonce("test12 materiel", "a", "PretMateriel", "a", "a", "a"));

    }

    @FXML
    public void detailsAnnonce(){
        app.getSceneController().switchToConsulterAnnonce();
    }

    public void initialize(){

        VBox materiel = new VBox();
        materiel.setSpacing(10);
        anchorMateriel.getChildren().add(materiel);

        VBox services = new VBox();
        services.setSpacing(10);
        anchorServices.getChildren().add(services);

        //afficher les annonces
        for (Annonce annonce : this.annonces){
            HBox hbox = new HBox();
            hbox.setStyle("-fx-background-color: #eeeeee; prefHeight=\"279.0\"");

            Label titre = new Label(annonce.getTitre());
            titre.setPrefWidth(150);
            titre.setPrefHeight(10);
            titre.setWrapText(true);

            Label ville = new Label(annonce.getVille());
            ville.setPrefWidth(150);
            ville.setPrefHeight(10);
            ville.setWrapText(true);

            Label prix = new Label(annonce.getPrix());
            prix.setPrefWidth(100);
            prix.setPrefHeight(10);
            prix.setWrapText(true);

            Button details = new Button();
            details.setText("Voir les détails");
            details.setOnAction(e -> app.getSceneController().switchToConsulterAnnonce());

            hbox.getChildren().addAll(titre, ville, prix, details);

            if (annonce.getCategorie().equals("PretMateriel")){
                materiel.getChildren().add(hbox);
            }
            else if (annonce.getCategorie().equals("OffreService")){
                services.getChildren().add(hbox);
            }
        }
    }
}
