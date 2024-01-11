package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;
import eu.telecomnancy.codingweek.utils.DataReportUtils;
import eu.telecomnancy.codingweek.utils.Report;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class ShowReportsController implements Observer {


        private Application app;
        private ArrayList<Report> reports;

        @FXML
        private VBox signalements;
        @FXML
        private Label boxLabel;


        public ShowReportsController(Application app) {
            this.app = app;
            app.addObserver(this);
        }

        public void initialize() throws IOException {
            reports = new ArrayList<Report>();

            //vider les VBox pour éviter les doublons
            signalements.getChildren().clear();

            //récupérer les annonces
            try {
                this.reports = DataReportUtils.getInstance().getReports(app.getMainUser());
            } catch (IOException e) {
                // Do nothing
            }

            boxLabel.setText("Reports");

            for (Report report : reports) {
                HBox hbox = new HBox();
                hbox.setStyle("-fx-background-color: #eeeeee; prefHeight:\"279.0\"");

                Label titre = new Label(report.getMessage());
                titre.setPrefWidth(200);
                titre.setPrefHeight(10);
                titre.setWrapText(true);

                Label referent = new Label(report.getReferent());
                referent.setPrefWidth(200);
                referent.setPrefHeight(10);
                referent.setWrapText(true);


                HBox hboxPrix = new HBox();

                hboxPrix.setPrefWidth(100);
                hboxPrix.setPrefHeight(10);

                Label prix = new Label(report.getTime());
                prix.setWrapText(true);
                hboxPrix.getChildren().add(prix);

                hboxPrix.getChildren();
                hbox.getChildren().addAll(titre, referent, hboxPrix);

                signalements.getChildren().add(hbox);
            }

        }

        @Override
        public void update(String type) {
            try {
                initialize();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
}
