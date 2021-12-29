<%-- 
    Document   : buscar
    Created on : 10-jul-2020, 17:40:29
    Author     : Bruno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginaMaestra titulo="Informacion de Spot">
    <jsp:body>
        <fmt:setLocale value="en-US" />
        
        <div>
        <label for="titulo">Código</label>
        <input type="text" name="codigo" id="codigo" value="${not empty spot ? spot.id : spot.id}" readonly="readonly" />
        <p>
        </div>
        
        <div>
        <label for="titulo">Titulo</label>
        <input type="text" name="titulo" id="titulo" value="${not empty spot ? spot.titulo : spot.titulo}" readonly="readonly" />.
        <p>
        </div>
    
    <div>
        <label for="precio">Precio</label>
        <input type="text" name="precio" id="precio" value="${not empty spot ? spot.precio : spot.precio}" readonly="readonly" />
        <p>
    </div> 
            <div>
                <label for="fechaINI">Fecha Inicio</label>
                <input type="text" name="fechaINI" id="fechaINI" value="${not empty spot ? spot.fechaInicio : spot.fechaInicio}" readonly="readonly" />
                <p>
            </div>
            <div>
                <label for="fechaFIN">Fecha Final</label>
                <input type="date" name="fechaFIN" id="fechaFIN" value="${not empty spot ? spot.fechaFinal : spot.fechaFinal}" readonly="readonly" />
                <p>
            </div>
                
             <table class="listado">
            <tr>
                <th>CÉDULA</th><th>NOMBRE</th><th>TIPO</th>
            </tr>
            <c:forEach items="${personas}" var="personas">
                <tr>
                    <td class="texto-centro">${personas.cedula}</td>
                    <td>${personas.nombre}</td>
                    <td>${personas.toString()}</td>                  
                </tr>
            </c:forEach>
             </table>    
                
                <p> 
                
            <table class="listado">
            <tr>
                <th>ID</th><th>TIPO</th><th>MARCA</th><th>MODELO</th>
            </tr>
            
            <c:forEach items="${equipos}" var="equipo">
                <tr>
                    <td class="texto-centro">${equipo.id}</td>
                    <td>${equipo.tipo}</td>
                    <td>${equipo.marca}</td>
                    <td>${equipo.modelo}</td>
                    
                </tr>
            </c:forEach>
        </table>
                <P>
           
         
     
        <t:mensajes />
    </jsp:body>
</t:paginaMaestra>

