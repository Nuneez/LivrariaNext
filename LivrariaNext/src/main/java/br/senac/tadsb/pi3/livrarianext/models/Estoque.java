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
 * @author roger
 */
public class Estoque {
    
    private int id;
    private Loja loja;
    private Boolean ativo;
    private List<EstoqueProduto> produtos;
    
    public Estoque(){ ativo = true; }
    
    public Estoque(Loja loja){
        this.loja = loja;
        this.ativo = true;
    }
    
    public Estoque(int id, Loja loja, Boolean ativo){
        this(loja);
        this.ativo = ativo;
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
     * @return the ativo
     */
    public Boolean getAtivo() {
        return ativo;
    }

    /**
     * @param ativo the ativo to set
     */
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    /**
     * @return the produtos
     */
    public List<EstoqueProduto> getProdutos() {
        return produtos;
    }

    /**
     * @param produtos the produtos to set
     */
    public void setProdutos(List<EstoqueProduto> produtos) {
        this.produtos = produtos;
    }
    
    public void adicionarNovoProduto(EstoqueProduto produto) {
        if (this.produtos == null)
            this.produtos = new ArrayList<>();
        
        this.produtos.add(produto);
    }
}
