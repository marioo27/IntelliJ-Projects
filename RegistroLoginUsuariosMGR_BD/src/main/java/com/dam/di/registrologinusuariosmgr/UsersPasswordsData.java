package com.dam.di.registrologinusuariosmgr;

import javafx.scene.control.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UsersPasswordsData {

    private static final Logger log = LoggerFactory.getLogger(UsersPasswordsData.class);
    public static final int MAX_CHARS = 10, MIN_CHARS = 5, MAX_USERS = 10;
    public static final String USABLE_CHARS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                    "abcdefghijklmnopqrstuvwxyz" +
                    "0123456789" +
                    "!#$%&'()*+,-./:;<=>?@[]^_`{|}~";
    private HelloController controller;

    private DBManager dbManager;

    public UsersPasswordsData() throws IOException, ClassNotFoundException {
        dbManager = new DBManager();
    }

    public void setController(HelloController controller) {
        this.controller = controller;
    }

    public boolean addUser(String username, String password) {
        if (username.length() < MIN_CHARS || username.length() > MAX_CHARS) {
            controller.mostrarAlerta(Alert.AlertType.ERROR, "Error de registro", "Longitud del nombre de usuario incorrecta. MIN = " + MIN_CHARS + ", MAX = " + MAX_CHARS + ", longitud = " + username.length());
            return false;
        }

        if (!validChars(username.toCharArray())) {
            controller.mostrarAlerta(Alert.AlertType.ERROR, "Error de registro", "El nombre de usuario contiene caracteres inválidos.");
            return false;
        }

        String sql = "INSERT INTO usuarios (username, pass) VALUES (?, ?)";
        try (Connection conn = dbManager.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            controller.mostrarAlerta(Alert.AlertType.INFORMATION, "Registro exitoso", "Usuario registrado correctamente: " + username);
            return true;
        } catch (SQLException e) {
            controller.mostrarAlerta(Alert.AlertType.ERROR, "Error de registro", "Error al guardar el usuario: " + e.getMessage());
            log.error("Error al registrar usuario en la base de datos: " + e.getMessage(), e);
            return false;
        }
    }

    private boolean validChars(char[] row) {
        for (char c : row) {
            if (USABLE_CHARS.indexOf(c) == -1) return false;
        }
        return true;
    }

    public int access(String username, String password) throws NoSuchAlgorithmException {
        String sql = "SELECT pass FROM usuarios WHERE username = ?";
        try (Connection conn = dbManager.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String hashedPassword = codificar(password);
                String storedPassword = rs.getString("pass");
                return storedPassword.equals(hashedPassword) ? 1 : 0;
            } else {
                return -1; // Usuario no encontrado
            }
        } catch (SQLException e) {
            controller.mostrarAlerta(Alert.AlertType.ERROR, "Error de acceso", "Error al acceder al usuario: " + e.getMessage());
            log.error("Error al acceder al usuario en la base de datos: " + e.getMessage(), e);
            return -1;
        }
    }

    public Map<String, String> getUsers() {
        Map<String, String> users = new HashMap<>();
        String sql = "SELECT username, pass FROM usuarios";
        try (Connection conn = dbManager.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                users.put(rs.getString("username"), rs.getString("pass"));
            }
        } catch (SQLException e) {
            controller.mostrarAlerta(Alert.AlertType.ERROR, "Error", "Error al cargar los usuarios: " + e.getMessage());
            log.error("Error al cargar los usuarios de la base de datos: " + e.getMessage(), e);
        }
        return users;
    }

    public static String codificar(String pass) throws NoSuchAlgorithmException {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(pass.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
