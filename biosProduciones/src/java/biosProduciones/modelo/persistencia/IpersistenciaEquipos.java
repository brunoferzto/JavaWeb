/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biosProduciones.modelo.persistencia;

import java.util.List;
import biosProduciones.modelo.compartidos.beans.datatypes.DTEquipos;
import biosProduciones.modelo.compartidos.beans.excepciones.ExcepcionPersonalizada;

/**
 *
 * @author Bruno
 */
public interface IpersistenciaEquipos {
    
    void registarEquipo(DTEquipos recurso) throws ExcepcionPersonalizada;
    
    void modificarEquipo(DTEquipos recurso) throws ExcepcionPersonalizada;
    
    void eliminarEquipo(int id) throws ExcepcionPersonalizada;
    
    List<DTEquipos> listarEquipos() throws ExcepcionPersonalizada;
    
    List<DTEquipos> equiposdeSpot(int idSpot) throws ExcepcionPersonalizada;
    
    DTEquipos buscarEquipo (int id) throws ExcepcionPersonalizada;
            
            
    
}
