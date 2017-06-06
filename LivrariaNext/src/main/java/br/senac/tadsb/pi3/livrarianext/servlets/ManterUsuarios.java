/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.servlets;

import br.senac.tadsb.pi3.livrarianext.database.*;
import br.senac.tadsb.pi3.livrarianext.exceptions.UsuarioException;
import br.senac.tadsb.pi3.livrarianext.models.Usuario;
import br.senac.tadsb.pi3.livrarianext.servicos.ServicoUsuario;
import java.io.IOException;
import java.sql.SQLException;
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
public class ManterUsuarios extends ExtendedHttpServlet {    
    
    ServicoUsuario servico;
    
    public ManterUsuarios() {
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
        authRequest(request, response);
        try
        {               
            //Obtendo parametros
            String usuarioId = request.getParameter("id");
                        
            //Setando atributos para o jsp
            request.setAttribute("usuario", (usuarioId != null && !usuarioId.isEmpty()) ? servico.obterUsuarioPorId(Integer.parseInt(usuarioId)) : new Usuario());            
            request.setAttribute("perfis", servico.obterPerfis());         
                    
            //Despachando a requisi��o
            RequestDispatcher dispatcher = request.getRequestDispatcher("Usuario.jsp");        

            try
            {
                dispatcher.forward(request, response);
            }
            catch(IOException ex)
            {
                throw new UsuarioException("N�o foi poss�vel enviar a requisi��o.");
            }           
        }
        catch(UsuarioException ue)
        {
            request.setAttribute("erro", ue.getMessage());
        }  
        catch(Exception ex)
        {
            request.setAttribute("erro", ex.getMessage());
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
        authRequest(request, response);
        //Obtendo parametros
        String id = request.getParameter("id");
        String nome = request.getParameter("nome");
        String sobrenome = request.getParameter("sobrenome");
        String username  = request.getParameter("username");
        String email  = request.getParameter("email");
        String ativo = request.getParameter("ativo");
        String perfil = request.getParameter("perfil");
        
        try
        {            
            if (id.isEmpty() || id.equals("0"))            
                servico.incluir(nome, sobrenome, username, email, Integer.parseInt(perfil));
            else
                servico.alterar(Integer.parseInt(id), nome, sobrenome, Boolean.parseBoolean(ativo), Integer.parseInt(perfil));
                        
            response.sendRedirect("ListarUsuarios");
        }
        catch(UsuarioException ue)
        {
            
        } catch (Exception ex) {
            Logger.getLogger(ManterUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
