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

public class OffresEtDemandesController {

    private Application app;
    private String type;
    private ArrayList<Annonce> annonces = new ArrayList<Annonce>();
    @FXML
    private AnchorPane anchorServices;
    @FXML
    private AnchorPane anchorMateriel;
    @FXML
    private Label serviceLabel;
    @FXML
    private Label materielLabel;




    public OffresEtDemandesController(Application app, String type) {
        this.app = app;
        this.type = type;
        annonces.add(new Annonce("test service", "a", "OffreService", "a", "a", "a"));
        annonces.add(new Annonce("test materiel", "a", "PretMateriel", "a", "a", "a"));
        annonces.add(new Annonce("test2 materiel", "a", "PretMateriel", "a", "a", "a"));
        annonces.add(new Annonce("test3 materiel", "a", "PretMateriel", "a", "a", "a"));
        annonces.add(new Annonce("test4 materiel", "a", "PretMateriel", "a", "a", "a"));
        annonces.add(new Annonce("test5 materiel", "a", "PretMateriel", "a", "a", "a"));
        annonces.add(new Annonce("test6 materiel", "a", "PretMateriel", "a", "a", "a"));
        annonces.add(new Annonce("test7 materiel", "a", "PretMateriel", "a", "a", "a"));
        annonces.add(new Annonce("test8 materiel", "a", "DemandeMateriel", "a", "a", "a"));
        annonces.add(new Annonce("test9 service", "a", "DemandeService", "a", "a", "a"));
    }

    public void setType(String type){
        this.type = type;
    }

    @FXML
    public void detailsAnnonce(){
        app.getSceneController().switchToConsulterAnnonce();
    }

    public void initialize(){

        if (type.equals("Offre")){
            serviceLabel.setText("Offres de services");
            materielLabel.setText("Offres de matériel");
        }
        else if (type.equals("Demande")){
            serviceLabel.setText("Demandes de services");
            materielLabel.setText("Demandes de matériel");
        }

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

            if (type.equals("Offre")){
                if (annonce.getCategorie().equals("PretMateriel")){
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
