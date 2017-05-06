/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.exceptions;

import br.senac.tadsb.pi3.livrarianext.enums.ExceptionTypesEnum;

/**
 *
 * @author roger
 */
public class VendaException extends LivrariaException {
    public VendaException(String message){
        super(message);
    }
    
    public VendaException(String message, ExceptionTypesEnum type){
        super(message, type);
    }

    public VendaException(ExceptionTypesEnum exceptionTypesEnum) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
