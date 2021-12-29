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
import biosProduciones.modelo.compartidos.beans.datatypes.DTperLogistico;
import biosProduciones.modelo.compartidos.beans.datatypes.DTperTecnico;
import biosProduciones.modelo.compartidos.beans.excepciones.ExcepcionPersistencia;
import biosProduciones.modelo.compartidos.beans.excepciones.ExcepcionPersonalizada;

/**
 *
 * @author Bruno
 */
class PersistenciapersonalLogistico implements IpersistenciapersonalLogistico {
    
    
    
    private static PersistenciapersonalLogistico instancia = null;
    
    
    public static PersistenciapersonalLogistico getInstancia() {
        if (instancia == null) {
            instancia = new PersistenciapersonalLogistico();
        }
        
        return instancia;
    }
    
    private PersistenciapersonalLogistico() {
        
    }
    
    @Override
    public void registro(DTperLogistico per)throws ExcepcionPersonalizada {
    {
        Connection conexion = null;
        
       CallableStatement consulta = null; 
     try {
       conexion = Conexion.getConexion();
     
       consulta = conexion.prepareCall("{ CALL RegistropersonalLogistico (?, ?, ?) }");
       
       consulta.setInt(1, per.getCedula());
       consulta.setString(2, per.getNombre());
       consulta.setString(3, per.getArea());
       
       int filasAf = consulta.executeUpdate();
       
       if(filasAf < 1)
          throw new ExcepcionPersistencia("Error en DB, No se pudo Agregar");
          
        } 
        catch (ExcepcionPersonalizada ex) {throw ex; }
        catch (Exception ex){throw new ExcepcionPersistencia("No se pudo Agregar", ex); }
        finally { Conexion.cerrarRecursos(consulta,conexion);}
    }
    }
    
    @Override
    public void modificar (DTperLogistico per)throws ExcepcionPersonalizada {
    {
        Connection conexion = null;
        
       CallableStatement consulta = null; 
     try {
         
         conexion = Conexion.getConexion();
            
       consulta = conexion.prepareCall("{ CALL ModificarpersonalLogistico (?, ?, ?) }");
       
       consulta.setInt(1, per.getCedula());
       consulta.setString(2, per.getNombre());
       consulta.setString(3, per.getArea());
       
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
     public void eliminar (DTperLogistico per)throws ExcepcionPersonalizada {
    {
        Connection conexion = null;
        
       CallableStatement consulta = null; 
     try {
                 conexion = Conexion.getConexion();
  
       consulta = conexion.prepareCall("{ CALL EliminarpersonalLogistico (?) }");
       
       consulta.setInt(1, per.getCedula());

       int filasAf = consulta.executeUpdate();
       
       if(filasAf < 1)
          throw new ExcepcionPersistencia("Error en DB, No se pudo Eliminar");
          
        } 
        catch (ExcepcionPersonalizada ex) {throw ex; }
        catch (Exception ex){throw new ExcepcionPersistencia("No se pudo Eliminar  Personal", ex); }
        finally { Conexion.cerrarRecursos(consulta,conexion);}
    }
    }
     
    @Override
     public List<DTperLogistico> listarpersonalLogistico() throws ExcepcionPersonalizada   {
     Connection conexion = null;
        
       CallableStatement consulta = null; 
        ResultSet resultados = null;
        
        try {
       
       conexion = Conexion.getConexion();
            
       consulta = conexion.prepareCall("{ CALL ListarpersonalLogistico () }");
       
       resultados = consulta.executeQuery();
       
        List<DTperLogistico> lista = new ArrayList();
              DTperLogistico perLogisitico ;
            
              Integer cedula;
              String nombre,area;
              
              while(resultados.next())
              {
                  cedula = (int)resultados.getObject("Cedula");
                  nombre = resultados.getString("NombreCompleto");
                  area = resultados.getString("Area");
                  
                  perLogisitico = new DTperLogistico(area,cedula,nombre);
                  
                  lista.add(perLogisitico);              
              }
              return lista;
              
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo listar Personal Logistico ", ex);
        } finally {
            Conexion.cerrarRecursos(resultados, consulta, conexion);
        } 
    }
     
     protected List<DTperLogistico> logisticodeSpot(int idspot) throws ExcepcionPersonalizada   {
     Connection conexion = null;
        
       CallableStatement consulta = null; 
        ResultSet resultados = null;
        
        try {
                  conexion = Conexion.getConexion();
 
       consulta = conexion.prepareCall("{ CALL LogisticodeSpot (?) }");
       
       consulta.setInt(1, idspot);

            
       
       resultados = consulta.executeQuery();
       
        List<DTperLogistico> lista = new ArrayList();
              DTperLogistico perLogistico ;
            
              Integer cedula;
              String nombre,area;
              
              while(resultados.next())
              {
                  cedula = (int)resultados.getObject("Cedula");
                  nombre = resultados.getString("NombreCompleto");
                  area = resultados.getString("Area");
                  
                  perLogistico = new DTperLogistico(area,cedula,nombre);
                  
                  lista.add(perLogistico);              
              }
              return lista;
              
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo listar Personal Logistico de Spot ", ex);
        } finally {
            Conexion.cerrarRecursos(resultados, consulta, conexion);
        } 
    }
     
     @Override
     public DTperLogistico buscarLogistico(int cedula) throws ExcepcionPersonalizada   {
     Connection conexion = null;
        
       CallableStatement consulta = null; 
        ResultSet resultados = null;
        
        try {
       
       conexion = Conexion.getConexion();
            
       consulta = conexion.prepareCall("{ CALL BuscarLogistico (?) }");
       consulta.setInt(1,cedula );

       
       resultados = consulta.executeQuery();
       
              DTperLogistico perLogisitico = null ;
            
              String nombre,area;
              
              while(resultados.next())
              {
                  cedula = (int)resultados.getObject("Cedula");
                  nombre = resultados.getString("NombreCompleto");
                  area = resultados.getString("Area");
                  
                  perLogisitico = new DTperLogistico(area,cedula,nombre);
                  
              }
              return perLogisitico;
              
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo Buscar Personal Logistico ", ex);
        } finally {
            Conexion.cerrarRecursos(resultados, consulta, conexion);
        } 
    }
    
}
