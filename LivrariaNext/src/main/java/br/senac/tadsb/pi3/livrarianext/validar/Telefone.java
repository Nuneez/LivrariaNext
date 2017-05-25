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
public class Telefone  {
//extends CaracteresEspeciais
    private String telefone;

    public Telefone(String telefone) {
        //super(telefone);
        this.telefone = telefone;
    }

    public boolean validarTelefone() {
        //telefone = removerCaracter();
        try {
            Integer.parseInt(telefone);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}