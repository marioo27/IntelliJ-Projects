module com.dam.di.usandojavahelp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    requires javahelp;

    opens com.dam.di.usandojavahelp to javafx.fxml;
    exports com.dam.di.usandojavahelp;
}