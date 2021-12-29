<%-- 
    Document   : agregar
    Created on : 06-jul-2020, 17:41:03
    Author     : Bruno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginaMaestra titulo="Agregar Equipo">
    <jsp:body>
        <t:editorEquipos idFoco="id" textoBoton="Agregar" />
                
        <t:mensajes />
    </jsp:body>
</t:paginaMaestra>
