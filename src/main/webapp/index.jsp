<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Aves</title>
    </head>
    <body>
<%-- FALTA LA ESTRUCTURA DEL PROYECTO EN CUANTO A CARPETAS --%>
<%-- EN LAS OPCIONES DEL MENÚ TE FALLAN LOS PLURALES Y SINGULARES --%>
        <h1>Página de avistamientos</h1>
        <ul>
            <li><a href="<%= request.getContextPath()%>/Controlador1?insertar" >Añadir Ave</a></li>
            <li><a href="<%= request.getContextPath()%>/Controlador1?visualizar" >Visualizar Aves</a></li>
            <li><a href="<%= request.getContextPath()%>/Controlador1?actualizar" >Actualizar Aves</a></li>
            <li><a href="<%= request.getContextPath()%>/Controlador1?borrar" >Eliminar Ave</a></li>
        </ul>
    </body>
</html>
