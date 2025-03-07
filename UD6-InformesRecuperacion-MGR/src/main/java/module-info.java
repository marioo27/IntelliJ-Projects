module com.dam.di.ud6informesrecuperacionmgr {
    requires javafx.controls;
    requires javafx.fxml;

    requires javafx.swing;
    requires net.sf.jasperreports.core;
    requires jrviewer.fx;
    requires java.sql;

    opens com.dam.di.ud6informesrecuperacionmgr to javafx.fxml;
    exports com.dam.di.ud6informesrecuperacionmgr;
}