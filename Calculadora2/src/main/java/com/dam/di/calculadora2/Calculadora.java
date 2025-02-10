package com.dam.di.calculadora2;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Calculadora {

    public String evaluarExpresion(String expresion) {
        try {
            expresion = expresion.replace("x", "*").replace("÷", "/").replace(",", ".");

            Expression e = new ExpressionBuilder(expresion).build();
            double resultado = e.evaluate();

            return String.valueOf(resultado);

        } catch (Exception e) {
            return "Error";
        }
    }
}
