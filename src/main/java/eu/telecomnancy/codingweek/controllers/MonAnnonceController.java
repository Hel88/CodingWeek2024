package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;
import eu.telecomnancy.codingweek.global.Annonce;
import eu.telecomnancy.codingweek.global.Transaction;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class MonAnnonceController implements Observer {

    private final Application app;
    private ArrayList<Transaction> transactions;
    @FXML
    private Label titre;
    @FXML
    private Label description;
    @FXML
    private Label prix;
    @FXML
    private VBox reservations;

    private Annonce annonce;


    public MonAnnonceController(Application app) {
        this.app = app;
        app.addObserver(this);
    }

    @FXML
    public void modifierAnnonce() {
        //on passe l'annonce à modifier à l'application
        app.setAnnonceAffichee(annonce);
        app.notifyObservers("annonce");
        app.getSceneController().switchToModifierAnnonce();
    }

    @FXML
    public void supprimerAnnonce() throws IOException {
        //pop up etes-vous sûr de vouloir supprimer cette annonce ?
        Alert alert = new Alert(AlertType.CONFIRMATION);

        alert.setTitle("Confirmation");
        alert.setHeaderText("Etes-vous sûr de vouloir supprimer cette annonce ?");
        alert.showAndWait();

        if (alert.getResult().getButtonData().isDefaultButton()) {
            //suppression de l'annonce
            app.getDataAnnoncesUtils().deleteAnnonce(annonce.getId() + "");
            app.notifyObservers("annonce");
            app.getSceneController().switchToMesAnnonces();
        }

    }

    public void initialize() throws IOException {

        transactions = new ArrayList<Transaction>();

        //vider les VBox pour éviter les doublons
        reservations.getChildren().clear();

        //récupérer les transactions
        if (app.getDataAnnoncesUtils() != null) {
            this.transactions = app.getDataTransactionUtils().getTransactionsByAnnonce(annonce);
        }

        //afficher les transactions

        for (Transaction transaction : this.transactions) {

            HBox hbox = new HBox();
            hbox.setStyle("-fx-background-color: #eeeeee; prefHeight:\"279.0\"");

            Label id = new Label("(id : " + transaction.getId() + ")");
            id.setPrefWidth(50);
            id.setPrefHeight(10);
            id.setWrapText(true);

            Label client = new Label(transaction.getIdClient());
            client.setPrefWidth(200);
            client.setPrefHeight(10);
            client.setWrapText(true);

            Label status = new Label(transaction.getStatus());
            status.setPrefWidth(200);
            status.setPrefHeight(10);
            status.setWrapText(true);

            hbox.getChildren().addAll(status, client, id);

            switch (transaction.getStatus()) {
                case "En attente" -> hbox.setStyle("-fx-background-color: #DAA520; prefHeight:\"279.0\"");
                case "Acceptée" -> hbox.setStyle("-fx-background-color: #00FF00; prefHeight:\"279.0\"");
                case "Refusée" -> hbox.setStyle("-fx-background-color: #FF0000; prefHeight:\"279.0\"");
            }

            reservations.getChildren().add(hbox);
        }
    }

    @Override
    public void update(String type) {
        annonce = app.getAnnonceAffichee();
        if (annonce != null) {
            titre.setText(annonce.getTitre());
            description.setText(annonce.getDescription());
            prix.setText(annonce.getPrix() + "");
            try {
                initialize();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
