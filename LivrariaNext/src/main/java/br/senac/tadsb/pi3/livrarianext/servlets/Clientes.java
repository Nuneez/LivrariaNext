/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.servlets;

import br.senac.tadsb.pi3.livrarianext.exceptions.ClienteException;
import br.senac.tadsb.pi3.livrarianext.models.Cliente;
import br.senac.tadsb.pi3.livrarianext.models.Perfil;
import br.senac.tadsb.pi3.livrarianext.models.Usuario;
import br.senac.tadsb.pi3.livrarianext.servicos.ServicoCliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thiago
 */
public class Clientes extends HttpServlet {
  @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        try
        {
            String nome = request.getParameter("nome");
            String cpf = request.getParameter("cpf");
                        
            ServicoCliente servico = new  ServicoCliente();

            List<Cliente> clientes = servico.obterClientes(nome, cpf);
            request.setAttribute("clientes", clientes);

            RequestDispatcher dispatcher = request.getRequestDispatcher("Clientes.jsp");

            try
            {
                dispatcher.forward(request, response);
            }
            catch(IOException ex)
            {

            }
        }
        catch(ClienteException ux)
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
   
    }
}
