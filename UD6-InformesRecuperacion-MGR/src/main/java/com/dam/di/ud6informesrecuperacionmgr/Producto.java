package com.dam.di.ud6informesrecuperacionmgr;

import java.time.LocalDate;
import java.util.Date;

public class Producto {

    private int codigo;
    private String nombre;
    private Categoria categoria;
    private double precio;
    private LocalDate fechaCad;

    public Producto(LocalDate fechaCad, double precio, Categoria categoria, String nombre, int codigo) {
        this.fechaCad = fechaCad;
        this.precio = precio;
        this.categoria = categoria;
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDate getFechaCad() {
        return fechaCad;
    }

    public void setFechaCad(LocalDate fechaCad) {
        this.fechaCad = fechaCad;
    }
}
