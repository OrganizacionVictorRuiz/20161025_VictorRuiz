<%-- 
    Document   : actualizar
    Created on : 01-nov-2016, 22:58:27
    Author     : Zack
--%>

<%@page import="es.albarregas.modelo.Ave"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actualizado</title>
    </head>
    <body>    
        <h1>Actualizar Aves</h1>
        <% Ave ave = (Ave) request.getAttribute("creado");%>

        <form action="ControladorRUD2" method="post">
            <label for="anilla">Anilla:</label>&nbsp;&nbsp; 
            <input type="text"  name="anilla" value="<%=ave.getAnilla()%>" readonly=""/>&nbsp;&nbsp;

            <label for="especie">Especie:</label>&nbsp;&nbsp; 
            <input type="text"  name="especie" value="<%=ave.getEspecie()%>"/>&nbsp;&nbsp; 

            <label for="lugar">Lugar:</label>&nbsp;&nbsp; 
            <input type="text"  name="lugar" value="<%=ave.getLugar() %>"/>&nbsp;&nbsp; 

            <label for="fecha">Fecha:</label>&nbsp;&nbsp; 
            <input type="text"  name="fecha" value="<%=ave.getFecha()%>"/>
            <br/><br/>
            <input  type="submit" value="actualizar" name="actualizarFin"/>
        </form> 
        <br/><br/>
        <p><a href="<%= request.getContextPath()%>">Volver</a></p>

    </body>
</html>
