<%-- 
    Document   : borrado
    Created on : 02-nov-2016, 10:39:16
    Author     : Zack
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="es.albarregas.modelo.Ave"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>El ave se ha eliminado correctamente</h2>
        <input type="submit" name="aceptar" value="Aceptar">
        <p><a href="<%= request.getContextPath()%>">Volver</a></p>
    </body>
</html>
