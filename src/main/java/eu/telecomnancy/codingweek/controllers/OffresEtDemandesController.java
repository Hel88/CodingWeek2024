package eu.telecomnancy.codingweek.controllers;

import java.io.IOException;
import java.util.ArrayList;

import eu.telecomnancy.codingweek.Application;
import eu.telecomnancy.codingweek.utils.Annonce;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class OffresEtDemandesController implements Observer{

    private final Application app;
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
        app.addObserver(this);
    }

    public void setType(String type){
        this.type = type;
    }

    public void versRecherche(){
        app.getSceneController().switchToRecherche();
    }

    public void consulterAnnonce(Annonce annonce){
        app.setAnnonceAffichee(annonce);
        app.notifyObservers("annonce");
        app.getSceneController().switchToConsulterAnnonce(annonce.getId());
    }

    public void initialize() throws IOException {

        annonces = new ArrayList<Annonce>();
        services.getChildren().clear();
        materiel.getChildren().clear();

        if (app.getDataAnnoncesUtils() != null) {
            this.annonces = app.getDataAnnoncesUtils().getAnnonces();
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
            hbox.setStyle("-fx-background-color: #eeeeee; prefHeight:\"279.0\"");

            Label titre = new Label(annonce.getTitre());
            titre.setPrefWidth(200);
            titre.setPrefHeight(10);
            titre.setWrapText(true);

            Label referent = new Label(annonce.getReferent());
            referent.setPrefWidth(100);
            referent.setPrefHeight(10);
            referent.setWrapText(true);


            HBox hboxPrix = new HBox();

            hboxPrix.setPrefWidth(100);
            hboxPrix.setPrefHeight(10);

            Label prix = new Label(annonce.getPrix()+"");
            prix.setWrapText(true);
            hboxPrix.getChildren().add(prix);

            Image image = new Image(getClass().getResource("images/florain.jpg").toExternalForm());
            ImageView imagev = new ImageView(image);
            
            Rectangle clip = new Rectangle(image.getWidth(), image.getHeight());
            clip.setArcWidth(20); // Modifier la courbure selon vos préférences
            clip.setArcHeight(20);
            imagev.setClip(clip);
            imagev.setFitHeight(18);
            imagev.setFitWidth(18);
            hboxPrix.getChildren().add(imagev);

            Button details = new Button();
            details.setText("Voir les détails");
            //details.setOnAction(e -> app.getSceneController().switchToConsulterAnnonce(annonce.getId()));
            details.setOnAction(e->{consulterAnnonce(annonce);});
            hbox.getChildren().addAll(titre, referent, hboxPrix, details);

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

    @Override
    public void update(String type) {
            try {
                initialize();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        
    }
}
