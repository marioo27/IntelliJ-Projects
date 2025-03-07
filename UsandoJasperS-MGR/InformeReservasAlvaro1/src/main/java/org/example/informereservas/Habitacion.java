package org.example.informereservas;

/**
 * @author Mario Garcia Rodriguez
 * @version 1
 * @since 18/02/2025
 * Representa una habitación de un hotel, incluyendo su número, precio y una reserva asociada.
 */
public class Habitacion {
    private int num;
    private double precio;
    private Reserva reserva;

    /**
     * Constructor para crear una habitación con un número, precio y reserva.
     *
     * @param num  Número de la habitación.
     * @param precio  Precio de la habitación.
     * @param reserva Reserva asociada a la habitación.
     */
    public Habitacion(int num, double precio, Reserva reserva) {
        this.num = num;
        this.precio = precio;
        this.reserva = reserva;
    }

    /**
     * Obtiene el número de la habitación.
     *
     * @return Número de la habitación.
     */
    public int getNum() {
        return num;
    }

    /**
     * Establece el número de la habitación.
     *
     * @param num Nuevo número de la habitación.
     */
    public void setNum(int num) {
        this.num = num;
    }

    /**
     * Obtiene el precio de la habitación.
     *
     * @return Precio de la habitación.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio de la habitación.
     *
     * @param precio Nuevo precio de la habitación.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene la reserva asociada a la habitación.
     *
     * @return La reserva de la habitación.
     */
    public Reserva getReserva() {
        return reserva;
    }

    /**
     * Asigna una reserva a la habitación.
     *
     * @param reserva Nueva reserva de la habitación.
     */
    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
}
