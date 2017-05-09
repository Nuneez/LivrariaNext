/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.servlets;

import br.senac.tadsb.pi3.livrarianext.database.*;
import static br.senac.tadsb.pi3.livrarianext.helpers.Parser.tryParseInt;
import br.senac.tadsb.pi3.livrarianext.models.Pedido;
import br.senac.tadsb.pi3.livrarianext.servicos.ServicoPedido;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thiago
 */
public class FazerPedido extends HttpServlet {
    private ServicoPedido servico;
    
    public FazerPedido() {
        try {
            ConnectionUtils util = new ConnectionUtils();
            servico = new ServicoPedido(new DaoPedido(util), new DaoItemPedido(util));
        } catch(SQLException sqlex){
             System.out.println(sqlex.getMessage());
         }
         catch(Exception ex){
             System.out.println(ex.getMessage());
         }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("Pedidos.jsp");
        String id = request.getParameter("id");
        Pedido pedido = new Pedido();
        System.out.println(id);
        if (id == null || id.isEmpty() || id.equals("0")) {
            request.setAttribute("pedido", pedido);
        } else {
            request.setAttribute("pedido", servico.obter(tryParseInt(id)));
        }
        try {
            dispatcher.forward(request, response);
        } catch(IOException ex) {
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("Pedidos.jsp");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
