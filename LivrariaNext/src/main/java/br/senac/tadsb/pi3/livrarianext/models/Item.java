/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.models;

/**
 *
 * @author roger
 */
public class Item {
    private int id;
    private int quantidade;
    private String nome;
    private String descricao;
    private double custo;
    private double preco;
    private Boolean ativo;
    
    public Item(){ this.ativo = true; }
    
    public Item(String nome, String descricao, double custo, double preco, Boolean ativo){        
        this();
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
        this.preco = preco;
    }
    
    public Item(int id, String nome, String descricao, double custo, double preco, Boolean ativo){
        this(nome, descricao, custo, preco, ativo);
        this.id = id;
    }
    
    public Item(int id, int quantidade, String nome, String descricao, double custo, double preco, Boolean ativo){
        this(nome, descricao, custo, preco, ativo);
        this.id = id;
        this.quantidade = quantidade;
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
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the custo
     */
    public double getCusto() {
        return custo;
    }

    /**
     * @param custo the custo to set
     */
    public void setCusto(double custo) {
        this.custo = custo;
    }

    /**
     * @return the preco
     */
    public double getPreco() {
        return preco;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(double preco) {
        this.preco = preco;
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
}
