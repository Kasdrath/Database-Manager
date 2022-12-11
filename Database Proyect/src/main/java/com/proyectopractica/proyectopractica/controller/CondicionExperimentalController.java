package com.proyectopractica.proyectopractica.controller;

import com.proyectopractica.proyectopractica.modelo.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.ParsePosition;

public class CondicionExperimentalController {

    private final ObservableList<TablaCondicion> tablaCondicionx = FXCollections.observableArrayList(); //DEBE ESTAR PARA VISUALIZAR TABLA
    ObservableList<String> lista1 = FXCollections.observableArrayList();
    ObservableList<String> lista2 = FXCollections.observableArrayList();
    ObservableList<String> lista3 = FXCollections.observableArrayList();
    ObservableList<Integer> lista4 = FXCollections.observableArrayList();



    @FXML
    private TextField agregarcompuestoTF;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ComboBox comboMate;

    @FXML
    private ComboBox comboCata;

    @FXML
    private ComboBox comboTasa;

    @FXML
    private ComboBox comboTemp;

    @FXML
    private TextField nombrexpTF;

    @FXML
    private Label ok;

    @FXML
    private Pane pane;

    @FXML
    private TextField porcentTF;

    @FXML
    private TableView<?> tablaDatos;

    @FXML
    public void initialize() {
        pane.setVisible(false);
        ok.setVisible(false);
        DecimalFormat format = new DecimalFormat( "#.0" );
        porcentTF.setTextFormatter( new TextFormatter<>(c ->
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
        ///LLENADO COMBOBOX
        Connection c = null;
        Statement stmt = null;
        try {
            /*comboMate.getItems().clear();
            comboCata.getItems().clear();
            comboTasa.getItems().clear();
            comboTemp.getItems().clear();*/
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "hola123");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT id_material_relac,material_relac FROM material_relac");
            while (rs.next()){
                lista1.add(new TablaMaterial(rs.getInt("id_material_relac"),rs.getString("material_relac")).getMaterial_relac());
            }


            rs = stmt.executeQuery("SELECT id_catalizador,catalizador FROM catalizador");
            while (rs.next()){
                lista2.add(new TablaCatalizador(rs.getInt("id_catalizador"),rs.getString("catalizador")).getCatalizador());
            }


            rs = stmt.executeQuery("SELECT id_tasa_calent,tasa_calent FROM tasa_calent");
            while (rs.next()){
                lista3.add(new TablaTasa(rs.getInt("id_tasa_calent"),rs.getString("tasa_calent")).getTasa_calent());
            }


            rs = stmt.executeQuery("SELECT id_temp_pirol,temp_pirolisis FROM temp_pirolisis");
            while (rs.next()){
                lista4.add(new TablaTemp(rs.getInt("id_temp_pirol"),rs.getInt("temp_pirolisis")).getTemp_pirolisis());
            }


            rs.close();
            stmt.close();
            c.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        comboMate.setItems(lista1);
        comboCata.setItems(lista2);
        comboTasa.setItems(lista3);
        comboTemp.setItems(lista4);

    }


    @FXML
    void agregarExperimento(ActionEvent event) {

    }

    @FXML
    void calcularArea(ActionEvent event) {
        Paint rojo = Paint.valueOf("red");
        Paint verde = Paint.valueOf("green");
        Connection c = null;
        Statement stmt = null;
        try {

            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "hola123");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs;
            float aux = 0;
            rs = stmt.executeQuery("select sum(porcentarea) as suma_total from cond_exp;");
            while (rs.next()){
                aux = rs.getFloat("suma_total");
            }
            if (aux == 100){
                ok.setTextFill(verde);
                ok.setText("OK!");
                ok.setVisible(true);
            }
            else if (aux < 100){
                ok.setTextFill(rojo);
                ok.setText("El porcentaje es menor a 100!");
                ok.setVisible(true);
            }
            else if (aux > 100){
                ok.setTextFill(rojo);
                ok.setText("El porcentaje es mayor a 100!");
                ok.setVisible(true);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void habilitarIngreso(ActionEvent event) {
        pane.setVisible(true);
    }

    @FXML
    void nuevoExperimento(ActionEvent event) {
        Connection c = null;
        Statement stmt = null;
        try {

            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "hola123");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT id_material_relac,material_relac FROM material_relac");
            while (rs.next()){
                lista1.add(new TablaMaterial(rs.getInt("id_material_relac"),rs.getString("material_relac")).getMaterial_relac());
            }


            rs = stmt.executeQuery("SELECT id_catalizador,catalizador FROM catalizador");
            while (rs.next()){
                lista2.add(new TablaCatalizador(rs.getInt("id_catalizador"),rs.getString("catalizador")).getCatalizador());
            }


            rs = stmt.executeQuery("SELECT id_tasa_calent,tasa_calent FROM tasa_calent");
            while (rs.next()){
                lista3.add(new TablaTasa(rs.getInt("id_tasa_calent"),rs.getString("tasa_calent")).getTasa_calent());
            }


            rs = stmt.executeQuery("SELECT id_temp_pirol,temp_pirolisis FROM temp_pirolisis");
            while (rs.next()){
                lista4.add(new TablaTemp(rs.getInt("id_temp_pirol"),rs.getInt("temp_pirolisis")).getTemp_pirolisis());
            }


            rs.close();
            stmt.close();
            c.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        comboMate.setItems(lista1);
        comboCata.setItems(lista2);
        comboTasa.setItems(lista3);
        comboTemp.setItems(lista4);

    }

    @FXML
    void revisarExistencia(ActionEvent event) {

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
            ResultSet rs = stmt.executeQuery("SELECT id_cond_exp,nombre_exp,catalizador,temp_pirolisis,material_relac,tasa_calent,compq_name,porcentarea FROM cond_exp");
            tablaCondicionx.clear();
            while (rs.next()){
                tablaCondicionx.add(new TablaCondicion(rs.getInt("id_cond_exp"),rs.getString("nombre_exp"),rs.getString("catalizador"),rs.getInt("temp_pirolisis"),rs.getString("material_relac"),rs.getString("tasa_calent"),rs.getString("compq_name"), rs.getFloat("porcentarea")));
            }
            Scene scene = anchorPane.getScene();
            @SuppressWarnings("unchecked")
            TableView<TablaCondicion> tableView = tableView = (TableView<TablaCondicion>) scene.lookup("#tablaDatos");
            tableView.setItems(tablaCondicionx);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
