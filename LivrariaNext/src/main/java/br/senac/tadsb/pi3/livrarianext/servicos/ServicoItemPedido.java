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
 * @author Thiago
 */
public class ServicoItemPedido extends Servico<ItemPedido> {
    final DaoItemPedido daoItem;

    public ServicoItemPedido(Dao dao) {
        super(dao);
        this.daoItem = (DaoItemPedido)dao;
    }
    
    public void incluir(int qnt, int idProduto, int lastPedido, double preco) throws DaoException, PedidoException {
        try {
            ItemPedido dominio = new ItemPedido(qnt, idProduto, lastPedido, preco);
            super.incluir(dominio);
        } catch(ServicoException se) {
            throw new PedidoException(ExceptionTypesEnum.SPECIFIC_CRUD);
        }
    }
    
    public void alterar(int id, int qnt, int idProduto, int lastPedido, double preco) throws PedidoException {
        try {
            ItemPedido dominio = new ItemPedido(id, qnt, idProduto, lastPedido, preco);
            super.alterar(dominio);
        } catch (ServicoException ex) {
            Logger.getLogger(ServicoItemPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void excluir(int id) throws PedidoException {
        try
        {
            ItemPedido dominio = daoItem.obterPorId(id);
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
            Logger.getLogger(ServicoItemPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ItemPedido obter(int id) {
        try {
            ItemPedido dominion = daoItem.obterPorId(id);
            return dominion;
        } catch (DaoException ex) {
            Logger.getLogger(ServicoItemPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
