package eu.telecomnancy.codingweek;

import eu.telecomnancy.codingweek.controllers.Observer;
import eu.telecomnancy.codingweek.controllers.SceneController;
import eu.telecomnancy.codingweek.global.*;
import eu.telecomnancy.codingweek.utils.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Application extends javafx.application.Application {

    private static SceneController sceneController;
    private DataUsersUtils dataUsersUtils;
    private DataAnnoncesUtils dataAnnoncesUtils;
    private DataTransactionUtils dataTransactionUtils;
    private DataNoteUtils dataNoteUtils;
    private DataReportUtils dataReportUtils;
    private DataMessagesUtils dataMessagesUtils;
    private DataConversationsUtils dataConversationsUtils;
    private User mainUser;
    private final ArrayList<Observer> observers = new ArrayList<Observer>();
    private Annonce annonceAffichee;
    private Report reportAffiche;
    private Conversations conversationsAffichee;
    private String categorieAnnonceACreer;


    @Override
    public void start(Stage stage) {
        try {
            sceneController = new SceneController(stage, this);
            this.dataUsersUtils = DataUsersUtils.getInstance();
            this.dataAnnoncesUtils = DataAnnoncesUtils.getInstance();
            this.dataTransactionUtils = DataTransactionUtils.getInstance();
            this.dataNoteUtils = DataNoteUtils.getInstance();
            this.dataReportUtils = DataReportUtils.getInstance();
            this.mainUser = null;
            this.dataMessagesUtils = DataMessagesUtils.getInstance();
            this.dataConversationsUtils = DataConversationsUtils.getInstance();
        } catch (Exception e) {
            System.out.println("Error while loading the scene controller");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        launch();
        CalendarDisplay calendarDisplay = new CalendarDisplay(sceneController);
        calendarDisplay.calendarSave();
    }

    public SceneController getSceneController() {
        return sceneController;
    }
    public DataUsersUtils   getDataUsersUtils() {
        return dataUsersUtils;
    }
    public DataAnnoncesUtils getDataAnnoncesUtils() {
        return dataAnnoncesUtils;
    }
    public DataTransactionUtils getDataTransactionUtils() {
        return dataTransactionUtils;
    }
    public DataNoteUtils getDataNoteUtils() {
        return dataNoteUtils;
    }
    public DataReportUtils getDataReportUtils() {
        return dataReportUtils;
    }
    public DataMessagesUtils getDataMessagesUtils() {
        return dataMessagesUtils;
    }
    public DataConversationsUtils getDataConversationsUtils() {
        return dataConversationsUtils;
    }

    public User getMainUser() {
        return mainUser;
    }

    public void setMainUser(User mainUser) {
        this.mainUser = mainUser;
    }

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers(String type) {
        for (Observer obs : observers) {
            obs.update(type);
        }
    }

    public Annonce getAnnonceAffichee() {
        return annonceAffichee;
    }

    public void setAnnonceAffichee(Annonce annonceAffichee) {
        this.annonceAffichee = annonceAffichee;
    }

    public String getCategorieAnnonceACreer() {
        return categorieAnnonceACreer;
    }

    public void setCategorieAnnonceACreer(String categorieAnnonceACreer) {
        this.categorieAnnonceACreer = categorieAnnonceACreer;
    }

    public Report getReportAffiche() {
        return reportAffiche;
    }

    public void setReportAffiche(Report reportAffiche) {
        this.reportAffiche = reportAffiche;
    }

    public Conversations getConversationsAffichee() {
        return conversationsAffichee;
    }

    public void setConversationsAffichee(Conversations conversationsAffichee) {
        this.conversationsAffichee = conversationsAffichee;
    }
}