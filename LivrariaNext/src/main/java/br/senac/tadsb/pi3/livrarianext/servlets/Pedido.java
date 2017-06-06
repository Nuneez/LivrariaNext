/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.servlets;

import br.senac.tadsb.pi3.livrarianext.database.ConnectionUtils;
import br.senac.tadsb.pi3.livrarianext.database.DaoCliente;
import br.senac.tadsb.pi3.livrarianext.database.DaoItemPedido;
import br.senac.tadsb.pi3.livrarianext.database.DaoPedido;
import br.senac.tadsb.pi3.livrarianext.database.DaoProduto;
import br.senac.tadsb.pi3.livrarianext.exceptions.ClienteException;
import static br.senac.tadsb.pi3.livrarianext.helpers.Parser.tryParseInt;
import br.senac.tadsb.pi3.livrarianext.models.Cliente;
import br.senac.tadsb.pi3.livrarianext.models.ItemPedido;
import br.senac.tadsb.pi3.livrarianext.models.Loja;
import br.senac.tadsb.pi3.livrarianext.models.Produto;
import br.senac.tadsb.pi3.livrarianext.models.Usuario;
import br.senac.tadsb.pi3.livrarianext.servicos.ServicoCliente;
import br.senac.tadsb.pi3.livrarianext.servicos.ServicoItemPedido;
import br.senac.tadsb.pi3.livrarianext.servicos.ServicoPedido;
import br.senac.tadsb.pi3.livrarianext.servicos.ServicoProduto;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class Pedido extends ExtendedHttpServlet {

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
        authRequest(request, response);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Pedido.jsp");
        dispatcher.forward(request, response);
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
        authRequest(request, response);
        String produtos = request.getParameter("produtos");
        String cpf = request.getParameter("cliente");
        
        Gson gson = new Gson();
        JsonElement json = gson.fromJson(produtos, JsonElement.class);
        ArrayList<ItemPedido> listItems = new ArrayList<ItemPedido>();
        if (json.isJsonArray()) {
            ConnectionUtils util = new ConnectionUtils();
            try {
                ServicoCliente servicoCliente = new ServicoCliente(new DaoCliente(util));
                Cliente cliente = servicoCliente.obterClientePorCpf(cpf);
                ServicoPedido sp = new ServicoPedido(new DaoPedido(util));
                ServicoItemPedido sip = new ServicoItemPedido(new DaoItemPedido(util));
                ServicoProduto servicoProdudo = new ServicoProduto(new DaoProduto(util));
                // TODO: Vendedor e loja
                Usuario u = new Usuario();
                u.setId(1);
                Loja l = new Loja();
                l.setId(1);
                sp.incluir(cliente, u, l);
                int lastPedido = sp.obterUltimo().getId();
                JsonArray jsonArray = json.getAsJsonArray();
                for (int i = 0; i < jsonArray.size(); i++) {
                    final int qnt = tryParseInt(jsonArray.get(i).getAsJsonObject().get("qnt").toString().replace("\"", ""));
                    final int id = tryParseInt(jsonArray.get(i).getAsJsonObject().get("id").toString().replace("\"", ""));
                    final Produto prod = servicoProdudo.obterProdutoPorId(id);
                    if (prod != null) {
                        sip.incluir(qnt, id, lastPedido, prod.getPreco());
                    } else {
                        sip.incluir(qnt, id, lastPedido, (double) 0);
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            System.out.println("Mostrar erro");
        }
        
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
