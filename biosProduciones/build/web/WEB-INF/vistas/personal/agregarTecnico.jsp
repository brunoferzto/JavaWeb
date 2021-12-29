<%-- 
    Document   : AgregarTecnico
    Created on : 07-jul-2020, 19:00:42
    Author     : Bruno
--%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<form name="editor" method="post" accept-charset="ISO-8859-1">
    <t:paginaMaestra titulo="Agregar Personal Técnico">
            <jsp:body>      
      <div>
        <label for="cedula">Cédula</label>
       <input type="text" name="cedula" id="cedula" value="${not empty persona ? persona.cedula : param.cedula}" />
        </div>
     
    <div>
        <label for="nombre">Nombre</label>
        <input type="text" name="nombre" id="nombre" value="${not empty persona ? persona.nombre : param.nombre}" />
    </div>
    
   <p>
        <input type="radio" id="Director" name="cargo" value="Director" checked="">
        <label for="Director">Director</label><br>
        
        <input type="radio" id="Camarógrafo" name="cargo" value="Camarógrafo">
        <label for="Camarógrafo">Camarógrafo</label><br>        
        <input type="radio" id="Editor" name="cargo" value="Editor">
        <label for="Editor">Editor</label><br>       
        <input type="radio" id="Otro" name="cargo" value="Otro">
        <label for="Otro">Otro</label>
        <br>   
    
    <div>
        <input type="submit" value="Agregar"/> 
    </div>
</form>
            <t:mensajes />
         </jsp:body>
</t:paginaMaestra>
<script>
    document.getElementById('${idFoco}').focus();
    document.getElementById('${idFoco}').select();
</script>