/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.helpers;

import br.senac.tadsb.pi3.livrarianext.exceptions.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 *
 * @author roger
 */
public class Parser {
    public static int tryParseInt(String value){
        try
        {
            return Integer.parseInt(value);
        }
        catch(NumberFormatException nfe)
        {
            return 0;
        }
    }
    
    public static Double parseDouble(String value) throws ParserHelperException, ParseException {
        try
        {
            NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
            Number number = format.parse(value);
            return number.doubleValue();
        }
        catch(ParseException pe)
        {
            throw new ParserHelperException(value);
        }
        catch(NumberFormatException nfe)
        {
            throw new ParserHelperException(value);
        }
    }
}
