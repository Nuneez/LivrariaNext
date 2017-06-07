/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.servlets;

import br.senac.tadsb.pi3.livrarianext.database.*;
import br.senac.tadsb.pi3.livrarianext.exceptions.*;
import br.senac.tadsb.pi3.livrarianext.models.Usuario;
import br.senac.tadsb.pi3.livrarianext.servicos.ServicoUsuario;
import br.senac.tadsb.pi3.livrarianext.helpers.Parser;
import java.io.IOException;
import java.sql.SQLException;
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
public class ListarUsuarios extends ExtendedHttpServlet {
    
    private ServicoUsuario servico;
    
    public ListarUsuarios(){
        try
        {
            ConnectionUtils util = new ConnectionUtils();            
            servico = new ServicoUsuario(new DaoUsuario(util), new DaoPerfil(util));
        }
        catch(UsuarioException ux){
             System.out.println(ux.getMessage());
         }
         catch(SQLException sqlex){
             System.out.println(sqlex.getMessage());
         }
         catch(Exception ex){
             System.out.println(ex.getMessage());
         }
    }
    
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
        if (!authRequest(request, response)) { return; }
        try
        {
            //Obtendo parametros
            String nome = request.getParameter("nome");
            String ativo = request.getParameter("ativo");
            String perfil = request.getParameter("perfil");
                
            //tratando os atributos            
            Boolean ativos = ativo == null || ativo.isEmpty() ? true : Boolean.parseBoolean(ativo);            
            
            //Setando atributos para o jsp
            request.setAttribute("perfis", servico.obterPerfis());
            request.setAttribute("usuarios", obterUsuarios(nome, ativos, Parser.tryParseInt(perfil)));
            request.setAttribute("ativo", ativos);

            //Despachando a requisição
            RequestDispatcher dispatcher = request.getRequestDispatcher("Usuarios.jsp");

            try
            {
                dispatcher.forward(request, response);
            }
            catch(IOException ex)
            {
                throw new UsuarioException("Não foi possivel enviar a requisição.");
            }
        }
        catch(UsuarioException ux)
        {
            System.out.println(ux.getMessage());
        }        
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
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
        if (!authRequest(request, response)) { return; }
        String action = request.getParameter("action");
                
        try
        {            
            switch (action) {
                case "excluir":
                    String usuarioId = request.getParameter("id");
                    servico.excluir(Integer.parseInt(usuarioId));
                    break;
                default:
                    throw new AssertionError();
            }
            
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{ \"sucesso\" : \"true\", \"mensagem\" : \"operação concluida com sucesso.\" }");
        }
        catch(UsuarioException ux)
        {
            System.out.println(ux.getMessage());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{ sucesso : false, mensagem : 'Falha na operação. Detalhes: " + ux.getMessage() + "' }");
        }        
    }
    
    private List<Usuario> obterUsuarios(String nome, Boolean ativos, int perfil) throws UsuarioException {
        return servico.obterUsuarios(nome, ativos, perfil);
    }    
}
