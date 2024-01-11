package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class UserEvaluationsController implements Observer{
    
    private Application app;

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
        if (type.equals("evaluations")){

            userEvalue = app.getAnnonceAffichee().getReferent(); //valable si on est sur la page d'une annonce
            if (type.equals("evaluations")){
                //VBoxEvaluations.getChildren().clear();
                username.setText(userEvalue);
                //ajouter les evaluations à la VBox
            }
        }
        
    }
    
    
}
