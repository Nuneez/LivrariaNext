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
public class LivrariaException extends Exception {
    
    private String displayMessage;

    public LivrariaException(){
        this(null, ExceptionTypesEnum.GENERAL);
    }
    
    public LivrariaException(String message){
        this(message, ExceptionTypesEnum.DISPLAY);
    }
    
    public LivrariaException(String message, ExceptionTypesEnum type){
        super(message);
        
        switch (type) {
            case DATABASE:
                displayMessage = "o sisitema encontrou problemas para acessar a base de dados.";
                break;                
            case GENERAL:
                displayMessage = "O sistema encontrou um erro e não pode concluir a requisição.";
                break;
            case DISPLAY:
            default:
                displayMessage = message;
        }
    }

    /**
     * @return the displayMessage
     */
    public final String getDisplayMessage() {
        return displayMessage;
    }
}
