package com.dam.di.reservashotel;

public class Reserva {
    private int numPersonas;
    private String nombre;
    private int duracion;

    public Reserva(int numPersonas, String nombre, int duracion) {
        this.numPersonas = numPersonas;
        this.nombre = nombre;
        this.duracion = duracion;
    }

    public int getNumPersonas() {
        return numPersonas;
    }

    public void setNumPersonas(int numPersonas) {
        this.numPersonas = numPersonas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
}
