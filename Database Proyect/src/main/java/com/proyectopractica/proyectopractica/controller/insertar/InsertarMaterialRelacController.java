package com.proyectopractica.proyectopractica.controller.insertar;

import com.proyectopractica.proyectopractica.Inicio;
import com.proyectopractica.proyectopractica.modelo.TablaMaterial;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class InsertarMaterialRelacController {

    private final ObservableList<TablaMaterial> tablaMaterialx = FXCollections.observableArrayList(); //DEBE ESTAR PARA VISUALIZAR TABLA



    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField nombrematerialtf;


    @FXML
    void agregarMaterial(ActionEvent event) throws IOException {
        if (nombrematerialtf.getText().trim().isEmpty()) { //VER QUE TEXTFIELD NO ESTE VACIO
            FXMLLoader fxmlLoader = new FXMLLoader(Inicio.class.getResource("Error.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 356, 113);
            Stage stage = new Stage();
            stage.setTitle("ERROR");
            stage.setScene(scene);
            stage.show();
            return;
        }
        else {
            Connection c = null;
            Statement stmt = null;
            try {
                Class.forName("org.postgresql.Driver");
                c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "hola123");
                c.setAutoCommit(false);
                stmt = c.createStatement();
                ResultSet rs;
                ////////////////////////////Validacion unique en tabla
                rs = stmt.executeQuery("select material_relac from material_relac");
                while (rs.next()){
                    if(nombrematerialtf.getText().equals(rs.getString("material_relac"))){ //COMPRUEBA QUE NO AGREGE UN NOMBRE QUE YA ESTE
                        FXMLLoader fxmlLoader = new FXMLLoader(Inicio.class.getResource("Error.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 356, 113);
                        Stage stage = new Stage();
                        stage.setTitle("ERROR");
                        stage.setScene(scene);
                        stage.show();
                        return;
                    }
                }
                String agregarFamily = "INSERT INTO material_relac VALUES" + "(DEFAULT,"+"'"+nombrematerialtf.getText()+"'"+");";
                stmt.executeUpdate(agregarFamily);
                rs.close();
                stmt.close();
                c.commit();
                c.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        nombrematerialtf.clear();

    }

    @FXML
    void mostrarTabla(ActionEvent event) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "hola123");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id_material_relac,material_relac FROM material_relac");
            tablaMaterialx.clear();
            while (rs.next()){
                tablaMaterialx.add(new TablaMaterial(rs.getInt("id_material_relac"),rs.getString("material_relac")));
            }
            Scene scene = anchorPane.getScene();
            @SuppressWarnings("unchecked")
            TableView<TablaMaterial> tableView = tableView = (TableView<TablaMaterial>) scene.lookup("#tablaMaterial");
            tableView.setItems(tablaMaterialx);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
