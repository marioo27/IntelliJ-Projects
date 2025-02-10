module com.dam.di.calculadora3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.dam.di.calculadora3 to javafx.fxml;
    exports com.dam.di.calculadora3;
}