/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biosProduciones.modelo.compartidos.beans.datatypes;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Bruno
 */
public class DTSpot implements Serializable  {
    
    private int id, precio; 
    private String titulo;
    private Date fechaInicio, fechaFinal;
    private List<DTPersonal> recursosH;
    private List<DTEquipos> equipos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public List<DTPersonal> getRecursosH() {
        return recursosH;
    }

    public void setRecursosH(List<DTPersonal> recursosH) {
        this.recursosH = recursosH;
    }

    public List<DTEquipos> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<DTEquipos> equipos) {
        this.equipos = equipos;
    }

    public DTSpot() {
//        this(0,0,"n/d","n/d",getFechaInicio().toString(),getFechaFinal());
    }

    public DTSpot(int id, int precio, String titulo, Date fechaInicio, Date fechaFinal) {
        setId(id);
        setPrecio(precio);
        setTitulo(titulo);
        setFechaInicio(fechaInicio);
        setFechaFinal(fechaFinal);
    }
    
    
    
}
