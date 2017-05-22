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
public class Cliente {
    private int id;
    private String nome;
    private String sobreNome;
    private String cpf;
    private String rg;
    private String nascimento;//Depois será alterado para data
    private String sexo;
    private String endereco;
    private String numero;
    private String bairro;
    private String email;
    private String telefone;
    private Loja loja;
    private Boolean ativo;
 
    public Cliente(){
    
    }
    
    public Cliente(String nome, String sobreNome, String cpf, String rg, String nascimento, String sexo, String email, String telefone, Boolean ativo){
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.cpf = cpf;
        this.rg = rg;
        this.nascimento = nascimento;
        this.sexo = sexo;
        this.email = email;
        this.telefone = telefone;
        this.ativo = ativo;
    }
    
    public Cliente(String nome, String sobreNome, String cpf, String rg, String nascimento, String sexo, String email, String telefone, String endereco, String numero, String bairro, Boolean ativo){
        this(nome, sobreNome, cpf, rg, nascimento, sexo, email, telefone, ativo);
        this.endereco = endereco;
        this.numero = numero;
        this.bairro = bairro;
    }
    
    public Cliente(int id, String nome, String sobreNome, String cpf, String rg, String nascimento, String sexo, String email, String telefone, String endereco, String numero, String bairro, Loja loja, Boolean ativo){
        this(nome, sobreNome, cpf, rg, nascimento, sexo, email, telefone, endereco, numero, bairro, ativo);
        this.id = id;
        this.loja = loja;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getRg() {
        return rg;
    }

    public String getNascimento() {
        return nascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public Loja getLoja() {
        return loja;
    }
    
    public Boolean getAtivo(){
        return ativo;   
    }
    
}
