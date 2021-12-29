/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biosProduciones.modelo.logica;

import biosProduciones.modelo.compartidos.beans.datatypes.DTPersonal;
import biosProduciones.modelo.compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.util.List;

/**
 *
 * @author Bruno
 */
public interface IlogicaPersonal {
    
    void registrarRecursoHumano (DTPersonal persona) throws ExcepcionPersonalizada;
    
    void modificarRecursoHumano(DTPersonal persona) throws ExcepcionPersonalizada;
    
    void eliminarRecursoHumano (int ced) throws ExcepcionPersonalizada;
        
     List<DTPersonal> listarPersonal() throws ExcepcionPersonalizada;
    
     DTPersonal buscarPersonal(int ced) throws ExcepcionPersonalizada;
}
