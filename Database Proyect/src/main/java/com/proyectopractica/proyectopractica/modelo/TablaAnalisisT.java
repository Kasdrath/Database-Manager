package com.proyectopractica.proyectopractica.modelo;

import javafx.beans.property.SimpleIntegerProperty;


public class TablaAnalisisT {
    private final SimpleIntegerProperty id_analisis_en_t;
    private final SimpleIntegerProperty tiempo;


    public TablaAnalisisT(SimpleIntegerProperty id_analisis_en_t, SimpleIntegerProperty tiempo) {
        this.id_analisis_en_t = id_analisis_en_t;
        this.tiempo = tiempo;
    }
    public TablaAnalisisT(int id_analisis_en_t, int tiempo) {
        this.id_analisis_en_t = new SimpleIntegerProperty(id_analisis_en_t);
        this.tiempo = new SimpleIntegerProperty(tiempo);
    }
    public int getId_analisis_en_t(){
        return this.id_analisis_en_t.get();
    }
    public void setId_analisis_en_t(int id_analisis_en_t){
        this.id_analisis_en_t.set(id_analisis_en_t);
    }
    public int getTiempo(){
        return this.tiempo.get();
    }
    public void setTiempo(int tiempo){
        this.tiempo.set(tiempo);
    }
    public SimpleIntegerProperty id_analisis_en_tProperty (){
        return id_analisis_en_t;
    }
    public SimpleIntegerProperty tiempoProperty (){
        return tiempo;
    }
}
