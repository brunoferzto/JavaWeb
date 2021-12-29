<%-- 
    Document   : modificarLogistico
    Created on : 07-jul-2020, 19:03:28
    Author     : Bruno
--%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<form name="editor" method="post" accept-charset="ISO-8859-1">
    <t:paginaMaestra titulo="Modificar Log�stico">
            <jsp:body>      
      <div>
        <label for="cedula">C�dula</label>
        <input type="text" name="id" id="id" value="${not empty persona ? persona.cedula : param.cedula}" readonly="true" />
    </div>
     
    <div>
        <label for="nombre">Nombre</label>
        <input type="text" name="nombre" id="nombre" value="${not empty persona ? persona.nombre : param.nombre}" />
    </div>
    
    <div>
        <label for="area">Area</label>
        <input type="text" name="area" id="area" value="${not empty persona ? persona.area : param.area}" />
    </div>    
    
    <div>
        <label for="tipo">Tipo de Personal:</label>
        <input type="text" name="tipo" readonly="true" id="tipo" value="${not empty persona ? persona.toString() : param.toString()}" />
    </div>
    
    <div>
        <input type="submit" value="Modificar"/> 
    </div>
</form>
            <t:mensajes />
         </jsp:body>
</t:paginaMaestra>
<script>
    document.getElementById('${idFoco}').focus();
    document.getElementById('${idFoco}').select();
</script>
