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
public class DTperLogistico extends DTPersonal implements Serializable {
  
    private String area; 

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public DTperLogistico(String area) {
        this.area = area;
    }

    public DTperLogistico(String area, int cedula, String nombre) {
        super(cedula, nombre);
        setArea(area);
    }

    public DTperLogistico() {
        this("n/d");
    }
    
     @Override
    public String toString() {
        return "Logistico";
    }
  
}
