<%-- 
    Document   : buscar
    Created on : 10-jul-2020, 17:59:29
    Author     : Bruno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<form name="editor" method="post" accept-charset="ISO-8859-1">
<t:paginaMaestra titulo="Buscar Spot">
    <jsp:body>
        <fmt:setLocale value="en-US" />
        
        <div>
        <label for="titulo">Ingrese Código</label>
        <input type="text" name="codigo" id="codigo"/>
        </div>
        <p>
         <div>
             <input type="submit" value="Buscar" name="accion"/> 
        </div>
        </form>
         <t:mensajes />
        
        <script>
            document.getElementById('buscar').focus();
            document.getElementById('buscar').select();
        </script>
    </jsp:body>
</t:paginaMaestra>
