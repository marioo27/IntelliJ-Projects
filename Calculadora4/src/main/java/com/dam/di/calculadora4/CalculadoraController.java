package com.dam.di.calculadora4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CalculadoraController {

    @FXML private TextField tfMantisa;
    private String input ="";


    @FXML protected void num(ActionEvent event) {
        Button btn = (Button) event.getSource();
        addText(btn.getText());
    }

    private void addText(String texto) {
        input += texto;
        tfMantisa.setText(input);
    }

    @FXML public void num1() { tfMantisa.setText(tfMantisa.getText() + "1"); }
    @FXML public void num2() { tfMantisa.setText(tfMantisa.getText() + "2"); }
    @FXML public void num3() { tfMantisa.setText(tfMantisa.getText() + "3"); }
    @FXML public void num4() { tfMantisa.setText(tfMantisa.getText() + "4"); }
    @FXML public void num5() { tfMantisa.setText(tfMantisa.getText() + "5"); }
    @FXML public void num6() { tfMantisa.setText(tfMantisa.getText() + "6"); }
    @FXML public void num7() { tfMantisa.setText(tfMantisa.getText() + "7"); }
    @FXML public void num8() { tfMantisa.setText(tfMantisa.getText() + "8"); }
    @FXML public void num9() { tfMantisa.setText(tfMantisa.getText() + "9"); }

}