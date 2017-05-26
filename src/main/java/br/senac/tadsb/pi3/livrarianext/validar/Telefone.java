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
public class Telefone{
    private String telefone;

    public Telefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean validarTelefone() {
        try {
            Integer.parseInt(telefone);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}