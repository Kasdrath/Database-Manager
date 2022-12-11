package com.proyectopractica.proyectopractica.controller.eliminar;

import com.proyectopractica.proyectopractica.Inicio;
import com.proyectopractica.proyectopractica.modelo.TablaMaterial;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class EliminarMaterialRelacController {

    private final ObservableList<TablaMaterial> tablaMaterialx = FXCollections.observableArrayList(); //DEBE ESTAR PARA VISUALIZAR TABLA
    ObservableList<String> lista = FXCollections.observableArrayList();


    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ComboBox combo1;

    public void initialize(){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "hola123");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT id_material_relac,material_relac FROM material_relac");
            combo1.getItems().clear();
            while (rs.next()){
                lista.add(new TablaMaterial(rs.getInt("id_material_relac"),rs.getString("material_relac")).getMaterial_relac());
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        combo1.setItems(lista);
    }
    


    @FXML
    void borrarMaterial(ActionEvent event) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "hola123");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs;
            if (combo1.getValue() == null) { //VALIDACION QUE COMBOBOX NO ESTE VACIO
                FXMLLoader fxmlLoader = new FXMLLoader(Inicio.class.getResource("Error.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 356, 113);
                Stage stage = new Stage();
                stage.setTitle("ERROR");
                stage.setScene(scene);
                stage.show();
                return;
            }
            int idfam = 0;
            rs = stmt.executeQuery("select id_material_relac, material_relac from material_relac");
            while (rs.next()){
                if (combo1.getValue().equals(rs.getString("material_relac"))){
                    idfam = rs.getInt("id_material_relac");
                }
            }
            String sql = "delete from material_relac where id_material_relac = "+idfam;
            stmt.executeUpdate(sql);

            ////////////LIMPIAR CB Y VOLVER A LLENAR
            combo1.getItems().clear();
            rs = stmt.executeQuery("SELECT id_material_relac,material_relac FROM material_relac"); //LLENAR COMBOBOX DESDE BDD
            while (rs.next()){
                lista.add(new TablaMaterial(rs.getInt("id_material_relac"),rs.getString("material_relac")).getMaterial_relac());
            }
            c.commit();
            rs.close();
            stmt.close();
            c.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    @FXML
    void verTabla(ActionEvent event) {
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
