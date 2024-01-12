package eu.telecomnancy.codingweek.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import eu.telecomnancy.codingweek.Application;
import eu.telecomnancy.codingweek.global.Annonce;
import eu.telecomnancy.codingweek.global.Transaction;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class MesAnnoncesController implements Observer {

    @FXML
    private VBox VBoxAnnonces;
    @FXML
    private VBox reservations;

    private final Application app;

    private ArrayList<Annonce> annonces;

    private ArrayList<Transaction> transactions;

    public MesAnnoncesController(Application app) {
        this.app = app;
        annonces = new ArrayList<Annonce>();
        transactions = new ArrayList<Transaction>();
        app.addObserver(this);
    }

    public void detailsAnnonce(Annonce annonce) {
        //on enregistre l'annonce à afficher pour y avoir accès dans ConsulterAnnonceController
        app.setAnnonceAffichee(annonce);
        app.notifyObservers("annonce");
        app.getSceneController().switchToMonAnnonce();
    }

    //les différentes méthodes pour créer une annonce en fonction du bouton sur lequel on a cliqué
    public void creerAnnonceDemandeService() {
        app.setCategorieAnnonceACreer("DemandeService");
        app.notifyObservers("annonce");
        app.getSceneController().switchToCreationAnnonce();
    }

    public void creerAnnonceDemandeMateriel() {
        app.setCategorieAnnonceACreer("DemandeMateriel");
        app.notifyObservers("annonce");
        app.getSceneController().switchToCreationAnnonce();
    }

    public void creerAnnonceOffreService() {
        app.setCategorieAnnonceACreer("OffreService");
        app.notifyObservers("annonce");
        app.getSceneController().switchToCreationAnnonce();
    }

    public void creerAnnonceOffreMateriel() {
        app.setCategorieAnnonceACreer("OffreMateriel");
        app.notifyObservers("annonce");
        app.getSceneController().switchToCreationAnnonce();
    }


    @FXML
    public void initialize() throws IOException {
        //gère l'affichage
        annonces = new ArrayList<Annonce>();

        VBoxAnnonces.getChildren().clear();
        reservations.getChildren().clear();

        synchroJson(); //synchronise les annonces avec le json

        //Affichage de chaque annonce
        for (Annonce annonce : this.annonces) {
            HBox hbox = new HBox();

            hbox.setPrefWidth(600);

            HBox hboxGauche = new HBox();
            HBox hboxCentre = new HBox();
            HBox hboxDroite = new HBox();

            hbox.getChildren().add(hboxGauche);
            hbox.getChildren().add(hboxCentre);
            hbox.getChildren().add(hboxDroite);

            hboxGauche.setPrefWidth(300);
            hboxCentre.setPrefWidth(100);
            hboxDroite.setPrefWidth(150);

            Label titrelabel = new Label(annonce.getTitre());
            titrelabel.setStyle("-fx-font-weight: bold;");
            hboxGauche.getChildren().add(titrelabel);

            hboxGauche.getChildren().add(new Label("   "));

            Label categorie = new Label(annonce.getCategorie());
            categorie.setStyle("-fx-text-fill: gray;");
            hboxGauche.getChildren().add(categorie);

            Label prix = new Label("Prix: ");
            prix.setStyle("-fx-text-fill: gray;");

            hboxCentre.getChildren().add(prix);
            hboxCentre.getChildren().add(new Label(annonce.getPrix() + ""));

            Image image = new Image(Objects.requireNonNull(getClass().getResource("images/florain.png")).toExternalForm());
            ImageView imagev = new ImageView(image);

            Rectangle clip = new Rectangle(image.getWidth(), image.getHeight());
            clip.setArcWidth(20); // Modifier la courbure selon vos préférences
            clip.setArcHeight(20);
            imagev.setClip(clip);
            imagev.setFitHeight(18);
            imagev.setFitWidth(18);

            hboxCentre.getChildren().add(imagev);

            Button button = new Button();
            button.setText("Voir les détails");
            button.setOnAction((event) -> detailsAnnonce(annonce));
            button.setId(annonce.getId() + "");
            hboxDroite.getChildren().add(button);

            hbox.setStyle("-fx-background-color: #eeeeee;");
            hbox.setSpacing(10);
            VBoxAnnonces.getChildren().add(hbox);
        }

        //affichage de chaque transaction

        for (Transaction transaction : this.transactions) {
            HBox hbox = new HBox();
            hbox.setSpacing(8);
            hbox.setStyle("-fx-background-color: #eeeeee; prefHeight:\"279.0\"");
            hbox.setPrefHeight(20);
            hbox.setSpacing(8);

            Label id = new Label("(id : " + transaction.getId() + ")");
            id.setPrefWidth(50);
            id.setWrapText(true);

            Label client = new Label(transaction.getIdClient());
            client.setPrefWidth(200);
            client.setWrapText(true);

            Label status = new Label(transaction.getStatus());
            status.setPrefWidth(100);
            status.setWrapText(true);

            Button accepter = new Button();
            accepter.setText("Accepter");
            accepter.setOnAction((event) -> {
                //pop up etes-vous sûr de vouloir accepter cette annonce ?
                Alert alert = new Alert(AlertType.CONFIRMATION);

                alert.setTitle("Confirmation");
                alert.setHeaderText("Etes-vous sûr de vouloir accepter cette annonce ?");
                alert.showAndWait();

                if (alert.getResult().getButtonData().isDefaultButton()) {
                    try {
                        app.getDataTransactionUtils().accepterTransaction(transaction);
                        app.setMainUser(app.getDataUsersUtils().getUserByUserName(app.getMainUser().getUserName()));
                        app.notifyObservers("user");
                        app.notifyObservers("transactions");
                        initialize();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            Button refuser = new Button();
            refuser.setText("Refuser");
            refuser.setOnAction((event) -> {
                //pop up etes-vous sûr de vouloir refuser cette annonce ?
                Alert alert = new Alert(AlertType.CONFIRMATION);

                alert.setTitle("Confirmation");
                alert.setHeaderText("Etes-vous sûr de vouloir refuser cette annonce ?");
                alert.showAndWait();

                if (alert.getResult().getButtonData().isDefaultButton()) {
                    try {
                        app.getDataTransactionUtils().refuserTransaction(transaction);
                        initialize();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            hbox.getChildren().addAll(status, client, id);

            switch (transaction.getStatus()) {
                case "En attente" -> {
                    hbox.setStyle("-fx-background-color: #FFEBCD; prefHeight:\"279.0\"");
                    hbox.getChildren().addAll(accepter, refuser);
                }
                case "Acceptée" -> hbox.setStyle("-fx-background-color: #98FB98; prefHeight:\"279.0\"");
                case "Refusée" -> hbox.setStyle("-fx-background-color: #FF7F50; prefHeight:\"279.0\"");
                case "Notée" -> hbox.setStyle("-fx-background-color: #eeeeee; prefHeight:\"279.0\"");
            }

            Button planning = new Button("Planning");
            planning.setOnAction(e -> {
                //TODO
            });
            hbox.getChildren().add(planning);

            reservations.getChildren().add(hbox);
        }

    }

    public void synchroJson() throws IOException {
        //synchronise les annonces avec le json
        //synchrinuse les transacitons avec le json
        if (app.getMainUser() != null) {
            this.annonces = app.getDataAnnoncesUtils().getAnnoncesByUsername(app.getMainUser().getUserName());
            this.transactions = app.getDataTransactionUtils().getTransactionsByRefentUser(app.getMainUser());
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



