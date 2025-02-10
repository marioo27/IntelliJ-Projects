module com.dam.di.calculadora2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires exp4j;

    opens com.dam.di.calculadora2 to javafx.fxml;
    exports com.dam.di.calculadora2;
}