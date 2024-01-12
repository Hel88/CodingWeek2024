package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;
import eu.telecomnancy.codingweek.global.Conversations;
import eu.telecomnancy.codingweek.global.Messages;
import eu.telecomnancy.codingweek.global.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class ConsulterConversationController implements Observer {

    private Application app;
    private Conversations conversations;
    private ArrayList<Messages> messages;

    @FXML
    private Label userName;
    @FXML
    private TextArea monMessage;
    @FXML
    private VBox VBoxMessages;


    public ConsulterConversationController(Application app) {
        this.app = app;
        app.addObserver(this);
    }

    public void initialize(){
        conversations = app.getConversationsAffichee();
        monMessage.setText("");
        if (conversations == null) { return; }
        User user = app.getMainUser();
        if(user != null && conversations.getUser1().equals(user.getUserName())){
            userName.setText(conversations.getUser2());
        }
        else if(user != null && conversations.getUser2().equals(user.getUserName())){
            userName.setText(conversations.getUser1());
        }
        else if (user == null){
            try {
                throw new IOException("User not connected");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            try {
                throw new IOException("User not in conversation");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            messages = app.getDataConversationsUtils().getMessagesFromConversation(conversations);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        VBoxMessages.getChildren().clear();

        for (Messages message : messages) {
            Label label = new Label();
            label.setText(message.getMessage());
            Label label2 = new Label();
            label2.setText(message.getExpediteur());
            label2.setStyle("-fx-font-weight: bold;");
            VBoxMessages.getChildren().add(label2);
            VBoxMessages.getChildren().add(label);
        }
    }

    @Override
    public void update(String type) {
        if(type == "consulterConversation"){
            initialize();
        }
    }

    @FXML
    public void send(){
        try {
            app.getDataMessagesUtils().addMessage(monMessage.getText(), app.getMainUser().getUserName(), String.valueOf(conversations.getId()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        app.notifyObservers("conversation");
        app.notifyObservers("consulterConversation");
        app.getSceneController().switchToConsulterMessagerie();
    }
}
