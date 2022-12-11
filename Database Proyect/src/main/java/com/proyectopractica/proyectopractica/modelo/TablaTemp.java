package com.proyectopractica.proyectopractica.modelo;

import javafx.beans.property.SimpleIntegerProperty;

public class TablaTemp {
    private final SimpleIntegerProperty id_temp_pirol;
    private final SimpleIntegerProperty temp_pirolisis;


    public TablaTemp(SimpleIntegerProperty id_temp_pirol, SimpleIntegerProperty temp_pirolisis) {
        this.id_temp_pirol = id_temp_pirol;
        this.temp_pirolisis = temp_pirolisis;
    }
    public TablaTemp(int id_temp_pirol, int temp_pirolisis) {
        this.id_temp_pirol = new SimpleIntegerProperty(id_temp_pirol);
        this.temp_pirolisis = new SimpleIntegerProperty(temp_pirolisis);
    }
    public int getId_temp_pirol(){
        return this.id_temp_pirol.get();
    }
    public void setId_temp_pirol(int id_temp_pirol){
        this.id_temp_pirol.set(id_temp_pirol);
    }
    public int getTemp_pirolisis(){
        return this.temp_pirolisis.get();
    }
    public void setTemp_pirolisis(int temp_pirolisis){
        this.temp_pirolisis.set(temp_pirolisis);
    }
    public SimpleIntegerProperty id_temp_pirolProperty (){
        return id_temp_pirol;
    }
    public SimpleIntegerProperty temp_pirolisisProperty (){
        return temp_pirolisis;
    }
}
