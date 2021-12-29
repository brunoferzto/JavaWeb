<%-- 
    Document   : AgregarActor
    Created on : 07-jul-2020, 18:59:54
    Author     : Bruno
--%>
                

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Nuevo Actor</title>
        
        <link rel="stylesheet" href="css/estilos.css" />
        
        <style>
            
            form div {
                margin-top: 1.5em;
            }
            
            label {
                font-weight: bold;
            }
            
            label:first-child {
                display: block;
                margin-bottom: .25em;
            }
        </style>
        
    </head>
    <body>
        <h1>Registrar Nuevo Actor</h1>

        <form  method="post" enctype="multipart/form-data">

            <div>
                <label for="cedula">Cédula</label>
                <input type="text" name="cedula" id="cedula"/>
            </div>
            <div>
                <label for="nombre">Nombre:</label>
                <input type="text" name="nombre" id="nombre" />
            </div>
            
            <div>
                <label for="imagen">Imagen:</label>
                <input type="file" name="imagen" id="imagen" />
            </div>
            
            <br>
            
     <%if( request.getAttribute("opcion")!=null && request.getAttribute("opcion").equals("Femenino"))
    {%>
        <input type="radio" id="Femenino" name="sexo" value="Femenino" checked="">
        <label for="Femenino">Femenino</label><br>
        <input type="radio" id="Masculino" name="sexo" value="Masculino">
        <label for="Masculino">Masculino</label><br>           
    <%} else
    {%>
        <input type="radio" id="Femenino" name="sexo" value="Femenino" >
        <label for="Femenino">Femenino</label><br>
        <input type="radio" id="Masculino" name="sexo" value="Masculino" checked="">
        <label for="Masculino">Masculino</label><br> 
        <%}%>
        
         <div>
            <label for="imagen">Fecha Nacimiento(DD/MM/AAAA): </label>
            <input type="text" name="fechaNTO" id="fechaNTO" />
            </div>
        
            <div>
                <input type="submit" name="accion" value="Agregar" />
            </div>
            
            
        </form>
    </body>
</html>
