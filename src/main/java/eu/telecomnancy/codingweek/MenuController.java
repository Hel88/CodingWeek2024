package eu.telecomnancy.codingweek;

import javafx.fxml.FXML;

public class MenuController {

    private HelloApplication app;

    public MenuController(HelloApplication app) {
        this.app = app;
    }

    @FXML
    public void offres(){
        System.out.println("offres");
    }

    @FXML
    public void demandes(){
        System.out.println("demandes");
    }
    
    @FXML
    public void messagerie(){
        System.out.println("messagerie");
    }
    
    @FXML
    public void mesAnnonces(){
        System.out.println("mes annonces");
    }
    
    @FXML
    public void monProfil(){
        System.out.println("profil");
    }
    @FXML
    public void deconnexion(){
        System.out.println("deconnexion");
    }
}
