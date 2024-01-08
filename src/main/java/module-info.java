module eu.telecomnancy.codingweek {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires jbcrypt;


    opens eu.telecomnancy.codingweek to javafx.fxml;
    exports eu.telecomnancy.codingweek;
}