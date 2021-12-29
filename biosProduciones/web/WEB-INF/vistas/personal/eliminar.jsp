<%-- 
    Document   : eliminar
    Created on : 07-jul-2020, 19:02:13
    Author     : Bruno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginaMaestra titulo="Eliminar Personal">
    <jsp:body>
        <c:if test="${not empty persona}">
            <p>¿Confirma que desea eliminar a la Pesona con Cédula <strong>${persona.cedula}</strong> (${persona.cedula})?</p>
            
            <form method="post" accept-charset="ISO-8859-1">
                <p>
                    <input type="hidden" name="id" value="${persona.cedula}" />
                    <input type="submit" name="accion" value="Eliminar" />
                </p>
            </form>
        </c:if>
       
        
        <t:mensajes />
    </jsp:body>
</t:paginaMaestra>