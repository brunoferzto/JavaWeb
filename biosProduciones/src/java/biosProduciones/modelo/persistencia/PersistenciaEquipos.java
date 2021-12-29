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
import biosProduciones.modelo.compartidos.beans.datatypes.DTEquipos;
import biosProduciones.modelo.compartidos.beans.excepciones.ExcepcionPersistencia;
import biosProduciones.modelo.compartidos.beans.excepciones.ExcepcionPersonalizada;

/**
 *
 * @author Bruno
 */
class PersistenciaEquipos implements IpersistenciaEquipos{
    
     private static PersistenciaEquipos instancia = null;
    
    
    public static PersistenciaEquipos getInstancia() {
        if (instancia == null) {
            instancia = new PersistenciaEquipos();
        }
        
        return instancia;
    }
    
    
    private PersistenciaEquipos() {
        
    }
    
     @Override
    public void registarEquipo(DTEquipos recurso) throws ExcepcionPersonalizada {
    {
        Connection conexion = null;
        
       CallableStatement consulta = null; 
        try {
            
       conexion = Conexion.getConexion();
       consulta = conexion.prepareCall("{ CALL RegistrarEquipo (?, ?, ?, ?, ?) }");
       
       consulta.setInt(1, recurso.getId());
       consulta.setString(2, recurso.getTipo());
       consulta.setString(3, recurso.getMarca());
       consulta.setString(4, recurso.getModelo());
       consulta.setString(5, recurso.getDescripcion());
       
       int filasAf = consulta.executeUpdate();
       
        if (filasAf == 1062)
        throw new ExcepcionPersistencia("Error en DB,Ya Existe un equipo con el 'ID' ingresado");//no llega 
       
        else if(filasAf < 1)
          throw new ExcepcionPersistencia("Error en DB, No se pudo Agregar");
                      
        } 
        catch (ExcepcionPersonalizada ex) {throw ex; }
        catch (Exception ex){throw new ExcepcionPersistencia("No se pudo Agregar el Equipo", ex); }
        finally { Conexion.cerrarRecursos(consulta,conexion);}    

    }
    
    }
    
     @Override
    public void modificarEquipo(DTEquipos recurso) throws ExcepcionPersonalizada {
    {
        Connection conexion = null;
        
       CallableStatement consulta = null; 
        try {
       conexion = Conexion.getConexion();
       consulta = conexion.prepareCall("{ CALL ModificarEquipo (?, ?, ?, ?, ?) }");
       
       consulta.setInt(1, recurso.getId());
       consulta.setString(2, recurso.getTipo());
       consulta.setString(3, recurso.getMarca());
       consulta.setString(4, recurso.getModelo());
       consulta.setString(5, recurso.getDescripcion());
       
       int filasAf = consulta.executeUpdate();
       
       if(filasAf < 1)
          throw new ExcepcionPersistencia("Error en DB, No se pudo Modificar");
          
        } 
        catch (ExcepcionPersonalizada ex) {throw ex; }
        catch (Exception ex){throw new ExcepcionPersistencia("No se pudo Modificar el Equipo", ex); }
        finally { Conexion.cerrarRecursos(consulta,conexion);}
        

    }
    
    }
    
     @Override
    public void eliminarEquipo(int id) throws ExcepcionPersonalizada {
    {
        Connection conexion = null;
        
       CallableStatement consulta = null; 
        try {
            
       conexion = Conexion.getConexion();
       consulta = conexion.prepareCall("{ CALL EliminarEquipo (?) }");
       
       consulta.setInt(1, id);

       
       int filasAf = consulta.executeUpdate();
       
       if(filasAf < 1)
          throw new ExcepcionPersistencia("Error en DB, No se pudo Eliminar");
          
        } 
        catch (ExcepcionPersonalizada ex) {throw ex; }
        catch (Exception ex){throw new ExcepcionPersistencia("No se pudo Eliminar el Equipo", ex); }
        finally { Conexion.cerrarRecursos(consulta,conexion);}
         

    }
    
    }
    
     @Override
    public List<DTEquipos> listarEquipos() throws ExcepcionPersonalizada   {
     Connection conexion = null;
        
       CallableStatement consulta = null; 
        ResultSet resultados = null;
        
        try {
            
            conexion = Conexion.getConexion();
            
       consulta = conexion.prepareCall("{ CALL ListarEquipo () }");
       
       resultados = consulta.executeQuery();
       
        List<DTEquipos> lista = new ArrayList();
              DTEquipos equipo;
            
              Integer id;
              String tipo,marca,modelo,descripcion;
              
              while(resultados.next())
              {
                  id = (int)resultados.getObject("Id");
                  tipo = resultados.getString("Tipo");
                  marca = resultados.getString("Marca");
                  modelo = resultados.getString("Modelo");
                  descripcion = resultados.getString("Descripcion");
                  
                  equipo = new DTEquipos(id, tipo, marca, modelo, descripcion);
                  
                  lista.add(equipo);              
              }
              return lista;
              
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo listar Equipos / Recursos ", ex);
        } finally {
            Conexion.cerrarRecursos(resultados, consulta, conexion);
        } 
    }
    
     @Override
    public DTEquipos buscarEquipo (int id) throws ExcepcionPersonalizada   {
        
       CallableStatement consulta = null; 
        ResultSet resultados = null;
        Connection conexion = null;
        
        try {
         conexion = Conexion.getConexion();

       consulta = conexion.prepareCall("{ CALL BuscarEquipo (?) }");
       consulta.setInt(1, id);

       resultados = consulta.executeQuery();
       
     
              DTEquipos equipo = null;
            
              
              String tipo,marca,modelo,descripcion;
              
              if(resultados.next())
              {
                  tipo = resultados.getString("Tipo");
                  marca = resultados.getString("Marca");
                  modelo = resultados.getString("Modelo");
                  descripcion = resultados.getString("Descripcion");
                  
                  equipo = new DTEquipos(id, tipo, marca, modelo, descripcion);
                  
                               
              }
              return equipo;
              
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo Buscar Equipo / Recursos ", ex);
        } finally {
            Conexion.cerrarRecursos(resultados, consulta, conexion);
        } 
    }
    
     @Override //? SAKR D INTERFAZ PONER INTERNAL
    public List<DTEquipos> equiposdeSpot(int idSpot) throws ExcepcionPersonalizada   {
     Connection conexion = null;
        
       CallableStatement consulta = null; 
        ResultSet resultados = null;
        
        try {
       conexion = Conexion.getConexion();
       consulta = conexion.prepareCall("{ CALL EquiposdeSpot (?) }");
       consulta.setInt(1, idSpot);
       
       resultados = consulta.executeQuery();
       
        List<DTEquipos> lista = new ArrayList();
              DTEquipos equipo;
            
              Integer id;
              String tipo,marca,modelo,descripcion;
              
              while(resultados.next())
              {
                  id = (int)resultados.getObject("Id");
                  tipo = resultados.getString("Tipo");
                  marca = resultados.getString("Marca");
                  modelo = resultados.getString("Modelo");
                  descripcion = resultados.getString("Descripcion");
                  
                  equipo = new DTEquipos(id, tipo, marca, modelo, descripcion);
                  
                  lista.add(equipo);              
              }
              return lista;
              
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo listar Equipos ", ex);
        } finally {
            Conexion.cerrarRecursos(resultados, consulta, conexion);
        } 
    }
}
    

