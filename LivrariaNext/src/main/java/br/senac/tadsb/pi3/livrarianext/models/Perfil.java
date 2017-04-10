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
public class Perfil {
    private int id;
    private String nome;
    private List<String> permissoes;
    
    public Perfil(String nome){
        this.nome = nome;
    }
    
    public Perfil(String nome, List<String> permissoes){
        this(nome);
        this.permissoes = permissoes;
    }
    
    public Perfil(int id, String nome, List<String> permissoes){
        this(nome, permissoes);
        this.id = id;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the permissoes
     */
    public List<String> getPermissoes() {
        return permissoes;
    }
    
    public void adicionarPermissao(String permissao){
        if (getPermissoes() == null)
            permissoes = new ArrayList<>();
        
        getPermissoes().add(permissao);
    }
}
