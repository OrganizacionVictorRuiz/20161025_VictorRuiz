<%-- 
    Document   : actualizado
    Created on : 02-nov-2016, 10:38:10
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
        <title>Lista para actualizar</title>
    </head>
    <body>
        <h2>Listado de todas las aves de la base de datos para actualizar</h2>
    <form method="post" action="ControladorRUD2">    
    <table>
    <%
    List<Ave> listado = null;
    listado = new ArrayList();
    listado = (ArrayList<Ave>)request.getAttribute("lista");
    for(Ave pajaro : listado){
    %>
    <tr>
        <td><input type="radio" name="anilla" value="<%=pajaro.getAnilla()%>"></td>
        <td><%=pajaro.getEspecie()%></td>
        <td><%=pajaro.getLugar()%></td>
        <td><%=pajaro.getFecha()%></td> 
    </tr>
    <%
    }
    %>
    <br/>
        <input type="submit" name="enviar2" value="Actualizar">
    </table>
    </form>
        

<br />
    </body>
</html>
