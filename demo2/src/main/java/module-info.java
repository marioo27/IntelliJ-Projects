module com.dam.di.demo2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.dam.di.demo2 to javafx.fxml;
    exports com.dam.di.demo2;
}