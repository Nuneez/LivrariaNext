/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.servicos;

import br.senac.tadsb.pi3.livrarianext.database.Dao;
import br.senac.tadsb.pi3.livrarianext.database.DaoPedido;
import br.senac.tadsb.pi3.livrarianext.enums.ExceptionTypesEnum;
import br.senac.tadsb.pi3.livrarianext.exceptions.*;
import static br.senac.tadsb.pi3.livrarianext.helpers.Parser.tryParseInt;
import br.senac.tadsb.pi3.livrarianext.models.Cliente;
import br.senac.tadsb.pi3.livrarianext.models.ItemPedido;
import br.senac.tadsb.pi3.livrarianext.models.Loja;
import br.senac.tadsb.pi3.livrarianext.models.Pedido;
import br.senac.tadsb.pi3.livrarianext.models.Usuario;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thiago
 */
public class ServicoPedido extends Servico<Pedido> {
    
    final DaoPedido dao;
    
    public ServicoPedido(Dao daoPedido) {
        super(daoPedido);
        this.dao = (DaoPedido)daoPedido;
    }
    
    public void incluir(Cliente cliente, Usuario vendedor, Loja loja)  throws PedidoException {
        try
        {
            Pedido novo = new Pedido(cliente, vendedor, loja);
            super.incluir(novo);
        }
        catch(ServicoException se)
        {
            throw new PedidoException(ExceptionTypesEnum.SPECIFIC_CRUD);
        }
    }
    
    public void alterar(int id, Cliente cliente, Usuario vendedor, Loja loja) throws PedidoException {
        try
        {
            Pedido pedido = dao.obterPorId(id);
            pedido.setCliente(cliente);
            pedido.setVendedor(vendedor);
            pedido.setLoja(loja);
            super.alterar(pedido);
        } catch(DaoException de) {
            throw new PedidoException(ExceptionTypesEnum.DATABASE);
        }
        catch(ServicoException se)
        {
            throw new PedidoException(ExceptionTypesEnum.SPECIFIC_CRUD);
        }
    }
    
    public void excluir(int id) throws PedidoException {
        try
        {
            Pedido dominio = dao.obterPorId(id);
            super.excluir(dominio);
        }
        catch(DaoException de)
        {
            throw new PedidoException(ExceptionTypesEnum.DATABASE);
        }
        catch(ServicoException se)
        {
            throw new PedidoException(ExceptionTypesEnum.SPECIFIC_CRUD);
        }
    }
    
    public Pedido obter(int id) {
        try {
            Pedido dominion = dao.obterPorId(id);
            return dominion;
        } catch (DaoException ex) {
            Logger.getLogger(ServicoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Pedido obterUltimo() throws Exception {
        try {
            Pedido dominion = dao.getLastPedido();
            return dominion;
        } catch (DaoException ex) {
            Logger.getLogger(ServicoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;        
    }
}
