/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biosProduciones.modelo.persistencia;

/**
 *
 * @author Bruno
 */
public class FabricaPersistencia {
    
    public static IpersistenciaEquipos getPersistenciaEquipos ()
    { return PersistenciaEquipos.getInstancia(); }
    
    public static IpersistenciaSpot getPersistenciaSpot ()
    { return PersistenciaSpot.getInstancia(); }
    
    public static IpersistenciapersonalActores getPersistenciapersonalActores ()
    { return PersistenciapersonalActores.getInstancia(); }
    
    public static IpersistenciapersonalLogistico getPersistenciapersonalLogistico ()
    { return PersistenciapersonalLogistico.getInstancia(); }
    
     public static IpersistenciapersonalTecnico getPersistenciapersonalTecnico ()
    { return PersistenciapersonalTecnico.getInstancia(); }
    
      public static IpersistenciaUsuario getPersistenciaUsuario ()
    { return PersistenciaUsuario.getInstancia(); }
    
        
    
    
    
    }
    

