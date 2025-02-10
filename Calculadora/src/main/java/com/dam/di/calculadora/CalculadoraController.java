package com.dam.di.calculadora;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class CalculadoraController {

    @FXML
    private TextField tfMantisa;
    private boolean decimal = false;
    private Calculadora calculadora;

    @FXML
    public void initialize() {
        calculadora = new Calculadora();
    }

    @FXML
    protected void borrarTodo() {
        tfMantisa.clear();
        decimal = false;
    }

    @FXML
    protected void borrarUno() {
        String text = tfMantisa.getText();
        if (!text.isEmpty()) {
            tfMantisa.setText(text.substring(0, text.length() - 1));
        }
    }

    @FXML
    protected void opSuma() {
        tfMantisa.setText(tfMantisa.getText() + "+");
    }

    @FXML
    protected void opResta() {
        tfMantisa.setText(tfMantisa.getText() + "-");
    }

    @FXML
    protected void opMult() {
        tfMantisa.setText(tfMantisa.getText() + "x");
    }

    @FXML
    protected void opDiv() {
        tfMantisa.setText(tfMantisa.getText() + "÷");
    }

    @FXML
    protected void opPorcentaje() {
        tfMantisa.setText(tfMantisa.getText() + "%");
    }

    @FXML
    protected void opRaiz() {
        tfMantisa.setText(tfMantisa.getText() + "√");
    }

    @FXML
    protected void decimal() {
        if (!decimal) {
            tfMantisa.setText(tfMantisa.getText() + ".");
            decimal = true;
        }
    }

    @FXML
    protected void opIgual() {
        String expresion = tfMantisa.getText();
        String resultado = calculadora.evaluarExpresion(expresion);
        tfMantisa.setText(resultado);
    }

    @FXML
    protected void num0() {
        tfMantisa.setText(tfMantisa.getText() + "0");
    }

    @FXML
    protected void num1() {
        tfMantisa.setText(tfMantisa.getText() + "1");
    }

    @FXML
    protected void num2() {
        tfMantisa.setText(tfMantisa.getText() + "2");
    }

    @FXML
    protected void num3() {
        tfMantisa.setText(tfMantisa.getText() + "3");
    }

    @FXML
    protected void num4() {
        tfMantisa.setText(tfMantisa.getText() + "4");
    }

    @FXML
    protected void num5() {
        tfMantisa.setText(tfMantisa.getText() + "5");
    }

    @FXML
    protected void num6() {
        tfMantisa.setText(tfMantisa.getText() + "6");
    }

    @FXML
    protected void num7() {
        tfMantisa.setText(tfMantisa.getText() + "7");
    }

    @FXML
    protected void num8() {
        tfMantisa.setText(tfMantisa.getText() + "8");
    }

    @FXML
    protected void num9() {
        tfMantisa.setText(tfMantisa.getText() + "9");
    }

    @FXML
    private void handleKeyPress(KeyEvent event) {
        String character = event.getText();

        if (character.matches("[0-9]")) {
            tfMantisa.appendText(character);
        } else if (character.equals("+")) {
            opSuma();
        } else if (character.equals("-")) {
            opResta();
        } else if (character.equals("*")) {
            opMult();
        } else if (character.equals("/")) {
            opDiv();
        } else if (character.equals(".")) {
            decimal();
        } else if (character.equals("Enter")) {
            opIgual();
        } else if (character.equals("%")) {
            opPorcentaje();
        } else if (character.equals("√")) {
            opRaiz();
        } else if (character.equals("Backspace")) {
            borrarUno();
        } else if (character.equals("Escape")) {
            borrarTodo();
        }
    }
}
