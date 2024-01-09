module eu.telecomnancy.codingweek {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires com.calendarfx.view;

    requires transitive javafx.graphics;

    opens eu.telecomnancy.codingweek to javafx.fxml;
    exports eu.telecomnancy.codingweek;
    exports eu.telecomnancy.codingweek.controllers;
    opens eu.telecomnancy.codingweek.controllers to javafx.fxml;
    exports eu.telecomnancy.codingweek.utils;
    opens eu.telecomnancy.codingweek.utils to javafx.fxml;
}