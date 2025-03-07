package com.dam.di.registrologinusuariosmgr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class HelloController {

    @FXML
    private Button btnSalir;

    @FXML
    private TextField txtUsuarioLogin, txtVisiblePasswordLogin, txtUsuarioAlta, txtVisiblePasswordAlta, txtVisibleConfirmarPasswordAlta;

    @FXML
    private PasswordField txtPasswordLogin, txtPasswordAlta, txtConfirmarPasswordAlta;

    @FXML
    private CheckBox chkVerContraLogin, chkVerContraAlta, chkVerContraAlta2;

    private UsersPasswordsData data;

    @FXML
    public void initialize() {
        try {
            data = new UsersPasswordsData();
            data.setController(this);
        } catch (IOException | ClassNotFoundException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudieron cargar los datos de usuario.");
        }
    }

    public void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public void acceder(ActionEvent actionEvent) {
        String username = txtUsuarioLogin.getText();
        String password = txtPasswordLogin.getText();
        try {
            int result = data.access(username, password);
            if (result == 1) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Acceso exitoso", "Bienvenido, " + username + "!");
                txtUsuarioLogin.clear();
                txtPasswordLogin.clear();
                txtVisiblePasswordLogin.clear();
                chkVerContraLogin.setSelected(false);
            } else if (result == 0) {
                mostrarAlerta(Alert.AlertType.ERROR, "Error de acceso", "Contraseña incorrecta.");
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error de acceso", "El usuario no existe.");
            }
        } catch (NoSuchAlgorithmException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Error al acceder al usuario: " + e.getMessage());
        }
    }


    public void confirmarAlta(ActionEvent actionEvent) {
        String username = txtUsuarioAlta.getText();
        String password = txtPasswordAlta.getText();
        String confirmPassword = txtConfirmarPasswordAlta.getText();

        if (username.length() < UsersPasswordsData.MIN_CHARS || username.length() > UsersPasswordsData.MAX_CHARS) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de registro", "El nombre de usuario debe tener entre " + UsersPasswordsData.MIN_CHARS + " y " + UsersPasswordsData.MAX_CHARS + " caracteres.");
            return;
        }
        if (password.length() < UsersPasswordsData.MIN_CHARS || password.length() > UsersPasswordsData.MAX_CHARS) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de registro", "La contraseña debe tener entre " + UsersPasswordsData.MIN_CHARS + " y " + UsersPasswordsData.MAX_CHARS + " caracteres.");
            return;
        }
        if (!password.equals(confirmPassword)) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de registro", "Las contraseñas no coinciden.");
            return;
        }
        if (!validarContraseña(password)) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de registro", "La contraseña debe contener al menos una mayúscula y un dígito.");
            return;
        }

        try {
            String hashedPassword = UsersPasswordsData.codificar(password);

            boolean added = data.addUser(username, hashedPassword);

            if (added) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Registro exitoso", "Usuario registrado correctamente.");
                txtUsuarioAlta.clear();
                txtPasswordAlta.clear();
                txtConfirmarPasswordAlta.clear();
                txtVisiblePasswordAlta.clear();
                txtVisibleConfirmarPasswordAlta.clear();
                chkVerContraAlta.setSelected(false);
                chkVerContraAlta2.setSelected(false);
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error de registro", "No se pudo registrar el usuario. Verifique los requisitos.");
            }
        } catch (NoSuchAlgorithmException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Error al registrar el usuario: " + e.getMessage());
        }
    }



    private boolean validarContraseña(String password) {
        boolean tieneMayuscula = false;
        boolean tieneDigito = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                tieneMayuscula = true;
            } else if (Character.isDigit(c)) {
                tieneDigito = true;
            }
        }
        return tieneMayuscula && tieneDigito;
    }

    public void alta(ActionEvent actionEvent) {
        abrirVentana("UIAlta/uiAlta.fxml", "Alta de Usuario", 350, 575, actionEvent);
    }

    public void login(ActionEvent actionEvent) {
        abrirVentana("UILogin/uiLogin.fxml", "Login", 350, 575, actionEvent);
    }

    public void exit(ActionEvent actionEvent) {
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }

    public void abrirVentana(String urlFxml, String titulo, int width, int height, ActionEvent actionEvent) {
        try {
            Stage stageActual = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stageActual.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(urlFxml));
            Stage stageNuevo = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), width, height);
            stageNuevo.setTitle(titulo);
            stageNuevo.setScene(scene);
            stageNuevo.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void volver(ActionEvent actionEvent) {
        abrirVentana("UIInicio/uiInicio.fxml", "Login", 350, 575, actionEvent);
    }

    public void verContraseniaLogin(ActionEvent actionEvent) {
        if (chkVerContraLogin.isSelected()) {
            txtVisiblePasswordLogin.setText(txtPasswordLogin.getText());
            txtVisiblePasswordLogin.setVisible(true);
            txtVisiblePasswordLogin.setManaged(true);
            txtPasswordLogin.setVisible(false);
            txtPasswordLogin.setManaged(false);
        } else {
            txtPasswordLogin.setText(txtVisiblePasswordLogin.getText());
            txtPasswordLogin.setVisible(true);
            txtPasswordLogin.setManaged(true);
            txtVisiblePasswordLogin.setVisible(false);
            txtVisiblePasswordLogin.setManaged(false);
        }
    }

    @FXML
    public void verContraseniaAlta(ActionEvent actionEvent) {
        if (chkVerContraAlta.isSelected()) {
            txtVisiblePasswordAlta.setText(txtPasswordAlta.getText());
            txtVisiblePasswordAlta.setVisible(true);
            txtVisiblePasswordAlta.setManaged(true);
            txtPasswordAlta.setVisible(false);
            txtPasswordAlta.setManaged(false);
        } else {
            txtPasswordAlta.setText(txtVisiblePasswordAlta.getText());
            txtPasswordAlta.setVisible(true);
            txtPasswordAlta.setManaged(true);
            txtVisiblePasswordAlta.setVisible(false);
            txtVisiblePasswordAlta.setManaged(false);
        }
    }

    @FXML
    public void verContraseniaConfirmarAlta(ActionEvent actionEvent) {
        if (chkVerContraAlta2.isSelected()) {
            txtVisibleConfirmarPasswordAlta.setText(txtConfirmarPasswordAlta.getText());
            txtVisibleConfirmarPasswordAlta.setVisible(true);
            txtVisibleConfirmarPasswordAlta.setManaged(true);
            txtConfirmarPasswordAlta.setVisible(false);
            txtConfirmarPasswordAlta.setManaged(false);
        } else {
            txtConfirmarPasswordAlta.setText(txtVisibleConfirmarPasswordAlta.getText());
            txtConfirmarPasswordAlta.setVisible(true);
            txtConfirmarPasswordAlta.setManaged(true);
            txtVisibleConfirmarPasswordAlta.setVisible(false);
            txtVisibleConfirmarPasswordAlta.setManaged(false);
        }
    }
}
