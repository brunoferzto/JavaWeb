/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biosProduciones.modelo.persistencia;

import java.util.List;
import biosProduciones.modelo.compartidos.beans.datatypes.DTperTecnico;
import biosProduciones.modelo.compartidos.beans.excepciones.ExcepcionPersonalizada;

/**
 *
 * @author Bruno
 */
public interface IpersistenciapersonalTecnico {
    
    void registro(DTperTecnico per)throws ExcepcionPersonalizada;
    
    void modificar (DTperTecnico per)throws ExcepcionPersonalizada;
    
    void eliminar (DTperTecnico per)throws ExcepcionPersonalizada;
    
    List<DTperTecnico> listarPersonalTecnico() throws ExcepcionPersonalizada;
        
    DTperTecnico buscarTecnico(int ced) throws ExcepcionPersonalizada;
    
}
