/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.servlets;

import br.senac.tadsb.pi3.livrarianext.database.*;
import br.senac.tadsb.pi3.livrarianext.exceptions.ProdutoException;
import br.senac.tadsb.pi3.livrarianext.models.Produto;
import br.senac.tadsb.pi3.livrarianext.servicos.ServicoProduto;
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
 * @author roger
 */
public class ListarProdutos extends ExtendedHttpServlet {

    private ServicoProduto servico;
    
    public ListarProdutos(){
        try
        {
            ConnectionUtils util = new ConnectionUtils();            
            servico = new ServicoProduto(new DaoProduto(util));
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
            Boolean ativos = ativo == null || ativo.isEmpty() ? true : Boolean.parseBoolean(ativo);            
                        
            //Setando atributos para o jsp
            request.setAttribute("produtos", obterProdutos(nome, cnpj, ativos));
            request.setAttribute("ativo", ativos);

            //Despachando a requisição
            RequestDispatcher dispatcher = request.getRequestDispatcher("Produtos.jsp");

            try {
                dispatcher.forward(request, response);
            }
            catch(IOException ex)
            {
                throw new ProdutoException("Não foi possivel enviar a requisição.");
            }
        }
        catch(ProdutoException ux)
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
            response.getWriter().write("{ \"sucesso\" : \"true\", \"mensagem\" : \"Operaï¿½ï¿½o concluida com sucesso.\" }");
        }
        catch(ProdutoException ux)
        {
            System.out.println(ux.getMessage());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{ sucesso : false, mensagem : 'Falha na Operaï¿½ï¿½o. Detalhes: " + ux.getMessage() + "' }");
        }
    }
   
    private List<Produto> obterProdutos(String nome, String cnpj, Boolean ativos) throws ProdutoException {
        return servico.obterProdutos(nome, cnpj);
    }  
}
