package org.drk.GestorVideojuegosHibernateJavaFX.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.drk.GestorVideojuegosHibernateJavaFX.session.AuthService;
import org.drk.GestorVideojuegosHibernateJavaFX.session.SimpleSessionService;
import org.drk.GestorVideojuegosHibernateJavaFX.user.User;
import org.drk.GestorVideojuegosHibernateJavaFX.user.UserRepository;
import org.drk.GestorVideojuegosHibernateJavaFX.utils.DataProvider;
import org.drk.GestorVideojuegosHibernateJavaFX.utils.JavaFXUtil;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @javafx.fxml.FXML
    private TextField txtContraseña;
    @javafx.fxml.FXML
    private TextField txtCorreo;
    @javafx.fxml.FXML
    private Label info;

    private UserRepository userRepository;
    private AuthService authService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userRepository = new UserRepository(DataProvider.getSessionFactory());
        authService = new AuthService(userRepository);
        System.out.println();
    }

    @javafx.fxml.FXML
    public void entrar(ActionEvent actionEvent) {
        Optional<User> user = authService.validateUser(txtCorreo.getText(),txtContraseña.getText() );
        if (user.isPresent()){
            SimpleSessionService sessionService = new SimpleSessionService();
            sessionService.login(user.get());
            sessionService.setObject("id", user.get().getId());
            JavaFXUtil.setScene("/org/drk/GestorVideojuegosHibernateJavaFX/main-view.fxml");
        }
    }

    @javafx.fxml.FXML
    public void Salir(ActionEvent actionEvent) {
        System.exit(0);
    }


}
