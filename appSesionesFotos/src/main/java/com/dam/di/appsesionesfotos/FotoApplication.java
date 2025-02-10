package com.dam.di.appsesionesfotos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FotoApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
    // FXMLLoader fxmlLoader = new FXMLLoader(FotoApplication.class.getResource("UIListado/uiListado.fxml"));
    //  Scene scene = new Scene(fxmlLoader.load(), 280, 250);

        FXMLLoader fxmlLoader = new FXMLLoader(FotoApplication.class.getResource("UIAcceso/uiAcceso.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 280, 250);


    //    FXMLLoader fxmlLoader = new FXMLLoader(FotoApplication.class.getResource("UISesiones/UIRetratos/uiRetrato.fxml"));
    //    Scene scene = new Scene(fxmlLoader.load(), 400, 500);

        stage.setTitle("KobaPhoto!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}