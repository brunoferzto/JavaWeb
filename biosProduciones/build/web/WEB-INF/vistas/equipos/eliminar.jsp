<%-- 
    Document   : eliminar
    Created on : 06-jul-2020, 20:20:04
    Author     : Bruno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginaMaestra titulo="Eliminar Equipo">
    <jsp:body>
        <c:if test="${not empty equipo}">
            <p>¿Confirma que desea eliminar el equipo con ID <strong>${equipo.id}</strong> (${equipo.id})?</p>
            
            <form method="post" accept-charset="ISO-8859-1">
                <p>
                    <input type="hidden" name="id" value="${equipo.id}" />
                    <input type="submit" name="accion" value="Eliminar" />
                </p>
            </form>
        </c:if>
       
        
        <t:mensajes />
    </jsp:body>
</t:paginaMaestra>
