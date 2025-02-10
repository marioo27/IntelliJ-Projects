module com.di.dam.calculadorax {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.di.dam.calculadorax to javafx.fxml;
    exports com.di.dam.calculadorax;
}