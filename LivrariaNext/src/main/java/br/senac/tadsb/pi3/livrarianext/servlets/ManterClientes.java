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
import br.senac.tadsb.pi3.livrarianext.validar.Cpf;
import br.senac.tadsb.pi3.livrarianext.validar.Email;
import br.senac.tadsb.pi3.livrarianext.validar.Telefone;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author roger
 */
@WebServlet(name = "ManterClientes", urlPatterns = {"/ManterClientes"})
public class ManterClientes extends HttpServlet {

    ServicoCliente servico;

    public ManterClientes() {
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

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            Cliente cliente = (id != null && !id.isEmpty()) ? servico.obterClientePorId(Integer.parseInt(id)) : new Cliente();
            request.setAttribute("cliente", cliente);
        } catch (ClienteException ce) {
            request.setAttribute("erro", ce.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("Cliente.jsp");

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

        //Obtendo parametros
        String id = request.getParameter("id");
        String nome = request.getParameter("nome");
        String sobrenome = request.getParameter("sobrenome");
        String sexo = request.getParameter("sexo");
        String cpf = request.getParameter("cpf");
        String rg = request.getParameter("rg");
        String endereco = request.getParameter("endereco");
        String bairro = request.getParameter("bairro");
        String numero = request.getParameter("numero");
        String nascimento = request.getParameter("nascimento");
        String email = request.getParameter("email");
        String telefone = request.getParameter("telefone");

        String mensagemDeErro = null;

        Email e = new Email(email);
        Telefone tell = new Telefone(telefone);
        Cpf c = new Cpf(cpf);
        //c.removeCaracterEspecial(cpf);
        if (!c.validarCpf()) {
            mensagemDeErro = "CPF invalido, digite novamente !";
        }
        if (!e.validarEmail()) {
            mensagemDeErro = "E-mail invalido, digite novamente !";
        }
        if (!tell.validarTelefone()) {
            mensagemDeErro = "Telefone invalido, digite novamente !";
        }
        
        request.setAttribute("erro", mensagemDeErro);

        try {
            if (mensagemDeErro == null) 
            {
                if (id.isEmpty() || id.equals("0")) {
                    servico.incluir(nome, sobrenome, cpf, rg, null, sexo, email, telefone, endereco, numero, bairro);
                } else {
                    servico.alterar(Integer.parseInt(id), nome, sobrenome, cpf, rg, null, sexo, email, telefone, endereco, numero, bairro);
                }
                
                response.sendRedirect("ListarClientes");
                
            } else {
                Cliente cliente = new Cliente( nome, sobrenome, cpf, rg, nascimento, sexo, email, telefone, endereco, numero, bairro);
                request.setAttribute("cliente", cliente);
                RequestDispatcher dispatcher = request.getRequestDispatcher("Cliente.jsp");
                dispatcher.forward(request, response);                
            }

        } catch (ClienteException ue) {

        } catch (Exception ex) {
            Logger.getLogger(ManterUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
