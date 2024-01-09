package eu.telecomnancy.codingweek;

import javafx.fxml.FXML;

public class MenuController {

    private HelloApplication app;

    public MenuController(HelloApplication app) {
        this.app = app;
    }

    @FXML
    public void offres(){
        app.getSceneController().switchToOffres();
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
        System.out.println("deconnexion");
    }
}
