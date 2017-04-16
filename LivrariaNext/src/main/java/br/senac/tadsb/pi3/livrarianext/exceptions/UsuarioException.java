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
public class UsuarioException extends LivrariaException {    
    public UsuarioException(String message){
        super(message);
    }
    
    public UsuarioException(String message, ExceptionTypesEnum type){
        super(message, type);
    }

    public UsuarioException(ExceptionTypesEnum exceptionTypesEnum) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
