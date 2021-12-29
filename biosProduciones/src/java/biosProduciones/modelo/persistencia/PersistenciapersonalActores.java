/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biosProduciones.modelo.persistencia;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import biosProduciones.modelo.compartidos.beans.datatypes.DTperActores;
import biosProduciones.modelo.compartidos.beans.excepciones.ExcepcionPersistencia;
import biosProduciones.modelo.compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 *
 * @author Bruno
 */
 class PersistenciapersonalActores implements IpersistenciapersonalActores {
     
      private static PersistenciapersonalActores instancia = null;
    
    
    public static PersistenciapersonalActores getInstancia() {
        if (instancia == null) {
            instancia = new PersistenciapersonalActores();
        }
        
        return instancia;
    }
    
    private PersistenciapersonalActores() {
        
    }
    
      @Override
     public void registro(DTperActores per)throws ExcepcionPersonalizada {
    {
        Connection conexion = null;
        
       CallableStatement consulta = null; 
     try {
            
       conexion = Conexion.getConexion();
       consulta = conexion.prepareCall("{ CALL RegistropersonalActores (?, ?, ?, ?, ?) }");
       
       Calendar calendario = Calendar.getInstance();
       calendario.setTime(per.getFechaNacimiento());
            int dia = calendario.get(Calendar.DAY_OF_MONTH);
            int mes = calendario.get(Calendar.MONTH);
               mes = mes + 1;
            int anio = calendario.get(Calendar.YEAR);
            
           String fecha = ""+anio+"-"+mes+"-"+dia;
        
       
       consulta.setInt(1, per.getCedula());
       consulta.setString(2, per.getNombre());
       consulta.setString(3, fecha);
       consulta.setString(4, per.getSexo());
       consulta.setString(5, per.getFoto());
       
       int filasAf = consulta.executeUpdate();
       
       if(filasAf < 1)
          throw new ExcepcionPersistencia("Error en DB, No se pudo Agregar");
          
        } 
        catch (ExcepcionPersonalizada ex) {throw ex; }
        catch (Exception ex){throw new ExcepcionPersistencia("No se pudo Agregar el Actor", ex); }
        finally { Conexion.cerrarRecursos(consulta,conexion);}
    }
    }
     
      @Override
     public void modificar(DTperActores per)throws ExcepcionPersonalizada {
    {
        Connection conexion = null;
        
       CallableStatement consulta = null; 
     try {
              conexion = Conexion.getConexion();
              
              //metodo para no duplicar 
               Calendar calendario = Calendar.getInstance();
       calendario.setTime(per.getFechaNacimiento());
            int dia = calendario.get(Calendar.DAY_OF_MONTH);
            int mes = calendario.get(Calendar.MONTH);
               mes = mes + 1;
            int anio = calendario.get(Calendar.YEAR);
            
           String fecha = ""+anio+"-"+mes+"-"+dia;
     
       consulta = conexion.prepareCall("{ CALL ModificarpersonalActores (?, ?, ?,?,?) }");
       
       
       consulta.setInt(1, per.getCedula());
       consulta.setString(2, per.getNombre());
       consulta.setString(3, fecha);
       consulta.setString(4, per.getSexo());
       consulta.setString(5, per.getFoto());
       
       int filasAf = consulta.executeUpdate();
       
       if(filasAf < 1)
          throw new ExcepcionPersistencia("Error en DB, No se pudo Modificar");
          
        } 
        catch (ExcepcionPersonalizada ex) {throw ex; }
        catch (Exception ex){throw new ExcepcionPersistencia("No se pudo Modificar el Actor", ex); }
        finally { Conexion.cerrarRecursos(consulta,conexion);}
    }
    }

      @Override
     public void eliminar(DTperActores per)throws ExcepcionPersonalizada {
    {
        Connection conexion = null;
        
       CallableStatement consulta = null; 
     try {
               conexion = Conexion.getConexion();
    
       consulta = conexion.prepareCall("{ CALL EliminarpersonalActores (?) }");
       
       consulta.setInt(1, per.getCedula());
       
       int filasAf = consulta.executeUpdate();
       
       if(filasAf < 1)
          throw new ExcepcionPersistencia("Error en DB, No se pudo Eliminar");
          
        } 
        catch (ExcepcionPersonalizada ex) {throw ex; }
        catch (Exception ex){throw new ExcepcionPersistencia("No se pudo Eliminar el Actor", ex); }
        finally { Conexion.cerrarRecursos(consulta,conexion);}
    }
    }
     
      @Override
     public List<DTperActores> listarpersonalActores() throws ExcepcionPersonalizada   {
     Connection conexion = null;
        
       CallableStatement consulta = null; 
        ResultSet resultados = null;
        
        try {
        
        conexion = Conexion.getConexion();
            
       consulta = conexion.prepareCall("{ CALL ListarpersonalActores () }");
       
       resultados = consulta.executeQuery();
       
        List<DTperActores> lista = new ArrayList();
              DTperActores actores ;
            
              Integer cedula;
              String nombre,sexo, foto;
              Date fechaNto;
              
              while(resultados.next())
              {
                  cedula = (int)resultados.getObject("Cedula");
                  nombre = resultados.getString("NombreCompleto");
                  sexo = resultados.getString("Sexo");
                  foto = resultados.getString("Foto");
                  fechaNto = resultados.getDate("FechaNacimiento");
                  
                  actores = new DTperActores(fechaNto, sexo, foto, cedula, nombre);
                  
                  lista.add(actores);              
              }
              return lista;
              
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo listar Equipos / Recursos ", ex);
        } finally {
            Conexion.cerrarRecursos(resultados, consulta, conexion);
        } 
    }

   
     protected  List<DTperActores> personaldeSpot(int idspot) throws ExcepcionPersonalizada   {
     Connection conexion = null;
        
       CallableStatement consulta = null; 
        ResultSet resultados = null;
        
        try {
          conexion = Conexion.getConexion();

       consulta = conexion.prepareCall("{ CALL ActoresdeSpot (?) }");
       
              consulta.setInt(1, idspot);

       
       resultados = consulta.executeQuery();
       
        List<DTperActores> lista = new ArrayList();
              DTperActores actores ;
            
              Integer cedula;
              String nombre,sexo, foto;
              Date fechaNto;
              
              while(resultados.next())
              {
                  cedula = (int)resultados.getObject("Cedula");
                  nombre = resultados.getString("NombreCompleto");
                  sexo = resultados.getString("Sexo");
                  foto = resultados.getString("Foto");
                  fechaNto = resultados.getDate("FechaNacimiento");
                  
                  actores = new DTperActores(fechaNto, sexo, foto, cedula, nombre);
                  
                  lista.add(actores);              
              }
              return lista;
              
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo listar Equipos / Recursos ", ex);
        } finally {
            Conexion.cerrarRecursos(resultados, consulta, conexion);
        } 
    }

      @Override
     public DTperActores buscarActor(int ced) throws ExcepcionPersonalizada   {
     Connection conexion = null;
        
       CallableStatement consulta = null; 
        ResultSet resultados = null;
        
        try {
        
        conexion = Conexion.getConexion();
            
       consulta = conexion.prepareCall("{ CALL BuscarActor (?) }");
       consulta.setInt(1, ced);

       resultados = consulta.executeQuery();
       
              DTperActores actores = null ;
            
              Integer cedula;
              String nombre,sexo, foto;
              Date fechaNto;
              
              if(resultados.next())
              {
                  cedula = (int)resultados.getObject("Cedula");
                  nombre = resultados.getString("NombreCompleto");
                  sexo = resultados.getString("Sexo");
                  foto = resultados.getString("Foto");
                  fechaNto = resultados.getDate("FechaNacimiento");
                  
                  actores = new DTperActores(fechaNto, sexo, foto, cedula, nombre);
                  
                                
              }
              return actores;
              
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo Buscar Actor ", ex);
        } finally {
            Conexion.cerrarRecursos(resultados, consulta, conexion);
        } 
    }

}
