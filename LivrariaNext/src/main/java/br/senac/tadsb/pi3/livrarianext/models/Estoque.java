/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.models;

import  br.senac.tadsb.pi3.livrarianext.enums.TipoMovimento;
import br.senac.tadsb.pi3.livrarianext.exceptions.LojaException;

/**
 *
 * @author roger
 */
public class Estoque {
    
    private int id;
    private Loja loja;
    private Produto produto;
    private Double saldo;
    
    public Estoque(Loja loja, Produto produto, Double saldo){
        this.loja = loja;
        this.produto = produto;
        this.saldo = saldo;
    }
    
    public Estoque(int id, Loja loja, Produto produto, Double saldo){
        this(loja, produto, saldo);
        this.id = id;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the loja
     */
    public Loja getLoja() {
        return loja;
    }

    /**
     * @param loja the loja to set
     */
    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    /**
     * @return the produto
     */
    public Produto getProduto() {
        return produto;
    }

    /**
     * @param produto the produto to set
     */
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    /**
     * @return the saldo
     */
    public Double getSaldo() {
        return saldo;
    }

    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
    
    public void atualizarSaldo(TipoMovimento tipo, Double quantidade) throws LojaException {
        if (tipo == TipoMovimento.ENTRADA){
            this.saldo += quantidade;
            return;
        }
        
        if (this.saldo - quantidade < 0)
            throw new LojaException("Saldo insuficiente para realizar a saída!");
        
        this.saldo -= quantidade;
    }
}
