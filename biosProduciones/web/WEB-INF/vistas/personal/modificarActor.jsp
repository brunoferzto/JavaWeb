<%-- 
    Document   : modificarActor
    Created on : 07-jul-2020, 19:03:16
    Author     : Bruno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <h1>Modificar Actor</h1>
        
        <form name="editor" method="post" accept-charset="ISO-8859-1">
            <div>
                <label for="cedula">Cédula</label>
                <input type="text" name="cedula" id="cedula" value="${not empty persona ? persona.cedula : param.cedula}" readonly="readonly"/>
            </div>
            <div>
                <label for="nombre">Nombre:</label>
                <input type="text" name="nombre" id="nombre" value="${not empty persona ? persona.nombre : param.nombre}"/>
            </div>
            
            <div>
                <img src="fotosActores/general.png" alt="${param.cedula}" style="vertical-align: middle;" /></p>
            </div>
            
            <br>
            
     <%if( request.getAttribute("sexo")!=null && request.getAttribute("sexo").equals("Femenino"))
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
            <label for="imagen">Fecha Nacimiento(AAAA/MM/DD): </label>
            <input type="text" name="fechaNTO" id="fechaNTO" value="${not empty persona ? persona.fechaNacimiento : param.fechaNacimiento}" />
            </div>
        
            <div>
        <label for="tipo">Tipo de Personal:</label>
        <input type="text" name="tipo" readonly="true" id="tipo" value="${not empty persona ? persona.toString() : param.tipo}" />
            </div>
            
            <div>
                <input type="submit" name="accion" value="Modificar" />
            </div>
            
            
        </form>
    </body>
</html>
