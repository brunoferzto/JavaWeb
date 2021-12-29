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
import biosProduciones.modelo.compartidos.beans.datatypes.DTEquipos;
import biosProduciones.modelo.compartidos.beans.datatypes.DTPersonal;
import biosProduciones.modelo.compartidos.beans.datatypes.DTSpot;
import biosProduciones.modelo.compartidos.beans.datatypes.DTperActores;
import biosProduciones.modelo.compartidos.beans.datatypes.DTperLogistico;
import biosProduciones.modelo.compartidos.beans.datatypes.DTperTecnico;
import biosProduciones.modelo.compartidos.beans.excepciones.ExcepcionPersistencia;
import biosProduciones.modelo.compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.util.Calendar;

/**
 *
 * @author Bruno
 */
class PersistenciaSpot implements IpersistenciaSpot{
    
    private static PersistenciaSpot instancia = null;
    
    
    public static PersistenciaSpot getInstancia() {
        if (instancia == null) {
            instancia = new PersistenciaSpot();
        }
        
        return instancia;
    }
    
    
    private PersistenciaSpot() {
        
    }
    
    private String fechas(Calendar calendario)
    {
        
            int dia = calendario.get(Calendar.DAY_OF_MONTH);
            int mes = calendario.get(Calendar.MONTH);
               mes = mes + 1;
            int anio = calendario.get(Calendar.YEAR);
            
             String fechapronta = ""+anio+"-"+mes+"-"+dia;
             
         return fechapronta; 
    }
    
    
    @Override
    public void registrarSpot (DTSpot spot) throws ExcepcionPersonalizada {
    
        Connection conexion = null;
        
       CallableStatement consulta = null; 
        try {
       conexion = Conexion.getConexion();
       
       Calendar calendario = Calendar.getInstance();
       calendario.setTime(spot.getFechaInicio());
       String fechaInicio = fechas(calendario);
       calendario.setTime(spot.getFechaFinal());
       String fechaFinal = fechas(calendario);
              
       consulta = conexion.prepareCall("{ CALL AltaSpot (?, ?, ?, ?) }");
       
       //consulta.setInt(1, spot.getId());
       consulta.setString(1, spot.getTitulo());
       consulta.setString(2,fechaInicio ); 
       consulta.setString(3,fechaFinal );
       consulta.setInt(4, spot.getPrecio());


       
       int filasAf = consulta.executeUpdate();
       
       if(filasAf < 1)
          throw new ExcepcionPersistencia("Error en DB, No se pudo Agregar");
          
        } 
        catch (ExcepcionPersonalizada ex) {throw ex; }
        catch (Exception ex){throw new ExcepcionPersistencia("No se pudo Agregar el Spot", ex); }
        finally { Conexion.cerrarRecursos(consulta,conexion);}
    
    }
         
    @Override
    public void asignarEquipoaSpot (DTSpot spot, DTEquipos eqp) throws ExcepcionPersonalizada {
        Connection conexion = null;
        
       CallableStatement consulta = null; 
        try {
            conexion = Conexion.getConexion();
       //conexion.setAutoCommit(false);
       
       //for(DTEquipos eqp : spot.getEquipos())
       //{           
       consulta = conexion.prepareCall("{ CALL AsignarEquipoSpot (?, ?) }");   
       consulta.setInt(1, spot.getId());
       consulta.setInt(2, eqp.getId());
       
       int filasAf = consulta.executeUpdate();  
       
       if(filasAf < 1)
       {                  
        conexion.rollback();
        throw new ExcepcionPersistencia("Ya existe Asignación, o El equipo está en uso");
        }
       //}
       
        // conexion.commit();
              
        } 
        catch (ExcepcionPersonalizada ex) {throw ex; }
        catch (Exception ex){throw new ExcepcionPersistencia("No se pudo Asignar Equipo al  Spot", ex); }
        finally { Conexion.cerrarRecursos(consulta,conexion);}
    
    }
        
    @Override
    public void asignarPersonalaSpot (DTSpot spot, DTPersonal per) throws ExcepcionPersonalizada {
        Connection conexion = null;
        
       CallableStatement consulta = null; 
        try {
       
       conexion = Conexion.getConexion();
       consulta = conexion.prepareCall("{ CALL AsignarHumanoSpot (?, ?) }"); 
       consulta.setInt(1, spot.getId());
       consulta.setInt(2, per.getCedula());
       
       int filasAf = consulta.executeUpdate();  
       
       if(filasAf < 1)
       {                  
        throw new ExcepcionPersistencia("Ya existe Asignación, o El equipo está en uso");
        }
        }
        catch (ExcepcionPersonalizada ex) {throw ex; }
        catch (Exception ex){throw new ExcepcionPersistencia("No se pudo Asignar Equipo al  Spot", ex); }
        finally { Conexion.cerrarRecursos(consulta,conexion);}
    
    }
       
    @Override
    public DTSpot buscarSpot (int id) throws ExcepcionPersonalizada {
         Connection conexion = null;   
         CallableStatement consulta = null; 
         ResultSet resultado = null;                     
        try {
            conexion = Conexion.getConexion();
            consulta = conexion.prepareCall("{ CALL BuscarSpot (?) }");
            consulta.setInt(1, id);
            
            resultado = consulta.executeQuery();
            
            DTSpot spot = null;
            Integer ide , precio;
            String titulo; 
            Date fechaInicio, fechaFinal;
            
            if (resultado.next())
            {
                ide = (int)resultado.getObject("Id");
                precio = (int)resultado.getObject("Precio");
                titulo = resultado.getString("Titulo");
                fechaInicio = resultado.getDate("FechaInicio"); 
                fechaFinal = resultado.getDate("FechaFinal");
                
                spot = new DTSpot(ide,precio,titulo,fechaInicio,fechaFinal);
                
                List<DTEquipos> equipo = PersistenciaEquipos.getInstancia().equiposdeSpot(ide);
                spot.setEquipos(equipo);
                
                 List<DTperTecnico> listaT = PersistenciapersonalTecnico.getInstancia().tecnicosdeSpot(ide);
                    List<DTperActores> listaA = PersistenciapersonalActores.getInstancia().personaldeSpot(ide);
                    List<DTperLogistico> listaL = PersistenciapersonalLogistico.getInstancia().logisticodeSpot(ide);
                    
                  List<DTPersonal> lista = new ArrayList();  
                    lista.addAll(listaT);
                    lista.addAll(listaA);
                    lista.addAll(listaL);
                    
                    spot.setRecursosH(lista);
                
                
            }
             return spot;
                                            
        } catch (Exception ex) {
           throw new ExcepcionPersistencia("No se pudo obtener el Spot ", ex);

        } finally {
            Conexion.cerrarRecursos(resultado, consulta, conexion);
        }  
    }
    
    @Override
    public List<DTSpot> equiposAsosciados (DTEquipos eqp) throws ExcepcionPersonalizada {
         Connection conexion = null;   
         CallableStatement consulta = null; 
         ResultSet resultado = null;                     
        try {
            conexion = Conexion.getConexion();
            consulta = conexion.prepareCall("{ CALL SpotAsosciadosEquipos (?) }");
            consulta.setInt(1, eqp.getId());
            
            resultado = consulta.executeQuery();
            
            DTSpot spot = null;
            List<DTSpot> listaSpot = new ArrayList(); 
            List<DTPersonal> lista = new ArrayList();             
            
            Integer ide , precio;
            String titulo; 
            Date fechaInicio, fechaFinal;
            
            while (resultado.next())
            {
                ide = (int)resultado.getObject("Id");
                precio = (int)resultado.getObject("Precio");
                titulo = resultado.getString("Titulo");
                fechaInicio = resultado.getDate("FechaInicio"); 
                fechaFinal = resultado.getDate("FechaFinal");
                
                spot = new DTSpot(ide,precio,titulo,fechaInicio,fechaFinal);
                
                List<DTEquipos> equipo = PersistenciaEquipos.getInstancia().equiposdeSpot(ide);
                spot.setEquipos(equipo);               
                    
                    listaSpot.add(spot);
                
            }
             return listaSpot;
                                            
        } catch (Exception ex) {
           throw new ExcepcionPersistencia("No se pudo obtener el Spot ", ex);

        } finally {
            Conexion.cerrarRecursos(resultado, consulta, conexion);
        }  
    }
    
    @Override
    public List<DTSpot> personalAsosciados (DTPersonal per) throws ExcepcionPersonalizada {
         Connection conexion = null;   
         CallableStatement consulta = null; 
         ResultSet resultado = null;                     
        try {
            conexion = Conexion.getConexion();
            consulta = conexion.prepareCall("{ CALL SpotAsosciadoPersonal (?) }");
            consulta.setInt(1, per.getCedula());
            
            resultado = consulta.executeQuery();
            
            DTSpot spot = null;
            List<DTSpot> listaSpot = new ArrayList(); 
            List<DTPersonal> lista = new ArrayList();             
            
            Integer ide , precio;
            String titulo; 
            Date fechaInicio, fechaFinal;
            
            while (resultado.next())
            {
                ide = (int)resultado.getObject("Id");
                precio = (int)resultado.getObject("Precio");
                titulo = resultado.getString("Titulo");
                fechaInicio = resultado.getDate("FechaInicio"); 
                fechaFinal = resultado.getDate("FechaFinal");
                
                spot = new DTSpot(ide,precio,titulo,fechaInicio,fechaFinal);
                                

                    List<DTperTecnico> listaT = PersistenciapersonalTecnico.getInstancia().tecnicosdeSpot(ide);
                    List<DTperActores> listaA = PersistenciapersonalActores.getInstancia().personaldeSpot(ide);
                    List<DTperLogistico> listaL = PersistenciapersonalLogistico.getInstancia().logisticodeSpot(ide);
                    
                    lista.addAll(listaT);
                    lista.addAll(listaA);
                    lista.addAll(listaL);
                    
                    spot.setRecursosH(lista);
                    
                    listaSpot.add(spot);
                
            }
             return listaSpot;
                                            
        } catch (Exception ex) {
           throw new ExcepcionPersistencia("No se pudo obtener el Spot ", ex);

        } finally {
            Conexion.cerrarRecursos(resultado, consulta, conexion);
        }  
    }
}

    
    
