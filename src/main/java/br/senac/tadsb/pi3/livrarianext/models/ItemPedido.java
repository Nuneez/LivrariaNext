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
import br.senac.tadsb.pi3.livrarianext.exceptions.ProdutoException;
import br.senac.tadsb.pi3.livrarianext.exceptions.ServicoException;
import br.senac.tadsb.pi3.livrarianext.servicos.ServicoProduto;
import java.util.List;

/**
 *
 * @author Thiago
 */
public class ItemPedido { 
    private int id;
    private int quantidade;
    private int idProduto;
    private int idVenda;
    private double valor;

    public ItemPedido(int id, int quantidade, int idProduto, int idVenda, double valor) {
        this.id = id;
        this.quantidade = quantidade;
        this.idProduto = idProduto;
        this.idVenda = idVenda;
        this.valor = valor;
    }

    public ItemPedido(int quantidade, int idProduto, int idVenda, double valorUnit) {
        this.quantidade = quantidade;
        this.idProduto = idProduto;
        this.idVenda = idVenda;
        this.valor = valorUnit;
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }
}
