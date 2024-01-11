package eu.telecomnancy.codingweek.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.CalendarView;
import com.calendarfx.view.DateControl;


import eu.telecomnancy.codingweek.Application;
import eu.telecomnancy.codingweek.utils.DataCalendarUtils;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;

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
    Scene mesTransactions;
    Scene noterUser;
    BorderPane layout;
    CalendarView calendarView;
    CalendarSource myCalendarSource;
    List<Integer> calendarList = new java.util.ArrayList<Integer>();
    Calendar currentCalendar;
    Calendar defaultCalendar;
    List<Calendar> currentCalendarList = new ArrayList<>();


    public SceneController(Stage primaryStage, Application app) throws Exception {

        this.primaryStage = primaryStage;

        this.layout = new BorderPane();
        layout.setMinSize(1000,850);

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
        pageLoader.setLocation(getClass().getResource("mesTransactions.fxml"));
        pageLoader.setControllerFactory(iC->new MesTransactionsController(app));
        pageScene = new Scene(pageLoader.load());
        this.mesTransactions = pageScene;

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

        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("noterUser.fxml"));
        pageLoader.setControllerFactory(iC->new NoterUserController(app));
        pageScene = new Scene(pageLoader.load());
        this.noterUser = pageScene;


        // on crée un calendrier
        calendarView = new CalendarView(); // (1)
        calendarView.setShowAddCalendarButton(false);
        calendarView.setShowPrintButton(false);
        calendarView.setShowSearchField(false);
        calendarView.setShowAddCalendarButton(false);
        calendarView.setShowPageToolBarControls(false);
//        calendarView.setShowSourceTray(false);
//        calendarView.setShowSourceTrayButton(false);

        myCalendarSource = new CalendarSource("My Calendars");
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

    public List<Integer> getCalendarList(){
        return calendarList;
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

    public void switchToMesTransactions(){
        setView(this.mesTransactions);
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

    public void switchToNoterUser(String idUser){
        setView(this.noterUser);
    }

    public void calendarSave() throws IOException {
        if(currentCalendarList != null) {
            DataCalendarUtils dataCalendarUtils = DataCalendarUtils.getInstance();
            for(Calendar calendar : currentCalendarList) {
                dataCalendarUtils.store(calendar);
            }
        }
    }

    public void calendarSwitchPreparation() throws IOException {
        calendarSave();

        myCalendarSource.getCalendars().clear();
        calendarView.getCalendarSources().clear();
        calendarView.setDefaultCalendarProvider(new Callback<DateControl, Calendar>() {
            @Override
            public Calendar call(DateControl param) {
                return null;
            }
        });
    }

    public void calendarSwitchSetCurrentCalendarToDefault() throws IOException {
        defaultCalendar = currentCalendar;
        calendarView.setDefaultCalendarProvider(new Callback<DateControl, Calendar>() {
            @Override
            public Calendar call(DateControl param) {
                return defaultCalendar;
            }
        });
    }

    public void calendarSwitchAddCalendar(int id, boolean save) throws IOException {
        Calendar calendar = DataCalendarUtils.getInstance().load(id);
        calendarList.add(id);

        currentCalendar = calendar;
        DataCalendarUtils.getInstance().reload(currentCalendar);

        if (save) {
            currentCalendarList.add(currentCalendar);
        }

        myCalendarSource.getCalendars().addAll(currentCalendar);
    }

    public void calendarSwitchAddCalendarWithStyle(int id, Calendar.Style style, boolean save) throws IOException {
        Calendar calendar = DataCalendarUtils.getInstance().load(id);
        calendar.setStyle(style);
        calendarList.add(id);

        currentCalendar = calendar;
        DataCalendarUtils.getInstance().reload(currentCalendar);

        if (save) {
            currentCalendarList.add(currentCalendar);
        }

        myCalendarSource.getCalendars().addAll(currentCalendar);
    }

    public void switchToCalendar() throws IOException {
        calendarView.setRequestedTime(LocalTime.now());
        calendarView.getCalendarSources().addAll(myCalendarSource);
        setView(this.calendar);
    }

    public Calendar getCurrentCalendar() {
        return currentCalendar;
    }

}
