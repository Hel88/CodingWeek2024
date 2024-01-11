package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Objects;

public class ModifierProfilController implements Observer{
    
    private Application app;
    @FXML
    private Label username;
    @FXML
    private TextField email;
    @FXML
    private TextField adresse;
    @FXML
    private TextField ville;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private Label note;
    @FXML
    private TextField ancienPWD;
    @FXML
    private TextField nouveauPWD;


    public ModifierProfilController(Application app) {
        this.app = app;
        app.addObserver(this);
    }

    @FXML
    public void validerModifProfil() throws IOException {

        app.getMainUser().setEmail(email.getText());
        app.getMainUser().setAddress(adresse.getText());
        app.getMainUser().setCity(ville.getText());
        app.getMainUser().setLastName(nom.getText());
        app.getMainUser().setFirstName(prenom.getText());

        if(!ancienPWD.getText().isEmpty() && !nouveauPWD.getText().isEmpty()){
            if(app.getDataUsersUtils().checkPassword(app.getMainUser().getUserName(), ancienPWD.getText())){
                app.getMainUser().setPassword(app.getDataUsersUtils().hashPassword(nouveauPWD.getText()));
            }
        }

        app.getDataUsersUtils().updateUser(app.getMainUser());

        app.notifyObservers("user");
        app.getSceneController().switchToMonProfil();
    }

    @Override
    public void update(String type){
        if (Objects.equals(type, "user")){
            if (app.getMainUser() == null) return;
            username.setText(app.getMainUser().getUserName());
            email.setText(app.getMainUser().getEmail());
            adresse.setText(app.getMainUser().getAddress());
            ville.setText(app.getMainUser().getCity());
            nom.setText(app.getMainUser().getLastName());
            prenom.setText(app.getMainUser().getFirstName());
            note.setText(app.getMainUser().getEval()+"");
        }
    }
}
