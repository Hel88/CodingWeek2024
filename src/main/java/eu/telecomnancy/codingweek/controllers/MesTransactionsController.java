package eu.telecomnancy.codingweek.controllers;

import java.io.IOException;
import java.util.ArrayList;

import eu.telecomnancy.codingweek.Application;
import eu.telecomnancy.codingweek.global.Transaction;
import eu.telecomnancy.codingweek.global.User;
import eu.telecomnancy.codingweek.utils.DataAnnoncesUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MesTransactionsController implements Observer {
    @FXML
    private VBox VBoxTransactions;

    private final Application app;

    public MesTransactionsController(Application app) {
        this.app = app;
        app.addObserver(this);
    }

    @Override
    public void update(String type) {
        if (type.equals("transactions")) {
            VBoxTransactions.getChildren().clear();
            //ajouter les transactions à la VBox
            try {
                User user = app.getMainUser();
                //System.out.println(user.getUserName());
                ArrayList<Transaction> transactions = app.getDataTransactionUtils().getTransactionsByClientUser(user);
                System.out.println(transactions.size());
                for (Transaction transaction : transactions){
                    HBox hboxStatut = new HBox();
                    HBox hboxTitre = new HBox();
                    HBox hboxUser = new HBox();
                    HBox hboxPrix = new HBox();
                    HBox HBox = new HBox();
                    HBox.setPrefHeight(20);
                    HBox.setSpacing(8);

                    // hboxStatut.setPrefWidth(100);
                    // hboxTitre.setPrefWidth(200);
                    // hboxUser.setPrefWidth(100);
                    //hboxPrix.setPrefWidth(100);

                    HBox.getChildren().add(hboxStatut);
                    HBox.getChildren().add(hboxTitre);
                    HBox.getChildren().add(hboxUser);
                    HBox.getChildren().add(hboxPrix);

                    VBoxTransactions.getChildren().add(HBox);

                    

                    Label statut = new Label(transaction.getStatus());
                    statut.setPrefWidth(100);
                    statut.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
                    hboxStatut.getChildren().add(statut);

                    Label titre = new Label(app.getDataAnnoncesUtils().getAnnonce(transaction.getIdAnnonce()).getTitre());
                    titre.setPrefWidth(150);
                    titre.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
                    hboxTitre.getChildren().add(titre);

                    Label referent = new Label(app.getDataAnnoncesUtils().getAnnonce(transaction.getIdAnnonce()).getReferent());
                    referent.setPrefWidth(170);
                    referent.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
                    hboxUser.getChildren().add(referent);

                    Label prix = new Label(app.getDataAnnoncesUtils().getAnnonce(transaction.getIdAnnonce()).getPrix() + "");
                    prix.setPrefWidth(50);
                    prix.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
                    hboxPrix.getChildren().add(prix);

                    Button planning = new Button("Planning");
                    planning.setOnAction(e -> {
                        //TODO
                    });

                    if (transaction.getStatus().equals("Acceptée")) {
                        //si la transaction est validée, on peut la noter
                        Button noterButton = new Button("Noter");
                        noterButton.setOnAction(e -> {
                            try {
                                app.setAnnonceAffichee(DataAnnoncesUtils.getInstance().getAnnonce(transaction.getIdAnnonce()));
                                app.getSceneController().switchToNoterUser();
                                app.getDataTransactionUtils().noterTransaction(transaction);
                                app.notifyObservers("transactions");
                            } catch (IOException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                        });
                        hboxPrix.getChildren().add(noterButton);
                        HBox.setStyle("-fx-background-color: #98FB98;");
                    }
                    hboxPrix.getChildren().add(planning);

                    if (transaction.getStatus().equals("Refusée")){
                        HBox.setStyle("-fx-background-color: #FF7F50;");
                    }

                    if (transaction.getStatus().equals("En attente")){
                        HBox.setStyle("-fx-background-color: #FFEBCD;");
                    }
                    


                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
