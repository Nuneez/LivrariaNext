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
public class Vendedor extends Usuario {
    
    public Vendedor(String nome, String sobreNome, Boolean ativo) {
        super(nome, sobreNome, ativo);
    }
    
    public Vendedor(int id, String nome, String sobreNome, Boolean ativo, Perfil perfil){        
        super(id, nome, sobreNome, ativo, perfil);
    }    
    
    public final void iniciarVenda(){
        //TODO [Implementar]
    }
}
