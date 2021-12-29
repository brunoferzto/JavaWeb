<%-- 
    Document   : agregaraSpot
    Created on : 10-jul-2020, 14:20:06
    Author     : Bruno
--%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<form name="editor" method="post" accept-charset="ISO-8859-1">
    <t:paginaMaestra titulo="Agregar Personal a Spot">
            <jsp:body>      
      <div>
        <label for="cedula">Cédula</label>
        <input type="text" name="id" id="id" value="${not empty persona ? persona.cedula : param.cedula}" readonly="true" />
    </div>
     
    <div>
        <label for="nombre">Nombre</label>
        <input type="text" name="nombre" readonly="true" id="nombre" value="${not empty persona ? persona.nombre : param.nombre}" />
    </div>
      
    <div>
        <label for="tipo">Tipo de Personal:</label>
        <input type="text" name="tipo" readonly="true" id="tipo" value="${not empty persona ? persona.toString() : param.toString()}" />
    </div>
    
    <div>
        <label for="tipo">Código de Spot:</label>
        <input type="text" name="spot"  id="spot"/>
    </div>
    
    <div>
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
