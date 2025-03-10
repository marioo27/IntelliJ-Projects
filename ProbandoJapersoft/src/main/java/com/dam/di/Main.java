package com.dam.di;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        Libro libro1 = new Libro("1111","Libro 1");
        Libro libro2 = new Libro("2222","Libro 2");
        Libro libro3 = new Libro("3333","Libro 3");
        Libro libro4 = new Libro("4444","Libro 4");

        HashSet<Libro> libros = new HashSet<>();
        libros.add(libro1);
        libros.add(libro2);
        libros.add(libro3);
        libros.add(libro4);
        //System.out.println("LIBROS ");
        try {
            File fichero = new File("./informes/informeLibros.jasper");
            JasperReport informe = (JasperReport) JRLoader.loadObject(fichero);
            // JasperReport informe = (JasperReport) JRLoader.loadObject(fichero);
            JRBeanCollectionDataSource coleccion = new JRBeanCollectionDataSource(libros);
            JasperPrint print = JasperFillManager.fillReport(informe,null,coleccion);
            // JasperExportManager.exportReportToPdf(print,fichero);
            JasperViewer visor = new JasperViewer(print, false);
            visor.setVisible(true);
        }
        catch (JRException e) {
            e.printStackTrace();
        }
    }
}