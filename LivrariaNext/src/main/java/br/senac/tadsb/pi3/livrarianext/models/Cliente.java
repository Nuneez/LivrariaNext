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
    
    public Cliente(String nome, String sobreNome, String cpf, String nascimento, String sexo, String email, String telefone){
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.cpf = cpf;
        this.nascimento = nascimento;
        this.sexo = sexo;
        this.email = email;
        this.telefone = telefone;
    }
    
    public Cliente(String nome, String sobreNome, String cpf, String nascimento, String sexo, String email, String telefone, String endereco, String numero, String bairro){
        this(nome, sobreNome, cpf, nascimento, sexo, email, telefone);
        this.endereco = endereco;
        this.numero = numero;
        this.bairro = bairro;
    }
    
    public Cliente(int id, String nome, String sobreNome, String cpf, String nascimento, String sexo, String email, String telefone, String endereco, String numero, String bairro, Loja loja){
        this(nome, sobreNome, cpf, nascimento, sexo, email, telefone, endereco, numero, bairro);
        this.id = id;
        this.loja = loja;
    }
}
