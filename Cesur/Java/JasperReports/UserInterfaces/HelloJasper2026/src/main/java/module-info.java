module org.drk.hellojasper2026 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires net.sf.jasperreports.core;


    opens org.drk.hellojasper2026 to javafx.fxml;
    exports org.drk.hellojasper2026;
}