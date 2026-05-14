module com.ecdtms {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.ecdtms to javafx.fxml;
    opens com.ecdtms.controller to javafx.fxml;

    exports com.ecdtms;
}