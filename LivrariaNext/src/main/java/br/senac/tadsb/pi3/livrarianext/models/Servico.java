/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.models;

import java.util.List;

/**
 *
 * @author roger
 */
public class Servico extends Item {    
    private List<String> materiais;
    
    public Servico(String nome, String descricao, double custo, double preco){
        super(nome, descricao, custo, preco);
    }
    
    public Servico(int id, String nome, String descricao, double custo, double preco, List<String> materiais){
        super(id, nome, descricao, custo, preco);
        this.materiais = materiais;
    }
}
