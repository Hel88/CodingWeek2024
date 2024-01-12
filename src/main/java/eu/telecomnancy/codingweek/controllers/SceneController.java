package eu.telecomnancy.codingweek.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.CalendarView;

import eu.telecomnancy.codingweek.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    Scene consulterAnnonce;
    Scene offres;
    Scene demandes;
    Scene calendar;
    Scene recherche;
    Scene modifierAnnonce;
    Scene mesTransactions;
    Scene noterUser;
    Scene userEvaluations;
    Scene reportBug;
    Scene reports;
    Scene consulterReport;
    Scene messageries;
    Scene consulterMessagerie;
    BorderPane layout;
    CalendarView calendarView;
    CalendarSource myCalendarSource;
    List<Integer> calendarList = new java.util.ArrayList<Integer>();
    Calendar currentCalendar;
    Calendar defaultCalendar;
    List<Calendar> currentCalendarList = new ArrayList<>();

    Application app;

    public SceneController(Stage primaryStage, Application app) throws Exception {

        this.app = app;

        this.primaryStage = primaryStage;
        
        primaryStage.setTitle("Telecom Nancy Direct Dealing");
        primaryStage.initStyle(StageStyle.DECORATED);
        //primaryStage.setFullScreen(true);
        this.layout = new BorderPane();
        layout.setMinSize(800, 700);

        //  // Récupérer les dimensions de l'écran principal
        // Screen screen = Screen.getPrimary();
        // double screenWidth = screen.getBounds().getWidth();
        // double screenHeight = screen.getBounds().getHeight();

        // // Régler la taille de la fenêtre en fonction des dimensions de l'écran
        // primaryStage.setWidth(screenWidth * 0.8); // Vous pouvez ajuster le facteur de mise à l'échelle selon vos besoins
        // primaryStage.setHeight(screenHeight * 0.8);
        

        // on charge toutes les scènes et on les associe à leur controller
        FXMLLoader pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("connexion.fxml"));
        pageLoader.setControllerFactory(iC -> new ConnexionController(app));
        Scene pageScene = new Scene(pageLoader.load());
        this.connexion = pageScene;

        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("inscription.fxml"));
        pageLoader.setControllerFactory(iC -> new InscriptionController(app));
        pageScene = new Scene(pageLoader.load());
        this.inscription = pageScene;

        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("creation_et_modificationAnnonce.fxml"));
        pageLoader.setControllerFactory(iC -> new CreationEtModificationAnnonceController(app, "creation"));
        pageScene = new Scene(pageLoader.load());
        this.creationAnnonce = pageScene;

        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("menuBar.fxml"));
        pageLoader.setControllerFactory(iC -> new MenuController(app));
        pageScene = new Scene(pageLoader.load());
        this.menu = pageScene;

        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("monProfil.fxml"));
        pageLoader.setControllerFactory(iC -> new MonProfilController(app));
        pageScene = new Scene(pageLoader.load());
        this.monProfil = pageScene;

        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("modifierProfil.fxml"));
        pageLoader.setControllerFactory(iC -> new ModifierProfilController(app));
        pageScene = new Scene(pageLoader.load());
        this.modifierProfil = pageScene;

        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("monAnnonce.fxml"));
        pageLoader.setControllerFactory(iC -> new MonAnnonceController(app));
        pageScene = new Scene(pageLoader.load());
        this.monAnnonce = pageScene;

        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("consulterAnnonce.fxml"));
        pageLoader.setControllerFactory(iC -> new ConsulterAnnonceController(app));
        pageScene = new Scene(pageLoader.load());
        this.consulterAnnonce = pageScene;

        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("mesAnnonces.fxml"));
        pageLoader.setControllerFactory(iC -> new MesAnnoncesController(app));
        pageScene = new Scene(pageLoader.load());
        this.mesAnnonces = pageScene;

        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("offres_et_demandes.fxml"));
        pageLoader.setControllerFactory(iC -> new OffresEtDemandesController(app, "Offre"));
        pageScene = new Scene(pageLoader.load());
        this.offres = pageScene;

        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("offres_et_demandes.fxml"));
        pageLoader.setControllerFactory(iC -> new OffresEtDemandesController(app, "Demande"));
        pageScene = new Scene(pageLoader.load());
        this.demandes = pageScene;

        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("mesTransactions.fxml"));
        pageLoader.setControllerFactory(iC -> new MesTransactionsController(app));
        pageScene = new Scene(pageLoader.load());
        this.mesTransactions = pageScene;

        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("recherche.fxml"));
        pageLoader.setControllerFactory(iC -> new RechercheController(app));
        pageScene = new Scene(pageLoader.load());
        this.recherche = pageScene;

        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("creation_et_modificationAnnonce.fxml"));
        pageLoader.setControllerFactory(iC -> new CreationEtModificationAnnonceController(app, "modification"));
        pageScene = new Scene(pageLoader.load());
        this.modifierAnnonce = pageScene;

        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("noterUser.fxml"));
        pageLoader.setControllerFactory(iC -> new NoterUserController(app));
        pageScene = new Scene(pageLoader.load());
        this.noterUser = pageScene;

        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("mesTransactions.fxml"));
        pageLoader.setControllerFactory(iC -> new MesTransactionsController(app));
        pageScene = new Scene(pageLoader.load());
        this.mesTransactions = pageScene;

        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("userEvaluations.fxml"));
        pageLoader.setControllerFactory(iC -> new UserEvaluationsController(app));
        pageScene = new Scene(pageLoader.load());
        this.userEvaluations = pageScene;

        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("pageReport.fxml"));
        pageLoader.setControllerFactory(iC->new ReportBugController(app));
        pageScene = new Scene(pageLoader.load());
        this.reportBug = pageScene;

        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("reports.fxml"));
        pageLoader.setControllerFactory(iC->new ReportsController(app));
        pageScene = new Scene(pageLoader.load());
        this.reports = pageScene;

        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("consulterReport.fxml"));
        pageLoader.setControllerFactory(iC->new ConsulterReportController(app));
        pageScene = new Scene(pageLoader.load());
        this.consulterReport = pageScene;

        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("mesConversations.fxml"));
        pageLoader.setControllerFactory(iC->new MesConversationController(app));
        pageScene = new Scene(pageLoader.load());
        this.messageries = pageScene;

        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("consulterConversation.fxml"));
        pageLoader.setControllerFactory(iC->new ConsulterConversationController(app));
        pageScene = new Scene(pageLoader.load());
        this.consulterMessagerie = pageScene;

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

        primaryStage.setTitle("TelecomNancy DirectDealing");
        primaryStage.setScene(new Scene(layout));
        primaryStage.show();
    }

    public void setView(Scene scene) {
        layout.setCenter(scene.getRoot());
    }

    public List<Integer> getCalendarList() {
        return calendarList;
    }


    // on définit les méthodes pour changer de scene
    public void switchToInscription() {
        setView(this.inscription);
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

    public void switchToAllReports() {
        setView(this.reports);
    }

    public void switchToModifierProfil() {
        setView(this.modifierProfil);
    }

    public void switchToMesTransactions() {
        setView(this.mesTransactions);
    }

    public void switchToMonAnnonce() {
        setView(this.monAnnonce);
    }

    public void switchToConsulterAnnonce() {
        setView(this.consulterAnnonce);
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

    public void switchToModifierAnnonce() {
        setView(this.modifierAnnonce);
    }

    public void switchToNoterUser() {
        setView(this.noterUser);
    }

    public void switchToUserEvaluations() {
        setView(this.userEvaluations);
    }

    public void switchToReportBug() {
        setView(this.reportBug);
    }

    public void switchToConsulterReport(){
        setView(this.consulterReport);
    }

    public void switchToMessageries(){
        setView(this.messageries);
    }

    public void switchToConsulterMessagerie(){
        setView(this.consulterMessagerie);
    }

    public void switchToCalendar() {
        calendarView.setRequestedTime(LocalTime.now());
        calendarView.getCalendarSources().addAll(myCalendarSource);
        app.notifyObservers("calendrierValide");
        setView(this.calendar);
    }

    public List<Calendar> getCurrentCalendarList() {
        return currentCalendarList;
    }

    public CalendarSource getMyCalendarSource() {
        return myCalendarSource;
    }

    public CalendarView getCalendarView() {
        return calendarView;
    }

    public Calendar getCurrentCalendar() {
        return currentCalendar;
    }

    public Calendar getDefaultCalendar() {
        return defaultCalendar;
    }

    public void setDefaultCalendar(Calendar calendar) {
        defaultCalendar = calendar;
    }

    public void setCurrentCalendar(Calendar calendar) {
        currentCalendar = calendar;
    }

    public void setCurrentCalendarList(List<Calendar> calendars) {
        currentCalendarList = calendars;
    }

    public void getCurrentCalendarList(List<Calendar> calendars) {
        currentCalendarList = calendars;
    }
}
