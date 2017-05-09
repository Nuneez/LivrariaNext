/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.servicos;

import br.senac.tadsb.pi3.livrarianext.database.Dao;
import br.senac.tadsb.pi3.livrarianext.database.DaoItemPedido;
import br.senac.tadsb.pi3.livrarianext.database.DaoPedido;
import br.senac.tadsb.pi3.livrarianext.enums.ExceptionTypesEnum;
import br.senac.tadsb.pi3.livrarianext.exceptions.*;
import br.senac.tadsb.pi3.livrarianext.models.Cliente;
import br.senac.tadsb.pi3.livrarianext.models.ItemPedido;
import br.senac.tadsb.pi3.livrarianext.models.Loja;
import br.senac.tadsb.pi3.livrarianext.models.Pedido;
import br.senac.tadsb.pi3.livrarianext.models.Usuario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author roger
 */
public class ServicoPedido extends Servico<Pedido> {
    
    final DaoPedido dao;
    final DaoItemPedido daoItem;  

    public ServicoPedido(Dao daoPedido, Dao daoItemPedido) {
        super(daoPedido);
        this.dao = (DaoPedido)daoPedido;
        this.daoItem = (DaoItemPedido) daoItemPedido;
    }
    
    public void incluir(Cliente cliente, Usuario vendedor, Loja loja, List<ItemPedido> itensPedidos)  throws PedidoException {
        try
        {
            Pedido novo = new Pedido(cliente, vendedor, loja, itensPedidos);
            super.incluir(novo);
        }
        catch(ServicoException se)
        {
            throw new PedidoException(ExceptionTypesEnum.SPECIFIC_CRUD);
        }
    }
    
    public void alterar(int id, Cliente cliente, Usuario vendedor, Loja loja, List<ItemPedido> itensPedidos) throws PedidoException {
        try
        {
            Pedido pedido = dao.obterPorId(id);
            pedido.setCliente(cliente);
            pedido.setVendedor(vendedor);
            pedido.setLoja(loja);
            pedido.setItensPedidos(itensPedidos);
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
    
    public List<ItemPedido> obterItensPedidos(int idPedido) {
        try {
            return daoItem.obterPorPedido(idPedido);
        } catch (DaoException ex) {
            Logger.getLogger(ServicoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Pedido obter(int id) {
        try {
            Pedido dominion = dao.obterPorId(id);
            dominion.setItensPedidos(this.obterItensPedidos(id));
            return dominion;
        } catch (DaoException ex) {
            Logger.getLogger(ServicoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
