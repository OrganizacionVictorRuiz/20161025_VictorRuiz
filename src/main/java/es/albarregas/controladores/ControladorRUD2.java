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
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "ControladorRUD2", urlPatterns = {"/ControladorRUD2"})
public class ControladorRUD2 extends HttpServlet {

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
    }// init Accedemos a la base de datos

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
        List<Ave> listado = null;
        String url = null;
        // Creamos las variables 
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al cargar el Driver");
            ex.printStackTrace();
        } //Cargamos el driver
        String anilla = request.getParameter("anilla");
        String especie = request.getParameter("especie");
        String lugar = request.getParameter("lugar");
        String fecha = request.getParameter("fecha");
        // Damos valores a las variables de las aves
        String sql = null;
        try { 
            conexion = ds.getConnection(); //Creamos la conexion
            if (request.getParameter("enviar") != null) { //Si nos llega  el boton enviar del formulario del jsp de borrar accedemos a la sentencia SQL DELETE

                url = "borrado.jsp"; //Ponemos la direccion a la que accederemos
                sql = "delete from aves where anilla=?";
                preparada = conexion.prepareStatement(sql);
                preparada.setString(1, anilla);

                int rowsInserted = preparada.executeUpdate(); //Añadimos los cambios
                if (rowsInserted > 0) {
                    System.out.println("A new user was inserted successfully!");
                }

            } else if (request.getParameter("enviar2") != null) { //Si nos llega el boton enviar del formulario del jsp de actualizar accedemos a la sentenica SQL UPDATE
                if (request.getParameter("eliminar") != null) {
                    anilla = request.getParameter("anilla");
                    especie = request.getParameter("especie");
                    lugar = request.getParameter("lugar");
                    fecha = request.getParameter("fecha");
                    url = "actualizar.jsp";
                    sql = "update aves set especie=?, lugar=?, fecha=? where anilla=?";
                    preparada = conexion.prepareStatement(sql);
                    preparada.setString(1, especie);
                    preparada.setString(2, lugar);
                    preparada.setString(3, fecha);
                    preparada.setString(4, anilla);
                    
                    int rowsInserted = preparada.executeUpdate();
                    if (rowsInserted > 0) {
                        System.out.println("A new user was inserted successfully!");
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
                } else {
                    url = "error.jsp"; //Este error es por si no seleccionamos ningun ningun registro
                    request.setAttribute("error", "No has seleccionado ningun registro");
                }
            } else if (request.getParameter("unaAnilla") != null) { //Esta parte engloba todo el visualizado, si nos llega la opcion una anilla, mostraremos el registro de la anilla seleccionada

                sql = "select * from aves where anilla = ?";
                preparada = conexion.prepareStatement(sql);
                preparada.setString(1, anilla);
                try {
                    resultado = preparada.executeQuery();

                    url = "unResultado.jsp";
                    resultado.next();
                    ave = new Ave();
                    ave.setAnilla(resultado.getString("anilla"));
                    ave.setEspecie(resultado.getString("especie"));
                    ave.setLugar(resultado.getString("lugar"));
                    ave.setFecha(resultado.getString("fecha"));
                    request.setAttribute("unoSolo", ave);

                } catch (SQLException e) {
                    url = "error.jsp"; //Error que nos dice que la anilla que buscamos no esta en la base de datos
                    request.setAttribute("error", "La anilla " + anilla
                            + " no existe en la base de datos ");

                }
            } else if (request.getParameter("todas") != null) { //Si queremos ver todos los registros de nuestra base de datos
                sql = "select * from aves";
                if (url == null) {
                    sentencia = conexion.createStatement();
                    resultado = sentencia.executeQuery(sql);
                    listado = new ArrayList();
                    url = "visualizar.jsp";

                    while (resultado.next()) {

                        ave = new Ave();
                        ave.setAnilla(resultado.getString("anilla"));
                        ave.setEspecie(resultado.getString("especie"));
                        ave.setLugar(resultado.getString("lugar"));
                        ave.setFecha(resultado.getString("fecha"));
                        listado.add(ave);
                    }
                    request.setAttribute("lista", listado);
                }
            }
            request.getRequestDispatcher(url).forward(request, response);
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) { //Error  por el cual nos indica que la anilla estaria duplicada
                url = "error.jsp";
                request.setAttribute("error", "La anilla que quiere ingresar esta duplicada");
                request.getRequestDispatcher(url).forward(request, response);
            }
            System.out.println("Error al crear la conexión");
            ex.printStackTrace();
        }finally { //Finally para cerrar todos los procesos
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
