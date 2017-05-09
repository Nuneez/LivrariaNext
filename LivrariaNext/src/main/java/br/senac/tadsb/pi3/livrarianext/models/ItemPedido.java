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
    private int produto;
    private double valor;

    public ItemPedido(int id, int idPedido, int produto, int quantidade, double valor) {
        this.id = id;
        this.idPedido = idPedido;
        this.quantidade = quantidade;
        this.produto = produto;
        this.valor = valor;
    }

    public ItemPedido(int idPedido, int produto, int quantidade, double valor) {
        this.idPedido = idPedido;
        this.quantidade = quantidade;
        this.produto = produto;
        this.valor = valor;
    }

    
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
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

    public int getProduto() {
        return produto;
    }

    public void setProduto(int produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
}
