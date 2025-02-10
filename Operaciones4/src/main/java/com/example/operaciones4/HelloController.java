package com.example.operaciones4;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class HelloController {

    @FXML private TextField TextFieldOperando1;
    @FXML private TextField TextFieldOperando2;
    @FXML private TextField TextFieldResultado;
    @FXML private ToggleGroup Operadores;

    protected int seleccion(){

        RadioButton botonSeleccionado = (RadioButton) Operadores.getSelectedToggle();
        int salida;

        if(botonSeleccionado!=null)
            switch (botonSeleccionado.getId()){
                case "RadioButtonSuma" -> salida = 1;
                case "RadioButtonResta" -> salida = 2;
                case "RadioButtonDivision" -> salida = 3;
                case "RadioButtonMultiplicacion" -> salida = 4;
                default -> salida = 0;
            }
        else
            salida = 0;

        return salida;
    }

    @FXML
    public void ButtonOperar(){
        String salida;
        try {
            switch (seleccion()){
                case 1 -> salida = "" + Operaciones.suma(TextFieldOperando1.getText(), TextFieldOperando2.getText());
                case 2 -> salida = "" + Operaciones.resta(TextFieldOperando1.getText(), TextFieldOperando2.getText());
                case 3 -> salida = "" + Operaciones.division(TextFieldOperando1.getText(), TextFieldOperando2.getText());
                case 4 -> salida = "" + Operaciones.multiplicacion(TextFieldOperando1.getText(), TextFieldOperando2.getText());

                case 0 -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("No has seleccionado una operacion");
                    alert.showAndWait();
                    salida = null;
                }
                default -> salida = null;
            }
        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("No has introducido un numero");
            alert.showAndWait();
            salida = null;

        } catch (ArithmeticException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("No se puede dividir entre 0");
            alert.showAndWait();
            salida = "X";
        }

        TextFieldResultado.setText(salida);
    }

}