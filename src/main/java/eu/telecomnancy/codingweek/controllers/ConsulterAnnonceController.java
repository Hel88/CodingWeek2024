package eu.telecomnancy.codingweek.controllers;

import java.io.IOException;

import com.calendarfx.model.Calendar;

import eu.telecomnancy.codingweek.Application;
import eu.telecomnancy.codingweek.global.Annonce;
import eu.telecomnancy.codingweek.global.CalendarDisplay;
import eu.telecomnancy.codingweek.global.User;
import eu.telecomnancy.codingweek.utils.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ConsulterAnnonceController implements Observer{

    private Application app;
    private Annonce annonce;
    @FXML
    private Label titre;
    @FXML
    private Label description;
    @FXML
    private Label name;
    @FXML
    private Label prix;
    @FXML
    private Label categorie;
    @FXML
    private Label lieu;
    @FXML
    private Label email;
    
    public ConsulterAnnonceController(Application app) {
        this.app = app;
        app.addObserver(this);
    }

    public void initialize() {
        annonce = app.getAnnonceAffichee();
        if (annonce == null){return;} 
        titre.setText(annonce.getTitre());
        description.setText(annonce.getDescription());
        name.setText(annonce.getReferent());
        prix.setText(annonce.getPrix()+"");
        categorie.setText(annonce.getCategorie());

        try {
            User user = app.getDataUsersUtils().getUserByUserName(annonce.getReferent());
            lieu.setText(user.getCity());
            email.setText(user.getEmail());
            name.setText(user.getFirstName()+" "+user.getLastName()+" ("+user.getUserName()+")");

        } catch (IOException e) {
            e.printStackTrace();
        }
            
        
    }

    public void update(String type) {
        if (type == "annonce") {
            initialize();
        }
    }

    @FXML
    public void voirEvaluations(){
        app.notifyObservers("evaluations");
        app.getSceneController().switchToUserEvaluations(annonce.getReferent());
    }



    @FXML
    public void reserver() throws IOException {
        int idTransac = app.getDataTransactionUtils().addTransaction(String.valueOf(annonce.getId()), app.getMainUser().getUserName(), "En attente");
        app.notifyObservers("transactions");
        app.setMainUser(app.getDataUsersUtils().getUserByUserName(app.getMainUser().getUserName()));
        CalendarDisplay calendarDisplay = new CalendarDisplay(app.getSceneController());
        calendarDisplay.calendarSwitchPreparation();
        calendarDisplay.calendarSwitchAddCalendarWithStyle(DataTransactionUtils.getInstance().getTransaction(idTransac).getPlanning(), Calendar.Style.STYLE1, true);
        calendarDisplay.calendarSwitchSetCurrentCalendarToDefault();

        calendarDisplay.calendarSwitchAddCalendarWithStyle(DataAnnoncesUtils.getInstance().getAnnonce(DataTransactionUtils.getInstance().getTransaction(idTransac).getIdAnnonce()).getPlanning(), Calendar.Style.STYLE5, false);

        app.getSceneController().switchToCalendar();
    }

    @FXML
    public void contacter() throws IOException {
        app.setConversationsAffichee(app.getDataConversationsUtils().getNewConversationWith(app.getMainUser().getUserName(), annonce.getReferent()));
        app.notifyObservers("conversation");
        app.notifyObservers("consulterConversation");
        app.getSceneController().switchToConsulterMessagerie();
    }
}
