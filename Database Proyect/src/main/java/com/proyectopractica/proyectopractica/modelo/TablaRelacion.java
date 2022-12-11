package com.proyectopractica.proyectopractica.modelo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TablaRelacion {
    private final SimpleIntegerProperty id_relac;
    private final SimpleStringProperty relacbiomcata;



    public TablaRelacion(SimpleIntegerProperty id_relac, SimpleStringProperty relacbiomcata) {
        this.id_relac = id_relac;
        this.relacbiomcata = relacbiomcata;
    }
    public TablaRelacion(int id_relac, String relacbiomcata) {
        this.id_relac = new SimpleIntegerProperty(id_relac);
        this.relacbiomcata = new SimpleStringProperty(relacbiomcata);
    }

    public int getId_relac(){
        return this.id_relac.get();
    }
    public void setId_relac(int id_relac){
        this.id_relac.set(id_relac);
    }
    public String getRelacBiomcata(){
        return this.relacbiomcata.get();
    }
    public void setRelacBiomcata(String relacbiomcata){
        this.relacbiomcata.set(relacbiomcata);
    }
    public SimpleIntegerProperty id_relacProperty (){
        return id_relac;
    }
    public SimpleStringProperty relacbiomcataProperty (){
        return relacbiomcata;
    }



}
