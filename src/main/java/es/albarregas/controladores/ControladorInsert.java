/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controladores;

import es.albarregas.modelo.Ave;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author Zack
 */
@WebServlet(name = "ControladorInsert", urlPatterns = {"/ControladorInsert"})
public class ControladorInsert extends HttpServlet {

    DataSource ds;

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            Context initialContext = new InitialContext();
            ds = (DataSource) initialContext.lookup("java:comp/env/jdbc/Pool");
        } catch (NamingException ex) {
            System.out.println("Problemas en el acceso a la BD...");
            ex.printStackTrace();
        }
    } // public init. Accedemos a la base de Datos

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conexion = null;
        Statement sentencia = null;
        PreparedStatement preparada = null;
        ResultSet resultado = null;
        Ave ave = null;
        String url = null;
        // Declaramos una serie de variables que tendremos que usar para nuestras sentencias SQL
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al cargar el Driver");
            ex.printStackTrace();
        } // Se carga el driver
        if (request.getParameter("anilla").length() > 3 || "".equals(request.getParameter("anilla"))) {
            url = "error.jsp";// Si hay un error nos llevara al jsp de errores
            request.setAttribute("error", "La anilla debe tener como maximo 3 caracteres");
            request.getRequestDispatcher(url).forward(request, response); //Controlamos que la anilla que introducimos tiene los requisitos impuestos
        } else {//If del controlador de la anilla

            String anilla = request.getParameter("anilla");
            String especie = request.getParameter("especie");
            String lugar = request.getParameter("lugar");
            String fecha = request.getParameter("fecha");
            String sql = null;
            // Guardamos los datos del formulario anterior
            try {
                conexion = ds.getConnection(); //Creamos la conexion
                if (request.getParameter("enviar") != null) { // Si el boton del formulario es pulsado entraremos en el if de la sentencia

                    url = "sumave.jsp"; // Esta url se introducira en el getRequestDispacher para llevarnos al jsp de muestra de datos
                    sql = "insert into aves (anilla, especie, lugar, fecha) values (?,?,?,?)"; // Sentencia sql de insertar datos en la tabla
                    preparada = conexion.prepareStatement(sql);
                    preparada.setString(1, anilla); //Valores por posicion que vamos a introducir
                    preparada.setString(2, especie);
                    preparada.setString(3, lugar);
                    preparada.setString(4, fecha);

                    int rowsInserted = preparada.executeUpdate(); //Actualizamos los nuevos datos introducidos
                    if (rowsInserted > 0) {
                        System.out.println("insertado");
                    }
                    sql = "select * from aves where anilla = ?";
                    preparada = conexion.prepareStatement(sql); // Esta parte nos servira para tomar los datos introducidos y mostrarlos en el jsp de muestra de datos
                    preparada.setString(1, anilla);
                    resultado = preparada.executeQuery();
                    resultado.next();
                    ave = new Ave();
                    ave.setAnilla(resultado.getString("anilla"));
                    ave.setEspecie(resultado.getString("especie"));
                    ave.setLugar(resultado.getString("lugar"));
                    ave.setFecha(resultado.getString("fecha"));
                    request.setAttribute("unoSolo", ave);
                    request.getRequestDispatcher(url).forward(request, response);
                } //if del boton

            } catch (SQLException ex) {
                if (ex.getErrorCode() == 1062) //Este error controla que la anilla introducida no sea un valor duplicado
                {
                    url = "error.jsp";
                    request.setAttribute("error", "La anilla que quiere ingresar esta duplicada");
                    request.getRequestDispatcher(url).forward(request, response);
                }//if getErrorCode
                System.out.println("Error al crear la conexión"); //Si la conexion no se ha realizado correctamente mostrara un mensaje
                ex.printStackTrace();
            }finally {
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                if (preparada != null) {
                    preparada.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                if (resultado != null) {
                    resultado.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
