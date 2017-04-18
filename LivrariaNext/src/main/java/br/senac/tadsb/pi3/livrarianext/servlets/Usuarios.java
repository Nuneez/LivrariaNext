/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.servlets;

import br.senac.tadsb.pi3.livrarianext.exceptions.UsuarioException;
import br.senac.tadsb.pi3.livrarianext.models.Usuario;
import br.senac.tadsb.pi3.livrarianext.models.Perfil;
import br.senac.tadsb.pi3.livrarianext.servicos.ServicoUsuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author roger
 */
public class Usuarios extends HttpServlet {
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
            String nome = request.getParameter("nome");
            
            System.out.println("testando com nome: " + nome);
            
            ServicoUsuario servico = new  ServicoUsuario();
        
//            List<Perfil> perfis = servico.ObterPerfis();        
//            request.setAttribute("perfis", perfis);

            List<Usuario> usuarios = servico.ObterUsuarios(nome);
            request.setAttribute("usuarios", usuarios);

            RequestDispatcher dispatcher = request.getRequestDispatcher("Usuarios.jsp");

            try
            {
                dispatcher.forward(request, response);
            }
            catch(IOException ex)
            {

            }
        }
        catch(UsuarioException ux)
        {
            System.out.println(ux.getMessage());
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
   
        String action = request.getParameter("action");
                
        try
        {
            ServicoUsuario servico = new ServicoUsuario();
            
            switch (action) {
                case "excluir":
                    String usuarioId = request.getParameter("usuario_id");
                    servico.excluir(Integer.parseInt(usuarioId));
                    break;
                default:
                    throw new AssertionError();
            }
            
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{ \"sucesso\" : \"true\", \"mensagem\" : \"Operação concluída com sucesso.\" }");
        }
        catch(UsuarioException ux)
        {
            System.out.println(ux.getMessage());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{ sucesso : false, mensagem : 'Falha na operação. Detalhes: " + ux.getMessage() + "' }");
        }        
    }
}
