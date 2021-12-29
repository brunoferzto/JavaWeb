<%-- 
    Document   : AgregarLogistico
    Created on : 07-jul-2020, 19:00:18
    Author     : Bruno
--%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<form name="editor" method="post" accept-charset="ISO-8859-1">
    <t:paginaMaestra titulo="Agregar Personal Logístico">
            <jsp:body>      
      <div>
        <label for="cedula">Cédula</label>
         <input type="text" name="cedula" id="cedula"/>
       </div>
     
    <div>
        <label for="nombre">Nombre</label>
        <input type="text" name="nombre" id="nombre" />
    </div>
    
      <p>
        <input type="radio" id="Casting" name="area" value="Casting" checked="">
        <label for="Casting">Casting</label><br>
        <input type="radio" id="Locaciones" name="area" value="Locaciones">
        <label for="Locaciones">Locaciones</label><br>
        <input type="radio" id="Contaduria" name="area" value="Contaduria">
        <label for="Contaduría">Contaduría</label><br>
        <input type="radio" id="Otro" name="area" value="Otro">
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
