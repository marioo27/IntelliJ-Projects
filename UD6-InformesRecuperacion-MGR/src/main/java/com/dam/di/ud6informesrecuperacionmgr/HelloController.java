package com.dam.di.ud6informesrecuperacionmgr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import win.zqxu.jrviewer.JRViewerFX;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController {
    @FXML
    private Button btnVisualizarInforme;
    @FXML
    private Button btnGuardarInforme;
    @FXML
    private Pane panelInforme;

    private ObservableList<Producto> listaProductos;

    public void initialize(){
        List<Producto> productos = new ArrayList<>();

        Categoria higiene = new Categoria("Higiene", "Productos de Higiene");
        Categoria comida = new Categoria("Comida", "Productos de Comida");
        Categoria juguetes = new Categoria("Juguetes", "Productos de Juguetes");

        productos.add(new Producto(LocalDate.of(2028, 9, 8), 3.0, comida, "Mantequilla", 100));
        productos.add(new Producto(LocalDate.of(2025, 4, 2), 20.0, comida, "Galletas de Chocolate", 101));
        productos.add(new Producto(LocalDate.of(2050, 1, 1), 5.0, comida, "Sal", 102));
        productos.add(new Producto(LocalDate.of(2026, 2, 14), 1.75, higiene, "Espuma de Afeitar", 103));
        productos.add(new Producto(LocalDate.of(2025, 3, 10), 2.0, comida, "Yogur de Fresa", 104));
        productos.add(new Producto(LocalDate.of(2028, 9, 8), 35.0, higiene, "Crema Hidratante", 105));
        productos.add(new Producto(LocalDate.of(2050, 1, 1), 200.0, juguetes, "Figura Goku", 106));
        productos.add(new Producto(LocalDate.of(2050, 1, 1), 5.0, juguetes, "Bolsa de Canicas", 107));
        productos.add(new Producto(LocalDate.of(2050, 1, 1), 1000, juguetes, "Casco de Iron Man", 108));
        productos.add(new Producto(LocalDate.of(2050, 1, 1), 2500, juguetes, "Estatua de Kratos ", 109));

        listaProductos = FXCollections.observableArrayList(productos);
    }

    @FXML
    private void generarInforme() {
        try {
            File fichero = new File("./Informes/InformeProductosMGR.jasper");
            JasperReport informe = (JasperReport) JRLoader.loadObject(fichero);
            JRBeanCollectionDataSource coleccion = new JRBeanCollectionDataSource(listaProductos);
            HashMap<String, Object> parametros = new HashMap<>();
            parametros.put("RUTA_IMAGEN", "./Informes/img.png");
            JasperPrint print = JasperFillManager.fillReport(informe, parametros, coleccion);
            JRViewerFX visor = new JRViewerFX(print);
            panelInforme.getChildren().add(visor);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void guardarInforme() {
        try {
            File fichero = new File("./Informes/InformeProductosMGR.jasper");
            JasperReport informe = (JasperReport) JRLoader.loadObject(fichero);
            JRBeanCollectionDataSource coleccion = new JRBeanCollectionDataSource(listaProductos);
            HashMap<String, Object> parametros = new HashMap<>();
            parametros.put("RUTA_IMAGEN", "./Informes/img.png");
            JasperPrint print = JasperFillManager.fillReport(informe, parametros, coleccion);

            File reportFile = new File("Informes/InformeProductosMGR.pdf");
            OutputStream output = new FileOutputStream(reportFile);
            JasperExportManager.exportReportToPdfStream(print, output);
        } catch (FileNotFoundException | JRException e) {
            throw new RuntimeException(e);
        }
    }

}