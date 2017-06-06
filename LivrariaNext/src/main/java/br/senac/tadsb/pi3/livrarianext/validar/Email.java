/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.validar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Nuneez
 */
public class Email {
    public static boolean validar(String email){

        if (email.trim().isEmpty())
            return false;
        
        String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();        
    }
}
