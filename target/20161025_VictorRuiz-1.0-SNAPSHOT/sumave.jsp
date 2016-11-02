<%-- 
    Document   : insertar
    Created on : 28-oct-2016, 17:28:50
    Author     : Zack
--%>

<%@page import="es.albarregas.modelo.Ave"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ave Añadida</title>
    </head>  
    <body>    
        <%
            Ave ave = (Ave) request.getAttribute("unoSolo");
        %>
        <h2>Se ha añadido la anilla <%=ave.getAnilla()%></h2>
        <p>La especie es: <%=ave.getEspecie()%></p>
        <p>se encuentra en: <%=ave.getLugar()%></p>
        <p>en la fecha: <%=ave.getFecha()%></p>
        <br />   

        <br />
        <p><a href="<%= request.getContextPath()%>">Volver</a></p>

    </body>
</html>
