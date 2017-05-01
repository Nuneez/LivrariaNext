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
public class Produto extends Item {
    private double precoMedio;
    private String ean;
        
    public Produto(String nome, String descricao, double custo, double preco){
        super(nome, descricao, custo, preco, true);
    }
    
    public Produto(int id, String nome, String descricao, double custo, double preco, String ean, Boolean ativo){
        super(id, nome, descricao, custo, preco, ativo);
        this.ean = ean;
    }

    /**
     * @return the precoMedio
     */
    public double getPrecoMedio() {
        return precoMedio;
    }

    /**
     * @param precoMedio the precoMedio to set
     */
    public void setPrecoMedio(double precoMedio) {
        this.precoMedio = precoMedio;
    }

    /**
     * @return the ean
     */
    public String getEan() {
        return ean;
    }

    /**
     * @param ean the ean to set
     */
    public void setEan(String ean) {
        this.ean = ean;
    }
}
