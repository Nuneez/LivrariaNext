package br.senac.tadsb.pi3.livrarianext.servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.senac.tadsb.pi3.livrarianext.database.*;
import br.senac.tadsb.pi3.livrarianext.exceptions.LojaException;
import br.senac.tadsb.pi3.livrarianext.models.Loja;
import br.senac.tadsb.pi3.livrarianext.servicos.ServicoLoja;
import br.senac.tadsb.pi3.livrarianext.validar.Cnpj;
import br.senac.tadsb.pi3.livrarianext.validar.Email;
import br.senac.tadsb.pi3.livrarianext.validar.Telefone;
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
 * @author Thiago
 */
public class ManterLojas extends HttpServlet {
    
    ServicoLoja servico;
    
    public ManterLojas(){
        try
         {  
            ConnectionUtils util = new ConnectionUtils();             
            servico = new ServicoLoja(new DaoLoja(util));
         }
         catch(LojaException ux){
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
        try
        {            
            String id = request.getParameter("id");
            Loja dominio = (id != null && !id.isEmpty()) ? servico.obterLojaPorId(Integer.parseInt(id)) : new Loja();                
            request.setAttribute("loja", dominio);
        }
        catch(LojaException ce)
        {
            request.setAttribute("erro", ce.getMessage());
        }               

        RequestDispatcher dispatcher = request.getRequestDispatcher("Loja.jsp");        
        
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
        String filial = request.getParameter("filial");
        String razaoSocial = request.getParameter("razaosocial");
        String ativo = request.getParameter("ativo");
        String cnpj = request.getParameter("cnpj");
        String inscricaoEstadual = request.getParameter("inscricaoestadual");
        String endereco = request.getParameter("endereco");
        String numero = request.getParameter("numero");
        String cidade = request.getParameter("cidade");
        String estado = request.getParameter("estado");
        String email = request.getParameter("email");
        String telefone = request.getParameter("telefone");
        String mensagemDeErro = null;
        Email e = new Email(email);
        Telefone tell = new Telefone(telefone);
        Cnpj c = new Cnpj(cnpj);
        if (!c.validarCnpj()) {
            mensagemDeErro = "CNPJ inválido, digite novamente !";
        }
        if (!e.validarEmail()) {
            mensagemDeErro = "E-mail inválido, digite novamente !";
        }
        if (!tell.validarTelefone()) {
            mensagemDeErro = "Telefone inválido, digite novamente !";
            //JOptionPane.showMessageDialog(null, mensagemDeErro);
        }

        request.setAttribute("erro", mensagemDeErro);

        try {
            if (mensagemDeErro == null) {
            if (id.isEmpty() || id.equals("0")) {            
                if (numero == null || numero.isEmpty() || numero.equals("0"))
                    numero= "";
                servico.incluir(nome, Boolean.parseBoolean(filial), cnpj, razaoSocial, telefone, endereco, numero, cidade, estado, email, inscricaoEstadual);
            }        
//                servico.incluir(nome, Boolean.parseBoolean(filial), cnpj, razaoSocial, telefone, endereco, numero, cidade, estado, email, inscricaoEstadual);
            else{
                servico.alterar(Integer.parseInt(id), nome, Boolean.parseBoolean(filial), cnpj, razaoSocial, telefone, endereco, numero, cidade, estado, email, inscricaoEstadual);
            }
             response.sendRedirect("ListarLojas");
            }   
            else{
                Loja loja = new Loja(nome, filial, razaoSocial, cnpj, inscricaoEstadual, endereco, numero, cidade, estado, email, telefone);
                request.setAttribute("loja", loja);
                RequestDispatcher dispatcher = request.getRequestDispatcher("Loja.jsp");
                dispatcher.forward(request, response);
            }
        }
        catch(LojaException ue)
        {
            
        } catch (Exception ex) {
            Logger.getLogger(ManterUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
}
