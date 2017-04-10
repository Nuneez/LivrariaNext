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
public class Usuario {
    private int id;
    private String nome;
    private String sobreNome;
    private Boolean ativo;
    private Perfil perfil;
    
    public Usuario(String nome, String sobreNome, Boolean ativo){
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.ativo = ativo;
    }
    
    public Usuario(int id, String nome, String sobreNome, Boolean ativo, Perfil perfil){
        this(nome, sobreNome, ativo);
        this.perfil = perfil;
        this.id = id;
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
    public final String getSobreNome() {
        return sobreNome;
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
}
