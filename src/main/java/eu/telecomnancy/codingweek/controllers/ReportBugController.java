package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ReportBugController implements Observer {

    private final Application app;

    @FXML
    private TextArea message;


    public ReportBugController(Application app) {
        this.app = app;
    }

    public void update(String str) {}

    @FXML
    public void sendReport() {
        app.getSceneController().switchToConnexion();
    }
}
