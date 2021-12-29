/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biosProduciones.modelo.persistencia;

import java.util.List;
import biosProduciones.modelo.compartidos.beans.datatypes.DTperActores;
import biosProduciones.modelo.compartidos.beans.excepciones.ExcepcionPersonalizada;

/**
 *
 * @author Bruno
 */
public interface IpersistenciapersonalActores {
    
    
    void registro(DTperActores per)throws ExcepcionPersonalizada;
    
    void modificar(DTperActores per)throws ExcepcionPersonalizada;
    
    void eliminar(DTperActores per)throws ExcepcionPersonalizada;
    
    List<DTperActores> listarpersonalActores() throws ExcepcionPersonalizada;
        
    DTperActores buscarActor(int ced) throws ExcepcionPersonalizada;

    
}