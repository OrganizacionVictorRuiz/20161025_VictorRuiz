/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controladores;

import es.albarregas.modelo.Ave;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "Controlador1", urlPatterns = {"/Controlador1"})
public class Controlador1 extends HttpServlet {

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

// COMENTARIOS MUY ESCASOS
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Connection conexion = null;
            Statement sentencia = null;
            PreparedStatement preparada = null;
            ResultSet resultado = null;
            Ave ave = null;
            List<Ave> listado = null;
            String url = null;
// ESTA PARTE ES PARA REALIZAR UNA CONEXIÓN DIRECTA A LA BD Y NO ENTIENDO QUE HACE AQUÍ
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al cargar el Driver");
                ex.printStackTrace();
            }
            String cadenaConexion = "jdbc:mysql://localhost:3306/pruebasJAVA";
            String anilla = request.getParameter("anilla");
            String especie = request.getParameter("especie");
            String lugar = request.getParameter("lugar");
            String fecha = request.getParameter("fecha");
            String sql = null;
            try {
                conexion = ds.getConnection();
                //Esta serie de if nos llevara a cada seccion del controlador
                if (request.getParameter("insertar") != null) {
                    url = "insertar.jsp";
                } //if insertar
                if (request.getParameter("visualizar") != null) {
                    url = "menuvisualizar.jsp";
                } //if visualizar
// REPITES EL MISMO CÓDIGO PARA ACTUALIZAR Y BORRAR LO ÚNICO QUE CAMBIA ES LA URL
                if (request.getParameter("actualizar") != null) {
                    url = "actualizado.jsp";
                    sentencia = conexion.createStatement();
                    sql = "select * from aves";
                    resultado = sentencia.executeQuery(sql);
                    listado = new ArrayList();

                    while (resultado.next()) {

                        ave = new Ave();
                        ave.setAnilla(resultado.getString("anilla"));
                        ave.setEspecie(resultado.getString("especie"));
                        ave.setLugar(resultado.getString("lugar"));
                        ave.setFecha(resultado.getString("fecha"));
                        listado.add(ave);
                    }
                    request.setAttribute("lista", listado);
                } //if actualizar
                if (request.getParameter("borrar") != null) {
                    url = "borrar.jsp";
                    sentencia = conexion.createStatement();
                    sql = "select * from aves";
                    resultado = sentencia.executeQuery(sql);
                    listado = new ArrayList();

                    while (resultado.next()) {

                        ave = new Ave();
                        ave.setAnilla(resultado.getString("anilla"));
                        ave.setEspecie(resultado.getString("especie"));
                        ave.setLugar(resultado.getString("lugar"));
                        ave.setFecha(resultado.getString("fecha"));
                        listado.add(ave);
                    }
                    request.setAttribute("lista", listado);
                } //if borrar
                request.getRequestDispatcher(url).forward(request, response);
            } catch (SQLException ex) {
                System.out.println("Error al crear la conexión");
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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
