/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.servlets;

import br.senac.tadsb.pi3.livrarianext.database.ConnectionUtils;
import br.senac.tadsb.pi3.livrarianext.database.DaoRelatorio;
import br.senac.tadsb.pi3.livrarianext.exceptions.RelatorioException;
import br.senac.tadsb.pi3.livrarianext.servicos.ServicoRelatorio;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author roger
 */
public class Relatorio extends ExtendedHttpServlet {
    
    ServicoRelatorio servico;
    
    public Relatorio(){
    
    try {
        ConnectionUtils util = new ConnectionUtils();
        servico = new ServicoRelatorio(new DaoRelatorio(util));
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try
        {
            //Obtendo parametros
            String inicio = request.getParameter("inicio");
            String fim = request.getParameter("fim");
            if (inicio != null && !inicio.isEmpty() && fim != null && !fim.isEmpty())
            {                            
                String loja = request.getParameter("loja");
                String usuario = request.getParameter("usuario");
                String produto = request.getParameter("produto");

                Date dataInicio = convertStringToDate(inicio);
                Date dataFim = convertStringToDate(fim);

                //Setando atributos para o jsp
                request.setAttribute("itens", servico.obterRelatorio(dataInicio, dataFim, loja, usuario, produto));                
            }

            //Despachando a requisição
            RequestDispatcher dispatcher = request.getRequestDispatcher("Relatorio.jsp");

            try
            {
                dispatcher.forward(request, response);
            }
            catch(IOException ex)
            {
                throw new RelatorioException("Não foi possível enviar a requisição.");
            }
        }
        catch(RelatorioException ux)
        {
            System.out.println(ux.getMessage());
        }        
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        } 
        
    }
}
