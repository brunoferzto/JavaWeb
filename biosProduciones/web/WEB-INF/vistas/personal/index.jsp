<%-- 
    Document   : index
    Created on : 06-jul-2020, 17:19:04
    Author     : Bruno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginaMaestra titulo="Recursos Humanos">
    <jsp:body>
        <fmt:setLocale value="en-US" />
        
        <p><a href="personal?accion=agregarLogistico"><img src="imagenes/glyphicons-191-plus-sign.png" alt="Agregar" title="Agregar Logístico..." ></a>
           <a href="personal?accion=agregarTecnico"><img src="imagenes/camera.png" alt="Agregar" title="Agregar Técnico..." ></a>
           <a href="personal?accion=agregarActores"><img src="imagenes/actores.png" alt="Agregar" title="Agregar Actores..." ></a>
        

        <table class="listado">
            <tr>
                <th>CÉDULA</th><th>NOMBRE</th><th>TIPO</th><th></th>
            </tr>
            
            <c:forEach items="${personas}" var="personal">
                <tr>
                    <td class="texto-centro">${personal.cedula}</td>
                    <td>${personal.nombre}</td>
                    <td>${personal.toString()}</td>
                    
                    
                    <td>
                        <a href="personal?accion=modificar&cedula=${personal.cedula}"><img src="imagenes/glyphicons-31-pencil.png" alt="Modificar" title="Modificar..." ></a>&nbsp;&nbsp;
                        <a href="personal?accion=eliminar&cedula=${personal.cedula}"><img src="imagenes/glyphicons-192-minus-sign.png" alt="Eliminar" title="Eliminar..." ></a>
                        <a href="personal?accion=agregarSpot&cedula=${personal.cedula}"><img src="imagenes/anunciospot.png" alt="Agregar" title="Agregar a Spot..." ></a>

                    </td>
                </tr>
            </c:forEach>
        </table>
                
        <t:mensajes />
        
        <script>
            document.getElementById('buscar').focus();
            document.getElementById('buscar').select();
        </script>
    </jsp:body>
</t:paginaMaestra>
