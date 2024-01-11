package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReportBugController implements Observer {

    private final Application app;

    @FXML
    private TextArea message;


    public ReportBugController(Application app) {
        this.app = app;
        app.addObserver(this);
    }

    public void update(String str) {}

    @FXML
    public void sendReport() throws IOException {
        try {
            String username = app.getMainUser().getUserName();
            app.getDataReportUtils().addReport(username, message.getText(), date());
        }
        catch (Exception e) {
            String username = "";
            app.getDataReportUtils().addReport(username, message.getText(), date());
        }

        if (app.getMainUser() != null) {
            app.getSceneController().switchToMonProfil();
        } else {
            app.getSceneController().switchToConnexion();
        }
    }

    private String date() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(cal.getTime());
    }
}
