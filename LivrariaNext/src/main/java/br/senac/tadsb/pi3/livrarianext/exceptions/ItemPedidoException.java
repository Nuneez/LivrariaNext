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
public class ItemPedidoException extends LivrariaException {
    public ItemPedidoException(String message){
        super(message);
    }
    
    public ItemPedidoException(String message, ExceptionTypesEnum type){
        super(message, type);
    }

    public ItemPedidoException(ExceptionTypesEnum exceptionTypesEnum) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
