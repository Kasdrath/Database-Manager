package com.proyectopractica.proyectopractica.modelo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TablaMaterial {
    private final SimpleIntegerProperty id_material_relac;
    private final SimpleStringProperty material_relac;



    public TablaMaterial(SimpleIntegerProperty id_material_relac, SimpleStringProperty material_relac) {
        this.id_material_relac = id_material_relac;
        this.material_relac = material_relac;
    }
    public TablaMaterial(int id_material_relac, String material_relac) {
        this.id_material_relac = new SimpleIntegerProperty(id_material_relac);
        this.material_relac = new SimpleStringProperty(material_relac);
    }

    public int getId_material_relac(){
        return this.id_material_relac.get();
    }
    public void setId_material_relac(int id_material_relac){
        this.id_material_relac.set(id_material_relac);
    }
    public String getMaterial_relac(){
        return this.material_relac.get();
    }
    public void setMaterial_relac(String material_relac){
        this.material_relac.set(material_relac);
    }
    public SimpleIntegerProperty id_material_relacProperty (){
        return id_material_relac;
    }
    public SimpleStringProperty material_relacProperty (){
        return material_relac;
    }
}
