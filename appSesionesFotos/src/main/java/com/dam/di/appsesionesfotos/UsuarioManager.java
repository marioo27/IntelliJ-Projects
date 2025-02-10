package com.dam.di.appsesionesfotos;

import java.util.ArrayList;

    public class UsuarioManager {

        private static UsuarioManager instance;
        private ArrayList<Usuario> listaUsuarios;

        private UsuarioManager() {
            listaUsuarios = new ArrayList<>();
            Usuario admin = new Usuario("admin", "1234");
            listaUsuarios.add(admin);
        }

        public static UsuarioManager getInstance() {
            if (instance == null) {
                instance = new UsuarioManager();
            }
            return instance;
        }

        public ArrayList<Usuario> getListaUsuarios() {
            return listaUsuarios;
        }

        public void addUsuario(Usuario usuario) {
            listaUsuarios.add(usuario);
        }
    }