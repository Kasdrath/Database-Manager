package com.proyectopractica.proyectopractica.controller.eliminar;

import com.proyectopractica.proyectopractica.Inicio;
import com.proyectopractica.proyectopractica.modelo.TablaAnalisisT;
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

public class EliminarAnalisisEnTController {
    private final ObservableList<TablaAnalisisT> tablaAnalisisx = FXCollections.observableArrayList(); //DEBE ESTAR PARA VISUALIZAR TABLA
    ObservableList<Integer> lista = FXCollections.observableArrayList();

    @FXML
    private ComboBox combo1;

    @FXML
    private TableView tablaAnalisis;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    public void initialize(){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "hola123");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT id_analisis_en_t,tiempo FROM analisis_en_t");
            combo1.getItems().clear();
            while (rs.next()){
                lista.add(new TablaAnalisisT(rs.getInt("id_analisis_en_t"),rs.getInt("tiempo")).getTiempo());
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
    void borrarTiempo(ActionEvent event) {
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
            rs = stmt.executeQuery("select id_analisis_en_t,tiempo from analisis_en_t");
            while (rs.next()){
                if (combo1.getValue().equals(rs.getInt("tiempo"))){
                    idfam = rs.getInt("id_analisis_en_t");
                }
            }
            String sql = "delete from analisis_en_t where id_analisis_en_t = "+idfam;
            stmt.executeUpdate(sql);

            ////////////LIMPIAR CB Y VOLVER A LLENAR
            combo1.getItems().clear();
            rs = stmt.executeQuery("SELECT id_analisis_en_t,tiempo FROM analisis_en_t"); //LLENAR COMBOBOX DESDE BDD
            while (rs.next()){
                lista.add(new TablaAnalisisT(rs.getInt("id_analisis_en_t"),rs.getInt("tiempo")).getTiempo());

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
            ResultSet rs = stmt.executeQuery("SELECT id_analisis_en_t,tiempo FROM analisis_en_t");
            tablaAnalisisx.clear();
            while (rs.next()){
                tablaAnalisisx.add(new TablaAnalisisT(rs.getInt("id_analisis_en_t"),rs.getInt("tiempo")));
            }
            Scene scene = anchorPane.getScene();
            @SuppressWarnings("unchecked")
            TableView<TablaAnalisisT> tableView = tableView = (TableView<TablaAnalisisT>) scene.lookup("#tablaAnalisis");
            tableView.setItems(tablaAnalisisx);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
