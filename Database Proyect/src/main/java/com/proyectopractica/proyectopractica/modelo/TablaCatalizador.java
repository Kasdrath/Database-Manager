package com.proyectopractica.proyectopractica.modelo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TablaCatalizador {
    private final SimpleIntegerProperty id_catalizador;
    private final SimpleStringProperty catalizador;



    public TablaCatalizador(SimpleIntegerProperty id_catalizador, SimpleStringProperty catalizador) {
        this.id_catalizador = id_catalizador;
        this.catalizador = catalizador;
    }
    public TablaCatalizador(int id_catalizador, String catalizador) {
        this.id_catalizador = new SimpleIntegerProperty(id_catalizador);
        this.catalizador = new SimpleStringProperty(catalizador);
    }

    public int getId_catalizador(){
        return this.id_catalizador.get();
    }
    public void setId_catalizador(int id_catalizador){
        this.id_catalizador.set(id_catalizador);
    }
    public String getCatalizador(){
        return this.catalizador.get();
    }
    public void setCatalizador(String catalizador){
        this.catalizador.set(catalizador);
    }
    public SimpleIntegerProperty id_catalizadorProperty (){
        return id_catalizador;
    }
    public SimpleStringProperty catalizadorProperty (){
        return catalizador;
    }
}
