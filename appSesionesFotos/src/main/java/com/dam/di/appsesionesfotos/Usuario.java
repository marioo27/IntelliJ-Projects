package com.dam.di.appsesionesfotos;

import java.util.Objects;

public class Usuario {

    private String user;
    private String passw;

    public Usuario(String user, String passw) {
        this.user = user;
        this.passw = passw;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "user='" + user + '\'' +
                ", passw='" + passw + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(getUser(), usuario.getUser()) && Objects.equals(getPassw(), usuario.getPassw());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getPassw());
    }
}
