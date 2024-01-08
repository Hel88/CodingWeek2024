module eu.telecomnancy.codingweek {
    requires javafx.controls;
    requires javafx.fxml;


    opens eu.telecomnancy.codingweek to javafx.fxml;
    exports eu.telecomnancy.codingweek;
}