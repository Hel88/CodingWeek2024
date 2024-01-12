package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;
import eu.telecomnancy.codingweek.global.Report;
import eu.telecomnancy.codingweek.global.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.Objects;

public class UnReportController implements Observer {

    private final Application app;
    private Report report;

    @FXML
    private Label userName;
    @FXML
    private Label message;
    @FXML
    private Label date;
    @FXML
    private Label email;
    @FXML
    private Label fullName;


    public UnReportController(Application app) {
        this.app = app;
        app.addObserver(this);
    }

    public void initialize() {
        report = app.getReportAffiche();
        if (report == null) {
            return;
        }
        userName.setText(report.getReferent());
        message.setText(report.getMessage());
        date.setText(report.getTime());

        if (userName.getText().isEmpty()) {
            userName.setText("Anonyme");
            email.setText("Anonyme");
            fullName.setText("Anonyme");
            return;
        }

        try {
            User user = app.getDataUsersUtils().getUserByUserName(report.getReferent());
            email.setText(user.getEmail());
            fullName.setText(user.getFirstName() + " " + user.getLastName() + " (" + user.getUserName() + ")");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String str) {
        if (Objects.equals(str, "report")) {
            initialize();
        }
    }
}
