/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.servlets;

import br.senac.tadsb.pi3.livrarianext.database.*;
import br.senac.tadsb.pi3.livrarianext.exceptions.LojaException;
import br.senac.tadsb.pi3.livrarianext.models.Loja;
import br.senac.tadsb.pi3.livrarianext.servicos.ServicoLoja;
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
public class ListarLojas extends ExtendedHttpServlet {

    private ServicoLoja servico;
    
    public ListarLojas(){
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
        if (!authRequest(request, response)) { return; }
        try
        {
            //Obtendo parametros
            String nome = request.getParameter("nome");
            String cnpj = request.getParameter("cnpj");
            String ativo = request.getParameter("ativo");
                
            //tratando os atributos            
            
            if (ativo == null || ativo.isEmpty()) {
                request.setAttribute("lojas", obterLojas(nome, cnpj));
            } else {
                Boolean ativos = Boolean.parseBoolean(ativo);            
                request.setAttribute("lojas", obterLojas(nome, cnpj, ativos));
            }           
            //Setando atributos para o jsp
            

            //Despachando a requisi��o
            RequestDispatcher dispatcher = request.getRequestDispatcher("Lojas.jsp");

            try
            {
                dispatcher.forward(request, response);
            }
            catch(IOException ex)
            {
                throw new LojaException("N�o foi possivel enviar a requisi��o.");
            }
        }
        catch(LojaException ux)
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
                    String id = request.getParameter("id");
                    servico.excluir(Integer.parseInt(id));
                    break;
                default:
                    throw new AssertionError();
            }
            
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{ \"sucesso\" : \"true\", \"mensagem\" : \"Opera��o concluida com sucesso.\" }");
        }
        catch(LojaException ux)
        {
            System.out.println(ux.getMessage());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{ sucesso : false, mensagem : 'Falha na Opera��o. Detalhes: " + ux.getMessage() + "' }");
        }
    }
    
    private List<Loja> obterLojas(String nome, String cnpj, Boolean ativos) throws LojaException {
        return servico.obterLojas(nome, cnpj, ativos);
    }    
     private List<Loja> obterLojas(String nome, String cnpj) throws LojaException {
        return servico.obterLojas(nome, cnpj);
    } 
}
