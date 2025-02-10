package com.dam.di.appsesionesfotos;

import java.util.Objects;

public class Sesion {
    private String tipo;
    private int numPersonas;
    private String otro;
    private double precio;

    public Sesion(String tipo, int numPersonas, String otro, double precio) {
        this.tipo = tipo;
        this.numPersonas = numPersonas;
        this.otro = otro;
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNumPersonas() {
        return numPersonas;
    }

    public void setNumPersonas(int numPersonas) {
        this.numPersonas = numPersonas;
    }

    public String getOtro() {
        return otro;
    }

    public void setOtro(String otro) {
        this.otro = otro;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Sesion{" +
                "tipo='" + tipo + '\'' +
                ", numPersonas=" + numPersonas +
                ", otro='" + otro + '\'' +
                ", precio=" + precio +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Sesion sesion = (Sesion) o;
        return getNumPersonas() == sesion.getNumPersonas() && Double.compare(getPrecio(), sesion.getPrecio()) == 0 && Objects.equals(getTipo(), sesion.getTipo()) && Objects.equals(getOtro(), sesion.getOtro());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTipo(), getNumPersonas(), getOtro(), getPrecio());
    }
}
