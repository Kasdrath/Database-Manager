package com.proyectopractica.proyectopractica.controller.modificar;

import com.proyectopractica.proyectopractica.Inicio;
import com.proyectopractica.proyectopractica.modelo.TablaTemp;
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

public class ModificarTempPirolisisController {

    private final ObservableList<TablaTemp> tablaTemp = FXCollections.observableArrayList(); //DEBE ESTAR PARA VISUALIZAR TABLA
    ObservableList<Integer> lista = FXCollections.observableArrayList();



    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ComboBox combo1;

    @FXML
    private TextField modificartemptf;

    @FXML
    public void initialize() {
        DecimalFormat format = new DecimalFormat( "#.0" );
        modificartemptf.setTextFormatter( new TextFormatter<>(c ->
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
            rs = stmt.executeQuery("SELECT id_temp_pirol,temp_pirolisis FROM temp_pirolisis");
            combo1.getItems().clear();
            while (rs.next()){
                lista.add(new TablaTemp(rs.getInt("id_temp_pirol"),rs.getInt("temp_pirolisis")).getTemp_pirolisis());
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
    void modificarTemp(ActionEvent event) throws IOException {
        if (modificartemptf.getText().trim().isEmpty()) { //VER QUE TEXTFIELD NO ESTE VACIO
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
                rs = stmt.executeQuery("select temp_pirolisis from temp_pirolisis");
                while (rs.next()){
                    if(Integer.parseInt(modificartemptf.getText()) == (rs.getInt("temp_pirolisis"))){ //COMPRUEBA QUE NO AGREGE UN NOMBRE QUE YA ESTE
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
                rs = stmt.executeQuery("select id_temp_pirol,temp_pirolisis from temp_pirolisis");
                while (rs.next()){
                    if (Integer.parseInt(combo1.getValue().toString()) == (rs.getInt("temp_pirolisis"))){
                        idflujo = rs.getInt("id_temp_pirol");
                    }
                }
                stmt.executeUpdate("update temp_pirolisis set temp_pirolisis ="+"'"+Integer.parseInt(modificartemptf.getText())+"'"+"where id_temp_pirol ="+idflujo+";");

                combo1.getItems().clear();
                rs = stmt.executeQuery("SELECT id_temp_pirol,temp_pirolisis FROM temp_pirolisis"); //LLENAR COMBOBOX DESDE BDD
                while (rs.next()){
                    lista.add(new TablaTemp(rs.getInt("id_temp_pirol"),rs.getInt("temp_pirolisis")).getTemp_pirolisis());
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
        modificartemptf.clear();



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
            ResultSet rs = stmt.executeQuery("SELECT id_temp_pirol,temp_pirolisis FROM temp_pirolisis");
            tablaTemp.clear();
            while (rs.next()){
                tablaTemp.add(new TablaTemp(rs.getInt("id_temp_pirol"),rs.getInt("temp_pirolisis")));
            }
            Scene scene = anchorPane.getScene();
            @SuppressWarnings("unchecked")
            TableView<TablaTemp> tableView = tableView = (TableView<TablaTemp>) scene.lookup("#tablaTempp");
            tableView.setItems(tablaTemp);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
