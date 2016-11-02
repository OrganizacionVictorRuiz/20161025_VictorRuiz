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
        <%
            Ave ave = (Ave) request.getAttribute("unoSolo");
        %>
        <h2>El ave actualizado con anilla: <%=ave.getAnilla()%></h2>
        <p>La especie es: <%=ave.getEspecie()%></p>
        <p>se encuentra en: <%=ave.getLugar()%></p>
        <p>en la fecha: <%=ave.getFecha()%></p>
        <br />   

        <br />
        <p><a href="<%= request.getContextPath()%>">Volver</a></p>

    </body>
</html>
