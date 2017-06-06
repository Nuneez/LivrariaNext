/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.servlets;

import br.senac.tadsb.pi3.livrarianext.database.*;
import br.senac.tadsb.pi3.livrarianext.enums.AcaoCrud;
import br.senac.tadsb.pi3.livrarianext.exceptions.*;
import br.senac.tadsb.pi3.livrarianext.models.Estoque;
import br.senac.tadsb.pi3.livrarianext.servicos.ServicoEstoque;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

/**
 *
 * @author roger
 */
@WebServlet(name = "ManterEstoques", urlPatterns = {"/ManterEstoques"})
public class ManterEstoques extends ExtendedHttpServlet {
    
    ServicoEstoque servico;
    Gson gson = new Gson();
    
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
        if (!authRequest(request, response)) { return; }
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
        if (!authRequest(request, response)) { return; }
        //Obtendo parametros
        try
        {
            String id = request.getParameter("id");
            String json = request.getParameter("produtos");
            Type colType = new TypeToken<List<ProdutoTemporario>>(){}.getType();
            List<ProdutoTemporario> produtos = gson.fromJson(json, colType);       
                        
            for(ProdutoTemporario p : produtos)
                if (p.getAction() != AcaoCrud.NONE)
                    servico.alterarEstoque(p.id, Integer.parseInt(id), p.produtoId, p.saldo, p.getAction());
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    class ProdutoTemporario{
        
        public int id;
        public int produtoId;
        public double saldo;
        public String action;
        
        public AcaoCrud getAction(){
                       
            switch (action) {
                case "insert":
                    return AcaoCrud.INSERT;
                case "edit":
                    return AcaoCrud.EDIT;
                case "delete":
                    return AcaoCrud.DELETE;
            }            
            
            return AcaoCrud.NONE;
        }
    }
}
