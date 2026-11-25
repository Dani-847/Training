module org.drk.GestorVideojuegosHibernateJavaFX {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;

    opens org.drk.GestorVideojuegosHibernateJavaFX.user;
    opens org.drk.GestorVideojuegosHibernateJavaFX;
    exports org.drk.GestorVideojuegosHibernateJavaFX;
    exports org.drk.GestorVideojuegosHibernateJavaFX.game;
    opens org.drk.GestorVideojuegosHibernateJavaFX.game;
    exports org.drk.GestorVideojuegosHibernateJavaFX.utils;
    opens org.drk.GestorVideojuegosHibernateJavaFX.utils;
    exports org.drk.GestorVideojuegosHibernateJavaFX.controllers;
    opens org.drk.GestorVideojuegosHibernateJavaFX.controllers;
}