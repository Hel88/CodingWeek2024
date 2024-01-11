package eu.telecomnancy.codingweek;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import eu.telecomnancy.codingweek.controllers.Observer;
import eu.telecomnancy.codingweek.controllers.SceneController;
import eu.telecomnancy.codingweek.utils.Annonce;
import eu.telecomnancy.codingweek.utils.DataAnnoncesUtils;
import eu.telecomnancy.codingweek.utils.DataCalendarUtils;
import eu.telecomnancy.codingweek.utils.DataTransactionUtils;
import eu.telecomnancy.codingweek.utils.DataUsersUtils;
import eu.telecomnancy.codingweek.utils.User;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    private static SceneController sceneController;
    private DataUsersUtils dataUsersUtils;
    private DataAnnoncesUtils dataAnnoncesUtils;
    private DataTransactionUtils dataTransactionUtils;
    private User mainUser;
    private final ArrayList<Observer> observers = new ArrayList<Observer>();
    private Annonce annonceAffichee;
    private String categorieAnnonceACreer;


    @Override
    public void start(Stage stage) {
        try {
            sceneController = new SceneController(stage, this);
            this.dataUsersUtils = DataUsersUtils.getInstance();
            this.dataAnnoncesUtils = DataAnnoncesUtils.getInstance();
            this.dataTransactionUtils = DataTransactionUtils.getInstance();
            this.mainUser = null;
        } catch (Exception e) {
            System.out.println("Error while loading the scene controller");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        launch();
        if(sceneController.getCurrentCalendar() != null) {
            DataCalendarUtils dataCalendarUtils = DataCalendarUtils.getInstance();
            dataCalendarUtils.store(sceneController.getCurrentCalendar());
        }
        List<Integer> calendars = sceneController.getCalendarList();
        System.out.println(calendars);
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
}