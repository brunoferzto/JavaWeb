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
public class DTperTecnico extends DTPersonal implements Serializable {
    
   private String cargo; 

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public DTperTecnico() {
    }

    public DTperTecnico(String cargo, int cedula, String nombre) {
        super(cedula, nombre);
        setCargo(cargo);
    }

    @Override
    public String toString() {
        return "Técnico";
    }
   
    
   
    
}
