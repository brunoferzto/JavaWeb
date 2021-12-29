/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biosProduciones.modelo.logica;

import biosProduciones.modelo.compartidos.beans.datatypes.DTSpot;
import biosProduciones.modelo.compartidos.beans.excepciones.ExcepcionPersonalizada;
import biosProduciones.modelo.persistencia.FabricaPersistencia;
import biosProduciones.modelo.persistencia.IpersistenciaSpot;
import biosProduciones.modelo.persistencia.IpersistenciaUsuario;

/**
 *
 * @author Bruno
 */
 class LogicaUsuario implements IlogicaUsuario{
    
    private static LogicaUsuario instancia = null;
    
    
    public static LogicaUsuario getInstancia() {
        if (instancia == null) {
            instancia = new LogicaUsuario();
        }
        
        return instancia;
    }
    
    
    private IpersistenciaUsuario persistencia = FabricaPersistencia.getPersistenciaUsuario();
    
    
    private LogicaUsuario() {
        
    }
    
     @Override
    public void logueo (String nom, String usu) throws ExcepcionPersonalizada
    {          
        persistencia.logueo(nom, usu);
    }
    
}
