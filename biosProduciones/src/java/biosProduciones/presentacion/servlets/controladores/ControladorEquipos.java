/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biosProduciones.presentacion.servlets.controladores;

import biosProduciones.modelo.compartidos.beans.datatypes.DTEquipos;
import biosProduciones.modelo.compartidos.beans.datatypes.DTSpot;
import biosProduciones.modelo.compartidos.beans.excepciones.ExcepcionPersonalizada;
import biosProduciones.modelo.logica.FabricaLogica;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Bruno
 */
public class ControladorEquipos extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null) accion = "index";
        
        switch (accion) {
            case "index":
                index_get(request, response);
                
                break;
            case "agregar":
                agregar_get(request, response);
                
                break;
            case "mostrar":
                mostrar_get(request, response);
                
                break;
            case "modificar":
                modificar_get(request, response);
                
                break;
            case "eliminar":
                eliminar_get(request, response);
                
                    break;
            case "agregarSpot":
                agregarSpot_get(request, response);
                
                break;
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        
        switch (accion) {
            case "agregar":
                agregar_post(request, response);                
                break;
                
            case "modificar":
                modificar_post(request, response);
                
                break;
            case "eliminar":
                eliminar_post(request, response);
                
                break;
                
                case "agregarSpot":
                agregarSpot_post(request, response);
                
                break;
        }
    }
    
    public void index_get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<DTEquipos> equipos = FabricaLogica.getLogicaEquipos().listarEquipos();
            
            request.setAttribute("equipos", equipos);
        }  catch (ExcepcionPersonalizada ex) {
            request.setAttribute("mensaje", ex.getMessage());
        } catch (Exception ex) {
            request.setAttribute("mensaje", "No se pudo listar los equipos.");
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
        
        request.getRequestDispatcher("WEB-INF/vistas/equipos/index.jsp").forward(request, response);
    }
    
     public void agregar_get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/vistas/equipos/agregar.jsp").forward(request, response);
    }
    
    public void agregar_post(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = 0;
        
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException ex) {
            request.setAttribute("mensaje", "¡ERROR! El 'id' no es válido.");
            
            request.getRequestDispatcher("WEB-INF/vistas/equipos/agregar.jsp").forward(request, response);
            
            return;
        }
        
        String tipo = request.getParameter("tipo");
        String modelo = request.getParameter("modelo");
        String marca = request.getParameter("marca");
        String descripcion = request.getParameter("descripcion");
     
       
        DTEquipos equipo = new DTEquipos(id, tipo,  marca,  modelo,  descripcion);
        
        try {
            FabricaLogica.getLogicaEquipos().registarEquipo(equipo);
            
            request.getSession().setAttribute("mensaje", "¡Equipo agregado con éxito!");
            
            response.sendRedirect("equipos");
        } catch (ExcepcionPersonalizada ex) {
            request.setAttribute("mensaje", "¡ERROR! " + ex.getMessage());
            
            request.getRequestDispatcher("WEB-INF/vistas/equipos/agregar.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("mensaje", "¡ERROR! Se produjo un error al agregar el equipo.");
            
            request.getRequestDispatcher("WEB-INF/vistas/equipos/agregar.jsp").forward(request, response);
        }
    }
    
    public void mostrar_get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id;
        
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException ex) {
            request.setAttribute("mensaje", "¡ERROR! El 'ID' no es válido");
            
            request.getRequestDispatcher("WEB-INF/vistas/equipos/mostrar.jsp").forward(request, response);
            
            return;
        }
        
        try {
            DTEquipos equipo = FabricaLogica.getLogicaEquipos().buscarEquipo(id);
            
            if (equipo != null) {
                request.setAttribute("equipo", equipo);
            } else {
                request.setAttribute("mensaje", "¡ERROR! No se encontró ningún equipo con el 'ID' " + id + ".");
            }
        } catch (ExcepcionPersonalizada ex) {
            request.setAttribute("mensaje", "¡ERROR! " + ex.getMessage());
        } catch (Exception ex) {
            request.setAttribute("mensaje", "¡ERROR! Se produjo un error al buscar el equipo.");
        }
        
        request.getRequestDispatcher("WEB-INF/vistas/equipos/mostrar.jsp").forward(request, response);
    }
    
    public void modificar_get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id;
        
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException ex) {
            request.setAttribute("ocultarFormulario", true);
            request.setAttribute("mensaje", "¡ERROR! El 'ID' no es válido");
            request.getRequestDispatcher("WEB-INF/vistas/equipos/modificar.jsp").forward(request, response);
            
            return;
        }
        
        try {
            DTEquipos equipo = FabricaLogica.getLogicaEquipos().buscarEquipo(id);
            if (equipo != null) {
                request.setAttribute("equipo", equipo);
                
                String opcion = equipo.getTipo();
                
                request.setAttribute("opcion", opcion);
         
            } else {
                request.setAttribute("ocultarFormulario", true);
                request.setAttribute("mensaje", "¡ERROR! No se encontró ningún equipo con el 'ID'  " + id + ".");
            }
        } catch (ExcepcionPersonalizada ex) {
            request.setAttribute("mensaje", "¡ERROR! " + ex.getMessage());
        } catch (Exception ex) {
            request.setAttribute("mensaje", "¡ERROR! Se produjo un error al buscar el equipo.");
        }
           
        request.getRequestDispatcher("WEB-INF/vistas/equipos/modificar.jsp").forward(request, response);
    }
    
    public void modificar_post(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id;
        
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException ex) {
            request.setAttribute("mensaje", "¡ERROR! El 'ID' no es válido");
            
            request.getRequestDispatcher("WEB-INF/vistas/equipos/modificar.jsp").forward(request, response);
            
            return;
        }
        
        String tipo = request.getParameter("tipo");
        String modelo = request.getParameter("modelo");
        String marca = request.getParameter("marca");
        String descripcion = request.getParameter("descripcion");
     
       
        DTEquipos equipo = new DTEquipos(id, tipo,  marca,  modelo,  descripcion);
        
        try {
            FabricaLogica.getLogicaEquipos().modificarEquipo(equipo);
            
            request.getSession().setAttribute("mensaje", "¡Equipo modificado con éxito!");
            
            response.sendRedirect("equipos");
        } catch (ExcepcionPersonalizada ex) {
            request.setAttribute("mensaje", "¡ERROR! " + ex.getMessage());
            
            request.getRequestDispatcher("WEB-INF/vistas/equipos/modificar.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("mensaje", "¡ERROR! Se produjo un error al modificar el equipo.");
            
            request.getRequestDispatcher("WEB-INF/vistas/equipos/modificar.jsp").forward(request, response);
        }
    }
    
    public void eliminar_get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id;
        
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException ex) {
            request.setAttribute("mensaje", "¡ERROR! El 'ID' no es válido");            
            request.getRequestDispatcher("WEB-INF/vistas/equipos/eliminar.jsp").forward(request, response);           
            return;
        }
        
        try {
            DTEquipos equipo = FabricaLogica.getLogicaEquipos().buscarEquipo(id);
            
            if (equipo != null) {
                request.setAttribute("equipo", equipo);
            } else {
                request.setAttribute("mensaje", "¡ERROR! No se encontró ningún equipo con el 'ID' : " + id + ".");
            }
        } catch (ExcepcionPersonalizada ex) {
            request.setAttribute("mensaje", "¡ERROR! " + ex.getMessage());
        } catch (Exception ex) {
            request.setAttribute("mensaje", "¡ERROR! Se produjo un error al buscar el equipo.");
        }
        
        request.getRequestDispatcher("WEB-INF/vistas/equipos/eliminar.jsp").forward(request, response);
    }
    
    public void eliminar_post(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id;
        
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException ex) {
            request.setAttribute("mensaje", "¡ERROR! El 'ID' no es válido");
            
            request.getRequestDispatcher("WEB-INF/vistas/equipos/eliminar.jsp").forward(request, response);
            
            return;
        }
        
        try {
            FabricaLogica.getLogicaEquipos().eliminarEquipo(id); 
            
            request.getSession().setAttribute("mensaje", "¡Equipo eliminado con éxito!");
            
            response.sendRedirect("equipos");
        } catch (ExcepcionPersonalizada ex) {
            request.setAttribute("mensaje", "¡ERROR! " + ex.getMessage());
            
            request.getRequestDispatcher("WEB-INF/vistas/equipos/eliminar.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("mensaje", "¡ERROR! Se produjo un error al eliminar el equipo.");
            
            request.getRequestDispatcher("WEB-INF/vistas/equipos/eliminar.jsp").forward(request, response);
        }
    }
    
    public void agregarSpot_get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id;
        
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException ex) {
            request.setAttribute("ocultarFormulario", true);
            request.setAttribute("mensaje", "¡ERROR! El 'ID' no es válido");
            request.getRequestDispatcher("WEB-INF/vistas/equipos/index.jsp").forward(request, response);
            
            return;
        }
        
        try {
            DTEquipos equipo = FabricaLogica.getLogicaEquipos().buscarEquipo(id);
            if (equipo != null) {
                request.setAttribute("equipo", equipo);                
                String opcion = equipo.getTipo();               
                request.setAttribute("opcion", opcion);
                
                HttpSession misession= (HttpSession) request.getSession();
                misession= request.getSession(true);
                misession.setAttribute("equipo",equipo);
         
            } else {
                request.setAttribute("ocultarFormulario", true);
                request.setAttribute("mensaje", "¡ERROR! No se encontró ningún equipo con el 'ID'  " + id + ".");
            }
        } catch (ExcepcionPersonalizada ex) {
            request.setAttribute("mensaje", "¡ERROR! " + ex.getMessage());
        } catch (Exception ex) {
            request.setAttribute("mensaje", "¡ERROR! Se produjo un error al buscar el equipo.");
        }
           
        request.getRequestDispatcher("WEB-INF/vistas/equipos/agregaraSpot.jsp").forward(request, response);
    }
    
    public void agregarSpot_post(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
              
        HttpSession misession= (HttpSession) request.getSession();
        DTEquipos equipo = (DTEquipos) misession.getAttribute("equipo"); 
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
                    FabricaLogica.getLogicaSpot().asignarEquipoaSpot(spot, equipo);
                    
                request.setAttribute("mensaje", "Asignación Correcta");
                response.sendRedirect("equipos");
                

                    
                } catch (Exception ex) {
                request.setAttribute("mensaje", "¡ERROR! No se Asignó el Equipo a Spot" + ex.getMessage().toString());
                 request.getRequestDispatcher("WEB-INF/vistas/equipos/index.jsp").forward(request, response);

                
                }
              
            } else {
                request.setAttribute("mensaje", "¡ERROR! No se encontró ningún Spot con el 'Código' : " + codigo + ".");
            }
        } catch (ExcepcionPersonalizada ex) {
            request.setAttribute("mensaje", "¡ERROR! " + ex.getMessage());
        } catch (Exception ex) {
            request.setAttribute("mensaje", "¡ERROR! Se produjo un error al buscar el Spot.");
        }
    
    }
}
