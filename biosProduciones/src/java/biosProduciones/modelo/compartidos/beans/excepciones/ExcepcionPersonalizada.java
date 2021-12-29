/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biosProduciones.modelo.compartidos.beans.excepciones;

import java.io.Serializable;

/**
 *
 * @author Bruno
 */
public class ExcepcionPersonalizada extends Exception implements Serializable {
    
    public ExcepcionPersonalizada() {
        
    }
    
    public ExcepcionPersonalizada(String mensaje) {
        super(mensaje);
    }
    
    public ExcepcionPersonalizada(String mensaje, Exception excepcionInterna) {
        super(mensaje, excepcionInterna);
    } 
    
}
    

