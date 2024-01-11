package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;
import eu.telecomnancy.codingweek.utils.DataReportUtils;
import eu.telecomnancy.codingweek.global.Report;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

        public void consulterReport(Report report){
            //on enregistre l'annonce à afficher pour y avoir accès dans ConsulterAnnonceController
            app.setReportAffiche(report);
            app.notifyObservers("report");
            app.getSceneController().switchToConsulterReport();
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


                HBox hboxReport = new HBox();

                hboxReport.setPrefWidth(100);
                hboxReport.setPrefHeight(10);

                Label time = new Label(report.getTime());
                time.setWrapText(true);
                hboxReport.getChildren().add(time);

                hboxReport.getChildren();

                Button details = new Button();
                details.setText("Voir les détails");
                details.setOnAction(e->{consulterReport(report);});
                hbox.getChildren().addAll(titre, referent, hboxReport, details);

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
