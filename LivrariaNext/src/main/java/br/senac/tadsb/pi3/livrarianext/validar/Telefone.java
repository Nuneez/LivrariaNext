package br.senac.tadsb.pi3.livrarianext.validar;

import java.util.regex.Pattern;

/**
 *
 * @author Nuneez
 */
public class Telefone {
    public static boolean validar(String telefone) {
        
        if (telefone.trim().isEmpty())
            return false;        
        
        if (Pattern.matches("[0-9]+", telefone) && telefone.length() > 7) {
            return true;
        }
        return false;
    }
}
