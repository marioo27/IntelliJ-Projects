package org.example.informereservas;

import javafx.application.Application;

public class Launcher {
    public static void main(String[] args) {
        Application.launch(HelloApplication.class, args);
    }
}


    /*
        -- module info:
            requires javafx.swing;
            requires net.sf.jasperreports.core;
            requires jrviewer.fx;
            requires java.sql;

        --
        net.sf.jasperreports 7.0.1
        net.sf.jasperreports.charts 7.0.0
        openjfx.javafx.swing 17
        win.zqxu.jrviewer.fx 0.1.1


        MyReports
│── JRE System Library [JavaSE-17]
│── JasperReports Library
│── Jaspersoft Server Library
│   │── js-extra-classes.jar - C:\Users\alumno\jaspersoftstudio\configuration\org.eclipse.osgi\48\0\.cp\lib
│   │── com.jaspersoft.commons.semantic
│   │── com.jaspersoft.commons.semantic.datasource
│   │── com.jaspersoft.ji.adhoc.jr
│   │── META-INF
│   │── MANIFEST.MF
│
│── Referenced Libraries
│   │── InformeReservas1.jar - C:\Users\alumno\IdeaProjects\InformeReservas\target
│   │   │── org.example.informereservas
│   │   │   │── Habitacion.class
│   │   │   │── HelloApplication.class
│   │   │   │── HelloController.class
│   │   │   │── Launcher.class
│   │   │   │── Reserva.class
│   │   │   │── hello-view.fxml
│   │   │── META-INF
│   │   │── module-info.class
│
│── Informe_Hotel.jasper
│── Informe_Hotel.jrxml
     */
