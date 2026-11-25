module org.example.hellojavafxcode {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.hellojavafxcode to javafx.fxml;
    exports org.example.hellojavafxcode;
}