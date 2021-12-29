<%-- 
    Document   : agregaraSpot
    Created on : 10-jul-2020, 16:52:40
    Author     : Bruno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<form name="editor" method="post" accept-charset="ISO-8859-1">
<t:paginaMaestra titulo="Agregar Recurso a Spot">
    <jsp:body>  
    <div>
                <p>

   <label for="id">Id</label>
    <input type="text" name="id" id="id" value="${not empty equipo ? equipo.id : param.id}" readonly="readonly" />
    </div>
    <div>
                <p>

    <label for="tipo">Tipo</label>
    <input type="text" name="tipo" id="tipo" value="${not empty equipo ? equipo.tipo : param.tipo}" readonly="readonly" />
    </div>
    
     <div>
                 <p>

    <label for="tipo">Ingrese Código de Spot</label>
    <input type="text" name="spot" id="spot" />
    </div>
    
    <div>
        <p>
        <input type="submit" value="Agregar" name="accion"/> 
    </div>
    </form>
    <t:mensajes />
    </jsp:body>
</t:paginaMaestra>
<script>
    document.getElementById('${idFoco}').focus();
    document.getElementById('${idFoco}').select();
</script>