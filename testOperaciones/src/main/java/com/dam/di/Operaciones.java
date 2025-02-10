package com.dam.di;

public class Operaciones {

    private double a;
    private double b;

    public Operaciones(double a, double b){
        this.a = a;
        this.b = b;
    }

    public double getA(){
        return a;
    }

    public double getB(){
        return b;
    }

    public double suma(double a, double b) {
        return a + b;
    }

    public double resta(double a, double b) {
        return a - b;
    }

    public double multiplicacion(double a, double b) {
        return a * b;
    }

    public double division(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("No se puede dividir por cero.");
        }
        return a / b;
    }
}
