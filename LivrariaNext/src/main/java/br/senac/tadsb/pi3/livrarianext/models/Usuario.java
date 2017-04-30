/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.senac.tadsb.pi3.livrarianext.models;

import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author roger
 */
public class Usuario{
    private int id;
    private String nome;
    private String sobrenome;
    private String username;
    private String password;
    private String email;
    private Perfil perfil;
    private Boolean ativo;
    
    public Usuario(){
        this.ativo = true;
    }
    
    public Usuario(String nome, String sobreNome, String username, String email, Perfil perfil){
        this();
        this.nome = nome;
        this.sobrenome = sobreNome;
        this.email = email;
        this.username = username;
        this.perfil = perfil;
    }
    
    public Usuario(int id, String nome, String sobrenome, String username, String password, String email, Boolean ativo, Perfil perfil){
        this(nome, sobrenome, username, email, perfil);        
        this.id = id;
        this.perfil = perfil;
        this.password = password;
        this.ativo = ativo;
    }

    /**
     * @return the id
     */
    public final int getId() {
        return id;
    }

    /**
     * @return the nome
     */
    public final String getNome() {
        return nome;
    }

    /**
     * @return the sobreNome
     */
    public final String getSobrenome() {
        return sobrenome;
    }

    /**
     * @return the ativo
     */
    public final Boolean getAtivo() {
        return ativo;
    }
    
    public final void Login(){
        //TODO [IMPLEMENTAR]
    }
    
    public final void Logout(){
        //TODO [IMPLEMENTAR]
    }

    /**
     * @return the perfil
     */
    public final Perfil getPerfil() {
        return perfil;
    }

    /**
     * @param perfil the perfil to set
     */
    public final void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @param sobreNome the sobreNome to set
     */
    public void setSobreNome(String sobreNome) {
        this.sobrenome = sobreNome;
    }

    /**
     * @param ativo the ativo to set
     */
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    
    public void gerarPassword(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
        this.password = RandomStringUtils.random(10, characters );        
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
}
