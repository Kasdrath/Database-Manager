package com.proyectopractica.proyectopractica.controller.modificar;

import com.proyectopractica.proyectopractica.Inicio;
import com.proyectopractica.proyectopractica.modelo.TablaComp;
import com.proyectopractica.proyectopractica.modelo.TablaFamilia;
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

public class ModificarCompuestoQuimicoController {

    private final ObservableList<TablaComp> tablaComp = FXCollections.observableArrayList(); //DEBE ESTAR PARA VISUALIZAR TABLA
    ObservableList<String> lista = FXCollections.observableArrayList();
    private final ObservableList<TablaFamilia> tablaFamilias = FXCollections.observableArrayList(); //DEBE ESTAR PARA VISUALIZAR TABLA


    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField casTF;

    @FXML
    private ComboBox combo1;

    @FXML
    private TextField nombreTF;

    @FXML
    private TableView tabladatos;

    @FXML
    public void initialize() {
        ///LLENADO COMBOBOX
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
    void modificarCompuesto(ActionEvent event) throws IOException {
        if (casTF.getText().trim().isEmpty() || nombreTF.getText().trim().isEmpty()) { //VER QUE TEXTFIELD NO ESTE VACIO
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
                int aux = 0;
                rs = stmt.executeQuery("select compq_cas from componentequimico");
                while (rs.next()){
                    if (casTF.getText().equals(rs.getString("compq_cas"))){
                        aux = 1;
                    }
                }
                if (aux == 0){
                    FXMLLoader fxmlLoader = new FXMLLoader(Inicio.class.getResource("Error.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 356, 113);
                    Stage stage = new Stage();
                    stage.setTitle("ERROR");
                    stage.setScene(scene);
                    stage.show();
                    return;
                }
                rs = stmt.executeQuery("select compq_name from componentequimico");
                while (rs.next()){
                    if (nombreTF.getText().equals(rs.getString("compq_name"))){
                        FXMLLoader fxmlLoader = new FXMLLoader(Inicio.class.getResource("Error.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 356, 113);
                        Stage stage = new Stage();
                        stage.setTitle("ERROR");
                        stage.setScene(scene);
                        stage.show();
                        return;
                    }
                }
                stmt.executeUpdate("update componentequimico set compq_name ="+"'"+nombreTF.getText()+"'"+","+"familia_name = "+"'"+combo1.getValue()+"'"+"where compq_cas ="+"'"+casTF.getText()+"'"+";");
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
        casTF.clear();
        nombreTF.clear();

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
            ResultSet rs = stmt.executeQuery("SELECT compq_cas,compq_name,familia_name FROM componentequimico");
            tablaComp.clear();
            while (rs.next()){
                tablaComp.add(new TablaComp(rs.getString("compq_cas"),rs.getString("compq_name"),rs.getString("familia_name")));
            }
            Scene scene = anchorPane.getScene();
            @SuppressWarnings("unchecked")
            TableView<TablaComp> tableView = tableView = (TableView<TablaComp>) scene.lookup("#tablaCompx");
            tableView.setItems(tablaComp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
