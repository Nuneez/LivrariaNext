/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tadsb.pi3.livrarianext.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author roger
 */
public class ExtendedHttpServlet extends Sessao {
    protected void writeJsonResponse(HttpServletResponse response, String json) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);    
    }
    
    protected Date convertStringToDate(String text){
        try
        {
            DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.FRANCE);
            return format.parse(text);
        }
        catch(ParseException p)
        {
            return null;
        }
    }
}
