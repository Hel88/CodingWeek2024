package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;
import eu.telecomnancy.codingweek.global.CalendarDisplay;
import eu.telecomnancy.codingweek.utils.DataAnnoncesUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Objects;


public class CreationEtModificationAnnonceController implements Observer {

    private final Application app;
    private String categorie;
    private final String action;
    @FXML
    private TextField titre;
    @FXML
    private TextArea description;
    @FXML
    private TextField prix;
    @FXML
    private Button addAnnonce;
    @FXML
    private Label label;
    @FXML
    private Label messageErreur;


    public CreationEtModificationAnnonceController(Application app, String action) {
        this.app = app;
        this.action = action;
        app.addObserver(this);
    }

    public void creerAnnonce() throws IOException {
        // Check if the price is an integer
        if (!prix.getText().matches("[0-9]+")) {
            messageErreur.setText("Le prix doit être un nombre entier.");
            return;
        }

        // Check if the title is empty
        if (titre.getText().isEmpty()) {
            messageErreur.setText("Le titre ne peut pas être vide.");
            return;
        }

        // Create the new annnounce
        int idAnnonce = app.getDataAnnoncesUtils().addAnnonce(titre.getText(), description.getText(), prix.getText(), categorie, app.getMainUser().getUserName());

        app.notifyObservers("annonce");
        int id = DataAnnoncesUtils.getInstance().getAnnonce(idAnnonce).getPlanning();
        CalendarDisplay calendarDisplay = new CalendarDisplay(app.getSceneController());
        calendarDisplay.calendarSwitchPreparation();
        calendarDisplay.calendarSwitchAddCalendar(id, true);
        calendarDisplay.calendarSwitchSetCurrentCalendarToDefault();
        app.getSceneController().switchToCalendar();
    }

    public void modifierAnnonce() throws IOException {
        // Check if the price is an integer
        if (!prix.getText().matches("[0-9]+")) {
            messageErreur.setText("Le prix doit être un nombre entier.");
            return;
        }

        // Check if the title is empty
        if (titre.getText().isEmpty()) {
            messageErreur.setText("Le titre ne peut pas être vide.");
            return;
        }

        // Create the new annnounce
        int idAnnonce = app.getDataAnnoncesUtils().modifyAnnonce(app.getAnnonceAffichee().getId(), titre.getText(), description.getText(), prix.getText(), app.getAnnonceAffichee().getCategorie(), app.getMainUser().getUserName(), true, app.getAnnonceAffichee().getPlanning());

        app.notifyObservers("annonce");
        CalendarDisplay calendarDisplay = new CalendarDisplay(app.getSceneController());
        calendarDisplay.calendarSwitchPreparation();
        calendarDisplay.calendarSwitchAddCalendar(DataAnnoncesUtils.getInstance().getAnnonce(idAnnonce).getPlanning(), true);
        calendarDisplay.calendarSwitchSetCurrentCalendarToDefault();
        app.getSceneController().switchToCalendar();
    }

    @FXML
    public void addAnnonce() throws IOException {
        // comme la page fxml se charge de la modification et de la création,
        // le bouton doit changer d'action selon ce qu'on veut faire
        if (Objects.equals(action, "creation")) {
            creerAnnonce();
        } else if (Objects.equals(action, "modification")) {
            modifierAnnonce();
        }

    }

    @Override
    public void update(String type) {
        if (Objects.equals(type, "annonce")) {
            categorie = app.getCategorieAnnonceACreer();
        }
        if (Objects.equals(action, "modification")) {
            label.setText("Modifier votre annonce");
            addAnnonce.setText("Modifier");
            if (app.getAnnonceAffichee() != null) {
                titre.setText(app.getAnnonceAffichee().getTitre());
                description.setText(app.getAnnonceAffichee().getDescription());
                prix.setText(app.getAnnonceAffichee().getPrix() + "");
            }
        }
    }
}
