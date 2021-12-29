/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biosProduciones.modelo.compartidos.beans.datatypes;

import java.io.Serializable;

/**
 *
 * @author Bruno
 */
public class DTEquipos implements Serializable  {
    
    private int id;
    private String tipo,marca,modelo,descripcion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public DTEquipos() {
        this(0,"n/d","n/d","n/d","n/d");
    }  
    
    public DTEquipos(int id, String tipo, String marca, String modelo, String descripcion) {
        setId(id);
        setTipo(tipo);
        setMarca(marca);
        setModelo(modelo);
        setDescripcion(descripcion);
    }
    
    
    
}
