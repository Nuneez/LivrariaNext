/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.validar;

/**
 *
 * @author Nuneez
 */
public class Email {
    
    private String email;
    
    public Email(String email){
        this.email = email;
    }
    
    public boolean validarEmail(){
        return(this.email.indexOf('@')>0);
    }
}
