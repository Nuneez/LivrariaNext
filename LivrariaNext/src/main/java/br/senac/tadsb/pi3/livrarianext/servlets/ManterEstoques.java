/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.servlets;

import br.senac.tadsb.pi3.livrarianext.database.*;
import br.senac.tadsb.pi3.livrarianext.exceptions.*;
import br.senac.tadsb.pi3.livrarianext.models.Estoque;
import br.senac.tadsb.pi3.livrarianext.servicos.ServicoEstoque;
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

/**
 *
 * @author roger
 */
@WebServlet(name = "ManterEstoques", urlPatterns = {"/ManterEstoques"})
public class ManterEstoques extends HttpServlet {
    
    ServicoEstoque servico;
    
    public ManterEstoques(){
        try
         {  
            ConnectionUtils util = new ConnectionUtils();             
            servico = new ServicoEstoque(new DaoEstoque(util));
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try
        {            
            String id = request.getParameter("id");
            Estoque dominio = servico.obterEstoquePorLojaId(Integer.parseInt(id));                
            request.setAttribute("estoque", dominio);
        }
        catch(EstoqueException ce)
        {
            request.setAttribute("erro", ce.getMessage());
        }               

        RequestDispatcher dispatcher = request.getRequestDispatcher("Estoque.jsp");        
        
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
        //String nascimento = request.getParameter("nascimento");
        String email = request.getParameter("email");
        String telefone = request.getParameter("telefone");
       
        Email e = new Email(email);
        Telefone tell = new Telefone(telefone);
        String mensagemDeErro = null;
        if(!e.validarEmail()){
            System.out.println("E-mail invalido, digite novamente !");
            mensagemDeErro = "E-mail invalido, digite novamente !";
        }
        if(!tell.validarTelefone()){
            System.out.println("Telefone invalido, digite novamente !");
            mensagemDeErro = "Telefone invalido, digite novamente !";
            //JOptionPane.showMessageDialog(null, mensagemDeErro);
        }
        
        request.setAttribute("erro", mensagemDeErro);
        
        try
        {
            
                        
            //response.sendRedirect("ListarClientes");
            RequestDispatcher dispatcher = request.getRequestDispatcher("Cliente.jsp");
            request.setAttribute("nome", nome);
            dispatcher.forward(request, response);
           
        }
//        catch(EstoqueException ue)
//        {
//            
//        } 
        catch (Exception ex) {
            Logger.getLogger(ManterUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
}
