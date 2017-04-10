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
        super(nome, descricao, custo, preco);
    }
    
    public Produto(int id, String nome, String descricao, double custo, double preco, String ean){
        super(id, nome, descricao, custo, preco);
        this.ean = ean;
    }
}
