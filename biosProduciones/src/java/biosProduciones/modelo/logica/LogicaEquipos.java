/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biosProduciones.modelo.logica;

import java.util.List;
import biosProduciones.modelo.compartidos.beans.datatypes.DTEquipos;
import biosProduciones.modelo.compartidos.beans.excepciones.ExcepcionLogica;
import biosProduciones.modelo.compartidos.beans.excepciones.ExcepcionPersonalizada;
import biosProduciones.modelo.persistencia.FabricaPersistencia;
import biosProduciones.modelo.persistencia.IpersistenciaEquipos;

/**
 *
 * @author Bruno
 */
public class LogicaEquipos implements IlogicaEquipos {
    
    
     private static LogicaEquipos instancia = null;
    
    
    public static LogicaEquipos getInstancia() {
        if (instancia == null) {
            instancia = new LogicaEquipos();
        }
        
        return instancia;
    }
    
      private IpersistenciaEquipos persistencia = FabricaPersistencia.getPersistenciaEquipos();
      
      private LogicaEquipos() {
        
    }
      
     @Override
      public void registarEquipo(DTEquipos recurso) throws ExcepcionPersonalizada{
          validar(recurso);
          
          persistencia.registarEquipo(recurso);
      }
      
     @Override
      public void modificarEquipo(DTEquipos recurso) throws ExcepcionPersonalizada{
             validar(recurso);
          
          persistencia.modificarEquipo(recurso);
      }
      
     @Override
     public void eliminarEquipo(int id) throws ExcepcionPersonalizada{
          
          persistencia.eliminarEquipo(id);
      }
      
     @Override
      public List<DTEquipos> listarEquipos() throws ExcepcionPersonalizada{
         
          return persistencia.listarEquipos();
      }
      
      protected void validar (DTEquipos recurso) throws ExcepcionPersonalizada{
          if(recurso == null)
       throw new ExcepcionLogica("Error, El Equipo es nulo");
          
          if(recurso.getId() < 0 )
        throw new ExcepcionLogica("Error, El 'Id' de Equipo es incorrecto");
         
          
          if(recurso.getDescripcion().length() > 200 )
        throw new ExcepcionLogica("Error, La 'Descripción' supera los 200 caracteres");
          
          if(recurso.getMarca().length() > 30 )
           throw new ExcepcionLogica("Error, La 'Marca' supera los 30 caracteres");

           if(recurso.getModelo().length() > 30 )
           throw new ExcepcionLogica("Error, El 'Modelo' supera los 30 caracteres");
           
//           if(recurso.getTipo().length() > 30 )
//           throw new ExcepcionLogica("Error, El 'Tipo' de Equipo supera los 30 caracteres");
           
           if(!"Camara".equals(recurso.getTipo()) && !"Grua".equals(recurso.getTipo()) && !"Reflector".equals(recurso.getTipo()) && !"Otro".equals(recurso.getTipo()))
              throw new ExcepcionLogica("Error, El 'Tipo' de Equipo no es correcto");

              
      }
      
      @Override 
      public DTEquipos buscarEquipo (int id) throws ExcepcionPersonalizada {
      return persistencia.buscarEquipo(id);
      }
     
      

    
    
}
