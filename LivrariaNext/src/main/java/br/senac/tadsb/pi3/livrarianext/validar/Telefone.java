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
public class Telefone {
    private String telefone;
    
    public Telefone(String telefone){
        this.telefone = telefone;
    }
    
    public boolean validarTelefone(){
//        return telefone.matches(".((10)|([1-9][1-9]).)\\s9?[6-9][0-9]{3}-[0-9]{4}")||
//               telefone.matches(".((10)|([1-9][1-9]).)\\s[2-5][0-9]{3}-[0-9]{4}")||
//               telefone.matches(".((10)|([1-9][1-9]).)\\s[2-5][0-9]{4}-[0-9]{4}")||
//               telefone.matches(".((10)|[1-9][1-9].)\\s[2-5][0-9]{3}[0-9]{4}")||
//               telefone.matches(".((10)|[1-9][1-9].)\\s[2-5][0-9]{3}-[0-9]{4}");
        return true;
    }
    
}
