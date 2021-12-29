<%-- 
    Document   : modificar
    Created on : 06-jul-2020, 19:05:49
    Author     : Bruno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginaMaestra titulo="Modificar Equipo">
    <jsp:body>
        <c:if test="${!ocultarFormulario}">
            <t:editorEquipos deshabilitarClave="true" idFoco="id" textoBoton="Modificar" />
        </c:if>
        
        <t:mensajes />
    </jsp:body>
</t:paginaMaestra>
