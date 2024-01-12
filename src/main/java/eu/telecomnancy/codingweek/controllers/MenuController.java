package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.HBox;

public class MenuController implements Observer {


    @FXML
    private Label username;

    @FXML
    private Label solde;

    @FXML
    private HBox hboxSolde;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Label instruction;

    private final Application app;

    public MenuController(Application app) {
        this.app = app;
        app.addObserver(this);
    }

    @Override
    public void update(String type) {
        if (app.getMainUser() != null) {
            menuBar.setVisible(true);
            hboxSolde.setVisible(true);
            username.setText(app.getMainUser().getUserName());
            solde.setText(app.getMainUser().getSolde()+"");
            instruction.setText("");
        }
        else{
            menuBar.setVisible(false);
            hboxSolde.setVisible(false);
        }

    }

    // mettre le menu sur les scènes si on est connecté
    @FXML
    public void initialize() {
        menuBar.setVisible(false);
        hboxSolde.setVisible(false);

    }

    // switch vers la scène correspondant au bouton cliqué
    @FXML
    public void offres() {
        app.getSceneController().switchToOffres();
    }

    @FXML
    public void demandes() {
        app.getSceneController().switchToDemandes();
    }

    @FXML
    public void messagerie() {
        System.out.println("messagerie");
    }

    @FXML
    public void mesAnnonces() {
        app.notifyObservers("annonces");
        app.notifyObservers("transactions");
        app.getSceneController().switchToMesAnnonces();
    }

    @FXML
    public void monProfil() {
        app.getSceneController().switchToMonProfil();
    }

    @FXML
    public void deconnexion() {
        app.setMainUser(null);
        app.notifyObservers("connexion");
        app.getSceneController().switchToConnexion();
    }

    @FXML
    public void mesTransactions() {
        app.notifyObservers("transactions");
        app.getSceneController().switchToMesTransactions();
    }

    @FXML
    public void report() {
        app.getSceneController().switchToReportBug();
    }

    @FXML
    public void allReports() {
        app.getSceneController().switchToAllReports();
    }
}
