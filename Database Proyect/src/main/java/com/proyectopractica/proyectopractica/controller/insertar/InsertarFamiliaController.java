package com.proyectopractica.proyectopractica.controller.insertar;


import com.proyectopractica.proyectopractica.Inicio;
import com.proyectopractica.proyectopractica.modelo.TablaFamilia;
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

public class InsertarFamiliaController {



    private final ObservableList<TablaFamilia> tablaFamilias = FXCollections.observableArrayList(); //DEBE ESTAR PARA VISUALIZAR TABLA

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField nombrefamiliatf;

   /*@FXML
    public void initialize() {
        DecimalFormat format = new DecimalFormat( "#.0" );
        nombrefamiliatf.setTextFormatter( new TextFormatter<>(c ->
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
    }*/

    @FXML
    void agregarFamilia(ActionEvent event) throws IOException {
        if (nombrefamiliatf.getText().trim().isEmpty()) { //VER QUE TEXTFIELD NO ESTE VACIO
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
                rs = stmt.executeQuery("select familia_name from familia");
                while (rs.next()){
                    if(nombrefamiliatf.getText().equals(rs.getString("familia_name"))){ //COMPRUEBA QUE NO AGREGE UN NOMBRE QUE YA ESTE
                        FXMLLoader fxmlLoader = new FXMLLoader(Inicio.class.getResource("Error.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 356, 113);
                        Stage stage = new Stage();
                        stage.setTitle("ERROR");
                        stage.setScene(scene);
                        stage.show();
                        return;
                    }
                }
                int idfamiliamaximo = 0;
                String obtenerid = "select max(familia_id) from familia";
                rs = stmt.executeQuery(obtenerid);
                while (rs.next()) {
                    idfamiliamaximo = rs.getInt("max");
                    idfamiliamaximo = idfamiliamaximo + 1;
                }
                String agregarFamily = "INSERT INTO FAMILIA VALUES" + "(" + idfamiliamaximo + "," + "'" + nombrefamiliatf.getText() + "'" + ");";
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
        nombrefamiliatf.clear();

    }

    @FXML
    void mostrarTabla(ActionEvent event) {
       /* if (nombrefamiliatf.getText().trim().isEmpty()){

        }
        ver si textfield esta vacio
        */

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "hola123");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT FAMILIA_ID,FAMILIA_NAME FROM FAMILIA");
            tablaFamilias.clear();
            while (rs.next()){
                tablaFamilias.add(new TablaFamilia(rs.getInt("familia_id"),rs.getString("familia_name")));
            }
            Scene scene = anchorPane.getScene();
            @SuppressWarnings("unchecked")
            TableView<TablaFamilia> tableView = tableView = (TableView<TablaFamilia>) scene.lookup("#tablaFamilia");
            tableView.setItems(tablaFamilias);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
