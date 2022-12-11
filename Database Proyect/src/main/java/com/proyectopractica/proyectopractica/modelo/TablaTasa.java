package com.proyectopractica.proyectopractica.modelo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TablaTasa {
    private final SimpleIntegerProperty id_tasa_calent;
    private final SimpleStringProperty tasa_calent;



    public TablaTasa(SimpleIntegerProperty id_tasa_calent, SimpleStringProperty tasa_calent) {
        this.id_tasa_calent = id_tasa_calent;
        this.tasa_calent = tasa_calent;
    }
    public TablaTasa(int id_tasa_calent, String tasa_calent) {
        this.id_tasa_calent = new SimpleIntegerProperty(id_tasa_calent);
        this.tasa_calent = new SimpleStringProperty(tasa_calent);
    }

    public int getId_tasa_calent(){
        return this.id_tasa_calent.get();
    }
    public void setId_tasa_calent(int id_tasa_calent){
        this.id_tasa_calent.set(id_tasa_calent);
    }
    public String getTasa_calent(){
        return this.tasa_calent.get();
    }
    public void setTasa_calent(String tasa_calent){
        this.tasa_calent.set(tasa_calent);
    }
    public SimpleIntegerProperty id_tasa_calentProperty (){
        return id_tasa_calent;
    }
    public SimpleStringProperty tasa_calentProperty (){
        return tasa_calent;
    }
    
}
