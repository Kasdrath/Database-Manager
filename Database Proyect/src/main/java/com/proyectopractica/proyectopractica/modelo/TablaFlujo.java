package com.proyectopractica.proyectopractica.modelo;

import javafx.beans.property.SimpleIntegerProperty;

public class TablaFlujo {
    private final SimpleIntegerProperty id_flujo;
    private final SimpleIntegerProperty flujo;


    public TablaFlujo(SimpleIntegerProperty id_flujo, SimpleIntegerProperty flujo) {
        this.id_flujo = id_flujo;
        this.flujo = flujo;
    }
    public TablaFlujo(int id_flujo, int flujo) {
        this.id_flujo = new SimpleIntegerProperty(id_flujo);
        this.flujo = new SimpleIntegerProperty(flujo);
    }
    public int getId_flujo(){
        return this.id_flujo.get();
    }
    public void setId_flujo(int id_flujo){
        this.id_flujo.set(id_flujo);
    }
    public int getFlujo(){
        return this.flujo.get();
    }
    public void setFlujo(int flujo){
        this.flujo.set(flujo);
    }
    public SimpleIntegerProperty id_flujoProperty (){
        return id_flujo;
    }
    public SimpleIntegerProperty flujoProperty (){
        return flujo;
    }

}
