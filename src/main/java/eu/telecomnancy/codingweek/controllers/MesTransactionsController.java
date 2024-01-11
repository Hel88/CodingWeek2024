package eu.telecomnancy.codingweek.controllers;

import java.io.IOException;
import java.util.ArrayList;

import eu.telecomnancy.codingweek.Application;
import eu.telecomnancy.codingweek.utils.Transaction;
import eu.telecomnancy.codingweek.utils.User;
import javafx.fxml.FXML;
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
            System.out.println("------------------------");
            VBoxTransactions.getChildren().clear();
            //ajouter les transactions à la VBox
            try {
                User user = app.getMainUser();
                //System.out.println(user.getUserName());
                ArrayList<Transaction> transactions = app.getDataTransactionUtils().getTransactionsByClientUser(user);
                System.out.println(transactions.size());
                for (Transaction transaction : transactions){
                    System.out.println(transaction.getId());
                    HBox hboxGauche = new HBox();
                    HBox hboxDroite = new HBox();
                    HBox hboxCentre = new HBox();

                    hboxGauche.setPrefWidth(200);
                    hboxDroite.setPrefWidth(200);
                    hboxCentre.setPrefWidth(200);

                    VBoxTransactions.getChildren().add(hboxGauche);
                    VBoxTransactions.getChildren().add(hboxCentre);
                    VBoxTransactions.getChildren().add(hboxDroite);

                    Label id = new Label(transaction.getId()+"");
                    hboxGauche.getChildren().add(id);

                    Label statut = new Label(transaction.getStatus());
                    hboxGauche.getChildren().add(statut);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
