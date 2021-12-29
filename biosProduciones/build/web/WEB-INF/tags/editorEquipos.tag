<%-- 
    Document   : editorEquipos
    Created on : 03-jul-2020, 15:01:19
    Author     : Bruno
--%>

<%@tag description="Editor de empleados." pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@attribute name="deshabilitarClave"%>
<%@attribute name="idFoco" required="true"%>
<%@attribute name="textoBoton" required="true"%>



<fmt:setLocale value="en-US" />


<form name="editor" method="post" accept-charset="ISO-8859-1">
    <div>
        <label for="id">Id</label>
        <c:choose>
            <c:when test="${deshabilitarClave}">
                <input type="text" name="id" id="id" value="${not empty equipo ? equipo.id : param.id}" readonly="readonly" />
            </c:when>
            <c:otherwise>
                <input type="text" name="id" id="id" value="${not empty equipo ? equipo.id : param.id}" />
            </c:otherwise>
        </c:choose>
    </div>
    <div>   
        
    <%if( request.getAttribute("opcion")!=null && request.getAttribute("opcion").equals("Camara"))
    {%>
        <input type="radio" id="camara" name="tipo" value="Camara" checked="">
        <label for="Camara">Camara</label><br>
        <input type="radio" id="Grua" name="tipo" value="Grua">
        <label for="Grua">Grua</label><br>
        <input type="radio" id="Reflector" name="tipo" value="Reflector">
        <label for="Reflector">Reflector</label><br>
        <input type="radio" id="Otro" name="tipo" value="Otro">
        <label for="Otro">Otro</label>
       
    <%}else if(request.getAttribute("opcion")!=null && request.getAttribute("opcion").equals("Grua"))                          
      {%>  
        <input type="radio" id="camara" name="tipo" value="Camara" >
        <label for="Camara">Camara</label><br>
        <input type="radio" id="Grua" name="tipo" value="Grua" checked="">
        <label for="Grua">Grua</label><br>
        <input type="radio" id="Reflector" name="tipo" value="Reflector">
        <label for="Reflector">Reflector</label><br>
        <input type="radio" id="Otro" name="tipo" value="Otro">
        <label for="Otro">Otro</label>
     <%}else if(request.getAttribute("opcion")!=null && request.getAttribute("opcion").equals("Reflector")) 
        {%> 
        <input type="radio" id="camara" name="tipo" value="Camara" >
        <label for="Camara">Camara</label><br>
        <input type="radio" id="Grua" name="tipo" value="Grua" >
        <label for="Grua">Grua</label><br>
        <input type="radio" id="Reflector" name="tipo" value="Reflector" checked="">
        <label for="Reflector">Reflector</label><br>
        <input type="radio" id="Otro" name="tipo" value="Otro" >
        <label for="Otro">Otro</label>
     <%}else{%>    
        <input type="radio" id="camara" name="tipo" value="Camara" >
        <label for="Camara">Camara</label><br>
        <input type="radio" id="Grua" name="tipo" value="Grua">
        <label for="Grua">Grua</label><br>
        <input type="radio" id="Reflector" name="tipo" value="Reflector">
        <label for="Reflector">Reflector</label><br>
        <input type="radio" id="Otro" name="tipo" value="Otro" checked="">
        <label for="Otro">Otro</label>
        <%}%>     
        
    </div>
    
 
    
    <div>
        <label for="marca">Marca</label>
        <input type="text" name="marca" id="marca" value="${not empty equipo ? equipo.marca : param.marca}" />
    </div>   
    
    <div>
        <label for="modelo">Modelo</label>
        <input type="text" name="modelo" id="modelo" value="${not empty equipo ? equipo.modelo : param.modelo}" />
    </div>
       
    <div>
        <label for="Descripcion">Descripcion</label>
        <textarea name="descripcion" id="descripcion" value="${not empty equipo ? equipo.descripcion : param.descripcion}" />${not empty equipo ? equipo.descripcion : param.descripcion}</textarea>
    </div>
    
    
    
    <div>
        <input type="submit" value="${textoBoton}"/> 
    </div>
</form>

<script>
    document.getElementById('${idFoco}').focus();
    document.getElementById('${idFoco}').select();
</script>
