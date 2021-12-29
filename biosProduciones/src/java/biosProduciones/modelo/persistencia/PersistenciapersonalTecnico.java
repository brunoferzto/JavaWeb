/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biosProduciones.modelo.persistencia;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import biosProduciones.modelo.compartidos.beans.datatypes.DTperTecnico;
import biosProduciones.modelo.compartidos.beans.excepciones.ExcepcionPersistencia;
import biosProduciones.modelo.compartidos.beans.excepciones.ExcepcionPersonalizada;

/**
 *
 * @author Bruno
 */
class PersistenciapersonalTecnico implements IpersistenciapersonalTecnico {
    
    private static PersistenciapersonalTecnico instancia = null;
    
    
    public static PersistenciapersonalTecnico getInstancia() {
        if (instancia == null) {
            instancia = new PersistenciapersonalTecnico();
        }
        
        return instancia;
    }
    
    private PersistenciapersonalTecnico() {
        
    }
    
    @Override
    public void registro(DTperTecnico per)throws ExcepcionPersonalizada {
    {
        Connection conexion = null;
        
       CallableStatement consulta = null; 
     try {
            
        conexion = Conexion.getConexion();

       consulta = conexion.prepareCall("{ CALL RegistropersonalTecnico (?, ?, ?) }");
       
       consulta.setInt(1, per.getCedula());
       consulta.setString(2, per.getNombre());
       consulta.setString(3, per.getCargo());
       
       int filasAf = consulta.executeUpdate();
       
       if(filasAf < 1)
          throw new ExcepcionPersistencia("Error en DB, No se pudo Agregar");
          
        } 
        catch (ExcepcionPersonalizada ex) {throw ex; }
        catch (Exception ex){throw new ExcepcionPersistencia("No se pudo Agregar Personal", ex); }
        finally { Conexion.cerrarRecursos(consulta,conexion);}
    }
    }
    
    @Override
    public void modificar (DTperTecnico per)throws ExcepcionPersonalizada {
    {
        Connection conexion = null;
        
       CallableStatement consulta = null; 
     try {
         
       conexion = Conexion.getConexion();
       consulta = conexion.prepareCall("{ CALL ModificarpersonalTecnico (?, ?, ?) }");
       
       consulta.setInt(1, per.getCedula());
       consulta.setString(2, per.getNombre());
       consulta.setString(3, per.getCargo());
       
       int filasAf = consulta.executeUpdate();
       
       if(filasAf < 1)
          throw new ExcepcionPersistencia("Error en DB, No se pudo Modificar");
          
        } 
        catch (ExcepcionPersonalizada ex) {throw ex; }
        catch (Exception ex){throw new ExcepcionPersistencia("No se pudo Modificar Personal", ex); }
        finally { Conexion.cerrarRecursos(consulta,conexion);}
    }
    }
    
    @Override
     public void eliminar (DTperTecnico per)throws ExcepcionPersonalizada {
    {
        Connection conexion = null;
        
       CallableStatement consulta = null; 
     try {
       
         conexion = Conexion.getConexion();
            
       consulta = conexion.prepareCall("{ CALL EliminarpersonalTecnico (?) }");
       
       consulta.setInt(1, per.getCedula());

       int filasAf = consulta.executeUpdate();
       
       if(filasAf < 1)
          throw new ExcepcionPersistencia("Error en DB, No se pudo Eliminar");
          
        } 
        catch (ExcepcionPersonalizada ex) {throw ex; }
        catch (Exception ex){throw new ExcepcionPersistencia("No se pudo Eliminar Personal", ex); }
        finally { Conexion.cerrarRecursos(consulta,conexion);}
    }
    }
     
    @Override
     public List<DTperTecnico> listarPersonalTecnico() throws ExcepcionPersonalizada   {
     Connection conexion = null;
        
       CallableStatement consulta = null; 
        ResultSet resultados = null;
        
        try {
       
            conexion = Conexion.getConexion();
            
       consulta = conexion.prepareCall("{ CALL ListarpersonalTecnico () }");
       
       resultados = consulta.executeQuery();
       
        List<DTperTecnico> lista = new ArrayList();
              DTperTecnico perTecnico ;
            
              Integer cedula;
              String nombre,cargo;
              
              while(resultados.next())
              {
                  cedula = (int)resultados.getObject("Cedula");
                  nombre = resultados.getString("NombreCompleto");
                  cargo = resultados.getString("Cargo");
                  
                  perTecnico = new DTperTecnico(cargo,cedula,nombre);
                  
                  lista.add(perTecnico);              
              }
              return lista;
              
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo lista Personal ", ex);
        } finally {
            Conexion.cerrarRecursos(resultados, consulta, conexion);
        } 
    }
     
     protected List<DTperTecnico> tecnicosdeSpot(int idspot) throws ExcepcionPersonalizada   {
     Connection conexion = null;
        
       CallableStatement consulta = null; 
        ResultSet resultados = null;
        
        try {
               conexion = Conexion.getConexion();
    
       consulta = conexion.prepareCall("{ CALL TecnicosdeSpot (?) }");
       
       consulta.setInt(1, idspot);

            
       
       resultados = consulta.executeQuery();
       
        List<DTperTecnico> lista = new ArrayList();
              DTperTecnico perTecnico ;
            
              Integer cedula;
              String nombre,cargo;
              
              while(resultados.next())
              {
                  cedula = (int)resultados.getObject("Cedula");
                  nombre = resultados.getString("NombreCompleto");
                  cargo = resultados.getString("Cargo");
                  
                  perTecnico = new DTperTecnico(cargo,cedula,nombre);
                  
                  lista.add(perTecnico);              
              }
              return lista;
              
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo listar Personal Tecnico de Spot ", ex);
        } finally {
            Conexion.cerrarRecursos(resultados, consulta, conexion);
        } 
    }
     
     @Override
     public DTperTecnico buscarTecnico(int ced) throws ExcepcionPersonalizada   {
     Connection conexion = null;
        
       CallableStatement consulta = null; 
        ResultSet resultados = null;
        
        try {
        
       conexion = Conexion.getConexion();
       consulta = conexion.prepareCall("{ CALL BuscarTecnico (?) }");
       
       consulta.setInt(1, ced);

            
       
       resultados = consulta.executeQuery();
       
              DTperTecnico perTecnico = null ;
            
              Integer cedula;
              String nombre,cargo;
              
              if(resultados.next())
              {
                  cedula = (int)resultados.getObject("Cedula");
                  nombre = resultados.getString("NombreCompleto");
                  cargo = resultados.getString("Cargo");
                  
                  perTecnico = new DTperTecnico(cargo,cedula,nombre);
                  
              }
              return perTecnico;
              
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo buscar Personal Tecnico", ex);
        } finally {
            Conexion.cerrarRecursos(resultados, consulta, conexion);
        } 
    }
}
