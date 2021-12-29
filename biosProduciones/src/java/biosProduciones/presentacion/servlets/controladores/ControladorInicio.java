/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biosProduciones.presentacion.servlets.controladores;

import biosProduciones.modelo.compartidos.beans.excepciones.ExcepcionPersonalizada;
import biosProduciones.modelo.logica.FabricaLogica;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bruno
 */
public class ControladorInicio extends HttpServlet {

     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
         if (accion == null) accion = "index";
        
        switch (accion) {
         case "index":
              index_get(request, response); 
                break;
        }
    }
    
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
            if (accion == null) accion = "index";
            
                switch (accion) {
         case "index":
              index_post(request, response); 
                break;
                }
    }
            
     
    
    private void index_get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/vistas/inicio/index.jsp").forward(request, response);
    }
    
    private void index_post(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String contrase = request.getParameter("contrasenia");
        
         try {
             
             FabricaLogica.getLogicaUsuario().logueo(usuario, contrase);
             request.getRequestDispatcher("WEB-INF/vistas/spot/index.jsp").forward(request, response);
           

       
         } catch (ExcepcionPersonalizada ex) {
              request.setAttribute("mensaje", "¡ERROR! Usuario no Logueado");
              
               try {
                    // no funciona si cambia la maquina, obviament,lei sobre ruta relativa y absoluta y no encontre solucion.
            String ruta = "C:\\Users\\Bruno.000\\Desktop\\Obligatorio\\ObligatorioJavaWeb\\biosProduciones\\web\\login\\logueofallido.txt";

            ///ServletContext contextoAplicacion = getServletContext();            
            //String rutaArchivo = contextoAplicacion.getRealPath("biosProduciones\\web\\login\\logueofallido.txt");
      //String      rutaArchivo = "biosProduciones//web//login//logueofallido.txt";
      //String      rutaArchivo = "C:\biosProduciones\\web\\login\\logueofallido.txt";
      
            Date date = new Date();         
          String contenido = "Fecha : " +  date + " Usuario: " + usuario + "\n";
           File file = new File(ruta);
          

    BufferedWriter bw = null;
    FileWriter fw = null;

    try {

        
        if (!file.exists()) {
            file.createNewFile();
        }
        fw = new FileWriter(file.getAbsoluteFile(), true);
        bw = new BufferedWriter(fw);
        bw.write(contenido);
    } catch (IOException e) {
        request.getRequestDispatcher("WEB-INF/vistas/inicio/index.jsp").forward(request, response);
    } finally {
        try {
            if (bw != null)
                bw.close();
            if (fw != null)
                fw.close();
        } catch (IOException exa) {
            exa.printStackTrace();
        }
    }
            
        } catch (Exception e) {
        request.getRequestDispatcher("WEB-INF/vistas/inicio/index.jsp").forward(request, response);
        }
                 
              
        request.getRequestDispatcher("WEB-INF/vistas/inicio/index.jsp").forward(request, response);

         }
         
      

         
        
        
        
    }
    
   
}
