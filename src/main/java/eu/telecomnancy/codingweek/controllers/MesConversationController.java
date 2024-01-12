package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;
import eu.telecomnancy.codingweek.global.Conversations;
import eu.telecomnancy.codingweek.global.Messages;
import eu.telecomnancy.codingweek.global.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.ArrayList;

public class MesConversationController implements Observer {
    private Application app;

    private ArrayList<Conversations> conversations;

    @FXML
    private VBox VBoxConversations;

    public MesConversationController(Application app) {
        this.app = app;
        app.addObserver(this);
    }

    public void initialize() {
        conversations = new ArrayList<Conversations>();
        VBoxConversations.getChildren().clear();

        try {
            User user = app.getMainUser();
            if(user == null){
                throw new IOException("User not connected");
            }
            conversations = app.getDataConversationsUtils().getConversationsByUser(user.getUserName());
            System.out.println(conversations);
        } catch (IOException e) {
            System.out.println("Erreur lors de la récupération des conversations : Utilisateur non connecté");
        }

        for (Conversations conversation : conversations) {
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

            Label user = new Label(conversation.getOtherUser(app.getMainUser().getUserName()));
            user.setStyle("-fx-font-weight: bold;");
            hboxGauche.getChildren().add(user);


            Label texte = null;
            try {
                Messages test = app.getDataMessagesUtils().getLastMessageFromConversation(String.valueOf(conversation.getId()));
                System.out.println(test);
                if (test == null) {
                    texte = new Label("Aucun message");
                } else {
                texte = new Label(test.getMessage());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            texte.setStyle("-fx-text-fill: gray;");

            hboxCentre.getChildren().add(texte);


            Button button = new Button();
            button.setText("Voir les détails");
            button.setOnAction((event) -> {
                detailsConversation(conversation);
            });
            button.setId(conversation.getId()+"");
            hboxDroite.getChildren().add(button);

            hbox.setStyle("-fx-background-color: #eeeeee;");
            hbox.setSpacing(10);
            VBoxConversations.getChildren().add(hbox);
        }
    }

    public void detailsConversation(Conversations conversation){
        app.setConversationsAffichee(conversation);
        app.notifyObservers("conversation");
        app.getSceneController().switchToConsulterMessagerie();
    }

    public void update(String type) {
        if (type == "conversation") {
            initialize();
        }
    }
}
