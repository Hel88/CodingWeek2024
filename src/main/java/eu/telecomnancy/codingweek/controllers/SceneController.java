package eu.telecomnancy.codingweek.controllers;

import java.time.LocalDate;
import java.time.LocalTime;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;

import eu.telecomnancy.codingweek.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SceneController {

    // On a besoin de la primaryStage pour changer de scene
    // On définit toutes les scenes pour pouvoir définir les changements de scenes
    Stage primaryStage;
    Scene connexion;
    Scene inscription;
    Scene menu;
    Scene creationAnnonce;
    Scene mesAnnonces;
    Scene monProfil;
    Scene modifierProfil;
    Scene monAnnonce;
    Scene consulterannonce;
    Scene offres;
    Scene demandes;
    Scene calendar;
    Scene recherche;
    Scene modifierAnnonce;
    BorderPane layout;


    public SceneController(Stage primaryStage, Application app) throws Exception {

        this.primaryStage = primaryStage;

        this.layout = new BorderPane();
        layout.setMinSize(1000,750);

        // on charge toutes les scènes et on les associe à leur controller
        FXMLLoader pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("connexion.fxml"));
        pageLoader.setControllerFactory(iC->new ConnexionController(app));
        Scene pageScene = new Scene(pageLoader.load());
        this.connexion = pageScene;

        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("inscription.fxml"));
        pageLoader.setControllerFactory(iC->new InscriptionController(app));
        pageScene = new Scene(pageLoader.load());
        this.inscription = pageScene;

        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("creation_et_modificationAnnonce.fxml"));
        pageLoader.setControllerFactory(iC->new CreationEtModificationAnnonceController(app, "creation"));
        pageScene = new Scene(pageLoader.load());
        this.creationAnnonce = pageScene;

        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("menuBar.fxml"));
        pageLoader.setControllerFactory(iC->new MenuController(app));
        pageScene = new Scene(pageLoader.load());
        this.menu = pageScene;

        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("monProfil.fxml"));
        pageLoader.setControllerFactory(iC->new MonProfilController(app));
        pageScene = new Scene(pageLoader.load());
        this.monProfil = pageScene;

        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("modifierProfil.fxml"));
        pageLoader.setControllerFactory(iC->new ModifierProfilController(app));
        pageScene = new Scene(pageLoader.load());
        this.modifierProfil = pageScene;

        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("monAnnonce.fxml"));
        pageLoader.setControllerFactory(iC->new MonAnnonceController(app));
        pageScene = new Scene(pageLoader.load());
        this.monAnnonce = pageScene;  
        
        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("consulterAnnonce.fxml"));
        pageLoader.setControllerFactory(iC->new ConsulterAnnonceController(app));
        pageScene = new Scene(pageLoader.load());
        this.consulterannonce = pageScene;
        this.consulterannonce = pageScene;

        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("mesAnnonces.fxml"));
        pageLoader.setControllerFactory(iC->new MesAnnoncesController(app));
        pageScene = new Scene(pageLoader.load());
        this.mesAnnonces = pageScene;

        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("offres_et_demandes.fxml"));
        pageLoader.setControllerFactory(iC->new OffresEtDemandesController(app, "Offre"));
        pageScene = new Scene(pageLoader.load());
        this.offres = pageScene;

        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("offres_et_demandes.fxml"));
        pageLoader.setControllerFactory(iC->new OffresEtDemandesController(app, "Demande"));
        pageScene = new Scene(pageLoader.load());
        this.demandes = pageScene;

        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("recherche.fxml"));
        pageLoader.setControllerFactory(iC->new RechercheController(app));
        pageScene = new Scene(pageLoader.load());
        this.recherche = pageScene;

        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("creation_et_modificationAnnonce.fxml"));
        pageLoader.setControllerFactory(iC->new CreationEtModificationAnnonceController(app, "modification"));
        pageScene = new Scene(pageLoader.load());
        this.modifierAnnonce = pageScene;


        // on crée un calendrier
        CalendarView calendarView = new CalendarView(); // (1)
        calendarView.setShowAddCalendarButton(false);
        calendarView.setShowPrintButton(false);
        calendarView.setShowSearchField(false);
        calendarView.setShowAddCalendarButton(false);
        calendarView.setShowPageToolBarControls(false);
        Calendar birthdays = new Calendar("Rendez-vous"); // (2)
        Calendar holidays = new Calendar("Holidays");
        Entry<String> dentistAppointment = new Entry<>("Dentiste");
        dentistAppointment.setCalendar(birthdays);
        birthdays.setStyle(Calendar.Style.STYLE1); // (3)
        holidays.setStyle(Calendar.Style.STYLE2);
        CalendarSource myCalendarSource = new CalendarSource("My Calendars"); // (4)
        myCalendarSource.getCalendars().addAll(birthdays, holidays);
        calendarView.getCalendarSources().addAll(myCalendarSource); // (5)
        calendarView.setRequestedTime(LocalTime.now());

        Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
            @Override
            public void run() {
                while (true) {
                    Platform.runLater(() -> {
                        calendarView.setToday(LocalDate.now());
                        calendarView.setTime(LocalTime.now());
                    });

                    try {
                        // update every 10 seconds
                        sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };

        updateTimeThread.setPriority(Thread.MIN_PRIORITY);
        updateTimeThread.setDaemon(true);
        updateTimeThread.start();
        pageScene = new Scene(calendarView);
        this.calendar = pageScene;

        // on définit la scene de base : on affiche la page de connexion au lancement de l'application
        layout.setTop(menu.getRoot());
        setView(this.connexion);
        
        primaryStage.setScene(new Scene(layout));
        primaryStage.show();
    }

    public void setView(Scene scene){
        layout.setCenter(scene.getRoot());
    }


    // on définit les méthodes pour changer de scene
    public void switchToInscription() {
        setView(this.inscription);
    }

    public void switchToConnexion(String userName) {
        setView(this.connexion);
    }

    public void switchToConnexion() {
        setView(this.connexion);
    }

    public void switchToCreationAnnonce() {
        setView(this.creationAnnonce);
        }

    public void switchToMesAnnonces() {
        setView(this.mesAnnonces);
    }

    public void switchToMonProfil() {
        setView(this.monProfil);
    }


    public void switchToModifierProfil() {
        setView(this.modifierProfil);
    }


    public void switchToMonAnnonce() {
        setView(this.monAnnonce);
    }

    public void switchToConsulterAnnonce(int id) {
        setView(this.consulterannonce);
    }

    public void switchToOffres() {
        setView(this.offres);
    }

    public void switchToDemandes() {
        setView(this.demandes);
    }

    public void switchToRecherche() {
        setView(this.recherche);
    }

    public void switchToModifierAnnonce(){
        setView(this.modifierAnnonce);
    }

}
