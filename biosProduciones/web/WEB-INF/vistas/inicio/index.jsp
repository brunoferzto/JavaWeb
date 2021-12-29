<%-- 
    Document   : index
    Created on : 10-jul-2020, 18:59:03
    Author     : Bruno
--%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<form name="editor" method="post" accept-charset="ISO-8859-1">

<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <link rel="stylesheet" href="css/estilos.css" />
        <div>
        <label for="cedula">Usuario</label>
       <input type="text" name="usuario" id="usuario"/>
        </div>
        <p>
        <div>
        <label for="cedula">Contraseña</label>
       <input <input type="password" name="contrasenia" id="contrasenia"/>
        </div>
        <p>
        <div>
        <input type="submit" value="Iniciar Sesion"/> 
        </div>
       
    </body>
</html>
</form>

<t:mensajes />