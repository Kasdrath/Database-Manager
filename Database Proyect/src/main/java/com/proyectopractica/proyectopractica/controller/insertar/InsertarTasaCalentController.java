package com.proyectopractica.proyectopractica.controller.insertar;

import com.proyectopractica.proyectopractica.Inicio;
import com.proyectopractica.proyectopractica.modelo.TablaTasa;
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

public class InsertarTasaCalentController {

    private final ObservableList<TablaTasa> tablaTasaa = FXCollections.observableArrayList(); //DEBE ESTAR PARA VISUALIZAR TABLA

    @FXML
    private AnchorPane anchorPane;


    @FXML
    private TextField tasacalenttf;

    @FXML
    void agregarTasa(ActionEvent event) throws IOException {
        if (tasacalenttf.getText().trim().isEmpty()) { //VER QUE TEXTFIELD NO ESTE VACIO
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
                rs = stmt.executeQuery("select tasa_calent from tasa_calent");
                while (rs.next()){
                    if(tasacalenttf.getText().equals(rs.getString("tasa_calent"))){ //COMPRUEBA QUE NO AGREGE UN NOMBRE QUE YA ESTE
                        FXMLLoader fxmlLoader = new FXMLLoader(Inicio.class.getResource("Error.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 356, 113);
                        Stage stage = new Stage();
                        stage.setTitle("ERROR");
                        stage.setScene(scene);
                        stage.show();
                        return;
                    }
                }
                String agregarFamily = "INSERT INTO tasa_calent VALUES" + "(DEFAULT,"+"'"+tasacalenttf.getText()+"'"+");";
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
        tasacalenttf.clear();


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
