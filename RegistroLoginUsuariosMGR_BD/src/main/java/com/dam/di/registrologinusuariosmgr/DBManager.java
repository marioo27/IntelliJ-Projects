package com.dam.di.registrologinusuariosmgr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class DBManager {
    private static final Logger log = LoggerFactory.getLogger(DBManager.class);
    private String user, password, driverName, dbUrl;
    private static final String PROPERTIES_FILE = "registroLoginUsuariosMGR.properties";
    private Connection conexion = null;

    public DBManager() {
        loadProperties();
        try {
            Class.forName(driverName);
            log.debug("Se ha registrado correctamente el driver: " + driverName);
            conexion = DriverManager.getConnection(dbUrl, user, password);
            log.debug("Se ha creado correctamente la conexion");
        } catch (ClassNotFoundException e) {
            log.error("Error registrando el driver: " + driverName, e);
        } catch (SQLException e) {
            log.error("Error estableciendo conexion con la BDD: " + e.getMessage(), e);
        }
    }

    private void loadProperties() {
        Properties props = new Properties();
        try (FileInputStream input = new FileInputStream(PROPERTIES_FILE)) {
            props.load(input);
            this.driverName = props.getProperty("driver");
            this.dbUrl = props.getProperty("url");
            this.user = props.getProperty("user");
            this.password = props.getProperty("password");
        } catch (IOException e) {
            log.error("Error al cargar el archivo de propiedades: " + e.getMessage(), e);
        }
    }

    public Connection getConexion() {
        return conexion;
    }

    public void closeConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                log.debug("Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
            log.error("Error al cerrar la conexión: " + e.getMessage(), e);
        }
    }
}
