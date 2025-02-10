package com.example.tablapersonas;

import java.io.Serializable;
import java.util.Objects;

public class Persona implements Serializable {


    private String nombre;
    private String apellidos;
    private int edad;

    public Persona(String nombre, String apellidos, int edad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return getEdad() == persona.getEdad() && Objects.equals(getNombre(), persona.getNombre()) && Objects.equals(getApellidos(), persona.getApellidos());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEdad(), getNombre(), getApellidos());
    }
}
