<%-- 
    Document   : modificarTecnico
    Created on : 07-jul-2020, 19:03:37
    Author     : Bruno
--%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<form name="editor" method="post" accept-charset="ISO-8859-1">
    <t:paginaMaestra titulo="Modificar Técnico">
            <jsp:body>      
      <div>
        <label for="cedula">Cédula</label>
        <input type="text" name="id" id="id" value="${not empty persona ? persona.cedula : param.cedula}" readonly="true" />
    </div>
     
    <div>
        <label for="nombre">Nombre</label>
        <input type="text" name="nombre" id="nombre" value="${not empty persona ? persona.nombre : param.nombre}" />
    </div>
    
    <div>
        <label for="cargo">Cargo</label>
        <input type="text" name="cargo" id="cargo" value="${not empty persona ? persona.cargo : param.cargo}" />
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
