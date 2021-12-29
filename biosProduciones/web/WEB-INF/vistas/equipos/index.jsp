<%-- 
    Document   : index
    Created on : 06-jul-2020, 21:55:25
    Author     : Bruno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginaMaestra titulo="Recursos : Equipos">
    <jsp:body>
        <fmt:setLocale value="en-US" />
        
        <p><a href="equipos?accion=agregar"><img src="imagenes/glyphicons-191-plus-sign.png" alt="Agregar" title="Agregar..." ></a></p>
        
        <table class="listado">
            <tr>
                <th>ID</th><th>TIPO</th><th>MARCA</th><th>MODELO</th><th></th>
            </tr>
            
            <c:forEach items="${equipos}" var="equipo">
                <tr>
                    <td class="texto-centro">${equipo.id}</td>
                    <td>${equipo.tipo}</td>
                    <td>${equipo.marca}</td>
                    <td>${equipo.modelo}</td>
                    
                    
                    <td>
                        <a href="equipos?accion=mostrar&id=${equipo.id}"><img src="imagenes/glyphicons-52-eye-open.png" alt="Ver" title="Ver..." ></a>&nbsp;&nbsp;
                        <a href="equipos?accion=modificar&id=${equipo.id}"><img src="imagenes/glyphicons-31-pencil.png" alt="Modificar" title="Modificar..." ></a>&nbsp;&nbsp;
                        <a href="equipos?accion=eliminar&id=${equipo.id}"><img src="imagenes/glyphicons-192-minus-sign.png" alt="Eliminar" title="Eliminar..." ></a>
                        <a href="equipos?accion=agregarSpot&id=${equipo.id}"><img src="imagenes/anunciospot.png" alt="Agregar" title="Agregar a Spot..." ></a>

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
