/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.servlets;

import br.senac.tadsb.pi3.livrarianext.database.ConnectionUtils;
import br.senac.tadsb.pi3.livrarianext.database.DaoPerfil;
import br.senac.tadsb.pi3.livrarianext.database.DaoUsuario;
import br.senac.tadsb.pi3.livrarianext.exceptions.UsuarioException;
import br.senac.tadsb.pi3.livrarianext.models.Usuario;
import br.senac.tadsb.pi3.livrarianext.servicos.ServicoUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author thiagomessias
 */
public class Sessao extends HttpServlet {

    private ServicoUsuario servico;

    public Sessao() {
        try {
            ConnectionUtils util = new ConnectionUtils();
            servico = new ServicoUsuario(new DaoUsuario(util), new DaoPerfil(util));
        } catch (UsuarioException ux) {
            System.out.println(ux.getMessage());
        } catch (SQLException sqlex) {
            System.out.println(sqlex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected boolean processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            return false;
        } else {
            String logout = request.getParameter("logout");
            if (logout != null && logout.equals("true")) {
                session.invalidate();
                request.setAttribute("sessionUser", null);
                return false;
            }
            request.setAttribute("sessionUser", session.getAttribute("user"));
            return true;
        }
    }
    
    protected boolean authRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException  {
        if (!processRequest(request, response)) {
            response.sendRedirect("Sessao");
            return false;
        }
        return true;
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
        if (processRequest(request, response)) {
            response.sendRedirect("Index");
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
            dispatcher.forward(request, response);
        }
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
        request.setAttribute("erro", "Nome de usuario ou senha invalido.");
        String username = request.getParameter("username");
        String passwd = request.getParameter("senha");
        if (username == null || passwd == null) {
            dispatcher.forward(request, response);
            return;
        }

        Usuario user = servico.obterPorUsername(username.trim());
        if (user != null && user.getPassword().equals(passwd.trim())) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);            
            response.sendRedirect("Index");
        } else {
            dispatcher.forward(request, response);
        }
    }
}
