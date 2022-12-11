package com.proyectopractica.proyectopractica.modelo;

import javafx.beans.property.SimpleStringProperty;

public class TablaComp {
    private final SimpleStringProperty compq_cas;
    private final SimpleStringProperty compq_name;
    private final SimpleStringProperty familia_name;


    public TablaComp(SimpleStringProperty compq_cas, SimpleStringProperty compq_name,SimpleStringProperty familia_name) {
        this.compq_cas = compq_cas;
        this.compq_name = compq_name;
        this.familia_name = familia_name;
    }
    public TablaComp(String compq_cas, String compq_name, String familia_name) {
        this.compq_cas = new SimpleStringProperty(compq_cas);
        this.compq_name = new SimpleStringProperty(compq_name);
        this.familia_name = new SimpleStringProperty(familia_name);
    }
    public String getCompq_cas(){
        return this.compq_cas.get();
    }
    public void setCompq_cas(String compq_cas){
        this.compq_cas.set(compq_cas);
    }
    public String getCompq_name(){
        return this.compq_name.get();
    }
    public void setCompq_name(String compq_name){
        this.compq_name.set(compq_name);
    }
    public String getFamilia_name(){
        return this.familia_name.get();
    }
    public void setFamilia_name(String familia_name){
        this.familia_name.set(familia_name);
    }
    public SimpleStringProperty compq_casProperty (){
        return compq_cas;
    }
    public SimpleStringProperty compq_nameProperty (){
        return compq_name;
    }
    public SimpleStringProperty familia_nameProperty(){
        return familia_name;
    }

}
