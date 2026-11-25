module org.drk.di_ejerciciorepaso_t3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;

    opens org.drk.di_ejerciciorepaso_t3;
    exports org.drk.di_ejerciciorepaso_t3;
    exports org.drk.di_ejerciciorepaso_t3.controllers;
    opens org.drk.di_ejerciciorepaso_t3.controllers;
    exports org.drk.di_ejerciciorepaso_t3.contacto;
    opens org.drk.di_ejerciciorepaso_t3.contacto;
}
