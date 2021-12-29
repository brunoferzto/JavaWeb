/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biosProduciones.modelo.logica;

import java.util.List;
import biosProduciones.modelo.compartidos.beans.datatypes.DTEquipos;
import biosProduciones.modelo.compartidos.beans.datatypes.DTPersonal;
import biosProduciones.modelo.compartidos.beans.datatypes.DTSpot;
import biosProduciones.modelo.compartidos.beans.excepciones.ExcepcionLogica;
import biosProduciones.modelo.compartidos.beans.excepciones.ExcepcionPersonalizada;
import biosProduciones.modelo.persistencia.FabricaPersistencia;
import biosProduciones.modelo.persistencia.IpersistenciaSpot;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 *
 * @author Bruno
 */
class LogicaSpot implements IlogicaSpot{
    
    private static LogicaSpot instancia = null;
    
    
    public static LogicaSpot getInstancia() {
        if (instancia == null) {
            instancia = new LogicaSpot();
        }
        
        return instancia;
    }
    
    
    private IpersistenciaSpot persistencia = FabricaPersistencia.getPersistenciaSpot();
    
    
    private LogicaSpot() {
        
    }
    
    @Override
    public void registrarSpot (DTSpot spot) throws ExcepcionPersonalizada
    {    
        validar(spot);       
        persistencia.registrarSpot(spot);
    }
   
    @Override
   public void asignarEquipoaSpot (DTSpot spot, DTEquipos eqp) throws ExcepcionPersonalizada {
        
        // Traigo los Spot que ya estén asociados al Equipo en cuestión.
       List<DTSpot> lista = persistencia.equiposAsosciados(eqp);
       
       int a = 0;
       
      for(DTSpot spotasociado : lista){
          
          if(spotasociado.getId() == spot.getId())
            throw new ExcepcionLogica("Error, este equipo ya está asociado a este Spot");
         
        
          if(spot.getFechaFinal().before(spotasociado.getFechaInicio()) || spot.getFechaInicio().after(spotasociado.getFechaFinal()))                          
         {
             a = a + 1;
             
             if(a == lista.size())
             {
                persistencia.asignarEquipoaSpot(spot, eqp);
             }
         }
         else
          throw new ExcepcionLogica("Error, el equipo está en uso en las fechas del Spot");     
      }
      
      if(lista.size() == 0)
             {
             persistencia.asignarEquipoaSpot(spot,eqp);
             }
       
       
        
    }
   
    @Override
   public void asignarPersonalaSpot (DTSpot spot, DTPersonal per)throws ExcepcionPersonalizada {
   
      List<DTSpot> lista = persistencia.personalAsosciados(per);
      
       int a = 0;
       
      for(DTSpot spotasociado : lista){
          
          if(spotasociado.getId() == spot.getId())
            throw new ExcepcionLogica("Error, esta Persona ya está asociada a este Spot");
         
                           
          if(spot.getFechaFinal().before(spotasociado.getFechaInicio()) || spot.getFechaInicio().after(spotasociado.getFechaFinal()))                          
         {
             a = a + 1;
             
             if(a == lista.size())
             {
                persistencia.asignarPersonalaSpot(spot, per);
             }
         }
         else
          throw new ExcepcionLogica("Error, la persona está ocupada en las fechas del Spot");     
      }
       if(lista.size() == 0)
             {
             persistencia.asignarPersonalaSpot(spot,per);
             }
      
   }
   
    @Override
   public DTSpot buscarSpot (int id) throws ExcepcionPersonalizada{
       
       return persistencia.buscarSpot(id);
             
   }
      
    protected void validar (DTSpot spot) throws ExcepcionPersonalizada {
    
       if(spot == null)
       throw new ExcepcionLogica("Error, Spot nulo");
        
        if(spot.getTitulo().length() > 30)
         throw new ExcepcionLogica("El Titulo supera el largo permitido (30)");
        
       if(spot.getPrecio() < 0)
        throw new ExcepcionLogica("El precio no puede ser menor a 0");
       
       if(spot.getFechaFinal().before(spot.getFechaInicio()))
        throw new ExcepcionLogica("Error, Fecha Inicio, Fecha Final");    
    }
    
}
