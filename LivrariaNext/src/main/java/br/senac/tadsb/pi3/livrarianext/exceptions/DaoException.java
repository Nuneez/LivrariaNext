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
public class DaoException extends Exception {    
    public DaoException(){
        super("Ocorreu um erro ao tentar acessar o banco de dados.");
    }
    
    public DaoException(String message){
        super(message);
    }
}
