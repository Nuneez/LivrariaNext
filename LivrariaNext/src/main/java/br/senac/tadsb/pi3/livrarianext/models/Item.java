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
    private String nome;
    private String descricao;
    private double custo;
    private double preco;
    
    public Item(String nome, String descricao, double custo, double preco){
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
        this.preco = preco;
    }
    
    public Item(int id, String nome, String descricao, double custo, double preco){
        this(nome, descricao, custo, preco);
        this.id = id;
    }
}
