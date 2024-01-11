package eu.telecomnancy.codingweek.controllers;

import com.calendarfx.model.Calendar;
import eu.telecomnancy.codingweek.Application;
import eu.telecomnancy.codingweek.utils.Annonce;
import eu.telecomnancy.codingweek.utils.DataAnnoncesUtils;
import eu.telecomnancy.codingweek.utils.DataTransactionUtils;
import eu.telecomnancy.codingweek.utils.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

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
    public void choisirDispo(){
        System.out.println("choisir dispo");
        //calendrier de mathis
        
    }



    @FXML
    public void reserver() throws IOException {
        int idTransac = app.getDataTransactionUtils().addTransaction(String.valueOf(annonce.getId()), app.getMainUser().getUserName(), "En attente");
        app.notifyObservers("transactions");
        app.getSceneController().calendarSwitchPreparation();
        app.getSceneController().calendarSwitchAddCalendarWithStyle(DataTransactionUtils.getInstance().getTransaction(idTransac).getPlanning(), Calendar.Style.STYLE1, true);
        app.getSceneController().calendarSwitchSetCurrentCalendarToDefault();

        app.getSceneController().calendarSwitchAddCalendarWithStyle(DataAnnoncesUtils.getInstance().getAnnonce(DataTransactionUtils.getInstance().getTransaction(idTransac).getIdAnnonce()).getPlanning(), Calendar.Style.STYLE5, false);

        app.getSceneController().switchToCalendar();
    }
}
