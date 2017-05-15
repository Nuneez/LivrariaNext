/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.servlets;

import br.senac.tadsb.pi3.livrarianext.database.*;
import br.senac.tadsb.pi3.livrarianext.exceptions.*;
import br.senac.tadsb.pi3.livrarianext.models.Cliente;
import br.senac.tadsb.pi3.livrarianext.servicos.ServicoCliente;
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
 * @author Thiago
 */
public class ListarClientes extends HttpServlet {

    ServicoCliente servico;

    public ListarClientes() {
        try {
            ConnectionUtils util = new ConnectionUtils();
            servico = new ServicoCliente(new DaoCliente(util));
        } catch (UsuarioException ux) {
            System.out.println(ux.getMessage());
        } catch (SQLException sqlex) {
            System.out.println(sqlex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        if (cpf != null) {
            try {   
                List<Cliente> clientes = servico.obterClientes(nome == null? "" : nome, cpf);
                request.setAttribute("clientes", clientes);
            } catch (ClienteException ux) {
                System.out.println(ux.getMessage());
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("Clientes.jsp");

        try {
            dispatcher.forward(request, response);
        } catch (IOException ex) {
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

        try {
            switch (action) {
                case "excluir":
                    String clienteId = request.getParameter("cliente_id");
                    servico.excluir(Integer.parseInt(clienteId));
                    break;
                default:
                    throw new AssertionError();
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{ \"sucesso\" : \"true\", \"mensagem\" : \"Operação concluída com sucesso.\" }");
        } catch (ClienteException ux) {
            System.out.println(ux.getMessage());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{ sucesso : false, mensagem : 'Falha na operação. Detalhes: " + ux.getMessage() + "' }");
        }
    }
}
