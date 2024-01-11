package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;

public class NoterUserController {
    
    private Application app;
    @FXML
    private CheckBox un;
    @FXML
    private CheckBox deux;
    @FXML
    private CheckBox trois;
    @FXML
    private CheckBox quatre;
    @FXML
    private CheckBox cinq;
    @FXML
    private TextArea commentaire;
    
    public NoterUserController(Application app) {
        this.app = app;
    }


    public int note(){
        int note = 0;
        if(un.isSelected()){
            note = 1;
        }
        if(deux.isSelected()){
            note = 2;
        }
        if(trois.isSelected()){
            note = 3;
        }
        if(quatre.isSelected()){
            note = 4;
        }
        if(cinq.isSelected()){
            note = 5;
        }
        return note;
    }

    @FXML
    public void noter(){
        app.getSceneController().switchToMesAnnonces();
            Integer note = note();
            String leCommentaireSaMere = this.commentaire.getText();
    }


}
