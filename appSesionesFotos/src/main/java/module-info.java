module com.dam.di.appsesionesfotos {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.dam.di.appsesionesfotos to javafx.fxml;
    exports com.dam.di.appsesionesfotos;
}