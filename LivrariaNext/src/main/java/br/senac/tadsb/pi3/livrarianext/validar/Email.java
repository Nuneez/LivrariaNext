/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.validar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.email);
        return matcher.matches();
    }
}
