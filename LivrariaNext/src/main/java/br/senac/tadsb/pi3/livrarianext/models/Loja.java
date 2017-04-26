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
public class Loja {
    private int id;
    private String nome;
    private Boolean ehFilial;
    private String cnpj;
    private String razaoSocial;
    private String inscricaoEstadual;
    private String endereco;
    private String numero;
    private String cidade;
    private String estado;
    private String telefone;
    private Boolean ativo;
    
    
    private List<Vendedor> funcionarios;
    
    public Loja(String nome, Boolean ehFilial, String cnpj, String razaoSocial, String telefone){
        this.nome = nome;
        this.ehFilial = ehFilial;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.telefone = telefone;
    }
    
    public Loja(String nome, Boolean ehFilial, String cnpj, String razaoSocial, String telefone, String endereco, String numero, String cidade, String estado){
        this(nome, ehFilial, cnpj, razaoSocial, telefone);
        this.endereco = endereco;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;        
    }
    
    public Loja(int id, String nome, Boolean ehFilial, String cnpj, String razaoSocial, String telefone, String endereco, String numero, String cidade, String estado){
        this(nome, ehFilial, cnpj, razaoSocial, telefone, endereco, numero, cidade, estado);
        this.id = id;
        this.funcionarios = funcionarios;
    }
    
    public void adicionarFuncionario(Vendedor funcionario){
        if (getFuncionarios() == null)
            funcionarios = new ArrayList<>();
        
        this.getFuncionarios().add(funcionario);
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
     * @return the ehFilial
     */
    public Boolean getEhFilial() {
        return ehFilial;
    }

    /**
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * @return the razaoSocial
     */
    public String getRazaoSocial() {
        return razaoSocial;
    }

    /**
     * @return the inscricaoEstadual
     */
    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    /**
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @return the funcionarios
     */
    public List<Vendedor> getFuncionarios() {
        return funcionarios;
    }

    /**
     * @return the ativo
     */
    public Boolean getAtivo() {
        return ativo;
    }
}
