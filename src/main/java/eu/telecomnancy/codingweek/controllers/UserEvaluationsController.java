package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;
import eu.telecomnancy.codingweek.global.Note;
import eu.telecomnancy.codingweek.global.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class UserEvaluationsController implements Observer {

    private final Application app;

    private User userEvalue;

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


            userEvalue = app.getUserEvalue(); //valable si on est sur la page d'une annonce
            //System.out.println(userEvalue.getUserName());


            VBoxEvaluations.getChildren().clear();
            username.setText("Evaluations de " + userEvalue.getUserName());
            //ajouter note moyenne

            ArrayList<Note> notes = app.getDataNoteUtils().getNotesByUser(userEvalue);
            //ArrayList<Note> notes = app.getDataNoteUtils().getNotes();

            System.out.println(notes.size());


            for (Note eval : notes) {

                HBox hbox = new HBox();

                HBox hboxNote = new HBox();
                hbox.getChildren().add(hboxNote);
                hboxNote.setPrefWidth(100);
                hboxNote.setPadding(new javafx.geometry.Insets(0, 0, 0, 20));

                HBox hboxUser = new HBox();
                hbox.getChildren().add(hboxUser);
                hboxUser.setPrefWidth(120);

                HBox hboxCommentaire = new HBox();
                hbox.getChildren().add(hboxCommentaire);


                int note = eval.getNote();
                Label noteLabel = new Label(note + "/5");
                hboxNote.getChildren().add(noteLabel);

                String userQuiAMisLaNote = eval.getUsernameClient();
                Label userQuiAMisLaNoteLabel = new Label(userQuiAMisLaNote);
                hboxUser.getChildren().add(userQuiAMisLaNoteLabel);


                String commentaire = eval.getCommentaire();
                Label commentaireLabel = new Label(commentaire);
                hboxCommentaire.getChildren().add(commentaireLabel);


                VBoxEvaluations.getChildren().add(hbox);
            }
        }
    }
}
