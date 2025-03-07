module com.dam.di.registrologinusuariosmgr {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.dam.di.registrologinusuariosmgr to javafx.fxml;
    exports com.dam.di.registrologinusuariosmgr;
}