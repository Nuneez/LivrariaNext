/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.servlets;

import br.senac.tadsb.pi3.livrarianext.database.*;
import br.senac.tadsb.pi3.livrarianext.exceptions.ProdutoException;
import br.senac.tadsb.pi3.livrarianext.models.NameValue;
import br.senac.tadsb.pi3.livrarianext.models.Produto;
import br.senac.tadsb.pi3.livrarianext.servicos.ServicoProduto;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

/**
 *
 * @author roger
 */
public class Produtos extends ExtendedHttpServlet {

    private ServicoProduto servico;
    Gson gson = new Gson();
    
    public Produtos(){
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
        try
        {
            //Obtendo parametros
            String search = request.getParameter("search");                        
            List<Produto> lista = servico.obterProdutos(search);
            writeJsonResponse(response, gson.toJson(lista));
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
}
