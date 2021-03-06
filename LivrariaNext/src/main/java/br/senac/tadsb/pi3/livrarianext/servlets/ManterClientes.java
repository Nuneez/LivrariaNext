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
import br.senac.tadsb.pi3.livrarianext.validar.CaracteresEspeciais;
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
public class ManterClientes extends ExtendedHttpServlet {

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
        if (!authRequest(request, response)) { return; }
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
        if (!authRequest(request, response)) { return; }
        String mensagemDeErro = "";   
        
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
            
        try 
        {
            if (!Cpf.validar(cpf))            
                mensagemDeErro += " \n CPF inv�lido, digite novamente !";

            if (!Email.validar(email))
                mensagemDeErro += " \n E-mail inv�lido, digite novamente !";

            if (!Telefone.validar(telefone))
                mensagemDeErro += " \n Telefone inv�lido, digite novamente !";

            if(nome.trim().isEmpty())
                mensagemDeErro += " \n Campo NOME obrigatorio !";

            if(cpf.trim().isEmpty())
                mensagemDeErro += " \n Campo CPF obrigat�rio !";

            if (validarCpfExistente(cpf, Integer.parseInt(id)))
                mensagemDeErro += " \n CPF pertencente a outro cliente cadastrado!";        

            request.setAttribute("erro", mensagemDeErro);

            if (mensagemDeErro != null && !mensagemDeErro.isEmpty())
            {
                dispatchFailedPost(new Cliente(nome, sobrenome, cpf, rg, nascimento, sexo, email, telefone, endereco, numero, bairro), request, response);          
                return;
            }
            
            if (id.isEmpty() || id.equals("0")) 
                servico.incluir(nome, sobrenome, cpf, rg, null, sexo, email, telefone, endereco, numero, bairro);
            else 
                servico.alterar(Integer.parseInt(id), nome, sobrenome, cpf, rg, null, sexo, email, telefone, endereco, numero, bairro);

            response.sendRedirect("ListarClientes");                
        } 
        catch (ClienteException ue) 
        {            
            Logger.getLogger(ManterUsuarios.class.getName()).log(Level.SEVERE, null, ue);            
            request.setAttribute("erro", mensagemDeErro);
            dispatchFailedPost(new Cliente(nome, sobrenome, cpf, rg, nascimento, sexo, email, telefone, endereco, numero, bairro), request, response);    
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(ManterUsuarios.class.getName()).log(Level.SEVERE, null, ex);            
            request.setAttribute("erro", "N�o foi possivel completar a Opera��o.");            
            dispatchFailedPost(new Cliente(nome, sobrenome, cpf, rg, nascimento, sexo, email, telefone, endereco, numero, bairro), request, response);
        }
    }
    
    private void dispatchFailedPost(Cliente cliente, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
            request.setAttribute("cliente", cliente);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Cliente.jsp");
            dispatcher.forward(request, response);      
    }
    
    private boolean validarCpfExistente(String cpf, int id) throws ClienteException {
        Cliente cliente = servico.obterClientePorCpf(cpf);
        
        if (cliente == null)
            return false;
        
        return cliente.getId() != id;
    }
}
