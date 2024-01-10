package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;

public class MenuController implements Observer{


    @FXML
    private Label username;
    @FXML
    private MenuBar menuBar;

    private Application app;

    public MenuController(Application app) {
        this.app = app;
        app.addObserver(this);
    }

    @Override
    public void update(String type) {
        if (app.getMainUser() != null){
            menuBar.setVisible(true);
            username.setText(app.getMainUser().getUserName());
        }
        else{
            menuBar.setVisible(false);
            username.setText("Bienvenue, veuillez vous connecter");
        }
        
    }

    @FXML
    public void initialize(){
        menuBar.setVisible(false);
        if (app.getMainUser() != null){
            menuBar.setVisible(true);

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
