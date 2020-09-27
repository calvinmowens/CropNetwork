module harryVersion.crop.network {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.base;

    opens main.controllers to javafx.fxml;
    exports main;
}