package com.dam.di.appsesionesfotos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class FotoController {

    public Button reservarRetrato;
    public Button cancelarRetrato;
    public MenuItem mi1Per;
    public MenuItem mi2Per;
    public SplitMenuButton lugar;
    public MenuItem miEstudio;
    public MenuItem miEscenario;

    @FXML public SplitMenuButton numPersonas;
    public SplitMenuButton cbVistaTabla;
    public MenuItem opRetratos;
    public MenuItem opAnimales;
    public MenuItem opEventos;
    public MenuItem opCoches;
    public MenuItem opTodo;

    @FXML private TableView<Sesion> tablaSesiones;
    @FXML private TableColumn<Sesion, String> colTipo;
    @FXML private TableColumn<Sesion, Integer> colNumPersonas;
    @FXML private TableColumn<Sesion, String> colOtro;
    @FXML private TableColumn<Sesion, Double> colPrecio;

    @FXML private PasswordField tfPassw;
    @FXML private TextField tfUser;
    @FXML private PasswordField tfNewPassw;
    @FXML private TextField tfNewUser;
    @FXML private ComboBox<String> miComboBox;
    @FXML public TextField tfPrecioRetrato;

    public Button btnCrearUser;
    public Button btnAcceder;
    public Button btnLogin;
    public Button btnAlta;
    public Button btnRetratos;
    public Button btnAnimales;
    public Button btnEventos;
    public Button btnCoche;
    public Button btnSesiones;

    private int numPers = 0;
    private int escenario = 0;

    private final ObservableList<Sesion> sesionesList = FXCollections.observableArrayList();


    private ArrayList<Usuario> listaUsuarios = new ArrayList<>();

    public void initialize() {
        listaUsuarios = UsuarioManager.getInstance().getListaUsuarios();
        Usuario admin = new Usuario("admin", "1234");
        listaUsuarios.add(admin);
        
       /* colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colNumPersonas.setCellValueFactory(new PropertyValueFactory<>("numPersonas"));
        colOtro.setCellValueFactory(new PropertyValueFactory<>("otro"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        tablaSesiones.setItems(sesionesList);

        */
    }

    // Métodos de navegación
    public void acceso(ActionEvent event) {
        abrirVentanaCerrarOtra(event, "UILogin/uiLogin.fxml", "Login", 280, 250);
    }

    public void abrirVentanaAlta(ActionEvent event) {
        abrirVentanaCerrarOtra(event, "UIAlta/uiAlta.fxml", "Nuevo Usuario", 280, 250);
    }

    public void login(ActionEvent event) {
        String user = tfUser.getText();
        String passw = tfPassw.getText();
        boolean accesoCorrecto = false;

        for (Usuario u : listaUsuarios) {
            if (user.equals(u.getUser()) && passw.equals(u.getPassw())) {
                accesoCorrecto = true;
                break;
            }
        }

        if (accesoCorrecto) {
            accesoPermitido("Acceso Correcto", "¡Acceso permitido!");
            abrirVentanaCerrarOtra(event, "UISesiones/uiSesiones.fxml", "Sesiones", 400, 500);
        } else {
            datosIncorrectos("Acceso Incorrecto", "Usuario o contraseña incorrectos");
        }
    }

    public void listarSesiones(ActionEvent event) {
        abrirVentanaCerrarOtra(event, "UIListado/uiListado.fxml", "Sesiones programadas", 400, 400);
    }

    public void retratos(ActionEvent event) {
        abrirVentanaCerrarOtra(event, "UISesiones/UIRetratos/uiRetrato.fxml", "Reservar Sesión Retratos", 400, 500);
    }

    public void animales(ActionEvent event) { }
    public void eventos(ActionEvent event) { }
    public void coches(ActionEvent event) { }

    private void actualizarPrecioRetrato() {
        tfPrecioRetrato.setText(String.valueOf((numPers - 1) * 10 + escenario));
    }

    public void unaPersona(ActionEvent event) {
        numPers = 1;
        actualizarPrecioRetrato();
    }

    public void dosPersonas(ActionEvent event) {
        numPers = 2;
        actualizarPrecioRetrato();
    }

    public void estudio(ActionEvent event) {
        escenario = 40;
        actualizarPrecioRetrato();
    }

    public void escenario(ActionEvent event) {
        escenario = 60;
        actualizarPrecioRetrato();
    }

    public void cancelarRetrato(ActionEvent event) {
        abrirVentanaCerrarOtra(event, "UISesiones/uiSesiones.fxml", "Sesiones", 400, 500);
    }

    public void reservarRetrato(ActionEvent event) {
        Sesion nuevaSesion = new Sesion("Retrato", numPers, escenario == 40 ? "Estudio" : "Escenario a elegir", (numPers - 1) * 10 + escenario);
        sesionesList.add(nuevaSesion);
    }

    public void crearUser(ActionEvent event) {
        String user = tfNewUser.getText();
        String passw = tfNewPassw.getText();
        boolean yaExiste = false;

        for (Usuario u : listaUsuarios) {
            if (user.equals(u.getUser())) {
                yaExiste = true;
                break;
            }
        }

        if (yaExiste)
            datosIncorrectos("Error", "El usuario ya existe");
        else if (user.isEmpty())
            datosIncorrectos("Error", "Introduzca un nombre de usuario");
        else if (passw.isEmpty())
            datosIncorrectos("Error", "Introduzca una contraseña");
        else {
            UsuarioManager.getInstance().addUsuario(new Usuario(user, passw));
            accesoPermitido("Usuario Creado", "El nuevo usuario ha sido creado exitosamente.");
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        }
    }

    public void abrirVentana(String urlFxml, String titulo, int width, int height) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(urlFxml));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), width, height);
            stage.setTitle(titulo);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void abrirVentanaCerrarOtra(ActionEvent event, String urlFxml, String titulo, int width, int height) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(urlFxml));
            Stage newStage = new Stage();
            Scene newScene = new Scene(fxmlLoader.load(), width, height);
            newStage.setTitle(titulo);
            newStage.setScene(newScene);
            newStage.show();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void accesoPermitido(String titulo, String mensaje) {
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle(titulo);
        successAlert.setHeaderText(null);
        successAlert.setContentText(mensaje);
        successAlert.showAndWait();
    }

    private void datosIncorrectos(String titulo, String mensaje) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(titulo);
        errorAlert.setHeaderText(null);
        errorAlert.setContentText(mensaje);
        errorAlert.showAndWait();
    }

    public void opRetratos(ActionEvent event) {
    }

    public void opAnimales(ActionEvent event) {
    }

    public void opEventos(ActionEvent event) {
    }

    public void opCoches(ActionEvent event) {
    }

    public void opTodo(ActionEvent event) {
    }
}
