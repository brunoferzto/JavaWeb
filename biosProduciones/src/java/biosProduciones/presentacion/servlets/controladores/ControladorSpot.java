/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biosProduciones.presentacion.servlets.controladores;

import biosProduciones.modelo.compartidos.beans.datatypes.DTSpot;
import biosProduciones.modelo.compartidos.beans.excepciones.ExcepcionPersonalizada;
import biosProduciones.modelo.logica.FabricaLogica;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bruno
 */
public class ControladorSpot extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null) accion = "index";
        
        switch (accion) {
            case "index":
                agregar_get(request, response);               
                break;
                
            case "buscar":
                buscar_get(request,response);
                break;
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        
        switch (accion) { 
            case "Agregar":
                agregar_post( request,  response);
                break;
                
                case "buscar":
                 buscar_post( request,  response);
                break;
    }

  

}
    
    public void agregar_get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{ 
                request.getRequestDispatcher("WEB-INF/vistas/spot/index.jsp").forward(request, response);     
    }
    
    public void agregar_post(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{ 
        int precio;
        
        try {
            precio = Integer.parseInt(request.getParameter("precio"));
        } catch (NumberFormatException ex) {
            request.setAttribute("ocultarFormulario", true);
            request.setAttribute("mensaje", "¡ERROR! El 'Precio' no es válido");          
            request.getRequestDispatcher("WEB-INF/vistas/spot/index.jsp").forward(request, response);           
            return;
        }
        
        String titulo = request.getParameter("titulo");
        DateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        formateador.setLenient(false); 
        DTSpot spot = null;
        try {
                
                Date fechaInicio = formateador.parse(request.getParameter("fechaINI"));
                Date fechaFinal = formateador.parse(request.getParameter("fechaFIN"));
                
                 spot = new DTSpot(0, precio, titulo, fechaInicio, fechaFinal);
                 
            } catch (ParseException ex) {
             request.setAttribute("mensaje", "¡ERROR! Ingrese las fecha correctamente");           
             request.getRequestDispatcher("WEB-INF/vistas/spot/index.jsp").forward(request, response);
            }
        
        try {
                        
                FabricaLogica.getLogicaSpot().registrarSpot(spot);
             request.setAttribute("mensaje", "Spot Registrado Correctamente");           
             request.getRequestDispatcher("WEB-INF/vistas/spot/index.jsp").forward(request, response);
            
        } catch (Exception e) {
            request.setAttribute("mensaje", "¡ERROR! Sucedieron Errores al Agregar");           
             request.getRequestDispatcher("WEB-INF/vistas/spot/index.jsp").forward(request, response);
            
        }

        
    }
    
    public void buscar_get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
     request.getRequestDispatcher("WEB-INF/vistas/spot/buscar.jsp").forward(request, response);

    }
            
    public void buscar_post(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{        
        int codigo;
        
        try {
            codigo = Integer.parseInt(request.getParameter("codigo"));
        } catch (NumberFormatException ex) {
            request.setAttribute("ocultarFormulario", true);
            request.setAttribute("mensaje", "¡ERROR! El 'Codigo' no es válido");          
            request.getRequestDispatcher("WEB-INF/vistas/spot/buscar.jsp").forward(request, response);           
            return;
        }
        
        try {
            DTSpot spot = FabricaLogica.getLogicaSpot().buscarSpot(codigo);
       
            if (spot != null) {
                request.setAttribute("spot", spot);
                request.setAttribute("personas", spot.getRecursosH());
                request.setAttribute("equipos", spot.getEquipos());
                request.getRequestDispatcher("WEB-INF/vistas/spot/spotlistados.jsp").forward(request, response);
            }
            
            else
            request.setAttribute("mensaje", "¡ERROR! No se encontró ningún Spot el 'Código' " + codigo + ".");

        }  catch (ExcepcionPersonalizada ex) {
            request.setAttribute("mensaje", "¡ERROR! " + ex.getMessage());
        } catch (Exception ex) {
            request.setAttribute("mensaje", "¡ERROR! Se produjo un error al buscar el Spot.");
        }
        request.getRequestDispatcher("WEB-INF/vistas/spot/buscar.jsp").forward(request, response);

    }
}
