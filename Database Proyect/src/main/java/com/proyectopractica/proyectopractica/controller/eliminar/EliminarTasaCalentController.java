package com.proyectopractica.proyectopractica.controller.eliminar;

import com.proyectopractica.proyectopractica.Inicio;
import com.proyectopractica.proyectopractica.modelo.TablaTasa;
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

public class EliminarTasaCalentController {
    private final ObservableList<TablaTasa> tablaTasaa = FXCollections.observableArrayList(); //DEBE ESTAR PARA VISUALIZAR TABLA

    ObservableList<String> lista = FXCollections.observableArrayList();


    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ComboBox combo1;

    @FXML
    private TableView tablaCalent;

    public void initialize(){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "hola123");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT id_tasa_calent,tasa_calent FROM tasa_calent");
            combo1.getItems().clear();

            while (rs.next()){
                lista.add(new TablaTasa(rs.getInt("id_tasa_calent"),rs.getString("tasa_calent")).getTasa_calent());
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
    void borrarTasa(ActionEvent event) {
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
            rs = stmt.executeQuery("select id_tasa_calent, tasa_calent from tasa_calent");
            while (rs.next()){
                if (combo1.getValue().equals(rs.getString("tasa_calent"))){
                    idfam = rs.getInt("id_tasa_calent");
                }
            }
            String sql = "delete from tasa_calent where id_tasa_calent = "+idfam;
            stmt.executeUpdate(sql);

            ////////////LIMPIAR CB Y VOLVER A LLENAR
            combo1.getItems().clear();
            rs = stmt.executeQuery("SELECT id_tasa_calent,tasa_calent FROM tasa_calent"); //LLENAR COMBOBOX DESDE BDD
            while (rs.next()){
                lista.add(new TablaTasa(rs.getInt("id_tasa_calent"),rs.getString("tasa_calent")).getTasa_calent());
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
            ResultSet rs = stmt.executeQuery("SELECT id_tasa_calent,tasa_calent FROM tasa_calent");
            tablaTasaa.clear();
            while (rs.next()){
                tablaTasaa.add(new TablaTasa(rs.getInt("id_tasa_calent"),rs.getString("tasa_calent")));
            }
            Scene scene = anchorPane.getScene();
            @SuppressWarnings("unchecked")
            TableView<TablaTasa> tableView = tableView = (TableView<TablaTasa>) scene.lookup("#tablaCalent");
            tableView.setItems(tablaTasaa);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }



}
