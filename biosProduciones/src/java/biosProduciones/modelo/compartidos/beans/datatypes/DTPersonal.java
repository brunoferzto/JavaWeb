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
public class DTPersonal implements Serializable {
    
    private int cedula;
    private String nombre;

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
     
    public DTPersonal() {
         this(0, "n/");
    }

    public DTPersonal(int cedula, String nombre) {
        setNombre(nombre);
        setCedula(cedula);
    }

   
    
    
    
    
}
