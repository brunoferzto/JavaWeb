/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biosProduciones.modelo.logica;

import biosProduciones.modelo.compartidos.beans.datatypes.DTPersonal;
import biosProduciones.modelo.compartidos.beans.datatypes.DTperActores;
import biosProduciones.modelo.compartidos.beans.datatypes.DTperLogistico;
import biosProduciones.modelo.compartidos.beans.datatypes.DTperTecnico;
import biosProduciones.modelo.compartidos.beans.excepciones.ExcepcionLogica;
import biosProduciones.modelo.compartidos.beans.excepciones.ExcepcionPersonalizada;
import biosProduciones.modelo.persistencia.FabricaPersistencia;
import biosProduciones.modelo.persistencia.IpersistenciapersonalActores;
import biosProduciones.modelo.persistencia.IpersistenciapersonalLogistico;
import biosProduciones.modelo.persistencia.IpersistenciapersonalTecnico;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruno
 */
public class LogicaPersonal implements IlogicaPersonal{
    
     private static LogicaPersonal instancia = null;
    
    
    public static LogicaPersonal getInstancia() {
        if (instancia == null) {
            instancia = new LogicaPersonal();
        }
        
        return instancia;
    }
    
    // SI A ESTA LOGICA LLEGA X TIPO INICIALIZAR SOLO ESE. CAMBIAR
  private IpersistenciapersonalActores persistenciaActores = FabricaPersistencia.getPersistenciapersonalActores();
  private IpersistenciapersonalLogistico persistenciaLogistico = FabricaPersistencia.getPersistenciapersonalLogistico();
  private IpersistenciapersonalTecnico persistenciaTecnico = FabricaPersistencia.getPersistenciapersonalTecnico();

     private LogicaPersonal() {
        
    }
     
     @Override
     public void registrarRecursoHumano (DTPersonal persona) throws ExcepcionPersonalizada{
         
         validar(persona);
         
         if(persona instanceof DTperActores)
             persistenciaActores.registro((DTperActores) persona);
                        
         if(persona instanceof DTperLogistico)
             persistenciaLogistico.registro((DTperLogistico) persona);

         if(persona instanceof DTperTecnico)
             persistenciaTecnico.registro((DTperTecnico) persona);
        
                 

     }
     
     @Override
     public void modificarRecursoHumano(DTPersonal persona) throws ExcepcionPersonalizada{
        
                  validar(persona);

           if(persona instanceof DTperActores)
             persistenciaActores.modificar((DTperActores) persona);
                        
         if(persona instanceof DTperLogistico)
             persistenciaLogistico.modificar((DTperLogistico) persona);

         if(persona instanceof DTperTecnico)
             persistenciaTecnico.modificar((DTperTecnico) persona);
    }
    
     @Override
     public void eliminarRecursoHumano (int cedula) throws ExcepcionPersonalizada{
        
          DTPersonal persona =  buscarPersonal(cedula);
            
           if(persona instanceof DTperActores)
             persistenciaActores.eliminar((DTperActores) persona);
                        
         if(persona instanceof DTperLogistico)
             persistenciaLogistico.eliminar((DTperLogistico) persona);

         if(persona instanceof DTperTecnico)
             persistenciaTecnico.eliminar((DTperTecnico) persona);
    }
     
     protected void validar (DTPersonal persona) throws ExcepcionPersonalizada{
         
         if(persona == null)
            throw new ExcepcionLogica("Error,  el personal ingresado es nulo");

         if(Integer.toString(persona.getCedula()).length() != 8)
          throw new ExcepcionLogica("Error, la cédula debe tener 8 caracteres");
         
         if(persona.getNombre().length() > 30 || "".equals(persona.getNombre()) )
          throw new ExcepcionLogica("Error,  el nombre  supera los 30 caracteres o está vacío");     

         if(persona instanceof DTperActores){                        
             
             if(!"Femenino".equals(((DTperActores) persona).getSexo()) && !"Masculino".equals(((DTperActores) persona).getSexo()))
          throw new ExcepcionLogica("Error, el sexo debe ser 'Masculino' o 'Femenino'");     

             if(((DTperActores) persona).getFoto().length() > 255)
           throw new ExcepcionLogica("Error en foto, supera el máximo de caracteres");  
             
          
         }
                        
         if(persona instanceof DTperLogistico){
             if(!"Casting".equals(((DTperLogistico) persona).getArea()) && !"Locaciones".equals(((DTperLogistico) persona).getArea()) && !"Contaduria".equals(((DTperLogistico) persona).getArea()) && !"Otro".equals(((DTperLogistico) persona).getArea()) )
                  throw new ExcepcionLogica("Error, en 'Area', no se ha especificado correctamente");     
                 }
         

         if(persona instanceof DTperTecnico){
             if(!"Director".equals(((DTperTecnico) persona).getCargo()) && !"Camarógrafo".equals(((DTperTecnico) persona).getCargo()) && !"Editor".equals(((DTperTecnico) persona).getCargo()) && !"Otro".equals(((DTperTecnico) persona).getCargo()) )
                  throw new ExcepcionLogica("Error, en 'Cargo', no se ha especificado correctamente");
             
         }         
     }
     
      @Override
     public List<DTPersonal> listarPersonal() throws ExcepcionPersonalizada{
         
         List<DTPersonal> lista = new ArrayList();              

        for(DTPersonal persona : persistenciaActores.listarpersonalActores())
        {
            lista.add(persona);
        }
        
        for(DTPersonal persona : persistenciaLogistico.listarpersonalLogistico())
        {
            lista.add(persona);
        }
        
        for(DTPersonal persona : persistenciaTecnico.listarPersonalTecnico())
        {
            lista.add(persona);
        }
        
        
           
         
         return lista;
     }
     
     @Override
     public DTPersonal buscarPersonal(int ced) throws ExcepcionPersonalizada{
         DTPersonal persona;
         
         persona = persistenciaActores.buscarActor(ced);
         
         if(persona == null){
          persona = persistenciaLogistico.buscarLogistico(ced);
          if(persona == null)
              persona = persistenciaTecnico.buscarTecnico(ced);
         }                           
       return persona; 
     }
}
