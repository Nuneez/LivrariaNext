/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.models;

import br.senac.tadsb.pi3.livrarianext.database.DaoProduto;
import br.senac.tadsb.pi3.livrarianext.enums.ExceptionTypesEnum;
import br.senac.tadsb.pi3.livrarianext.exceptions.DaoException;
import br.senac.tadsb.pi3.livrarianext.exceptions.ItemPedidoException;
import br.senac.tadsb.pi3.livrarianext.exceptions.ServicoException;
import java.util.List;

/**
 *
 * @author Thiago
 */
public class ItemPedido {
    private int id;
    private int idPedido;
    private int quantidade;
    private Produto produto;

    public ItemPedido(int idPedido, int quantidade, Produto produto) {
        this.idPedido = idPedido;
        this.quantidade = quantidade;
        this.produto = produto;
    }

    public ItemPedido(int id, int idPedido, int quantidade, int idProduto) throws Exception {
        DaoProduto dp = new DaoProduto();
        this.id = id;
        this.idPedido = idPedido;
        this.quantidade = quantidade;
        try {
            this.produto = dp.obterPorId(idProduto);
        } catch(DaoException de) {
            throw new ItemPedidoException(ExceptionTypesEnum.DATABASE);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
}
