<%-- 
    Document   : ver
    Created on : 06-jul-2020, 18:48:23
    Author     : Bruno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginaMaestra titulo="Ver Equipo">
    <jsp:body>
        <c:if test="${not empty equipo}">
            <jsp:useBean id="equipo" type="biosProduciones.modelo.compartidos.beans.datatypes.DTEquipos" scope="request" />
            
            
            <ul>
                <li><strong>Id :</strong> <jsp:getProperty name="equipo" property="id" /></li>
                <li><strong>Tipo :</strong> <jsp:getProperty name="equipo" property="tipo" /></li>
                <li><strong>Marca</strong> <jsp:getProperty name="equipo" property="marca" /></li>
                <li><strong>Modelo</strong> <jsp:getProperty name="equipo" property="modelo" /></li>
                <li><strong>Descripcion</strong> <jsp:getProperty name="equipo" property="descripcion" /></li>


            </ul>
        </c:if>
        
        
        <t:mensajes />
    </jsp:body>
</t:paginaMaestra>