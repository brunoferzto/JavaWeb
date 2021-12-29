/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biosProduciones.modelo.persistencia;

import biosProduciones.modelo.compartidos.beans.datatypes.DTEquipos;
import biosProduciones.modelo.compartidos.beans.excepciones.ExcepcionPersistencia;
import biosProduciones.modelo.compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author Bruno
 */
 class PersistenciaUsuario implements IpersistenciaUsuario {
    
    private static PersistenciaUsuario instancia = null;
    
    
    public static PersistenciaUsuario getInstancia() {
        if (instancia == null) {
            instancia = new PersistenciaUsuario();
        }
        
        return instancia;
    }
    
    
    private PersistenciaUsuario() {
        
    }
    
    @Override
    public void logueo(String nom, String con) throws ExcepcionPersonalizada {
    {
        Connection conexion = null;
        
       CallableStatement consulta = null;
       
        ResultSet resultado = null;                     

        try {
            
       conexion = Conexion.getConexion();
       consulta = conexion.prepareCall("{ CALL Logueo (?,?) }");
       
       consulta.setString(1, nom);
       consulta.setString(2, con);

       
        resultado = consulta.executeQuery();       
        if (resultado.next())  // si ingreso solo nombre de usuario(correcto"admin"), viene algo de la bd.        
        {
                
        }
        else 
           throw new ExcepcionPersistencia("Usuario no logueado, datos inválidos");
         
        } 
        catch (ExcepcionPersonalizada ex) {throw ex; }
        catch (Exception ex){throw new ExcepcionPersistencia("Error, Usuario no Logueado", ex); }
        finally { Conexion.cerrarRecursos(consulta,conexion);}    

    }
    
    }
    
    
    
}
