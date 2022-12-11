package com.proyectopractica.proyectopractica.controller.modificar;

import com.proyectopractica.proyectopractica.Inicio;
import com.proyectopractica.proyectopractica.modelo.TablaRelacion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class ModificarRelacionBiomCatController {

    private final ObservableList<TablaRelacion> tablaRelacion = FXCollections.observableArrayList(); //DEBE ESTAR PARA VISUALIZAR TABLA
    ObservableList<String> lista = FXCollections.observableArrayList();


    @FXML
    private ComboBox combo1;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField modificarbiomcattf;

    @FXML
    private TableView tablaRelacionx;

    public void initialize(){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "hola123");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT id_relac,relacbiomcata FROM relacbiomcata");
            combo1.getItems().clear();

            while (rs.next()){
                lista.add(new TablaRelacion(rs.getInt("id_relac"),rs.getString("relacbiomcata")).getRelacBiomcata());
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
    void modificarRelacion(ActionEvent event) throws IOException {
        if (modificarbiomcattf.getText().trim().isEmpty()) { //VER QUE TEXTFIELD NO ESTE VACIO
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
                if (combo1.getValue() == null) { //VALIDACION QUE COMBOBOX NO ESTE VACIO
                    FXMLLoader fxmlLoader = new FXMLLoader(Inicio.class.getResource("Error.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 356, 113);
                    Stage stage = new Stage();
                    stage.setTitle("ERROR");
                    stage.setScene(scene);
                    stage.show();
                    return;
                }
                rs = stmt.executeQuery("select relacbiomcata from relacbiomcata");
                while (rs.next()){
                    if(modificarbiomcattf.getText().equals(rs.getString("relacbiomcata"))){ //COMPRUEBA QUE NO AGREGE UN NOMBRE QUE YA ESTE
                        FXMLLoader fxmlLoader = new FXMLLoader(Inicio.class.getResource("Error.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 356, 113);
                        Stage stage = new Stage();
                        stage.setTitle("ERROR");
                        stage.setScene(scene);
                        stage.show();
                        return;
                    }
                }
                int idfam = 0;
                rs = stmt.executeQuery("select id_relac, relacbiomcata from relacbiomcata");
                while (rs.next()){
                    if (combo1.getValue().equals(rs.getString("relacbiomcata"))){
                        idfam = rs.getInt("id_relac");
                    }
                }
                stmt.executeUpdate("update relacbiomcata set relacbiomcata ="+"'"+modificarbiomcattf.getText()+"'"+"where id_relac ="+idfam+";");

                combo1.getItems().clear();
                rs = stmt.executeQuery("SELECT id_relac,relacbiomcata FROM relacbiomcata"); //LLENAR COMBOBOX DESDE BDD
                while (rs.next()){
                    lista.add(new TablaRelacion(rs.getInt("id_relac"),rs.getString("relacbiomcata")).getRelacBiomcata());
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
        modificarbiomcattf.clear();



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
