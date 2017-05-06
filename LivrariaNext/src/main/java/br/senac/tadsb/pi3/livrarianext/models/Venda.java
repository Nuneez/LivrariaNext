/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thiago
 */
public class Venda {
    private int id;
    private List<Produto> produtos;
    private List<Integer> qntProdutos;
    private Cliente cliente;
    private float total;

    public Venda(int id, List<Produto> produtos, List<Integer> qntProdutos, Cliente cliente, float total) {
        this.id = id;
        this.produtos = produtos;
        this.qntProdutos = qntProdutos;
        this.cliente = cliente;
        this.total = total;
    }

    public Venda(List<Produto> produtos, List<Integer> qntProdutos, Cliente cliente, float total) {
        this.produtos = produtos;
        this.qntProdutos = qntProdutos;
        this.cliente = cliente;
        this.total = total;
    }

    public List<Integer> getQntProdutos() {
        return qntProdutos;
    }

    public void setQntProdutos(List<Integer> qntProdutos) {
        this.qntProdutos = qntProdutos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
