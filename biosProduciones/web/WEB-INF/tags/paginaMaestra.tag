<%-- 
    Document   : paginaMaestra
    Created on : 03-jul-2020, 15:02:39
    Author     : Bruno
--%>

<%@tag description="Pagina Maestra del Sitio" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="titulo"%>

<%-- any content can be specified here e.g.: --%>
<h2>${message}</h2>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>bios Produciones - ${titulo}</title>
        
        <link rel="stylesheet" href="css/estilos.css" />
 
    </head>
    <body>
        <div class="cabezal">
            <h1>biosProduciónes</h1>
            
            <h2>${titulo}</h2>
            
        <div class="vertical-menu">
         <a href="equipos"><img src="imagenes/reflectorico.png" alt="Ver" title="Ir a Equipos..." ></a>&nbsp;&nbsp;
         <a href="personal"><img src="imagenes/rhumanoico.png" alt="Ver" title="Ir a Recursos Humanos..." ></a>&nbsp;&nbsp;
         <a href="spot"><img src="imagenes/spotico.png" alt="Ver" title="Registrar Spot..." ></a>&nbsp;&nbsp;
         <a href="spot?accion=buscar"><img src="imagenes/lupaico.png" alt="Ver" title="Buscar Spot..." ></a>&nbsp;&nbsp;
         <a href="inicio?accion=index"><img src="imagenes/puertaico.png" alt="Ver" title="Salir..." ></a>&nbsp;&nbsp;
     
         </div>
        </div>
        
        
        
        <jsp:doBody />
        
        <script src="js/scripts.js"></script>
    </body>
</html>