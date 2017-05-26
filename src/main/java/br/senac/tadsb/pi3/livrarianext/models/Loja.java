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
    private String email;
    private Boolean ativo;
    
    private List<Vendedor> funcionarios;
    
    public Loja(){
        this.ativo = true;
    }
    
    public Loja(String nome, Boolean ehFilial, String cnpj, String razaoSocial, String telefone, String email, String inscricaoEstadual){
        this.nome = nome;
        this.ehFilial = ehFilial;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.telefone = telefone;
        this.email = email;
        this.inscricaoEstadual = inscricaoEstadual;
    }
    
    public Loja(String nome, Boolean ehFilial, String cnpj, String razaoSocial, String telefone, String endereco, String numero, String cidade, String estado, String email, String inscricaoEstadual){
        this(nome, ehFilial, cnpj, razaoSocial, telefone, email, inscricaoEstadual);
        this.endereco = endereco;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;        
    }
    
    public Loja(int id, String nome, Boolean ehFilial, String cnpj, String razaoSocial, String telefone, String endereco, String numero, String cidade, String estado, String email, String inscricaoEstadual, Boolean ativo){
        this(nome, ehFilial, cnpj, razaoSocial, telefone, endereco, numero, cidade, estado, email, inscricaoEstadual);
        this.id = id;
        this.ativo = ativo;
        //this.funcionarios = funcionarios;
    }

    public Loja(String nome, String filial, String razaoSocial, String cnpj, String inscricaoEstadual, String endereco, String numero, String cidade, String estado, String email, String telefone) {
      this.nome = nome;
      this.ehFilial = ehFilial;
      this.cnpj = cnpj;
      this.razaoSocial = razaoSocial;
      this.inscricaoEstadual = inscricaoEstadual;
      this.endereco = endereco;
      this.numero = numero;
      this.cidade = cidade;
      this.estado = estado;
      this.telefone = telefone;
      this.email = email;
    }
    
    public void adicionarFuncionario(Vendedor funcionario){
        if (getFuncionarios() == null)
            setFuncionarios(new ArrayList<Vendedor>());
        
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

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @param ehFilial the ehFilial to set
     */
    public void setEhFilial(Boolean ehFilial) {
        this.ehFilial = ehFilial;
    }

    /**
     * @param cnpj the cnpj to set
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * @param razaoSocial the razaoSocial to set
     */
    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    /**
     * @param inscricaoEstadual the inscricaoEstadual to set
     */
    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @param cidade the cidade to set
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @param ativo the ativo to set
     */
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    /**
     * @param funcionarios the funcionarios to set
     */
    public void setFuncionarios(List<Vendedor> funcionarios) {
        this.funcionarios = funcionarios;
    }
}
