/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.servlets;

import br.senac.tadsb.pi3.livrarianext.exceptions.UsuarioException;
import br.senac.tadsb.pi3.livrarianext.models.Usuario;
//import br.senac.tadsb.pi3.livrarianext.servicos.ServicoVenda;
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
 * @author Thiago
 */
public class FazerVenda extends HttpServlet {
    //ServicoVenda servico;
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
        
         try {
            //Despachando a requisição
            RequestDispatcher dispatcher = request.getRequestDispatcher("Venda.jsp");        

            try {
                dispatcher.forward(request, response);
            }
            catch(IOException ex) {
                throw new UsuarioException("Não foi possível enviar a requisição.");
            }           
        }
        catch(UsuarioException ue) {
            request.setAttribute("erro", ue.getMessage());
        }  
        catch(Exception ex) {
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
        //Obtendo parametros
//        String id = request.getParameter("id");
//        String nome = request.getParameter("nome");
//        String sobrenome = request.getParameter("sobrenome");
//        String username  = request.getParameter("username");
//        String email  = request.getParameter("email");
//        String ativo = request.getParameter("ativo");
//        String perfil = request.getParameter("perfil");
//        
//        try
//        {            
//            if (id.isEmpty() || id.equals("0"))            
//                servico.incluir(nome, sobrenome, username, email, Integer.parseInt(perfil));
//            else
//                servico.alterar(Integer.parseInt(id), nome, sobrenome, Boolean.parseBoolean(ativo), Integer.parseInt(perfil));
//                        
//            response.sendRedirect("ListarUsuarios");
//        }
//        catch(UsuarioException ue)
//        {
//            
//        } catch (Exception ex) {
//            Logger.getLogger(ManterUsuarios.class.getName()).log(Level.SEVERE, null, ex);
//        }
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
