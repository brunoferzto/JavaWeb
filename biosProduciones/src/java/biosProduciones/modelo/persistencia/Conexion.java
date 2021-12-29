/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biosProduciones.modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Bruno
 */
public class Conexion {
    
     private static final String URL_CONEXION = "jdbc:mysql://localhost:3306/biosproducionesdb";
    private static final String NOMBRE_USUARIO_BASE_DATOS = "root";
    private static final String CONTRASENA_BASE_DATOS = "Rodo1125";
    
    
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            System.out.println("No se pudo instanciar el driver JDBC.");
        }
    }
    
    
    protected static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL_CONEXION, NOMBRE_USUARIO_BASE_DATOS, CONTRASENA_BASE_DATOS);
    }
    
    protected static void cerrarRecursos(AutoCloseable... recursos) {
        try {
            for (AutoCloseable r : recursos) {
                if (r != null) {
                    r.close();
                }
            }
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un problema al cerrar los recursos.");
        }
    }
    
    
}
