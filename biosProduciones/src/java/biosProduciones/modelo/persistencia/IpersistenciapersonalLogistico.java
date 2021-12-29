/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biosProduciones.modelo.persistencia;

import java.util.List;
import biosProduciones.modelo.compartidos.beans.datatypes.DTperLogistico;
import biosProduciones.modelo.compartidos.beans.datatypes.DTperTecnico;
import biosProduciones.modelo.compartidos.beans.excepciones.ExcepcionPersonalizada;

/**
 *
 * @author Bruno
 */
public interface IpersistenciapersonalLogistico {
    
    void registro(DTperLogistico per)throws ExcepcionPersonalizada;
    
    void modificar (DTperLogistico per)throws ExcepcionPersonalizada;
    
    void eliminar (DTperLogistico per)throws ExcepcionPersonalizada;
    
    List<DTperLogistico> listarpersonalLogistico() throws ExcepcionPersonalizada;
        
    DTperLogistico buscarLogistico(int cedula) throws ExcepcionPersonalizada ;
    
}
