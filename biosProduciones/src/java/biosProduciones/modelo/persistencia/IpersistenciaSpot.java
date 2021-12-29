/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biosProduciones.modelo.persistencia;

import java.util.List;
import biosProduciones.modelo.compartidos.beans.datatypes.DTEquipos;
import biosProduciones.modelo.compartidos.beans.datatypes.DTPersonal;
import biosProduciones.modelo.compartidos.beans.datatypes.DTSpot;
import biosProduciones.modelo.compartidos.beans.excepciones.ExcepcionPersonalizada;

/**
 *
 * @author Bruno
 */
public interface IpersistenciaSpot {
    
   void registrarSpot (DTSpot spot) throws ExcepcionPersonalizada;
   
  void asignarEquipoaSpot (DTSpot spot, DTEquipos eqp) throws ExcepcionPersonalizada;
   
   void asignarPersonalaSpot (DTSpot spot,DTPersonal per) throws ExcepcionPersonalizada;
   
   DTSpot buscarSpot (int id) throws ExcepcionPersonalizada;
   
   List<DTSpot> equiposAsosciados (DTEquipos eqp) throws ExcepcionPersonalizada;
   
  List<DTSpot> personalAsosciados (DTPersonal per) throws ExcepcionPersonalizada;
    
    
    
}
