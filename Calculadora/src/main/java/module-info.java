module com.dam.di.calculadora {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.scripting;
    requires exp4j;

    opens com.dam.di.calculadora to javafx.fxml;
    exports com.dam.di.calculadora;
}