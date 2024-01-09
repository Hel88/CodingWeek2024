package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MonProfilController implements Observer {

    private Application app;
    @FXML
    private Label username;
    @FXML
    private Label email;
    @FXML
    private Label address;
    @FXML
    private Label city;
    @FXML
    private Label lastName;
    @FXML
    private Label firstName;
    @FXML
    private Label eval;

    public MonProfilController(Application app) {
        this.app = app;
        app.addObserver(this);
        }

    @FXML
    public void modifierProfil(){
        app.notifyObservers();
        app.getSceneController().switchToModifierProfil();
    }

    @FXML
    public void supprimerProfil(){
        System.out.println("on a dit que non en fait");
    }

        @Override
    public void update(){
        username.setText(app.getMainUser().getUserName());
        email.setText(app.getMainUser().getEmail());
        address.setText(app.getMainUser().getAddress());
        city.setText(app.getMainUser().getCity());
        lastName.setText(app.getMainUser().getLastName());
        firstName.setText(app.getMainUser().getFirstName());
        eval.setText(app.getMainUser().getEval()+"");
    }
    
}
