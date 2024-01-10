package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MenuController implements Observer{


    @FXML
    private Label username;

    private Application app;

    public MenuController(Application app) {
        this.app = app;
        app.addObserver(this);
    }

    @Override
    public void update(String type) {
        if (app.getMainUser() != null){
            username.setText(app.getMainUser().getUserName());
        }
    }

    @FXML
    public void initialize(){
        if (app.getMainUser() != null){
            username.setText(app.getMainUser().getUserName());
        }
    }
    @FXML
    public void offres(){
        app.getSceneController().switchToOffres();
    }
    
    @FXML
    public void demandes(){
        app.getSceneController().switchToDemandes();
    }
    
    @FXML
    public void messagerie(){
        System.out.println("messagerie");
    }
    
    @FXML
    public void mesAnnonces(){
        //System.out.println("mes annonces");
        app.getSceneController().switchToMesAnnonces();
    }
    
    @FXML
    public void monProfil(){
        //System.out.println("profil");
        app.getSceneController().switchToMonProfil();
    }
    @FXML
    public void deconnexion(){
        app.setMainUser(null);
        app.notifyObservers("connexion");
        app.getSceneController().switchToConnexion();
    }

    @FXML
    public void connexion(){
        app.getSceneController().switchToConnexion();
    }
}
