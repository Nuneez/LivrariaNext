/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.livrarianext.helpers;

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
}
