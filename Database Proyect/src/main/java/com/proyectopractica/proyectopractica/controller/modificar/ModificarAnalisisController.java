package com.proyectopractica.proyectopractica.controller.modificar;

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
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.ParsePosition;

public class ModificarAnalisisController {

    ObservableList<Integer> lista = FXCollections.observableArrayList();
    private final ObservableList<TablaAnalisisT> tablaAnalisisx = FXCollections.observableArrayList(); //DEBE ESTAR PARA VISUALIZAR TABLA


    @FXML
    private ComboBox combo1;

    @FXML
    private TextField modificartiempotf;

    @FXML
    private TableView<?> tablaAnalisis;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    public void initialize() {
        DecimalFormat format = new DecimalFormat( "#.0" );
        modificartiempotf.setTextFormatter( new TextFormatter<>(c ->
        {
            if ( c.getControlNewText().isEmpty() )
            {
                return c;
            }

            ParsePosition parsePosition = new ParsePosition( 0 );
            Object object = format.parse( c.getControlNewText(), parsePosition );

            if ( object == null || parsePosition.getIndex() < c.getControlNewText().length() )
            {
                return null;
            }
            else
            {
                return c;
            }
        }));
        //////////////////////////////////////
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
    void modificarTiempo(ActionEvent event) throws IOException {
        if (modificartiempotf.getText().trim().isEmpty()) { //VER QUE TEXTFIELD NO ESTE VACIO
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
                rs = stmt.executeQuery("select tiempo from analisis_en_t");
                while (rs.next()){
                    if(Integer.parseInt(modificartiempotf.getText()) == (rs.getInt("tiempo"))){ //COMPRUEBA QUE NO AGREGE UN NOMBRE QUE YA ESTE
                        FXMLLoader fxmlLoader = new FXMLLoader(Inicio.class.getResource("Error.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 356, 113);
                        Stage stage = new Stage();
                        stage.setTitle("ERROR");
                        stage.setScene(scene);
                        stage.show();
                        return;
                    }
                }
                int idtiempo = 0;
                rs = stmt.executeQuery("select id_analisis_en_t,tiempo from analisis_en_t");
                while (rs.next()){
                    if (Integer.parseInt(combo1.getValue().toString()) == (rs.getInt("tiempo"))){
                        idtiempo = rs.getInt("id_analisis_en_t");
                    }
                }
                stmt.executeUpdate("update analisis_en_t set tiempo ="+"'"+Integer.parseInt(modificartiempotf.getText())+"'"+"where id_analisis_en_t ="+idtiempo+";");

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
        modificartiempotf.clear();


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
