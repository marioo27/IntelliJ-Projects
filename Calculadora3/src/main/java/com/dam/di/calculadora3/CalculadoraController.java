package com.dam.di.calculadora3;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CalculadoraController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}