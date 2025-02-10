package com.example.tablapersonas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Button btnAgregar;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtEdad;
    @FXML
    private TableView<Persona> tblPersonas;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colApellidos;
    @FXML
    private TableColumn colEdad;

    private ObservableList<Persona> personas;
    private static final String RUTA_ARCHIVO = "personas.dat"; // Ruta del archivo

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        personas = FXCollections.observableArrayList();
        this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colApellidos.setCellValueFactory(new PropertyValueFactory("apellidos"));
        this.colEdad.setCellValueFactory(new PropertyValueFactory("edad"));

        // Cargar las personas desde el archivo al iniciar
        cargarPersonasDesdeArchivo();
    }

    // Cargar personas desde el archivo
    private void cargarPersonasDesdeArchivo() {
        List<Persona> listaPersonas = GestorBin.leer(RUTA_ARCHIVO);
        personas.addAll(listaPersonas); // Agregar las personas leídas a la lista Observable
        this.tblPersonas.setItems(personas); // Refrescar la tabla
    }

    @FXML
    private void agregarPersona(ActionEvent event) {
        try {
            String nombre = this.txtNombre.getText();
            String apellidos = this.txtApellidos.getText();
            int edad = Integer.parseInt(this.txtEdad.getText());

            Persona p = new Persona(nombre, apellidos, edad);

            if (!this.personas.contains(p)) {
                this.personas.add(p);
                GestorBin.add(RUTA_ARCHIVO, p); // Guardar la nueva persona en el archivo
                this.tblPersonas.setItems(personas);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("La persona existe");
                alert.showAndWait();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Formato incorrecto");
            alert.showAndWait();
        }
    }

    @FXML
    private void modificarPersona(ActionEvent event) {
        Persona p = this.tblPersonas.getSelectionModel().getSelectedItem();
        if (p == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debes seleccionar una persona");
            alert.showAndWait();
        } else {
            try {
                String nombre = this.txtNombre.getText();
                String apellidos = this.txtApellidos.getText();
                int edad = Integer.parseInt(this.txtEdad.getText());

                Persona aux = new Persona(nombre, apellidos, edad);

                if (!this.personas.contains(aux)) {
                    p.setNombre(aux.getNombre());
                    p.setApellidos(aux.getApellidos());
                    p.setEdad(aux.getEdad());

                    this.tblPersonas.refresh();
                    GestorBin.escribir(RUTA_ARCHIVO, this.personas); // Actualizar el archivo
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Info");
                    alert.setContentText("Persona modificada");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("La persona existe");
                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Formato incorrecto");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void borrarPersona(ActionEvent event) {
        Persona p = this.tblPersonas.getSelectionModel().getSelectedItem();
        if (p == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debes seleccionar una persona");
            alert.showAndWait();
        } else {
            this.personas.remove(p);
            GestorBin.eliminarPorPosicion(RUTA_ARCHIVO, this.personas.indexOf(p)); // Eliminar desde el archivo
            this.tblPersonas.refresh();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Info");
            alert.setContentText("Persona eliminada");
            alert.showAndWait();
        }
    }

    @FXML
    public void seleccionarPersona(javafx.scene.input.MouseEvent mouseEvent) {
        Persona p = this.tblPersonas.getSelectionModel().getSelectedItem();
        if (p != null) {
            this.txtNombre.setText(p.getNombre());
            this.txtApellidos.setText(p.getApellidos());
            this.txtEdad.setText(String.valueOf(p.getEdad()));
        }
    }
}
