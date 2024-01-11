package eu.telecomnancy.codingweek.controllers;

import com.calendarfx.model.Calendar;
import eu.telecomnancy.codingweek.Application;
import eu.telecomnancy.codingweek.utils.DataCalendarUtils;
import eu.telecomnancy.codingweek.utils.DataUsersUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

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
        app.notifyObservers("user");
        app.getSceneController().switchToModifierProfil();
    }

    @FXML
    public void supprimerProfil(){
        System.out.println("on a dit que non en fait");
    }

        @Override
    public void update(String type){
        if (type == "user"){
            if (app.getMainUser() == null) return;
            username.setText(app.getMainUser().getUserName());
            email.setText(app.getMainUser().getEmail());
            address.setText(app.getMainUser().getAddress());
            city.setText(app.getMainUser().getCity());
            lastName.setText(app.getMainUser().getLastName());
            firstName.setText(app.getMainUser().getFirstName());
            eval.setText(app.getMainUser().getEval()+"");
        }
    }

    @FXML
    public void displayPlanning() throws IOException {
        app.notifyObservers("user");
        DataUsersUtils dataUsersUtils = DataUsersUtils.getInstance();
        int id = dataUsersUtils.getCalendarOf(app.getMainUser().getUserName());
        DataCalendarUtils dataCalendarUtils = DataCalendarUtils.getInstance();
        Calendar calendar = dataCalendarUtils.load(id);
        app.getSceneController().switchToCalendar(calendar);
    }
}
