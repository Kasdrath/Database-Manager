package com.proyectopractica.proyectopractica.controller.insertar;

import com.proyectopractica.proyectopractica.Inicio;
import com.proyectopractica.proyectopractica.modelo.TablaRelacion;
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

public class InsertarRelacionBiomCatController {


    private final ObservableList<TablaRelacion> tablaRelacion = FXCollections.observableArrayList(); //DEBE ESTAR PARA VISUALIZAR TABLA

    @FXML
    private TableView tablaRelacionx;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField relaciontf;



    @FXML
    void ingresarRelacion(ActionEvent event) throws IOException {
        if (relaciontf.getText().trim().isEmpty()) { //VER QUE TEXTFIELD NO ESTE VACIO
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
                rs = stmt.executeQuery("select relacbiomcata from relacbiomcata");
                while (rs.next()){
                    if(relaciontf.getText().equals(rs.getString("relacbiomcata"))){ //COMPRUEBA QUE NO AGREGE UN NOMBRE QUE YA ESTE
                        FXMLLoader fxmlLoader = new FXMLLoader(Inicio.class.getResource("Error.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 356, 113);
                        Stage stage = new Stage();
                        stage.setTitle("ERROR");
                        stage.setScene(scene);
                        stage.show();
                        return;
                    }
                }
                String agregarFamily = "INSERT INTO relacbiomcata VALUES" + "(DEFAULT,"+"'"+relaciontf.getText()+"'"+");";
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
        relaciontf.clear();



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
            ResultSet rs = stmt.executeQuery("SELECT id_relac,relacbiomcata FROM relacbiomcata");
            tablaRelacion.clear();
            while (rs.next()){
                tablaRelacion.add(new TablaRelacion(rs.getInt("id_relac"),rs.getString("relacbiomcata")));
            }
            Scene scene = anchorPane.getScene();
            @SuppressWarnings("unchecked")
            TableView<TablaRelacion> tableView = tableView = (TableView<TablaRelacion>) scene.lookup("#tablaRelacionx");
            tableView.setItems(tablaRelacion);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
