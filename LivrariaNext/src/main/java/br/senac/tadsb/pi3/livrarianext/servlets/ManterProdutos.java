/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.servlets;

import br.senac.tadsb.pi3.livrarianext.database.ConnectionUtils;
import br.senac.tadsb.pi3.livrarianext.database.DaoProduto;
import br.senac.tadsb.pi3.livrarianext.exceptions.*;
import br.senac.tadsb.pi3.livrarianext.helpers.Parser;
import static br.senac.tadsb.pi3.livrarianext.helpers.Parser.tryParseInt;
import br.senac.tadsb.pi3.livrarianext.models.*;
import br.senac.tadsb.pi3.livrarianext.servicos.ServicoProduto;
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
 * @author roger
 */
public class ManterProdutos extends ExtendedHttpServlet {
 
    ServicoProduto servico;
    
    public ManterProdutos(){
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
            String id = request.getParameter("id");
            Produto dominio = (id != null && !id.isEmpty()) ? servico.obterProdutoPorId(Integer.parseInt(id)) : new Produto();                
            request.setAttribute("produto", dominio);
        }
        catch(ProdutoException ce)
        {
            request.setAttribute("erro", ce.getMessage());
        }               

        RequestDispatcher dispatcher = request.getRequestDispatcher("Produto.jsp");        
        
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
        if (!authRequest(request, response)) { return; }
        //Obtendo parametros
        String id = request.getParameter("id");
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String custo = request.getParameter("custo");
        //String preco = request.getParameter("preco");
        String ean = request.getParameter("ean");
        String ativo = request.getParameter("ativo");
        Integer quantidade = tryParseInt(request.getParameter("quantidade"));
        
        try
        {
            if (id.isEmpty() || id.equals("0"))        
                servico.incluir(nome, descricao, Parser.parseDouble(custo), Parser.parseDouble(custo), ean, quantidade);
            else
                servico.alterar(Integer.parseInt(id), nome, descricao, Parser.parseDouble(custo), Parser.parseDouble(custo), ean, Boolean.parseBoolean(ativo), quantidade);
                        
            response.sendRedirect("ListarProdutos");
        }
        catch(ProdutoException ue)
        {
            
        } catch (Exception ex) {
            Logger.getLogger(ManterUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
