package com.proyectopractica.proyectopractica.controller.modificar;

import com.proyectopractica.proyectopractica.Inicio;
import com.proyectopractica.proyectopractica.modelo.TablaFlujo;
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

public class ModificarFlujoController {

    private final ObservableList<TablaFlujo> tablaFlujo = FXCollections.observableArrayList(); //DEBE ESTAR PARA VISUALIZAR TABLA
    ObservableList<Integer> lista = FXCollections.observableArrayList();


    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ComboBox combo1;

    @FXML
    private TextField modificarflujotf;

    @FXML
    public void initialize() {
        DecimalFormat format = new DecimalFormat( "#.0" );
        modificarflujotf.setTextFormatter( new TextFormatter<>(c ->
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
            rs = stmt.executeQuery("SELECT id_flujo,flujo FROM flujo");
            combo1.getItems().clear();
            while (rs.next()){
                lista.add(new TablaFlujo(rs.getInt("id_flujo"),rs.getInt("flujo")).getFlujo());
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
    void modificarFlujo(ActionEvent event) throws IOException {
        if (modificarflujotf.getText().trim().isEmpty()) { //VER QUE TEXTFIELD NO ESTE VACIO
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
                rs = stmt.executeQuery("select flujo from flujo");
                while (rs.next()){
                    if(Integer.parseInt(modificarflujotf.getText()) == (rs.getInt("flujo"))){ //COMPRUEBA QUE NO AGREGE UN NOMBRE QUE YA ESTE
                        FXMLLoader fxmlLoader = new FXMLLoader(Inicio.class.getResource("Error.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 356, 113);
                        Stage stage = new Stage();
                        stage.setTitle("ERROR");
                        stage.setScene(scene);
                        stage.show();
                        return;
                    }
                }
                int idflujo = 0;
                rs = stmt.executeQuery("select id_flujo,flujo from flujo");
                while (rs.next()){
                    if (Integer.parseInt(combo1.getValue().toString()) == (rs.getInt("flujo"))){
                        idflujo = rs.getInt("id_flujo");
                    }
                }
                stmt.executeUpdate("update flujo set flujo ="+"'"+Integer.parseInt(modificarflujotf.getText())+"'"+"where id_flujo ="+idflujo+";");

                combo1.getItems().clear();
                rs = stmt.executeQuery("SELECT id_flujo,flujo FROM flujo"); //LLENAR COMBOBOX DESDE BDD
                while (rs.next()){
                    lista.add(new TablaFlujo(rs.getInt("id_flujo"),rs.getInt("flujo")).getFlujo());
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
        modificarflujotf.clear();


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
            ResultSet rs = stmt.executeQuery("SELECT id_flujo,flujo FROM flujo");
            tablaFlujo.clear();
            while (rs.next()){
                tablaFlujo.add(new TablaFlujo(rs.getInt("id_flujo"),rs.getInt("flujo")));
            }
            Scene scene = anchorPane.getScene();
            @SuppressWarnings("unchecked")
            TableView<TablaFlujo> tableView = tableView = (TableView<TablaFlujo>) scene.lookup("#tablaFlujo");
            tableView.setItems(tablaFlujo);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
