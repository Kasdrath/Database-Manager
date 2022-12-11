package com.proyectopractica.proyectopractica.controller;
import com.proyectopractica.proyectopractica.Inicio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;


public class LoginController {


    @FXML
    void abrirVentana(ActionEvent event) {
        try {
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); //para cerrar ventana antes de abrir
            //Abrir nueva ventana
            FXMLLoader fxmlLoader = new FXMLLoader(Inicio.class.getResource("Menu.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 482, 342);
            Stage stage = new Stage();
            stage.setTitle("Menu");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
