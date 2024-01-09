package eu.telecomnancy.codingweek.controllers;

import java.util.ArrayList;

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
        //exemple
        annonces.add(new Annonce("a", "a", "a", "a", "a", "a"));
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
            hbox.setStyle("-fx-background-color: #eeeeee;");
            hbox.setPrefHeight(279.0);
            VBoxAnnonces.getChildren().add(hbox);
        }
    }


}
