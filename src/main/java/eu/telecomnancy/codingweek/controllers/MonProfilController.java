package eu.telecomnancy.codingweek.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import com.calendarfx.model.Calendar;

import eu.telecomnancy.codingweek.Application;
import eu.telecomnancy.codingweek.global.Annonce;
import eu.telecomnancy.codingweek.global.CalendarDisplay;
import eu.telecomnancy.codingweek.global.Transaction;
import eu.telecomnancy.codingweek.utils.DataUsersUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;

public class MonProfilController implements Observer {

    private final Application app;
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
    @FXML
    private ToggleButton sleep;

    public MonProfilController(Application app) {
        this.app = app;
        app.addObserver(this);
    }

    @FXML
    public void modifierProfil() {
        app.notifyObservers("user");
        app.getSceneController().switchToModifierProfil();
    }

    @FXML
    public void supprimerProfil() throws IOException {
        //pop up etes-vous sûr de vouloir supprimer cette annonce ?
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Confirmation");
        alert.setHeaderText("Etes-vous sûr de vouloir supprimer cette annonce ?");
        alert.showAndWait();

        if (alert.getResult().getButtonData().isDefaultButton()) {
            ArrayList<Annonce> annonces = app.getDataAnnoncesUtils().getAnnoncesByUsername(app.getMainUser().getUserName());
            for (Annonce annonce : annonces) {
                app.getDataAnnoncesUtils().deleteAnnonce(annonce.getId() + "");
            }
            app.getDataUsersUtils().deleteUser(app.getMainUser().getUserName());
            app.setMainUser(null);
            app.notifyObservers("connexion");
            app.getSceneController().switchToConnexion();
        }
    }

    @FXML
    public void voirEvaluations(){
        app.setUserEvalue(app.getMainUser());
        app.notifyObservers("evaluations");
        app.getSceneController().switchToUserEvaluations();
    }

    @Override
    public void update(String type) {
        if (Objects.equals(type, "user")) {
            if (app.getMainUser() == null) return;
            username.setText("Nom d'utilisateur: "+app.getMainUser().getUserName());
            firstName.setText("Prénom: "+app.getMainUser().getFirstName());
            lastName.setText("Nom: "+app.getMainUser().getLastName());
            email.setText("Email: "+app.getMainUser().getEmail());
            address.setText("Adresse: "+app.getMainUser().getAddress());
            city.setText("Ville: "+app.getMainUser().getCity());
            try {
                eval.setText(app.getMainUser().getMoyenne()+"");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (isSommeil()) {
                sleep.setSelected(true);
            } else {
                sleep.setSelected(false);
            }
        }
    }

    @FXML
    public void switchSleep() {
        if (sleep.isSelected()) {
            mettreSommeil();
        } else {
            enleverSommeil();
        }
        app.notifyObservers("user");
        app.notifyObservers("annonce");
    }

    @FXML
    public void displayPlanning() throws IOException {
        DataUsersUtils dataUsersUtils = DataUsersUtils.getInstance();
        int id = dataUsersUtils.getCalendarOf(app.getMainUser().getUserName());
        CalendarDisplay calendarDisplay = new CalendarDisplay(app.getSceneController());
        calendarDisplay.calendarSwitchPreparation();

        // adding the user's calendar
        calendarDisplay.calendarSwitchAddCalendarWithStyle(id, Calendar.Style.STYLE1, true);
        calendarDisplay.calendarSwitchSetCurrentCalendarToDefault();

        // adding Annonces and Transactions to the calendar
        ArrayList<Annonce> annonces = app.getDataAnnoncesUtils().getAnnoncesByUsername(app.getMainUser().getUserName());
        for (Annonce annonce : annonces) {
            calendarDisplay.calendarSwitchAddCalendarWithStyle(annonce.getPlanning(), Calendar.Style.STYLE2, true);
            ArrayList<Transaction> transactions = app.getDataTransactionUtils().getTransactionsByAnnonce(annonce);
            for (Transaction transaction : transactions) {
                calendarDisplay.calendarSwitchAddCalendarWithStyle(transaction.getPlanning(), Calendar.Style.STYLE3, false);
            }
        }


        app.getSceneController().switchToCalendar();
    }


    public boolean isSommeil() {
        try {
            return app.getDataUsersUtils().isUserSleeping(app.getMainUser().getUserName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mettreSommeil() {
        try {
            app.getDataUsersUtils().setUserSleeping(app.getMainUser().getUserName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void enleverSommeil() {
        try {
            app.getDataUsersUtils().setUserNotSleeping(app.getMainUser().getUserName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
