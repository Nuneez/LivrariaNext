/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.models;

import br.senac.tadsb.pi3.livrarianext.enums.TipoMovimento;
import br.senac.tadsb.pi3.livrarianext.exceptions.LojaException;

/**
 *
 * @author roger
 */
public class EstoqueProduto {
    private int id;
    private Estoque estoque;
    private Produto produto;
    private Double saldo;
    
    public EstoqueProduto(Estoque estoque){
        this.estoque = estoque;
        this.saldo = 0.0;
    }
    
    public EstoqueProduto(int id, Estoque estoque, Produto produto, Double saldo){
        this(estoque);
        this.id = id;
        this.produto = produto;
        this.saldo = saldo;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the estoque
     */
    public Estoque getEstoque() {
        return estoque;
    }

    /**
     * @param estoque the estoque to set
     */
    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
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
}
