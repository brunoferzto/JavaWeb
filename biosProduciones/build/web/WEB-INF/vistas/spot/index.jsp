<%-- 
    Document   : index
    Created on : 10-jul-2020, 12:52:34
    Author     : Bruno
--%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<form name="editor" method="post" accept-charset="ISO-8859-1">
    <t:paginaMaestra titulo="Agregar Spot">
            <jsp:body>      
     
    <div>
        <label for="titulo">Titulo</label>
        <input type="text" name="titulo" id="titulo" />
    </div>
    
    <div>
        <label for="precio">Precio</label>
        <input type="text" name="precio" id="precio" />
    </div> 
            <div>
                <label for="fechaINI">Fecha Inicio</label>
                <input type="text" name="fechaINI" id="fechaINI">
            </div>
            <div>
                <label for="fechaFIN">Fecha Final</label>
                <input type="date" name="fechaFIN" id="fechaFIN">
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

