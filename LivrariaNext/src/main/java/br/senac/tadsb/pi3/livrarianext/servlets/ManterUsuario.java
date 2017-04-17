/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.servlets;

import br.senac.tadsb.pi3.livrarianext.exceptions.UsuarioException;
import br.senac.tadsb.pi3.livrarianext.models.Usuario;
import br.senac.tadsb.pi3.livrarianext.servicos.ServicoUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author roger
 */
public class ManterUsuario extends HttpServlet {
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
        
        try
        {
            String usuarioId = request.getParameter("id");

            if (usuarioId != null && !usuarioId.isEmpty()){
                ServicoUsuario servico = new ServicoUsuario();
                
                Usuario usuario = servico.ObterUsuarioPorId(Integer.parseInt(usuarioId));
                
                request.setAttribute("usuario", usuario);
            }    
        }
        catch(UsuarioException ue)
        {
            request.setAttribute("erro", ue.getMessage());
        }               

        RequestDispatcher dispatcher = request.getRequestDispatcher("Usuario.jsp");        
        
        try
        {
            dispatcher.forward(request, response);
        }
        catch(IOException ex)
        {

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
        
        String nome = request.getParameter("nome");
        String sobrenome = request.getParameter("sobrenome");
        String ativo = request.getParameter("ativo");
        
        try
        {
            ServicoUsuario servico = new ServicoUsuario();
            servico.incluir(nome, sobrenome);
            response.sendRedirect("Usuarios");
        }
        catch(UsuarioException ue)
        {
            
        } catch (Exception ex) {
            Logger.getLogger(ManterUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
}
