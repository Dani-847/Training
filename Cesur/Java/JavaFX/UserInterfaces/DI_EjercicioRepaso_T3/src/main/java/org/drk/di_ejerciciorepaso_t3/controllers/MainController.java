package org.drk.di_ejerciciorepaso_t3.controllers;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.SpinnerValueFactory.ListSpinnerValueFactory;
import org.drk.di_ejerciciorepaso_t3.contacto.Contacto;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TextField cCorreo;
    @FXML
    private TextField cPais;
    @FXML
    private Spinner<String> cPlataforma;
    @FXML
    private TableView<Contacto> tabla;
    @FXML
    private TableColumn<Contacto,String> cTablaCorreo;
    @FXML
    private TableColumn<Contacto,String> cTablaPais;
    @FXML
    private TableColumn<Contacto,String> cTablaPlataforma;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnSalir;
    @FXML
    private Label txtInfo;

    private final ObservableList<Contacto> contactos = FXCollections.observableArrayList();
    private final ObservableList<String> plataformas = FXCollections.observableArrayList(
            "Android","IOS","PC","MAC","Consola"
    );
    private final String PLATAFORMA_POR_DEFECTO = "Android";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarSpinner();
        configurarTabla();
        configurarEventos();
        refreshTable();
    }

    private void configurarSpinner() {
        var factory = new ListSpinnerValueFactory<>(plataformas);
        factory.setValue(PLATAFORMA_POR_DEFECTO); // valor por defecto
        cPlataforma.setValueFactory(factory);
        cPlataforma.setEditable(false);

        // Actualiza txtInfo solo cuando hay un valor (no poner "Bienvenido!" al limpiar)
        cPlataforma.valueProperty().addListener((obs, oldV, newV) -> {
            if (newV != null && !newV.isBlank()) {
                txtInfo.setText("Plataforma: " + newV);
            }
        });
    }

    private void configurarTabla() {
        cTablaCorreo.setCellValueFactory(row -> new SimpleStringProperty(row.getValue().getCorreo()));
        cTablaPais.setCellValueFactory(row -> new SimpleStringProperty(row.getValue().getPais()));
        cTablaPlataforma.setCellValueFactory(row -> new SimpleStringProperty(row.getValue().getPlataforma()));
        tabla.setItems(contactos);
        tabla.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    private void configurarEventos() {
        btnAgregar.setOnAction(this::agregar);
        btnSalir.setOnAction(this::salir);
        tabla.getSelectionModel().selectedItemProperty().addListener((obs, oldItem, newItem) -> {
            if (newItem != null) mostrarDetalle(newItem);
        });
    }

    private void mostrarDetalle(Contacto c) {
        String mensaje = "Correo: " + c.getCorreo() + "\n" +
                "País: " + c.getPais() + "\n" +
                "Plataforma: " + c.getPlataforma();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Detalle del contacto");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.initOwner(tabla.getScene().getWindow());
        alert.showAndWait();
    }

    private void refreshTable() {
        // ObservableList actualiza automáticamente; refrescar por seguridad visual
        tabla.refresh();
    }

    @FXML
    private void agregar(ActionEvent e) {
        String correo = safeText(cCorreo);
        String pais = safeText(cPais);
        String plataforma = cPlataforma.getValue() != null ? cPlataforma.getValue().trim() : "";

        if (correo.isEmpty() || pais.isEmpty() || plataforma.isEmpty()) {
            txtInfo.setText("Por favor ingrese el Correo");
            limpiarCampos(false);
            return;
        }
        if (!correo.contains("@")) {
            txtInfo.setText("El correo no es válido");
            limpiarCampos(false);
            return;
        }
        boolean existe = contactos.stream()
                .anyMatch(c -> c.getCorreo() != null && c.getCorreo().equalsIgnoreCase(correo));
        if (existe) {
            txtInfo.setText("El correo ya existe");
            limpiarCampos(false);
            return;
        }

        Contacto nuevo = new Contacto();
        nuevo.setCorreo(correo);
        nuevo.setPais(pais);
        nuevo.setPlataforma(plataforma);

        contactos.add(nuevo);
        txtInfo.setText("Contacto agregado correctamente");
        limpiarCampos(true); // mantener plataforma seleccionada por usabilidad
    }

    @FXML
    private void salir(ActionEvent e) {
        Platform.exit();
    }

    private void limpiarCampos(boolean mantenerPlataforma) {
        cCorreo.clear();
        cPais.clear();
        if (!mantenerPlataforma) {
            // restablecer al valor por defecto si se desea
            cPlataforma.getValueFactory().setValue(PLATAFORMA_POR_DEFECTO);
        }
    }

    private String safeText(TextField tf) {
        return tf.getText() != null ? tf.getText().trim() : "";
    }
}
