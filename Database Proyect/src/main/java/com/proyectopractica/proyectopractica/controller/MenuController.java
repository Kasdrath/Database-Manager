package com.proyectopractica.proyectopractica.controller;

import com.proyectopractica.proyectopractica.Inicio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MenuController implements Initializable {

    @FXML
    public ComboBox combo1;
    @FXML
    public ComboBox combo2;
    @FXML
    public ComboBox combo3;


    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list1 = FXCollections.observableArrayList("Componente Químico","Familia","Analisis en T","Tasa Calent","Flujo", "Relac Biom/Cat","Material Relac","Temp Pirolisis","Catalizador");//para llenar el combobox
        ObservableList<String> list2 = FXCollections.observableArrayList("Componente Químico","Familia","Analisis en T","Tasa Calent","Flujo", "Relac Biom/Cat","Material Relac","Temp Pirolisis","Catalizador");
        ObservableList<String> list3 = FXCollections.observableArrayList("Componente Químico","Familia","Analisis en T","Tasa Calent","Flujo", "Relac Biom/Cat","Material Relac","Temp Pirolisis","Catalizador");
        combo1.setItems(list1);//para llenar el combobox
        combo2.setItems(list2);
        combo3.setItems(list3);

    }

   /* @FXML
    void hacerr(ActionEvent event) {
        String s1 = combo1.getSelectionModel().getSelectedItem().toString(); //para llenar el combobox
        String s2 = combo2.getSelectionModel().getSelectedItem().toString();
        String s3 = combo3.getSelectionModel().getSelectedItem().toString();


    }*/
    @FXML
    void insertarDatoss(ActionEvent event) throws IOException {
        //((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); //para cerrar ventana antes de abrir
        //Abrir nueva ventana


        switch(combo1.getValue().toString()){
            case "Familia":
                FXMLLoader fxmlLoader = new FXMLLoader(Inicio.class.getResource("InsertarFamilia.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 600);
                Stage stage = new Stage();
                stage.setTitle("Insertar Familia");
                stage.setScene(scene);
                stage.show();
                break;

            case "Componente Químico":
                fxmlLoader = new FXMLLoader(Inicio.class.getResource("InsertarCompQuimico.fxml"));
                scene = new Scene(fxmlLoader.load(), 600, 600);
                stage = new Stage();
                stage.setTitle("Insertar Compuesto Quimico");
                stage.setScene(scene);
                stage.show();
                break;

            case "Analisis en T":
                fxmlLoader = new FXMLLoader(Inicio.class.getResource("InsertarAnalisisenT.fxml"));
                scene = new Scene(fxmlLoader.load(), 600, 600);
                stage = new Stage();
                stage.setTitle("Insertar Analisis en T");
                stage.setScene(scene);
                stage.show();
                break;

            case "Tasa Calent":
                fxmlLoader = new FXMLLoader(Inicio.class.getResource("InsertarTasaCalent.fxml"));
                scene = new Scene(fxmlLoader.load(), 600, 600);
                stage = new Stage();
                stage.setTitle("Insertar tasa calentamiento");
                stage.setScene(scene);
                stage.show();
                break;

            case "Flujo":
                fxmlLoader = new FXMLLoader(Inicio.class.getResource("InsertarFlujo.fxml"));
                scene = new Scene(fxmlLoader.load(), 600, 600);
                stage = new Stage();
                stage.setTitle("Insertar flujo");
                stage.setScene(scene);
                stage.show();
                break;

            case "Relac Biom/Cat":
                fxmlLoader = new FXMLLoader(Inicio.class.getResource("InsertarRelacionBiomCat.fxml"));
                scene = new Scene(fxmlLoader.load(), 600, 600);
                stage = new Stage();
                stage.setTitle("Insertar relacion biomasa/catalizador");
                stage.setScene(scene);
                stage.show();
                break;

            case "Material Relac":
                fxmlLoader = new FXMLLoader(Inicio.class.getResource("InsertarMaterialRelac.fxml"));
                scene = new Scene(fxmlLoader.load(), 600, 600);
                stage = new Stage();
                stage.setTitle("Insertar Material Relac");
                stage.setScene(scene);
                stage.show();
                break;

            case "Temp Pirolisis":
                fxmlLoader = new FXMLLoader(Inicio.class.getResource("InsertarTempPirolisis.fxml"));
                scene = new Scene(fxmlLoader.load(), 600, 600);
                stage = new Stage();
                stage.setTitle("Insertar Temperatura Pirolisis");
                stage.setScene(scene);
                stage.show();
                break;

            case "Catalizador":
                fxmlLoader = new FXMLLoader(Inicio.class.getResource("InsertarCatalizador.fxml"));
                scene = new Scene(fxmlLoader.load(), 600, 600);
                stage = new Stage();
                stage.setTitle("Insertar Catalizador");
                stage.setScene(scene);
                stage.show();
                break;



        }
    }

    @FXML
    void ingresarCondicion(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Inicio.class.getResource("CondicionExperimental.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 794, 649);
        Stage stage = new Stage();
        stage.setTitle("Condicion Experimental");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void modificarDatoss(ActionEvent event) throws IOException {
        switch(combo2.getValue().toString()){
            case "Familia":
                FXMLLoader fxmlLoader = new FXMLLoader(Inicio.class.getResource("ModificarFamilia.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 600);
                Stage stage = new Stage();
                stage.setTitle("Modificar Familia");
                stage.setScene(scene);
                stage.show();
                break;

            case "Componente Químico":
                fxmlLoader = new FXMLLoader(Inicio.class.getResource("ModificarCompuestoQuimico.fxml"));
                scene = new Scene(fxmlLoader.load(), 837, 326);
                stage = new Stage();
                stage.setTitle("Modificar Compuesto Quimico");
                stage.setScene(scene);
                stage.show();
                break;

            case "Analisis en T":
                fxmlLoader = new FXMLLoader(Inicio.class.getResource("ModificarAnalisis.fxml"));
                scene = new Scene(fxmlLoader.load(), 600, 600);
                stage = new Stage();
                stage.setTitle("Modificar Analisis en T");
                stage.setScene(scene);
                stage.show();
                break;
            case "Tasa Calent":
                fxmlLoader = new FXMLLoader(Inicio.class.getResource("ModificarTasaCalent.fxml"));
                scene = new Scene(fxmlLoader.load(), 600, 600);
                stage = new Stage();
                stage.setTitle("Modificar tasa calentamiento");
                stage.setScene(scene);
                stage.show();
                break;

            case "Flujo":
                fxmlLoader = new FXMLLoader(Inicio.class.getResource("ModificarFlujo.fxml"));
                scene = new Scene(fxmlLoader.load(), 600, 600);
                stage = new Stage();
                stage.setTitle("Modificar flujo");
                stage.setScene(scene);
                stage.show();
                break;

            case "Relac Biom/Cat":
                fxmlLoader = new FXMLLoader(Inicio.class.getResource("ModificarRelacionBiomCat.fxml"));
                scene = new Scene(fxmlLoader.load(), 600, 600);
                stage = new Stage();
                stage.setTitle("Modificar relacion biomasa/catalizador");
                stage.setScene(scene);
                stage.show();
                break;

            case "Material Relac":
                fxmlLoader = new FXMLLoader(Inicio.class.getResource("ModificarMaterialRelac.fxml"));
                scene = new Scene(fxmlLoader.load(), 600, 600);
                stage = new Stage();
                stage.setTitle("Modificar Material Relac");
                stage.setScene(scene);
                stage.show();
                break;

            case "Temp Pirolisis":
                fxmlLoader = new FXMLLoader(Inicio.class.getResource("ModificarTempPirolisis.fxml"));
                scene = new Scene(fxmlLoader.load(), 600, 600);
                stage = new Stage();
                stage.setTitle("Modificar Temperatura Pirolisis");
                stage.setScene(scene);
                stage.show();
                break;

            case "Catalizador":
                fxmlLoader = new FXMLLoader(Inicio.class.getResource("ModificarCatalizador.fxml"));
                scene = new Scene(fxmlLoader.load(), 600, 600);
                stage = new Stage();
                stage.setTitle("Modificar Catalizador");
                stage.setScene(scene);
                stage.show();
                break;
        }

    }
    @FXML
    void eliminarDatos(ActionEvent event) throws IOException {
        switch(combo3.getValue().toString()){
            case "Familia":
                FXMLLoader fxmlLoader = new FXMLLoader(Inicio.class.getResource("EliminarFamilia.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 485, 585);
                Stage stage = new Stage();
                stage.setTitle("Eliminar Familia");
                stage.setScene(scene);
                stage.show();
                break;

            case "Componente Químico":
                fxmlLoader = new FXMLLoader(Inicio.class.getResource("EliminarCompuestoQuimico.fxml"));
                scene = new Scene(fxmlLoader.load(), 600, 600);
                stage = new Stage();
                stage.setTitle("Eliminar Compuesto Quimico");
                stage.setScene(scene);
                stage.show();
                break;

            case "Analisis en T":
                fxmlLoader = new FXMLLoader(Inicio.class.getResource("EliminarAnalisisEnT.fxml"));
                scene = new Scene(fxmlLoader.load(), 600, 600);
                stage = new Stage();
                stage.setTitle("Eliminar Analisis en T");
                stage.setScene(scene);
                stage.show();
                break;

            case "Tasa Calent":
                fxmlLoader = new FXMLLoader(Inicio.class.getResource("EliminarTasaCalent.fxml"));
                scene = new Scene(fxmlLoader.load(), 600, 600);
                stage = new Stage();
                stage.setTitle("Eliminar tasa calentamiento");
                stage.setScene(scene);
                stage.show();
                break;

            case "Flujo":
                fxmlLoader = new FXMLLoader(Inicio.class.getResource("EliminarFlujo.fxml"));
                scene = new Scene(fxmlLoader.load(), 600, 600);
                stage = new Stage();
                stage.setTitle("Eliminar flujo");
                stage.setScene(scene);
                stage.show();
                break;

            case "Relac Biom/Cat":
                fxmlLoader = new FXMLLoader(Inicio.class.getResource("EliminarRelacionBiomCat.fxml"));
                scene = new Scene(fxmlLoader.load(), 600, 600);
                stage = new Stage();
                stage.setTitle("Eliminar relacion biomasa/catalizador");
                stage.setScene(scene);
                stage.show();
                break;

            case "Material Relac":
                fxmlLoader = new FXMLLoader(Inicio.class.getResource("EliminarMaterialRelac.fxml"));
                scene = new Scene(fxmlLoader.load(), 600, 600);
                stage = new Stage();
                stage.setTitle("Eliminar Material Relac");
                stage.setScene(scene);
                stage.show();
                break;

            case "Temp Pirolisis":
                fxmlLoader = new FXMLLoader(Inicio.class.getResource("EliminarTempPirolisis.fxml"));
                scene = new Scene(fxmlLoader.load(), 600, 600);
                stage = new Stage();
                stage.setTitle("Eliminar Temperatura Pirolisis");
                stage.setScene(scene);
                stage.show();
                break;

            case "Catalizador":
                fxmlLoader = new FXMLLoader(Inicio.class.getResource("EliminarCatalizador.fxml"));
                scene = new Scene(fxmlLoader.load(), 600, 600);
                stage = new Stage();
                stage.setTitle("Eliminar Catalizador");
                stage.setScene(scene);
                stage.show();
                break;
        }
    }


}
