<%--
    Document   : insertar
    Created on : 28-oct-2016, 17:28:50
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
        <h2>Listado de todas las aves de la base de datos</h2>
        <form method="post" action="ControladorRUD2">
            <table>
                <%
                    List<Ave> listado = null;
                    listado = new ArrayList();
                    listado = (ArrayList<Ave>) request.getAttribute("lista");
                    for (Ave pajaro : listado) {
// TE FALTAN LOS CHECKBOX EN CADA REGISTRO
                %>
                <tr>
                    <td><%=pajaro.getAnilla()%></td>
                    <td><%=pajaro.getEspecie()%></td>
                    <td><%=pajaro.getLugar()%></td>
                    <td><%=pajaro.getFecha()%></td>
                </tr>
                <%
                    }
                %>
                <br/>
            </table>
            Anilla del ave que quiere borrar:
            <input type="text" name="anilla" value="" >
            <br/>
            <br/>
            <input type="submit" name="enviar" value="Borrar">

        </form>


        <br />
    </body>
</html>
