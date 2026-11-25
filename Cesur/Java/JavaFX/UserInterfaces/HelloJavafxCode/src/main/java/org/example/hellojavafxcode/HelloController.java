package org.example.hellojavafxcode;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    private Button btnCancelar;

    @FXML
    private TextField txtCorreo;

    @FXML
    public void cancelar(ActionEvent actionEvent) {
        System.out.println("Cancelar");
    }

    @FXML
    public void continuar(ActionEvent actionEvent) {
        System.out.println("Continuar");
    }
}
