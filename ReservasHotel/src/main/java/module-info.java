module com.dam.di.reservashotel {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires net.sf.jasperreports.core;

    requires java.sql;

    opens com.dam.di.reservashotel to javafx.fxml;
    exports com.dam.di.reservashotel;
}