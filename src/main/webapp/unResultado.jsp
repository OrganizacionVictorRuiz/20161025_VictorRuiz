<%@page import="es.albarregas.modelo.Ave"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Resultados</title>
</head>
<body>
    <%
        Ave ave = (Ave)request.getAttribute("unoSolo");
    %>
    <h2>Salida de resultados para la anilla <%=ave.getAnilla()%></h2>
    <p>La especie es: <%=ave.getEspecie() %></p>
    <p>se encuentra en: <%=ave.getLugar() %></p>
    <p>en la fecha: <%=ave.getFecha() %></p>
    
          

<br />
<p><a href="<%= request.getContextPath()%>">Volver</a></p>
</body>
</html>