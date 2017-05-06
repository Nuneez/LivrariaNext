/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.exceptions;

/**
 *
 * @author roger
 */
public class ParserHelperException extends Exception {
    public ParserHelperException(String value){
        super(String.format("Não foi possível converter o valor %s. Verifique se este é compatível como tipo de dado esperado.", value));
    }
}
