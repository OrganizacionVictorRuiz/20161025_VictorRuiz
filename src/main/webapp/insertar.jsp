<%-- 
    Document   : insertar
    Created on : 28-oct-2016, 17:28:50
    Author     : Zack
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Introducion de datos</title>
    </head>
    <body>
        <h2>Formulario de introducion de datos</h2>
        <form method="post" action="ControladorInsert">
            Anilla: 
            <input type="text" name="anilla" ><br/>
            Especie: 
            <input type="text" name="especie" ><br/>
            Lugar: 
            <input type="text" name="lugar" ><br/>
            Fecha: 
            <input type="text" name="fecha" ><br/>
            <br/>
            <input type="submit" name="enviar" value="Enviar">
            <input type="reset" name="limpiar" value="Limpiar">
        </form>
    </body>
</html>
