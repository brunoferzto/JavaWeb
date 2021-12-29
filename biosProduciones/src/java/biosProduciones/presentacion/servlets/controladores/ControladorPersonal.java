/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biosProduciones.presentacion.servlets.controladores;

import biosProduciones.modelo.compartidos.beans.datatypes.DTEquipos;
import biosProduciones.modelo.compartidos.beans.datatypes.DTPersonal;
import biosProduciones.modelo.compartidos.beans.datatypes.DTSpot;
import biosProduciones.modelo.compartidos.beans.datatypes.DTperActores;
import biosProduciones.modelo.compartidos.beans.datatypes.DTperLogistico;
import biosProduciones.modelo.compartidos.beans.datatypes.DTperTecnico;
import biosProduciones.modelo.compartidos.beans.excepciones.ExcepcionPersonalizada;
import biosProduciones.modelo.logica.FabricaLogica;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Bruno
 */
public class ControladorPersonal extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null) accion = "index";
        
        switch (accion) {
            case "index":
                index_get(request, response);               
                break;
                
            case "agregarLogistico":
        request.getRequestDispatcher("WEB-INF/vistas/personal/agregarLogistico.jsp").forward(request, response);
                break;
                
                case "agregarTecnico":
        request.getRequestDispatcher("WEB-INF/vistas/personal/agregarTecnico.jsp").forward(request, response);
                break;
                      
                case "agregarActores":
        request.getRequestDispatcher("WEB-INF/vistas/personal/agregarActor.jsp").forward(request, response);
                break;
                                                  
            case "modificar":
                modificar_get(request, response);
                
                break;
            case "eliminar":
                eliminar_get(request, response);               
                break;
                
                case"agregarSpot":
                    agregarspot_get(request, response);                    
                    break;
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        
        switch (accion) { // ver con substring
            case "agregarLogistico":
                agregar_post( request,  response);
                break;
                
                case "agregarTecnico":
                agregar_post( request,  response);
                    break;
                      
                case "agregarActores":
                agregar_post( request,  response);
                break;
                
            case "modificar":
                modificar_post(request, response);
                
                break;
            case "eliminar":
                eliminar_post(request, response);               
                break;
                
                 case"agregarSpot":
                    agregarspot_post(request, response);                    
                    break;
        }
    }
    
    public void index_get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<DTPersonal> personas = FabricaLogica.getLogicaPersonal().listarPersonal();
                
            
            request.setAttribute("personas", personas);
        }  catch (ExcepcionPersonalizada ex) {
            request.setAttribute("mensaje", ex.getMessage());
        } catch (Exception ex) {
            request.setAttribute("mensaje", "No se pudo listar las personas.");
        }
        
        String mensajeSesion = (String)request.getSession().getAttribute("mensaje");
        
        if (mensajeSesion != null) {
            String mensaje = (String)request.getAttribute("mensaje");
            
            if (mensaje == null) {
                request.setAttribute("mensaje", mensajeSesion);
            } else {
                request.setAttribute("mensaje", mensajeSesion + "<br /><br />" + mensaje);
            }
            
            request.getSession().removeAttribute("mensaje");
        }
        
        request.getRequestDispatcher("WEB-INF/vistas/personal/index.jsp").forward(request, response);
    }
    
     public void modificar_get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int cedula;
        
        try {
            cedula = Integer.parseInt(request.getParameter("cedula"));
        } catch (NumberFormatException ex) {
            request.setAttribute("ocultarFormulario", true);
            request.setAttribute("mensaje", "¡ERROR! La 'Cédula' no es válido");
            
            request.getRequestDispatcher("WEB-INF/vistas/personal/index.jsp").forward(request, response);
            
            return;
        }
        
        try {
            DTPersonal persona = FabricaLogica.getLogicaPersonal().buscarPersonal(cedula);
            
            if (persona != null) {
                request.setAttribute("persona", persona);
                
                
                if(persona.toString() == "Actor") 
                {
                    
                request.setAttribute("nombre", persona.getNombre());
                request.setAttribute("fechaNacimiento", ((DTperActores) persona).getFechaNacimiento());
                request.setAttribute("sexo", ((DTperActores) persona).getSexo());
                request.getRequestDispatcher("WEB-INF/vistas/personal/modificarActor.jsp").forward(request, response);

                }
               
               
                
                else if(persona.toString() == "Logistico")
        request.getRequestDispatcher("WEB-INF/vistas/personal/modificarLogistico.jsp").forward(request, response);
                
                else
            request.getRequestDispatcher("WEB-INF/vistas/personal/modificarTecnico.jsp").forward(request, response);
                
            } else {
                request.setAttribute("ocultarFormulario", true);
                request.setAttribute("mensaje", "¡ERROR! No se encontró ningún Personal con la  'Cédula'  " + cedula + ".");
            }
        } catch (ExcepcionPersonalizada ex) {
            request.setAttribute("mensaje", "¡ERROR! " + ex.getMessage());
        } catch (Exception ex) {
            request.setAttribute("mensaje", "¡ERROR! Se produjo un error al buscar el Personal");
        }
           
    }
    
    public void modificar_post(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int cedula;
        
        try {
            cedula = Integer.parseInt(request.getParameter("cedula"));
        } catch (NumberFormatException ex) {
            request.setAttribute("mensaje", "¡ERROR! La 'Cédula' no es válida");          
            request.getRequestDispatcher("WEB-INF/vistas/personal/index.jsp").forward(request, response);           
            return;
        }
        
        String nombre = request.getParameter("nombre");
        
        if( "Actor".equals(request.getParameter("tipo"))){
            
        String foto;
        DTperActores actor = null;
         foto = "/fotosActores/general.png";
        
        
        try {
            String fecha = request.getParameter("fechaNTO");
           
               int año = Integer.parseInt(fecha.substring(0, 4));
               int mes = Integer.parseInt(fecha.substring(5,7));
               int dia = Integer.parseInt(fecha.substring(8,10));
               
               String fechaalrevez = ""+dia+"/"+mes+"/"+año;
            
        DateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        formateador.setLenient(false); 
            try {
                
                String sexo = request.getParameter("sexo");
                Date fechaNacimiento = formateador.parse(fechaalrevez);
                  actor = new DTperActores(fechaNacimiento,sexo,foto,cedula,nombre);
                 
            } catch (ParseException ex) {
             request.setAttribute("mensaje", "¡ERROR! Ingrese la fecha correctamente");           
             request.getRequestDispatcher("WEB-INF/vistas/personal/modificarActor.jsp").forward(request, response);
            }
                               
                FabricaLogica.getLogicaPersonal().modificarRecursoHumano(actor);
                
            } catch (ExcepcionPersonalizada ex) {
            request.setAttribute("mensaje", "¡ERROR! No se pudo Agregar Personal");           
            request.getRequestDispatcher("WEB-INF/vistas/personal/modificarActor.jsp").forward(request, response);
            }        
        }
                
        else if("Logistico".equals(request.getParameter("tipo"))){
            
        String area = request.getParameter("area");        
        DTperLogistico perLog = new DTperLogistico(area,cedula,nombre);       
            try {
                FabricaLogica.getLogicaPersonal().modificarRecursoHumano(perLog);
            } catch (ExcepcionPersonalizada ex) {
                
                 request.setAttribute("mensaje", "¡ERROR! No se pudo modificar Personal Logisitico");
            
            request.getRequestDispatcher("WEB-INF/vistas/personal/modificarLogistico.jsp").forward(request, response);            
            return;
            }
        }
                
        else
        {
            String cargo = request.getParameter("cargo"); 
             DTperTecnico perTec = new DTperTecnico(cargo,cedula,nombre);       
            try {
                FabricaLogica.getLogicaPersonal().modificarRecursoHumano(perTec);
            } catch (ExcepcionPersonalizada ex) {
                
                 request.setAttribute("mensaje", "¡ERROR! No se pudo modificar Personal Técnico");
            
            request.getRequestDispatcher("WEB-INF/vistas/personal/modificarTecnico.jsp").forward(request, response);            
            return;
            }

        }        
        
   
            response.sendRedirect("personal");
    }
    
    public void eliminar_get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int cedula;
        
        try {
            cedula = Integer.parseInt(request.getParameter("cedula"));
        } catch (NumberFormatException ex) {
            request.setAttribute("mensaje", "¡ERROR! La 'Cedula' no es válida");
            
            request.getRequestDispatcher("WEB-INF/vistas/personal/eliminar.jsp").forward(request, response);
            
            return;
        }
        
        try {
            DTPersonal persona = FabricaLogica.getLogicaPersonal().buscarPersonal(cedula);
            
            if (persona != null) {
                request.setAttribute("persona", persona);
            } else {
                request.setAttribute("mensaje", "¡ERROR! No se encontró ningún Personal con la 'Cédula' : " + cedula + ".");
            }
        } catch (ExcepcionPersonalizada ex) {
            request.setAttribute("mensaje", "¡ERROR! " + ex.getMessage());
        } catch (Exception ex) {
            request.setAttribute("mensaje", "¡ERROR! Se produjo un error al buscar el Personal.");
        }
        
        request.getRequestDispatcher("WEB-INF/vistas/personal/eliminar.jsp").forward(request, response);
    }
    
    public void eliminar_post(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int cedula;
        
        try {
            cedula = Integer.parseInt(request.getParameter("cedula"));
        } catch (NumberFormatException ex) {
            request.setAttribute("mensaje", "¡ERROR! La 'Cedula' no es válida");
            
            request.getRequestDispatcher("WEB-INF/vistas/personal/eliminar.jsp").forward(request, response);
            
            return;
        }
        
        try {
            FabricaLogica.getLogicaPersonal().eliminarRecursoHumano(cedula);
            
            request.getSession().setAttribute("mensaje", "¡Personal eliminado con éxito!");
            
            response.sendRedirect("personal");
        } catch (ExcepcionPersonalizada ex) {
            request.setAttribute("mensaje", "¡ERROR! " + ex.getMessage());
            
            request.getRequestDispatcher("WEB-INF/vistas/personal/eliminar.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("mensaje", "¡ERROR! Se produjo un error al eliminar el Personal.");
            
            request.getRequestDispatcher("WEB-INF/vistas/personal/eliminar.jsp").forward(request, response);
        }
    }
    
   public void agregar_post(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        int cedula;
        
        try {

            cedula = Integer.parseInt(request.getParameter("cedula"));
        } catch (NumberFormatException ex) {
            request.setAttribute("mensaje", "¡ERROR! La 'Cédula' no es válida");          
            response.sendRedirect("personal");
           
            return;
        }
        
        String nombre = request.getParameter("nombre");
        
        String cargo = request.getParameter("cargo");
        String area = request.getParameter("area");
        String sexo = request.getParameter("sexo");
        
        if(cargo != null)
        {
                DTperTecnico perTec = new DTperTecnico(cargo,cedula,nombre);
         
            try {
                FabricaLogica.getLogicaPersonal().registrarRecursoHumano(perTec);
                
            } catch (ExcepcionPersonalizada ex) {
            request.setAttribute("mensaje", "¡ERROR! No se pudo Agregar Personal Tecnico" + "--" + ex.getMessage());           
            request.getRequestDispatcher("WEB-INF/vistas/personal/agregarTecnico.jsp").forward(request, response); 
               
            }
        }
         
        else if (area != null)
        {
            DTperLogistico perLog = new DTperLogistico(area,cedula,nombre);
            try {
                FabricaLogica.getLogicaPersonal().registrarRecursoHumano(perLog);
                
            } catch (ExcepcionPersonalizada ex) {
            request.setAttribute("mensaje", "¡ERROR! No se pudo Agregar Personal Logístico" + "--" + ex.getMessage());           
            request.getRequestDispatcher("WEB-INF/vistas/personal/agregarLogistico.jsp").forward(request, response);
            }

        }
        
        else if (sexo != null) 
        {
        BufferedImage imagen = ImageIO.read(request.getPart("imagen").getInputStream());
        String foto;
        DTperActores actor = null;
        if (imagen != null) {
            
            
            ServletContext contextoAplicacion = getServletContext();            
            String rutaImagenes = contextoAplicacion.getRealPath("C:\\Users\\Bruno.000\\Desktop\\3ro\\1-Java Web\\biosProduciones\\web\\fotosActores\\");            
            File archivo = new File(rutaImagenes + cedula + ".png");
            //File archivo = new File(rutaImagenes+"/"+cedula+".png");
            archivo.createNewFile();            
            ImageIO.write(imagen, "png", archivo); 
            
            
             foto = rutaImagenes + cedula + ".png";
        }
        
        else{
           foto = "/fotosActores/general.png";
        }
        
        try {
       //DateFormat formateador = DateFormat.getDateInstance(DateFormat.SHORT);
       DateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");

       formateador.setLenient(false); 
            try {
                
                Date fechaNacimiento = formateador.parse(request.getParameter("fechaNTO"));
                  actor = new DTperActores(fechaNacimiento,sexo,foto,cedula,nombre);
                 
            } catch (ParseException ex) {
             request.setAttribute("mensaje", "¡ERROR! Ingrese la fecha correctamente");           
             request.getRequestDispatcher("WEB-INF/vistas/personal/agregarActor.jsp").forward(request, response);
            }
                               
                FabricaLogica.getLogicaPersonal().registrarRecursoHumano(actor);
                
            } catch (ExcepcionPersonalizada ex) {
            request.setAttribute("mensaje", "¡ERROR! No se pudo Agregar Personal" + "--" + ex.getMessage() );           
            request.getRequestDispatcher("WEB-INF/vistas/personal/agregarActor.jsp").forward(request, response);
            }
        }
        
        else
        {
        request.setAttribute("mensaje", "¡ERROR! Debe completar todos los campos");
        }
        
   
            response.sendRedirect("personal");
       
   }
     

   public void agregarspot_get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
       int cedula;
        
        try {
            cedula = Integer.parseInt(request.getParameter("cedula"));
        } catch (NumberFormatException ex) {
            request.setAttribute("mensaje", "¡ERROR! La 'Cedula' no es válida");
            
            request.getRequestDispatcher("WEB-INF/vistas/personal/index.jsp").forward(request, response);
            
            return;
        }
        
        try {
            DTPersonal persona = FabricaLogica.getLogicaPersonal().buscarPersonal(cedula);
            
            if (persona != null) {
                request.setAttribute("persona", persona);
                
                HttpSession misession= (HttpSession) request.getSession();
                misession= request.getSession(true);
                misession.setAttribute("persona",persona);
                
            } else {
                request.setAttribute("mensaje", "¡ERROR! No se encontró ningún Personal con la 'Cédula' : " + cedula + ".");
            }
        } catch (ExcepcionPersonalizada ex) {
            request.setAttribute("mensaje", "¡ERROR! " + ex.getMessage());
        } catch (Exception ex) {
            request.setAttribute("mensaje", "¡ERROR! Se produjo un error al buscar el Personal.");
        }
        
        request.getRequestDispatcher("WEB-INF/vistas/personal/agregaraSpot.jsp").forward(request, response);
   }
   
   public void agregarspot_post(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{      
        HttpSession misession= (HttpSession) request.getSession();
        DTPersonal persona = (DTPersonal) misession.getAttribute("persona");      
        int codigo;
        
         try {
            codigo = Integer.parseInt(request.getParameter("spot"));
        } catch (NumberFormatException ex) {
            request.setAttribute("mensaje", "¡ERROR! El 'Código' no es Válido");            
            request.getRequestDispatcher("WEB-INF/vistas/personal/agregaraSpot.jsp").forward(request, response);
            
            return;
        }
        
        try {
            DTSpot spot = FabricaLogica.getLogicaSpot().buscarSpot(codigo);
            if (spot != null) {
                try {
                    FabricaLogica.getLogicaSpot().asignarPersonalaSpot(spot, persona);
                    
                request.setAttribute("mensaje", "Asignación Correcta");
                response.sendRedirect("personal");
                    
                } catch (Exception ex) {
                request.setAttribute("mensaje", "¡ERROR! No se Asignó el Personal a Spot" + "--" + ex.getMessage());
                request.getRequestDispatcher("WEB-INF/vistas/personal/agregaraSpot.jsp").forward(request, response);

                
                }
              
            } else {
                request.setAttribute("mensaje", "¡ERROR! No se encontró ningún Spot con el 'Código' : " + codigo + ".");
                request.getRequestDispatcher("WEB-INF/vistas/personal/agregaraSpot.jsp").forward(request, response);

                
            }
        } catch (ExcepcionPersonalizada ex) {
            request.setAttribute("mensaje", "¡ERROR! " + ex.getMessage());
        } catch (Exception ex) {
            request.setAttribute("mensaje", "¡ERROR! Se produjo un error al buscar el Spot.");
        }
        
        
        
   }

}
