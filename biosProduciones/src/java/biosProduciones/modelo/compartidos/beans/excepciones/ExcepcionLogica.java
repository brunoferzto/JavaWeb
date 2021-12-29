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
public class ExcepcionLogica extends ExcepcionPersonalizada implements Serializable {
    
    public ExcepcionLogica() {
        
    }
    
    public ExcepcionLogica(String mensaje) {
        super(mensaje);
    }
    
    public ExcepcionLogica(String mensaje, Exception excepcionInterna) {
        super(mensaje, excepcionInterna);
    }
    
}
