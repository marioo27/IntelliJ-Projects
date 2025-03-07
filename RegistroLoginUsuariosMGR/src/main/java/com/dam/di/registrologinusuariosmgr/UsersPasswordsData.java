package com.dam.di.registrologinusuariosmgr;

import javafx.scene.control.Alert;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class UsersPasswordsData implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int MAX_CHARS = 10, MIN_CHARS = 5, MAX_USERS = 10;
    public static final String USABLE_CHARS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                    "abcdefghijklmnopqrstuvwxyz" +
                    "0123456789" +
                    "!#$%&'()*+,-./:;<=>?@[]^_`{|}~";

    private static final String usersDataFile = "UsersData.dat";

    private Map<String, String> users;
    private String user;

    private HelloController controller;

    public UsersPasswordsData() throws IOException, ClassNotFoundException {
        this.users = recoverUsers();
        this.user = null;
    }

    public void setController(HelloController controller) {
        this.controller = controller;
    }

    public boolean addUser(String username, String password) throws IOException, ClassNotFoundException {
        if (users.size() >= MAX_USERS) {
            controller.mostrarAlerta(Alert.AlertType.ERROR, "Error de registro", "Límite de usuarios alcanzado.");
            return false;
        }

        if (username.length() < MIN_CHARS || username.length() > MAX_CHARS) {
            controller.mostrarAlerta(Alert.AlertType.ERROR, "Error de registro", "Longitud del nombre de usuario incorrecta. MIN = " + MIN_CHARS + ", MAX = " + MAX_CHARS + ", longitud = " + username.length());
            return false;
        }



        if (!validChars(username.toCharArray())) {
            controller.mostrarAlerta(Alert.AlertType.ERROR, "Error de registro", "El nombre de usuario contiene caracteres inválidos.");
            return false;
        }

        try {
            users.put(username, password);
            saveUsers(users);
            controller.mostrarAlerta(Alert.AlertType.INFORMATION, "Registro exitoso", "Usuario registrado correctamente: " + username);
            return true;
        } catch (IOException e) {
            controller.mostrarAlerta(Alert.AlertType.ERROR, "Error de registro", "Error al guardar el usuario: " + e.getMessage());
            throw e;
        }
    }

    public Map<String, String> recoverUsers() throws IOException, ClassNotFoundException {
        File file = new File(usersDataFile);
        if (!file.exists()) return new HashMap<>();

        try (FileInputStream fileIn = new FileInputStream(usersDataFile);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return (Map<String, String>) in.readObject();
        } catch (EOFException e) {
            return new HashMap<>();
        }
    }

    private boolean validChars(char[] row) {
        for (char c : row) {
            if (USABLE_CHARS.indexOf(c) == -1) return false;
        }
        return true;
    }

    private static void saveUsers(Map<String, String> users) throws IOException {
        try (FileOutputStream fileOut = new FileOutputStream(usersDataFile);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(users);
        }
    }

    public int access(String user, String password) throws IOException, ClassNotFoundException, NoSuchAlgorithmException {
        users = recoverUsers();
        if (!users.containsKey(user)) return -1;
        String hashedPassword = codificar(password);
        return users.get(user).equals(hashedPassword) ? 1 : 0;
    }

    public Map<String, String> getUsers() throws IOException, ClassNotFoundException {
        users = recoverUsers();
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
