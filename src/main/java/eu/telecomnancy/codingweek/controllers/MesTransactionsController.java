package eu.telecomnancy.codingweek.controllers;

import java.io.IOException;
import java.util.ArrayList;

import eu.telecomnancy.codingweek.Application;
import eu.telecomnancy.codingweek.utils.Transaction;
import eu.telecomnancy.codingweek.utils.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MesTransactionsController implements Observer{
    @FXML
    private VBox VBoxTransactions;

    private Application app;

    public MesTransactionsController(Application app) {
        this.app = app;
        app.addObserver(this);
    }

    @Override
    public void update(String type) {
        if (type.equals("transactions")){
            VBoxTransactions.getChildren().clear();
            //ajouter les transactions à la VBox
            try {
                User user = app.getMainUser();
                //System.out.println(user.getUserName());
                ArrayList<Transaction> transactions = app.getDataTransactionUtils().getTransactionsByClientUser(user);
                for (Transaction transaction : transactions){
                    HBox hboxGauche = new HBox();
                    HBox hboxDroite = new HBox();
                    HBox hboxCentre = new HBox();
                    HBox HBox = new HBox();

                    hboxGauche.setPrefWidth(200);
                    hboxDroite.setPrefWidth(200);
                    hboxCentre.setPrefWidth(200);

                    HBox.getChildren().add(hboxGauche);
                    HBox.getChildren().add(hboxCentre);
                    HBox.getChildren().add(hboxDroite);

                    VBoxTransactions.getChildren().add(HBox);

                    Label id = new Label(transaction.getId()+"");
                    hboxGauche.getChildren().add(id);

                    Label statut = new Label(transaction.getStatus());
                    hboxGauche.getChildren().add(statut);

                    Label titre = new Label(app.getDataAnnoncesUtils().getAnnonce(transaction.getIdAnnonce()).getTitre());
                    hboxCentre.getChildren().add(titre);

                    Label referent = new Label(app.getDataAnnoncesUtils().getAnnonce(transaction.getIdAnnonce()).getReferent());
                    hboxCentre.getChildren().add(referent);

                    if (transaction.getStatus().equals("Acceptée")){
                        //si la transaction est validée, on peut la noter
                        Button noterButton = new Button("Noter");
                        //Associer action pour noter la transaction
                        // noterButton.setOnAction(e -> {
                        //     try {
                        //         app.getDataTransactionUtils().noter(transaction.getId());
                        //     } catch (IOException e1) {
                        //         e1.printStackTrace();
                        //     }
                        // });
                        hboxDroite.getChildren().add(noterButton);
                    }
                   

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
