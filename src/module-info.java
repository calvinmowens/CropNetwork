module cs2340.team44.m2 {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.base;
    opens main.controllers to javafx.fxml;
    exports main;
}