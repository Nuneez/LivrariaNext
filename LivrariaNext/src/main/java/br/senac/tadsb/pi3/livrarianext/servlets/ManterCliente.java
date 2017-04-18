/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.servlets;

import br.senac.tadsb.pi3.livrarianext.exceptions.ClienteException;
import br.senac.tadsb.pi3.livrarianext.models.Cliente;
import br.senac.tadsb.pi3.livrarianext.servicos.ServicoCliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author roger
 */
@WebServlet(name = "ManterCliente", urlPatterns = {"/ManterCliente"})
public class ManterCliente extends HttpServlet {

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
        //Cliente cliente = new Cliente("Thiago", "Messias", "123.456.789-10", "01/01/1996", "masculino", "email@email.com", "+5511987654321", true);
        try
        {            
            ServicoCliente servico = new ServicoCliente();          
            
            String clienteId = request.getParameter("id");

            if (clienteId != null && !clienteId.isEmpty()){
                
                Cliente cliente = servico.obterClientePorId(Integer.parseInt(clienteId));
                
                request.setAttribute("cliente", cliente);
            }    
        }
        catch(ClienteException ce)
        {
            request.setAttribute("erro", ce.getMessage());
        }               

        RequestDispatcher dispatcher = request.getRequestDispatcher("Cliente.jsp");        
        
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
        String cpf = request.getParameter("cpf");
        //String nascimento = request.getParameter("nascimento");
        String sexo = request.getParameter("sexo");
        String email = request.getParameter("email");
        String telefone = request.getParameter("telefone");
        
        try
        {
            ServicoCliente servico = new ServicoCliente();
            servico.incluir(nome, sobrenome, cpf, null, sexo, email, null, null, null, null);
            response.sendRedirect("Clientes");
        }
        catch(ClienteException ue)
        {
            
        } catch (Exception ex) {
            Logger.getLogger(ManterUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

}
