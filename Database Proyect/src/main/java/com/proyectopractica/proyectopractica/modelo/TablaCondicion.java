package com.proyectopractica.proyectopractica.modelo;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TablaCondicion {
    private final SimpleIntegerProperty id_cond_exp;
    private final SimpleStringProperty nombre_exp;
    private final SimpleStringProperty catalizador;
    private final SimpleIntegerProperty temp_pirolisis;
    private final SimpleStringProperty material_relac;
    private final SimpleStringProperty tasa_calent;
    private final SimpleStringProperty compq_name;
    private final SimpleFloatProperty porcentarea;


    public TablaCondicion(SimpleIntegerProperty id_cond_exp, SimpleStringProperty nombre_exp,SimpleStringProperty catalizador,SimpleIntegerProperty temp_pirolisis,SimpleStringProperty material_relac,SimpleStringProperty tasa_calent,SimpleStringProperty compq_name,SimpleFloatProperty porcentarea) {
        this.id_cond_exp = id_cond_exp;
        this.nombre_exp = nombre_exp;
        this.catalizador = catalizador;
        this.temp_pirolisis = temp_pirolisis;
        this.material_relac = material_relac;
        this.tasa_calent = tasa_calent;
        this.compq_name = compq_name;
        this.porcentarea = porcentarea;
    }
    public TablaCondicion(int id_cond_exp, String nombre_exp, String catalizador, int temp_pirolisis, String material_relac, String tasa_calent, String compq_name, float porcentarea) {
        this.id_cond_exp = new SimpleIntegerProperty(id_cond_exp);
        this.nombre_exp = new SimpleStringProperty(nombre_exp);
        this.catalizador = new SimpleStringProperty(catalizador);
        this.temp_pirolisis = new SimpleIntegerProperty(temp_pirolisis);
        this.material_relac = new SimpleStringProperty(material_relac);
        this.tasa_calent = new SimpleStringProperty(tasa_calent);
        this.compq_name = new SimpleStringProperty(compq_name);
        this.porcentarea = new SimpleFloatProperty(porcentarea);
    }
    public int getId_cond_exp(){
        return this.id_cond_exp.get();
    }
    public void setId_cond_exp(int id_cond_exp){
        this.id_cond_exp.set(id_cond_exp);
    }
    public String getNombre_exp(){return this.nombre_exp.get();}
    public void setNombre_exp(String nombre_exp){this.nombre_exp.set(nombre_exp);}
    public String getCatalizador(){return this.catalizador.get();}
    public void setCatalizador(String catalizador){this.catalizador.set(catalizador);}
    public int getTemp_pirolisis(){
        return this.temp_pirolisis.get();
    }
    public void setTemp_pirolisis(int temp_pirolisis){
        this.temp_pirolisis.set(temp_pirolisis);
    }
    public String getMaterial_relac(){return this.material_relac.get();}
    public void setMaterial_relac(String material_relac){this.material_relac.set(material_relac);}
    public String getTasa_calent(){return this.tasa_calent.get();}
    public void setTasa_calent(String tasa_calent){this.tasa_calent.set(tasa_calent);}
    public String getCompq_name(){return this.compq_name.get();}
    public void setCompq_name(String compq_name){this.compq_name.set(compq_name);}
    public float getPorcentarea(){return this.porcentarea.get();}
    public void setPorcentarea(float porcentarea){this.porcentarea.set(porcentarea);}

    public SimpleIntegerProperty id_cond_expProperty (){
        return id_cond_exp;
    }
    public SimpleStringProperty nombre_expProperty (){
        return nombre_exp;
    }
    public SimpleStringProperty catalizadorProperty(){
        return catalizador;
    }
    public SimpleIntegerProperty temp_pirolisisProperty(){
        return temp_pirolisis;
    }
    public SimpleStringProperty relacbiomcataProperty(){
        return material_relac;
    }
    public SimpleStringProperty tasa_calentProperty(){
        return tasa_calent;
    }
    public SimpleStringProperty compq_nameProperty(){return compq_name;}
    public SimpleFloatProperty porcentareaProperty(){return porcentarea;}
}
