package com.dam.di.reservashotel;

public class Habitacion {

    private int numHabitacion;
    private double precio;
    private Reserva reserva;

    public Habitacion(int numHabitacion, double precio, Reserva reserva) {
        this.numHabitacion = numHabitacion;
        this.precio = precio;
        this.reserva = reserva;
    }

    public int getNumero() {
        return numHabitacion;
    }

    public double getPrecio() {
        return precio;
    }

    public boolean estaReservada() {
        return reserva != null;
    }

    public void reservar(Reserva reserva) {
        if (this.reserva == null) {
            this.reserva = reserva;
        } else {
            throw new IllegalStateException("La habitacion ya esta reservada.");
        }
    }

    public void cancelarReserva() {
        this.reserva = null;
    }
}
