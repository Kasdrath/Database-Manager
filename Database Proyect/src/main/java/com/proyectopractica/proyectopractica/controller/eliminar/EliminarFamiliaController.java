package com.proyectopractica.proyectopractica.controller.eliminar;

import com.proyectopractica.proyectopractica.Inicio;
import com.proyectopractica.proyectopractica.modelo.TablaFamilia;
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

public class EliminarFamiliaController {
    ObservableList<String> lista = FXCollections.observableArrayList();
    @FXML
    private ComboBox combo1;
    @FXML
    private AnchorPane anchorPane;
    private final ObservableList<TablaFamilia> tablaFamilias = FXCollections.observableArrayList();

    public void initialize(){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "hola123");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT familia_id,familia_name FROM familia");
            combo1.getItems().clear();
            while (rs.next()){
                lista.add(new TablaFamilia(rs.getInt("familia_id"),rs.getString("familia_name")).getFamilia_name());
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
    void borrarFamilia(ActionEvent event) {
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
                rs = stmt.executeQuery("select familia_id, familia_name from familia");
                while (rs.next()){
                    if (combo1.getValue().equals(rs.getString("familia_name"))){
                        idfam = rs.getInt("familia_id");
                    }
                }
                String sql = "delete from familia where familia_id = "+idfam;
                stmt.executeUpdate(sql);

                ////////////LIMPIAR CB Y VOLVER A LLENAR
                combo1.getItems().clear();
                rs = stmt.executeQuery("SELECT familia_id,familia_name FROM familia"); //LLENAR COMBOBOX DESDE BDD
                while (rs.next()){
                    lista.add(new TablaFamilia(rs.getInt("familia_id"),rs.getString("familia_name")).getFamilia_name());
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

            ResultSet rs = stmt.executeQuery("SELECT FAMILIA_ID,FAMILIA_NAME FROM FAMILIA");
            tablaFamilias.clear();
            while (rs.next()){
                tablaFamilias.add(new TablaFamilia(rs.getInt("familia_id"),rs.getString("familia_name")));
            }
            Scene scene = anchorPane.getScene();
            @SuppressWarnings("unchecked")
            TableView<TablaFamilia> tableView = tableView = (TableView<TablaFamilia>) scene.lookup("#tablaFamilia");
            tableView.setItems(tablaFamilias);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
