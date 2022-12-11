package com.proyectopractica.proyectopractica.controller.eliminar;

import com.proyectopractica.proyectopractica.Inicio;
import com.proyectopractica.proyectopractica.modelo.TablaCatalizador;
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

public class EliminarCatalizadorController {

    private final ObservableList<TablaCatalizador> tablaCata = FXCollections.observableArrayList(); //DEBE ESTAR PARA VISUALIZAR TABLA
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
            rs = stmt.executeQuery("SELECT id_catalizador,catalizador FROM catalizador");
            combo1.getItems().clear();
            while (rs.next()){
                lista.add(new TablaCatalizador(rs.getInt("id_catalizador"),rs.getString("catalizador")).getCatalizador());
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
    void borrarCatalizador(ActionEvent event) {
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
            rs = stmt.executeQuery("select id_catalizador, catalizador from catalizador");
            while (rs.next()){
                if (combo1.getValue().equals(rs.getString("catalizador"))){
                    idfam = rs.getInt("id_catalizador");
                }
            }
            String sql = "delete from catalizador where id_catalizador = "+idfam;
            stmt.executeUpdate(sql);

            ////////////LIMPIAR CB Y VOLVER A LLENAR
            combo1.getItems().clear();
            rs = stmt.executeQuery("SELECT id_catalizador,catalizador FROM catalizador"); //LLENAR COMBOBOX DESDE BDD
            while (rs.next()){
                lista.add(new TablaCatalizador(rs.getInt("id_catalizador"),rs.getString("catalizador")).getCatalizador());
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
            ResultSet rs = stmt.executeQuery("SELECT id_catalizador,catalizador FROM catalizador");
            tablaCata.clear();
            while (rs.next()){
                tablaCata.add(new TablaCatalizador(rs.getInt("id_catalizador"),rs.getString("catalizador")));
            }
            Scene scene = anchorPane.getScene();
            @SuppressWarnings("unchecked")
            TableView<TablaCatalizador> tableView = tableView = (TableView<TablaCatalizador>) scene.lookup("#tablaCalent");
            tableView.setItems(tablaCata);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

}
