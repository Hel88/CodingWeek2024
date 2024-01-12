package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UserEvaluationsController implements Observer {

    private final Application app;

    private String userEvalue;

    @FXML
    private VBox VBoxEvaluations;

    @FXML
    private Label username;


    public UserEvaluationsController(Application app) {
        this.app = app;
        app.addObserver(this);
    }

    @Override
    public void update(String type) {

        if (type.equals("evaluations")) {

            userEvalue = app.getAnnonceAffichee().getReferent(); //valable si on est sur la page d'une annonce
            //dans le cas où on est sur le profil:
            //userEvalue = app.getMainUser().getUserName();


            //VBoxEvaluations.getChildren().clear();
            username.setText(userEvalue);
            //ajouter les evaluations à la VBox

            HBox hbox = new HBox();
            int note = 4;//récupérer la note de l'utilisateur
            Label noteCalculee = new Label(note + "");

            hbox.setPrefWidth(200);

            String userQuiAMisLaNote = "user1";//récupérer le nom de l'utilisateur qui a mis la note

            Label userQuiAMisLaNoteLabel = new Label(userQuiAMisLaNote);
            hbox.getChildren().add(userQuiAMisLaNoteLabel);

            String commentaire = "super";
            Label commentaireLabel = new Label(commentaire);
            hbox.getChildren().add(commentaireLabel);

            hbox.getChildren().add(noteCalculee);
            VBoxEvaluations.getChildren().add(hbox);


        }

    }


}
