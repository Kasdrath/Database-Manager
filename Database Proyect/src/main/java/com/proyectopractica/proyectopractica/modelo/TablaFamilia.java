package com.proyectopractica.proyectopractica.modelo;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;



public class TablaFamilia {
    private final SimpleIntegerProperty familia_id;
    private final SimpleStringProperty familia_name;



    public TablaFamilia(SimpleIntegerProperty familia_id, SimpleStringProperty familia_name) {
        this.familia_id = familia_id;
        this.familia_name = familia_name;
    }
    public TablaFamilia(int familia_id, String familia_name) {
        this.familia_id = new SimpleIntegerProperty(familia_id);
        this.familia_name = new SimpleStringProperty(familia_name);
    }

    public int getFamilia_id(){
        return this.familia_id.get();
    }
    public void setFamilia_id(int familia_id){
        this.familia_id.set(familia_id);
    }
    public String getFamilia_name(){
        return this.familia_name.get();
    }
    public void setFamilia_name(String familia_name){
        this.familia_name.set(familia_name);
    }
    public SimpleIntegerProperty familia_idProperty (){
        return familia_id;
    }
    public SimpleStringProperty familia_nameProperty (){
        return familia_name;
    }
}
