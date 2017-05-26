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
public class ServicoException extends Exception {
    public ServicoException(){
        super("Ocorreu um erro ao tentar executar a ação.");
    }
    
    public ServicoException(String message){
        super(message);
    }
}
